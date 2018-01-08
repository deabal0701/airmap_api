package com.kt.airmap.base.config;

import javax.annotation.PostConstruct;

import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {
	
	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
	
	
	/** DB에Batch관련 테이블 생성하지 않고 DB기록 하지 않음 */
	@Bean
	public BatchConfigurer batchConfigurer() {
		
		BatchConfigurer configurer = new BatchConfigurer() {
        
		private PlatformTransactionManager transactionManager;
        private JobRepository jobRepository;
        private JobLauncher jobLauncher;
        private JobExplorer jobExplorer;

        @Override
        public PlatformTransactionManager getTransactionManager() throws Exception {
            return transactionManager;
        }

        @Override
        public JobRepository getJobRepository() throws Exception {
            return jobRepository;
        }

        @Override
        public JobLauncher getJobLauncher() throws Exception {
            return jobLauncher;
        }

        @Override
        public JobExplorer getJobExplorer() throws Exception {
            return jobExplorer;
        }

        @PostConstruct
        public void initialize() {
            if (this.transactionManager == null) {
                this.transactionManager = new ResourcelessTransactionManager();
            }
            try {
                MapJobRepositoryFactoryBean jrf = new MapJobRepositoryFactoryBean(this.transactionManager);
                jrf.afterPropertiesSet();
                this.jobRepository = jrf.getObject();

                MapJobExplorerFactoryBean jef = new MapJobExplorerFactoryBean(jrf);
                jef.afterPropertiesSet();
                this.jobExplorer = jef.getObject();

                SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                jobLauncher.setJobRepository(jobRepository);
                jobLauncher.afterPropertiesSet();
                this.jobLauncher = jobLauncher;
            } catch (Exception e) {
                throw new BatchConfigurationException(e);
            }
        }
    };
    return configurer;
	}
}