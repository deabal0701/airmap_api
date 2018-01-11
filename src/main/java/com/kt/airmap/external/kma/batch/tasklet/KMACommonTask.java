package com.kt.airmap.external.kma.batch.tasklet;

import org.springframework.beans.factory.annotation.Autowired;

import com.kt.airmap.external.kma.service.LocationCodeService;
import com.kt.airmap.external.kma.service.forecast.ForecastService;
import com.kt.airmap.external.kma.service.lifeindex.LifeIndexService;

public class KMACommonTask {

	@Autowired
	protected LifeIndexService lifeIndexService;
	
	@Autowired
	protected ForecastService forcastService;

	@Autowired
	protected LocationCodeService locationCodeService;
	
	protected String stdDateTime;
	
	public void setDateTime(String exeDate, String exeTime) {
		stdDateTime = exeDate + exeTime;
	}
	
	public void logging(){
		
	}
	
}
