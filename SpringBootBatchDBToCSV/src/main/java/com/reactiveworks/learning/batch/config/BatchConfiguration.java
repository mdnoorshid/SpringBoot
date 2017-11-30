package com.reactiveworks.learning.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.reactiveworks.learning.batch.listener.JobCompletionNotificationListener;
import com.reactiveworks.learning.batch.model.Person;
import com.reactiveworks.learning.batch.processor.PersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    /**
     * Reading from DataBase
     * @return
     */
    @Bean
    public JdbcCursorItemReader<Person> reader() {
    	JdbcCursorItemReader<Person> cursorItemReader=new JdbcCursorItemReader<>();
    	cursorItemReader.setDataSource(dataSource);
    	cursorItemReader.setSql("SELECT person_id,first_name,last_name,email,age FROM person");
    	cursorItemReader.setRowMapper(new PersonRowMapper());
    	return cursorItemReader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    /**
     * Writing into CSV file	
     * @return
     */
    @Bean
    public FlatFileItemWriter<Person> writer() {
    	FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
    	writer.setResource(new ClassPathResource("persons.csv"));
    	
    	DelimitedLineAggregator<Person> lineAggregator=new DelimitedLineAggregator<>();
    	lineAggregator.setDelimiter(",");
    	
    	BeanWrapperFieldExtractor<Person> fieldExtractor=new BeanWrapperFieldExtractor<>();
    	fieldExtractor.setNames(new String[]{"personId","firstName","lastName","email","age"});
        lineAggregator.setFieldExtractor(fieldExtractor);
    	
    	writer.setLineAggregator(lineAggregator);
    	
        return writer;
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
    	return stepBuilderFactory.get("step1").<Person,Person>chunk(100).reader(reader())
    			.processor(processor()).writer(writer()).build();
    }
    
    public Job exportPersonJob(){
    	return jobBuilderFactory.get("exportPersonJob").incrementer(new RunIdIncrementer())
    			.flow(step1()).end().build();
    }
    
}