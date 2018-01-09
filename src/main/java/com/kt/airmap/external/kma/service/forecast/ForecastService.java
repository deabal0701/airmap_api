package com.kt.airmap.external.kma.service.forecast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kt.airmap.Const;
import com.kt.airmap.base.adaptor.KMAAdaptorService;
import com.kt.airmap.base.common.NumberUtil;
import com.kt.airmap.base.common.ObjectConverter;
import com.kt.airmap.external.kma.base.message.KMAForecastResponse;
import com.kt.airmap.external.kma.base.message.KMAForecastResponseCode;
import com.kt.airmap.external.kma.dto.forecast.ForecastResponseDto;
import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;
import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.forcast.ForecastSpaceDataVo;

/**
동네예보
 -초단기실황조회: getForecastGribRequest
 -초단기예보조회: getForecastTimeDataRequest
 -동네예보조회: getForecastSpaceDataRequest
*/
@Service
public class ForecastService {
	
	private static final Logger logger = LoggerFactory.getLogger(ForecastService.class);
 	
	@Autowired
	protected KMAAdaptorService airmapApiService;
	
	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	
	//@Transactional
	public void forecast(String stdDateTime, String urlPath) {

		String stdDate = stdDateTime.substring(0,8);
		String stdTime = stdDateTime.substring(8,12);
		
		Map<String, String> parameter = new HashMap<String, String>();

		parameter.put("base_date", stdDate);
		parameter.put("base_time", stdTime);
		parameter.put("pageNo", "1"); // 페이지 결과수 todo: const 로 정의
		parameter.put("numOfRows", "30000"); // 한페이지에 결과수 , todo : const 로 정의
		parameter.put("_type", "json");
		// 해당 서비스 키 설정
		parameter.put("serviceKey", Const.KMA_DIST_FORCAST_SPACE_SERVICE_KEY);

		forecastData(stdDate, stdTime, parameter);
	}
	
	
 	/**
 	 * bDate : yyyyMMdd -  발표일자
 	 * bTime : hh24  - 발표시각
 	 * @throws IOException 
 	 * @throws JsonMappingException 
 	 * @throws JsonParseException 
 	 */
	public void forecastData(String bDate, String bTime, Map<String,String> parameter) {
		
		List<AreaVo> areaListVo = getAreaList();
		Iterator<AreaVo> iterator = areaListVo.iterator();
 		int i = 0;
 		
 		while(iterator.hasNext()){
 	
 			AreaVo areaVo = iterator.next();
 			
			parameter.put("nx", String.valueOf(areaVo.getXcrdVal()));
			parameter.put("ny", String.valueOf(areaVo.getYcrdVal()));
			
		    KMAForecastResponse response  = (KMAForecastResponse) this.airmapApiService.get(Const.KMA_DIST_FORCAST_SPACE_URI, parameter, KMAForecastResponse.class);

			System.out.println("response[" +areaVo.getXcrdVal() + "," +areaVo.getYcrdVal() + "] =>" + response.getResponse().toString());

			if (response.getResponse() != null) {

				Map<String, Object> responseData = (HashMap<String,Object>) response.getResponse();
				if (responseData.size() > 0) {
							
					ForecastResponseDto p = ObjectConverter.toObject(responseData, ForecastResponseDto.class);
					
					/** 성공인 경우 */
					if(p.getHeader().getResultCode().equals(KMAForecastResponseCode.OK.getValue()) && p.getBody().getTotalCount() > 0){
						if (p.getBody().getItems().getItem().size() > 0) {
							Iterator<ForecastResponseDto.BodyVo.ItemsVo.ItemVo> iterItem = p.getBody().getItems().getItem().iterator();
							ForecastSpaceDataVo forecastSpaceDataVo = new ForecastSpaceDataVo();
							
							while (iterItem.hasNext()) {
								ForecastResponseDto.BodyVo.ItemsVo.ItemVo itemVo = iterItem.next();
					
								logger.debug("ItemVo baseDate:" + itemVo.getBaseDate());
								logger.debug("ItemVo fcstValue:" + itemVo.getFcstValue());

								forecastSpaceDataVo.setPtDate(itemVo.getBaseDate());
								forecastSpaceDataVo.setPtTime(itemVo.getBaseTime());
								forecastSpaceDataVo.setForecDate(itemVo.getFcstDate());
								forecastSpaceDataVo.setForecTime(itemVo.getFcstTime());
								forecastSpaceDataVo.setXcrdVal(areaVo.getXcrdVal());
								forecastSpaceDataVo.setYcrdVal(areaVo.getYcrdVal());
								
								String fcstValue = String.valueOf(itemVo.getFcstValue());
								// 강수확율
								if ("POP".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setPrctPrbb(Double.valueOf(fcstValue));
								}
								// 강수형태
								if ("PTY".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setPrctShapCd(fcstValue);
								}
								// 6시간 강수량
								if ("R06".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setPrctQnt6Time(Double.valueOf(fcstValue));
								}
								// 습도
								if ("REH".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setHumdt(NumberUtil.isDouble(fcstValue) ? Double.valueOf(fcstValue) : null);
								}
								// 6시간 적설량
								if ("S06".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setFsnowc6Time(Double.valueOf(fcstValue));
								}
								// 하늘 상태
								if ("SKY".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setSkySttusCd(fcstValue);
								}
								// 3시간 기온
								if ("T3H".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setTempr3Time(Double.valueOf(fcstValue));
								}
								// 아침 최저기온
								if ("TMN".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setLowstTempr(Double.valueOf(fcstValue));
								}
								// 낮 최고기온
								if ("TMX".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setHigstTempr(Double.valueOf(fcstValue));
								}
								// 풍속(동서성분)
								if ("UUU".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setEwWsCmpnt(Double.valueOf(fcstValue));
								}
								// 풍속(남북성분)
								if ("VVV".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setSnWsCmpnt(Double.valueOf(fcstValue));
								}
								// 파고
								if ("WAV".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setFsnowc6Time(Double.valueOf(fcstValue));
								}
								// 풍향
								if ("VEC".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setFsnowc6Time(Double.valueOf(fcstValue));
								}
								// 풍속
								if ("WSD".equals(itemVo.getCategory())) {
									forecastSpaceDataVo.setFsnowc6Time(Double.valueOf(fcstValue));
								}
							
								try {
									
									i++;
									if(i > 30){
										
										System.out.println("===> Exception Occurred");
										throw new RuntimeException();
									}
									kMAMapperDao.addForecastSpaceData(forecastSpaceDataVo);
								} catch (Exception e) {
									throw new RuntimeException();
								}
							}
							//ToDo : Kweater로 연동point가 변경되면...예보날짜/시각 기준 insert/update 로 변경할것.
//							try{
//								kMAMapperDao.addForecastSpaceData(forecastSpaceDataVo);
//								
//							}catch(Exception e){
//								e.printStackTrace();
//							}
						}
					}
				}
			}
 		}
	}

	
	public List<AreaVo> getAreaList(){
		  
		List<AreaVo> areaListVo =  kMAMapperDao.getArea();
		if(areaListVo == null){
			//Todo : 지역데이터 없다는 Exception 처리
			return null;
		}else{
 			System.out.println("areaVo.size() : "+areaListVo.size());
 			return areaListVo;
 		}
	}

 }
