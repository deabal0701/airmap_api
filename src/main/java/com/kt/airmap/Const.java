
package com.kt.airmap;

public class Const {

	public static final String PACKAGE = "com.kt.airmap";
	
	public static final String RESPONSE_CODE_OK = "200"; 

	/** 동네예보 3시간 단위*/
	public static final String KMA_DIST_FORCAST_SPACE_URI = "/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData";
	
	/**	동네예보 - 초 단기 실황 조회	 */
	public static final String KMA_DIST_FORCAST_GRIB_URI = "/service/SecndSrtpdFrcstInfoService2/ForecastGrib";

	/**	동네예보 - 초 단기 예보 조회	 */
	public static final String KMA_DIST_FORCAST_TIME_URI = "/service/SecndSrtpdFrcstInfoService2/ForecastTimeData";
	
	
	/** 생활지수 - 식중독 지수 */
	public static final String KMA_LIFE_WEATHER_FOOD_POSISONING_URI = "/iros/RetrieveLifeIndexService2/getFsnLifeList";
	/** 생활지수 - 체감온도 */
	public static final String KMA_LIFE_WEATHER_SENSIBLE_TEMP_URI = "/iros/RetrieveLifeIndexService2/getSensorytemLifeList";
	/** 생활지수 - 열지수 */
	public static final String KMA_LIFE_WEATHER_HEAT_URI = "/iros/RetrieveLifeIndexService2/getHeatLifeList";
	/** 생활지수 - 불쾌지수 */
	public static final String KMA_LIFE_WEATHER_DISCOMFORT_URI = "/iros/RetrieveLifeIndexService2/getDsplsLifeList";
	/** 생활지수 - 동파지수 */
	public static final String KMA_LIFE_WEATHER_WINTER_URI = "/iros/RetrieveLifeIndexService2/getWinterLifeList";
	/** 생활지수 - 자외선지수 */
	public static final String KMA_LIFE_WEATHER_ULTRA_V_URI = "/iros/RetrieveLifeIndexService2/getUltrvLifeList";
	/** 생활지수 - 대기오염 확산지수 */
	public static final String KMA_LIFE_WEATHER_AIR_POLLUTION_URI = "/iros/RetrieveLifeIndexService2/getAirpollutionLifeList";
	
	
	
	/** 동네예보(실황, 초단기 예보, 동네예보) 서비스 키*/
	public static final String KMA_DIST_FORCAST_SPACE_SERVICE_KEY = "ri2HK3TW12dyrwDtk6jUK4W1b3w46GY3weGUg7l0kx0H3I%2Bc1l2kuTyKDUZPXQvPeW05bPShKSkfFjVR%2BSUUEg%3D%3D";
	                                                                 
	/** 생활 기상 지수 서비스 키*/
	public static final String KMA_LIFE_WEATHER_INDEX_SERVICE_KEY = "ri2HK3TW12dyrwDtk6jUK4W1b3w46GY3weGUg7l0kx0H3I%2Bc1l2kuTyKDUZPXQvPeW05bPShKSkfFjVR%2BSUUEg%3D%3D";
}
