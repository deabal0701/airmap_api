package com.kt.airmap;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kt.airmap.base.adaptor.KMALocAdaptorService;
import com.kt.airmap.external.kma.dto.forecast.ForecastResponseDto;
import com.kt.airmap.external.kma.service.LocationCodeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirMapApplication.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AirMapApplicationTests {

	@Autowired
	LocationCodeService locationCodeService;
	
//	@Autowired
//	ForecastMgrService forcastMgrService;
//	
//	@Autowired
//	LifeIndexMgrService lifeIndexMgrService;
//	
//
////	@Autowired
////	private WebApplicationContext webContext;
////	private MockMvc mockMvc;
////	
////	@Value("${airmap.url}")
////	String url;
//	
//	@Test
//	public void forecastSpaceData() throws JsonParseException, JsonMappingException, IOException{
//		forcastMgrService.forecast("20171226","0300",Const.KMA_DIST_FORCAST_SPACE_SERVICE_KEY);
//	}
//	
//	@Test
//	public void lifeIndexData() throws JsonParseException, JsonMappingException, IOException{
//		//lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_SENSIBLE_TEMP_URI);
//		
//	   lifeIndexMgrService.lifeIndex("20180102", "0006", Const.KMA_LIFE_WEATHER_FOOD_POSISONING_URI);
//	    
//	   lifeIndexMgrService.lifeIndex("20180102", "0006", Const.KMA_LIFE_WEATHER_SENSIBLE_TEMP_URI);
//	    
//	   lifeIndexMgrService.lifeIndex("20180102", "0006", Const.KMA_LIFE_WEATHER_HEAT_URI);
//	    
//	   lifeIndexMgrService.lifeIndex("20180102", "0006", Const.KMA_LIFE_WEATHER_DISCOMFORT_URI);
//	    
//	   lifeIndexMgrService.lifeIndex("20180102", "0006", Const.KMA_LIFE_WEATHER_ULTRA_V_URI);
//	    
//	   lifeIndexMgrService.lifeIndex("20180102", "0006", Const.KMA_LIFE_WEATHER_WINTER_URI);
//	    
//	   lifeIndexMgrService.lifeIndex("20171228", "0006", Const.KMA_LIFE_WEATHER_AIR_POLLUTION_URI);
//		    
//	}
	
	@Test
	public void test2(){
		String json = "{\"response\":{\"header\":{\"resultCode\":\"99\",\"resultMsg\":\"최근 1일 간의 자료만 제공합니다.\"}}}";
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
		ForecastResponseDto p = gson.fromJson(json, ForecastResponseDto.class);
		if (p.getHeader().getResultCode().equals("0000")) {
			/*if (p.getBody().getItems().getItem().size() > 0) {
				System.out.println("p.getBody().getItems().getItem().size()");
			}*/
		} else {
			System.out.println("연동실패==>" + p.getHeader().getResultCode());
		}
	}
	
	@Test
	public void kmaLocAdaptorService(){
	
		locationCodeService.locationCode("2018010811");
	}
}