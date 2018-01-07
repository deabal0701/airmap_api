package com.kt.airmap.external.kma.batch.kma;

import java.util.Date;

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
import com.kt.airmap.external.kma.batch.kma.tasklet.FoodPosioningTasklet;
import com.kt.airmap.external.kma.batch.kma.tasklet.SensibleTempTasklet;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class SensibleTempConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Scheduled(cron="${cron.expression}")
	public void perform() throws Exception {

		System.out.println("Job Started at :" + new Date());

		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher.run(sensibleTemp_Job(), param);

		System.out.println("Job finished with status :" + execution.getStatus());
	}
	
	@Bean
	public Job sensibleTemp_Job() {
		return jobBuilderFactory.get("sensibleTemp_Job").incrementer(new RunIdIncrementer()).flow(sensibleTemp_step()).end().build();
	}
		
	
	@Bean
	public Step sensibleTemp_step() {
		return stepBuilderFactory.get("sensibleTemp_step").tasklet(sensibleTempTasklet()).build();
	}
		
	@Bean
	public SensibleTempTasklet sensibleTempTasklet() {
		
		SensibleTempTasklet tasklet = new SensibleTempTasklet();
		tasklet.setDateTime(DateUtil.getToday(),DateUtil.getTime().substring(0,2));

		return tasklet;
	}
}