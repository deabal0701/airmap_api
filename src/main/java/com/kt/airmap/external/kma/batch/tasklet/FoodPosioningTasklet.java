package com.kt.airmap.external.kma.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.kt.airmap.Const;
import com.kt.airmap.external.kma.service.lifeindex.LifeIndexService;

public class FoodPosioningTasklet extends KMACommonTask implements Tasklet{
	
	
	@Override
	public RepeatStatus execute(StepContribution paramStepContribution, ChunkContext paramChunkContext)
			throws Exception {
		
	
	    lifeIndexService.lifeIndex(stdDateTime, Const.KMA_LIFE_WEATHER_FOOD_POSISONING_URI);
	    System.out.println(String.format("%s has been executed on thread %s", paramChunkContext.getStepContext(),Thread.currentThread().getName()));

	  	return RepeatStatus.FINISHED;
	}
}
