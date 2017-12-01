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
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.reactiveworks.learning.batch.model.Person;
import com.reactiveworks.learning.batch.step.PersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	static Logger logger = Logger.getLogger(BatchConfig.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	/**
	 * Configuring the processor method
	 * 
	 * @return
	 */
	@Bean
	public PersonItemProcessor processor() {
		logger.info("inside processor method....");
		return new PersonItemProcessor();
	}

	/**
	 * If we need to read information from an XML document, we can use the StaxEventItemReader<T> class.
	 * The StaxEventItemReader<T> class transforms XML fragments into objects by using an Unmarshaller
	 * @return
	 */
	@Bean
	public StaxEventItemReader<Person> reader() {
		StaxEventItemReader<Person> reader = new StaxEventItemReader<Person>();
		reader.setResource(new ClassPathResource("persons.xml"));
		reader.setFragmentRootElementName("person");

		Map<String, String> aliasesMap = new HashMap<String, String>();
		aliasesMap.put("person", "com.reactiveworks.learning.batch.model.Person");
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliasesMap);

		reader.setUnmarshaller(marshaller);
		return reader;
	}

	/**
	 * Defining JDBCItemWriter object and setting datasource, setting SQL query
	 * and setting the perpared item setter
	 * 
	 * @return
	 */
	   @Bean
	    public JdbcBatchItemWriter<Person> writer() {
	    	logger.info(".inside writer method...........");
	        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
	        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
	        writer.setSql("INSERT INTO tenager (first_name, last_name,email,age) VALUES (:firstName, :lastName,:email,:age)");
	        writer.setDataSource(dataSource);
	        return writer;
	    }

	/**
	 * configuring the Step
	 * 
	 * @return
	 */
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(100).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	/**
	 * Defining the Job
	 * 
	 * @return
	 */
	@Bean
	public Job exportPerosnJob() {
		logger.debug("calling JOB.......");
		return jobBuilderFactory.get("exportPeronJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
}
