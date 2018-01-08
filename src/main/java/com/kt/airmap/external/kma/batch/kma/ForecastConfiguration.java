package com.kt.airmap.external.kma.batch.kma;

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
import com.kt.airmap.external.kma.batch.kma.tasklet.ForecastTasklet;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class ForecastConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;
	

    //@Scheduled(cron="${cron.expression}")
	public void perform() throws Exception {

		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher.run(forecast_job(), param);
		System.out.println("Job finished with status :" + execution.getStatus());
	}
	
	@Bean
	public Job forecast_job() {
		return jobBuilderFactory.get("forecast_job").incrementer(new RunIdIncrementer()).flow(forecast_step()).end().build();
	}

	@Bean
	public Step forecast_step() {
		return stepBuilderFactory.get("forecast_step").tasklet(forecastTasklet()).build();
	}
		
	@Bean
	public ForecastTasklet forecastTasklet() {
		
		ForecastTasklet tasklet = new ForecastTasklet();
		tasklet.setDateTime(DateUtil.getCalTime(0,-3,0).substring(0,8), DateUtil.getCalTime(0, -3, 0).substring(8,10) + "00");

		return tasklet;
	}
}
