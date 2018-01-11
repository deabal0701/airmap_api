package com.kt.airmap.external.kma.batch;

import java.util.Date;

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
import com.kt.airmap.external.kma.batch.tasklet.LocationCodeTasklet;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class LocationCodeConfiguration {

	private static Logger log = Logger.getLogger(LocationCodeConfiguration.class); 

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	
    //@Scheduled(cron="${cron.kma.loc.expr}")
	public void perform() throws Exception {

    	System.out.println("Job Started at :" + new Date());
		JobParameters param = new JobParametersBuilder()
				.addString("LocationCode_JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		
		JobExecution execution = jobLauncher.run(locationCode_Job(), param);
	
	}
	
	@Bean
	public Job locationCode_Job() {
		return jobBuilderFactory.get("locationCode_Job")
				.incrementer(new RunIdIncrementer())
				.flow(locationCode_Step())
				.end()
				.build();
	}

	@Bean
	public Step locationCode_Step() {
		return stepBuilderFactory.get("locationCode_Step")
				.tasklet(locationCodeTasklet())
				.build();
	}

	

	@Bean
	public LocationCodeTasklet locationCodeTasklet() {
		
		LocationCodeTasklet tasklet = new LocationCodeTasklet();
		tasklet.setDateTime(DateUtil.getToday(),DateUtil.getTime().substring(0,2));

		return tasklet;
	}
}
