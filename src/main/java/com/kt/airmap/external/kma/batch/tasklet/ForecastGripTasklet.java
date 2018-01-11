package com.kt.airmap.external.kma.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.kt.airmap.Const;
import com.kt.airmap.external.kma.service.forecast.ForecastService;

public class ForecastGripTasklet extends KMACommonTask implements Tasklet{
	
//	@Autowired
//	ForecastService forcastService;

	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		forcastService.forecast(stdDateTime,Const.KMA_DIST_FORCAST_GRIB_URI);
	  	return RepeatStatus.FINISHED;
	}
}
