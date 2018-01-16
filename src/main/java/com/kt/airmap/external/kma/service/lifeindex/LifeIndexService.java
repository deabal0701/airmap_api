package com.kt.airmap.external.kma.service.lifeindex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.airmap.Const;
import com.kt.airmap.base.adaptor.KMAAdaptorService;
import com.kt.airmap.base.common.ObjectConverter;
import com.kt.airmap.external.comm.service.ExtCommService;
import com.kt.airmap.external.kma.base.message.KMALifeIndexResponseCode;
import com.kt.airmap.external.kma.base.message.KMALifeindexResponse;
import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;
import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexResponse;

@Service
public class LifeIndexService  {

	private  Logger logger = LoggerFactory.getLogger(LifeIndexService.class);
	
	@Autowired
	protected KMAAdaptorService airmapApiService;
	
	@Autowired
	protected ExtCommService extCommService;
	
	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	
	public void lifeIndex(String stdDateTime, String urlPath) {
		
		Map<String, String> parameter = new HashMap<String, String>();
	
		parameter.put("time", stdDateTime);
		parameter.put("serviceKey", "1");         // 페이지 결과수  todo: const 로 정의
		parameter.put("_type", "json");       
		
		//해당 서비스 키 설정
		parameter.put("serviceKey", Const.KMA_LIFE_WEATHER_INDEX_SERVICE_KEY);
	
		try{
			lifeIndexData(stdDateTime,parameter,urlPath);
		}catch(Exception e){
			//todo : exception 기록
			System.out.println("Exception Occurred");
			throw new RuntimeException();
		}
	}

    /**
     * 
     * @param stdDateTime
     * @param parameter
     * @param urlPath
     * @throws Exception
     */
	public void lifeIndexData(String stdDateTime, Map<String, String> parameter,String urlPath) throws Exception  {
		
		List<AreaVo> areaListVo = extCommService.getAreaList(Const.AREA.KMA_AREA_CODE);
	 	Iterator<AreaVo> iterator = areaListVo.iterator();
 		
 		while(iterator.hasNext()){
 	
 			AreaVo areaVo = iterator.next();
 			parameter.put("areaNo", String.valueOf(areaVo.getBstorCd()));
			
			KMALifeindexResponse response  = (KMALifeindexResponse) this.airmapApiService.get(urlPath, parameter, KMALifeindexResponse.class);
		
			if (response !=null && response.getResponse() != null) {

				@SuppressWarnings("unchecked")
				Map<String, Object> responseData = (HashMap<String,Object>) response.getResponse();
				if (responseData.size() > 0) {
							
					LifeIndexResponse p = ObjectConverter.toObject(responseData, LifeIndexResponse.class);
					if(p.getHeader().getReturnCode().equals(KMALifeIndexResponseCode.OK.getValue())){
			
						LifeIndexVo lifeIndexVo = new LifeIndexVo();
						lifeIndexVo.setPtDate(stdDateTime.substring(0,8));
						lifeIndexVo.setPtTime(stdDateTime.substring(8,10) + "00");
						lifeIndexVo.setBstorCd(p.getBody().getIndexModel().getAreaNo());  // 지점코드
						lifeIndexVo.setForecDate(p.getBody().getIndexModel().getDate().substring(0,8));
						lifeIndexVo.setForecTime(p.getBody().getIndexModel().getDate().substring(8));
						lifeIndexVo.setIndexnCd(p.getBody().getIndexModel().getCode());
						
						if(urlPath.equals(Const.KMA_LIFE_WEATHER_FOOD_POSISONING_URI) || urlPath.equals(Const.KMA_LIFE_WEATHER_ULTRA_V_URI)){
					    	lifeIndexVo.setForecVal(p.getBody().getIndexModel().getToday());
					    }else{
					    	lifeIndexVo.setForecVal(p.getBody().getIndexModel().getH3());
					    }
						
						//Todo : List에 담아놓고 한번에 처리하는 방안?
						kMAMapperDao.addLifeIndex(lifeIndexVo);
					}
				}
			}else{
				throw new RuntimeException();
			}
 		}
	}

}
