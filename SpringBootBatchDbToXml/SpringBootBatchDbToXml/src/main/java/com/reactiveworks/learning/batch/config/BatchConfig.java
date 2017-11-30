package com.reactiveworks.learning.batch.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.reactiveworks.learning.batch.model.Person;
import com.reactiveworks.learning.batch.processor.PersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	static Logger logger=Logger.getLogger(BatchConfig.class);
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Defining reader method which will read from database
	 * @return cursorItemReader
	 */
	@Bean
	public JdbcCursorItemReader<Person> reader(){
		logger.info("inside reader method....");
		JdbcCursorItemReader<Person> cursorItemReader = new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql("SELECT person_id,first_name,last_name,email,age FROM person");
		cursorItemReader.setRowMapper(new PersonRowMapper());
		return cursorItemReader;
	}
	
	/**
	 * Configuring the processor method
	 * @return
	 */
	@Bean
	public PersonItemProcessor processor(){
		logger.info("inside processor method....");
		return new PersonItemProcessor();
	}
	
	/**
	 * Defining the writer method
	 * @return
	 */
	@Bean
	public StaxEventItemWriter<Person> writer(){
		logger.info("inside writer method....");
		StaxEventItemWriter<Person> writer = new StaxEventItemWriter<Person>();
		writer.setResource(new ClassPathResource("persons.xml"));
		
		Map<String,String> aliasesMap =new HashMap<String,String>();
		aliasesMap.put("person", "com.reactiveworks.learning.batch.model.Person");
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliasesMap);
		writer.setMarshaller(marshaller);
		writer.setRootTagName("persons");
		writer.setOverwriteOutput(true);
		return writer;
	}
	
	/**
	 * configuring the Step
	 * @return
	 */
	@Bean
	public Step step1(){
		logger.info("Configuring reader,processor,writer in Step having chunk size as 100.........");
		return stepBuilderFactory.get("step1").<Person,Person>chunk(100).reader(reader()).processor(processor()).writer(writer()).build();
	}
   
	/**
	 * Defining the Job
	 * @return
	 */
	@Bean
	public Job exportPerosnJob(){
		logger.debug("calling JOB.......");
		return jobBuilderFactory.get("exportPeronJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
}
