package com.kt.airmap.external.task;

import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {

//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//   
//    @Autowired
//     ForecastMgrService forcastMgrService;
//    
//    @Autowired
//    LifeIndexMgrService lifeIndexMgrService;
//
//    private String date;
//    private String time;
//    
//    /**
//     * 동네 예보 - 동네예보 조회
//     * @throws IOException 
//     * @throws JsonMappingException 
//     * @throws JsonParseException 
//     */
//    @Scheduled(cron="${cron.expression}")
//    public void JobForForecastData() throws JsonParseException, JsonMappingException, IOException {
//     
//    	log.info("The time is now {}", dateFormat.format(new Date()));
//        
//  //    forcastMgrService.forecast("20171228","0500",Const.KMA_DIST_FORCAST_SPACE_SERVICE_KEY);
//        
//     // lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_SENSIBLE_TEMP_URI);
//       
//    }
//    
//    /**
//     * 생활지수 - 동네예보 조회
//     * @throws JsonParseException
//     * @throws JsonMappingException
//     * @throws IOException
//     */
//  @Scheduled(cron="${cron.expression}")
//  public void JobForLifeIndexData() throws JsonParseException, JsonMappingException, IOException {
//   
//  	log.info("The time is now {}", dateFormat.format(new Date()));
//
//      
////    lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_FOOD_POSISONING_URI);
////    
////    lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_SENSIBLE_TEMP_URI);
////    
////    lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_HEAT_URI);
////    
////    lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_DISCOMFORT_URI);
////    
////    lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_ULTRA_V_URI);
////    
////    lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_WINTER_URI);
////    
////    lifeIndexMgrService.lifeIndex("20171228", "0008", Const.KMA_LIFE_WEATHER_AIR_POLLUTION_URI);
////     
//  }
//  
//  
//	@Override
//	public void afterPropertiesSet() throws Exception {
//
//	}
//
//
//	@Override
//	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
//		
//		System.out.println("===> date=" + date + ",time=" + time);
//		   lifeIndexMgrService.lifeIndex(date, time, Const.KMA_LIFE_WEATHER_SENSIBLE_TEMP_URI);
//		return  RepeatStatus.FINISHED;
//	}
//
//
//	public String getDate() {
//		return date;
//	}
//
//
//	public void setDate(String date) {
//		this.date = date;
//	}
//
//
//	public String getTime() {
//		return time;
//	}
//
//
//	public void setTime(String time) {
//		this.time = time;
//	}



}
