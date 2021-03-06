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
import com.kt.airmap.external.kma.batch.tasklet.ForecastGripTasklet;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class ForecastGripConfiguration {

	private static Logger logger = Logger.getLogger(ForecastGripConfiguration.class); 
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;
	
    //@Scheduled(cron="${cron.forc.grip.expr}")
	@Scheduled(cron="${cron.expression}")
	public void perform() throws Exception {

		JobParameters param = new JobParametersBuilder()
				.addString("forecast_Grip_Job_Id", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher.run(forecast_Grip_Job(), param);
		System.out.println("Job finished with status :" + execution.getStatus());
	}
	
	@Bean
	public Job forecast_Grip_Job() {
		return jobBuilderFactory.get("forecast_Grip_Job")
				.incrementer(new RunIdIncrementer())
				.flow(forecast_Grip_Step())
				.end()
				.build();
	}

	@Bean
	public Step forecast_Grip_Step() {
		return stepBuilderFactory.get("forecast_Grip_Step")
			    .tasklet(forecastGripTasklet())
				.build();
	}
		
	@Bean
	public ForecastGripTasklet forecastGripTasklet() {
		
		ForecastGripTasklet tasklet = new ForecastGripTasklet();
		tasklet.setDateTime(DateUtil.getCalTime(0,0,0).substring(0,8), DateUtil.getCalTime(0, 0, 0).substring(8,10) + "00");

		return tasklet;
	}
}
