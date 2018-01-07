package com.kt.airmap.external.kma.batch.kma.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.kt.airmap.Const;
import com.kt.airmap.external.kma.service.forecast.ForecastService;

public class ForecastTasklet  implements Tasklet{
	
	@Autowired
	ForecastService forcastService;
	
    private String stdDateTime;
	
	public void setDateTime(String exeDate, String exeTime) {
		stdDateTime = exeDate + exeTime;
	}
	
	@Override
	public RepeatStatus execute(StepContribution paramStepContribution, ChunkContext paramChunkContext)	throws Exception {
			
		forcastService.forecast(stdDateTime,Const.KMA_DIST_FORCAST_SPACE_URI);
		  	return RepeatStatus.FINISHED;
	}
}
