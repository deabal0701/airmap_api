package com.kt.airmap.external.kma.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.kt.airmap.Const;
import com.kt.airmap.external.kma.service.lifeindex.LifeIndexService;

public class SensibleTempTasklet extends KMACommonTask implements Tasklet {

//	@Autowired
//	LifeIndexService lifeIndexService;


	@Override
	public RepeatStatus execute(StepContribution paramStepContribution, ChunkContext paramChunkContext)
			throws Exception {
		  lifeIndexService.lifeIndex(stdDateTime, Const.KMA_LIFE_WEATHER_SENSIBLE_TEMP_URI);
		return RepeatStatus.FINISHED;
	}
}
