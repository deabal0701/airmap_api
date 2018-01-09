package com.kt.airmap.external.kma.service.lifeindex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kt.airmap.Const;
import com.kt.airmap.base.adaptor.KMAAdaptorService;
import com.kt.airmap.base.common.ObjectConverter;
import com.kt.airmap.external.kma.base.message.KMALifeIndexResponseCode;
import com.kt.airmap.external.kma.base.message.KMALifeindexResponse;
import com.kt.airmap.external.kma.mapper.dao.KMAMapperDao;
import com.kt.airmap.external.kma.vo.AreaVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexDataVo;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexResponse;

@Service
public class LifeIndexService  {

	@Autowired
	protected KMAAdaptorService airmapApiService;
	
	@Autowired
	protected  KMAMapperDao kMAMapperDao;
	
	public void lifeIndex(String stdDateTime, String urlPath) {
		
		Map<String, String> parameter = new HashMap<String, String>();
	
		parameter.put("time", stdDateTime);
		parameter.put("serviceKey", "1");         // 페이지 결과수  todo: const 로 정의
		parameter.put("_type", "json");       
		
		//해당 서비스 키 설정
		parameter.put("serviceKey", Const.KMA_LIFE_WEATHER_INDEX_SERVICE_KEY);
	
		lifeIndexData(stdDateTime,parameter,urlPath);
	}


	public void lifeIndexData(String stdDateTime, Map<String, String> parameter,String urlPath) {
		
	    List<AreaVo> areaListVo = getAreaList();
	 	Iterator<AreaVo> iterator = areaListVo.iterator();
 		
 		while(iterator.hasNext()){
 	
 			AreaVo areaVo = iterator.next();
 			
			parameter.put("areaNo", String.valueOf(areaVo.getBstorCd()));
			
			
			KMALifeindexResponse response  = (KMALifeindexResponse) this.airmapApiService.get(urlPath, parameter, KMALifeindexResponse.class);
		
			if (response.getResponse() != null) {

				@SuppressWarnings("unchecked")
				Map<String, Object> responseData = (HashMap<String,Object>) response.getResponse();
				if (responseData.size() > 0) {
							
					LifeIndexResponse p = ObjectConverter.toObject(responseData, LifeIndexResponse.class);
					if(p.getHeader().getReturnCode().equals(KMALifeIndexResponseCode.OK.getValue())){
			
						LifeIndexDataVo lifeIndexDataVo = new LifeIndexDataVo();
						lifeIndexDataVo.setPtDate(p.getBody().getIndexModel().getDate().substring(0,8));
						lifeIndexDataVo.setPtTime(p.getBody().getIndexModel().getDate().substring(8));
						lifeIndexDataVo.setBstorCd(p.getBody().getIndexModel().getCode());
						lifeIndexDataVo.setIndexCd(p.getBody().getIndexModel().getCode());
					 
						if(urlPath.equals(Const.KMA_LIFE_WEATHER_FOOD_POSISONING_URI) || urlPath.equals(Const.KMA_LIFE_WEATHER_ULTRA_V_URI)){
					    	lifeIndexDataVo.setPrctVal(p.getBody().getIndexModel().getToday());
					    }else{
					    	lifeIndexDataVo.setPrctVal(p.getBody().getIndexModel().getH3());
					    }
						
						kMAMapperDao.addLifeIndexData(lifeIndexDataVo);
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
