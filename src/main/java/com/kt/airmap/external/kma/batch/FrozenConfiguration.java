package com.kt.airmap.external.kma.batch;

import java.util.List;

import javax.sql.DataSource;

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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.kt.airmap.Const;
import com.kt.airmap.external.kma.batch.chunk.listener.JobCompletionNotificationListener;
import com.kt.airmap.external.kma.batch.chunk.processor.LifeIndexProcessor;
import com.kt.airmap.external.kma.batch.chunk.reader.LifeIndexReader;
import com.kt.airmap.external.kma.batch.chunk.writer.LifeIndexWriter;
import com.kt.airmap.external.kma.service.LifeIndexBatchService;
import com.kt.airmap.external.kma.vo.lifeindex.LifeIndexVo;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class FrozenConfiguration {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleJobLauncher jobLauncher;

	@Autowired
    public LifeIndexBatchService lifeIndexBatchService;
	
    

    @Scheduled(cron="${cron.expression}")
 	public void perform() throws Exception {
 	
 		JobParameters param = new JobParametersBuilder().addString("prozenJob_ID", String.valueOf(System.currentTimeMillis()))
 				.toJobParameters();

 		JobExecution execution = jobLauncher.run(prozenJob(null), param);
 		System.out.println("Job finished with status :" + execution.getStatus());
 	}
    
    @Bean
    public Job prozenJob(JobCompletionNotificationListener listener ) {
        return jobBuilderFactory.get("prozenJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(prozon_step1())
                .end()
                .build();
    }

    //    @Bean
//    public Step prozon_step1() {
//        return stepBuilderFactory.get("prozon_step1")
//                .<LifeIndexVo, LifeIndexVo> chunk(3)
//                .reader(lifeIndexReader())
//                .processor(lifeIndexProcessor())
//                .writer(lifeIndexWriter())  
//                .build();
//    }
    
    @Bean
    public Step prozon_step1() {
        return stepBuilderFactory.get("prozon_step1")
                .<LifeIndexVo, LifeIndexVo> chunk(1)
                .reader(lifeIndexReader())
                .processor(lifeIndexProcessor())
                .writer(lifeIndexWriter())  
                .build();
    }

    
//    @Bean
//  	public Step step1() {
//
//  		return stepBuilderFactory.get("step1")
//  				.<String,String>chunk(2)
//  				.reader(new Reader())
//  					.build();
//  	}
    
    
    @Bean
	public ItemReader<LifeIndexVo> lifeIndexReader(){
	
		return new LifeIndexReader(lifeIndexBatchService);
	}

    @Bean
    public LifeIndexProcessor lifeIndexProcessor() {
        return new LifeIndexProcessor();
    }

	@Bean
    public ItemWriter<LifeIndexVo> lifeIndexWriter() {
        return new LifeIndexWriter();
    }
    


}
