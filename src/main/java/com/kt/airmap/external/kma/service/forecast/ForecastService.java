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
import com.kt.airmap.external.comm.service.ExtCommService;
import com.kt.airmap.external.kma.base.message.KMAForecastResponse;
import com.kt.airmap.external.kma.base.message.KMAForecastResponseCode;
import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;
import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.forcast.ForecastGripVo;
import com.kt.airmap.external.kma.vo.forcast.ForecastResponse;

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
	protected ExtCommService extCommService;
	
	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	
	public void forecast(String stdDateTime, String urlPath) {

		String stdDate = stdDateTime.substring(0,8);
		String stdTime = stdDateTime.substring(8,12);
		
		Map<String, String> parameter = new HashMap<String, String>();

		parameter.put("base_date", stdDate);
		parameter.put("base_time", stdTime);
		parameter.put("pageNo", "1"); 			// 페이지 결과수 todo: const 로 정의
		parameter.put("numOfRows", "30000"); 	// 한페이지에 결과수 , todo : const 로 정의
		parameter.put("_type", "json");
		// 해당 서비스 키 설정
		parameter.put("serviceKey", Const.KMA_DIST_FORCAST_SERVICE_KEY);

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
		
		List<AreaVo> areaListVo = extCommService.getAreaList(Const.AREA.KMA_AREA_GRID);
		Iterator<AreaVo> iterator = areaListVo.iterator();
 		int i = 0;
 		
 		while(iterator.hasNext()){
 	
 			AreaVo areaVo = iterator.next();
 			
			parameter.put("nx", String.valueOf(areaVo.getXcrdVal()));
			parameter.put("ny", String.valueOf(areaVo.getYcrdVal()));
			
		    KMAForecastResponse response  = (KMAForecastResponse) this.airmapApiService.get(Const.KMA_DIST_FORCAST_GRIB_URI, parameter, KMAForecastResponse.class);

			System.out.println("response[" +areaVo.getXcrdVal() + "," +areaVo.getYcrdVal() + "] =>" + response.getResponse().toString());

			if (response.getResponse() != null) {

				Map<String, Object> responseData = (HashMap<String,Object>) response.getResponse();
				if (responseData.size() > 0) {
							
					ForecastResponse p = ObjectConverter.toObject(responseData, ForecastResponse.class);
					
					/** 성공인 경우 */
					if(p.getHeader().getResultCode().equals(KMAForecastResponseCode.OK.getValue()) && p.getBody().getTotalCount() > 0){
						if (p.getBody().getItems().getItem().size() > 0) {
							Iterator<ForecastResponse.BodyVo.ItemsVo.ItemVo> iterItem = p.getBody().getItems().getItem().iterator();
							ForecastGripVo forecastGripVo = new ForecastGripVo();
							
							while (iterItem.hasNext()) {
								ForecastResponse.BodyVo.ItemsVo.ItemVo itemVo = iterItem.next();
					
								logger.debug("ItemVo baseDate:" + itemVo.getBaseDate());
								logger.debug("ItemVo obsrValue:" + itemVo.getObsrValue());

								forecastGripVo.setPtDate(itemVo.getBaseDate());   //발표일자
								forecastGripVo.setPtTime(itemVo.getBaseTime());   //발표시간
								forecastGripVo.setXcrdVal(areaVo.getXcrdVal());   //X좌표값
								forecastGripVo.setYcrdVal(areaVo.getYcrdVal());   //Y좌표값
								
								String obsrValue = String.valueOf(itemVo.getObsrValue());
								
								// 1시간기온
								if ("T1H".equals(itemVo.getCategory())) {
									forecastGripVo.setTemprVal(Double.valueOf(obsrValue));
								}
								// 1시간 강수량
								if ("RN1".equals(itemVo.getCategory())) {
									forecastGripVo.setPrcp1TimeQnt(Double.valueOf(obsrValue));
								}
								// 하늘 상태
								if ("SKY".equals(itemVo.getCategory())) {
									forecastGripVo.setSkySttusCd(obsrValue);
								}
								// 풍속(동서성분)
								if ("UUU".equals(itemVo.getCategory())) {
									forecastGripVo.setEwWindCmpntVal(Double.valueOf(obsrValue));
								}
								// 풍속(남북성분)
								if ("VVV".equals(itemVo.getCategory())) {
									forecastGripVo.setSnWindCmpntVal(Double.valueOf(obsrValue));
								}
								// 습도
								if ("REH".equals(itemVo.getCategory())) {
									forecastGripVo.setHumdtRate(NumberUtil.isDouble(obsrValue) ? Double.valueOf(obsrValue) : null);
								}
								// 강수형태
								if ("PTY".equals(itemVo.getCategory())) {
									forecastGripVo.setPrcpShapCd(obsrValue);
								}
								// 낙뢰
								if ("LGT".equals(itemVo.getCategory())) {
									forecastGripVo.setThnbltCd(obsrValue);
								}
								// 풍향
								if ("VEC".equals(itemVo.getCategory())) {
									forecastGripVo.setWdVal(Double.valueOf(obsrValue));
								}
								// 풍속
								if ("WSD".equals(itemVo.getCategory())) {
									forecastGripVo.setWsVal(Double.valueOf(obsrValue));
								}
								
							}
							
							kMAMapperDao.addForecastGrip(forecastGripVo);
						}
					}
				}
			}
 		}
	}



 }
