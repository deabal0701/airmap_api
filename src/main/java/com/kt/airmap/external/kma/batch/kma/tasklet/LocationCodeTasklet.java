package com.kt.airmap.external.kma.batch.kma.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.kt.airmap.external.kma.service.LocationCodeService;

public class LocationCodeTasklet implements Tasklet{

	@Autowired
	LocationCodeService locationCodeService;
    private String stdDateTime;
	
	public void setDateTime(String exeDate, String exeTime) {
		stdDateTime = exeDate + exeTime;
	}
		
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		locationCodeService.locationCode(stdDateTime);
	  	return RepeatStatus.FINISHED;
	}
}
