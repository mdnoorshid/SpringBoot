package com.reactiveworks.learning;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBootBatchSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatchSampleApplication.class, args);
	}
}
