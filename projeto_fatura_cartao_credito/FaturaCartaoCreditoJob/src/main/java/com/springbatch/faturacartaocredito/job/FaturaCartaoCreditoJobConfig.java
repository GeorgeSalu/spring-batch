package com.springbatch.faturacartaocredito.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class FaturaCartaoCreditoJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job faturaCartaoCreditojob(Step faturaCartaoCreditoStep) {
		return jobBuilderFactory
				.get("faturaCartaoCreditojob")
				.start(faturaCartaoCreditoStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
