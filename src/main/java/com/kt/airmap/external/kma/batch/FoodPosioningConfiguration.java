package com.kt.airmap.external.kma.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.kt.airmap.base.common.DateUtil;
import com.kt.airmap.external.kma.batch.tasklet.FoodPosioningTasklet;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class FoodPosioningConfiguration {

	private static Logger log = Logger.getLogger(LocationCodeConfiguration.class); 
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	
   // @Scheduled(cron="${cron.life.food.expr}")
	public void perform() throws Exception {
	
		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher.run(foodPosioning_Job(), param);
		System.out.println("Job finished with status :" + execution.getStatus());
	}
	
	@Bean
	public Job foodPosioning_Job() {
		return jobBuilderFactory.get("foodPosioning_Job")
				.incrementer(new RunIdIncrementer())
				.flow(foodPosioning_step())
				.end()
				.build();
	}

	@Bean
	public Step foodPosioning_step() {
		return stepBuilderFactory.get("foodPosioning_step")
				.tasklet(foodPosioningTasklet())
				.build();
	}

//	@Bean
//	public Step foodPosioning_step() {
//		return stepBuilderFactory.get("foodPosioning_step")
//				.chunk(100)
//				.reader(testReader())
//				.processor(testProcessor())
//				.writer(testWriter()).build();
//	}
	
	@Bean
	public FoodPosioningTasklet foodPosioningTasklet() {
		
		FoodPosioningTasklet tasklet = new FoodPosioningTasklet();
		tasklet.setDateTime(DateUtil.getToday(),DateUtil.getTime().substring(0,2));

		return tasklet;
	}

}
