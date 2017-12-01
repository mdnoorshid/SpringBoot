package com.reactiveworks.learning.batch.config;


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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import com.reactiveworks.learning.batch.listener.JobCompletionNotificationListener;
import com.reactiveworks.learning.batch.model.Person;
import com.reactiveworks.learning.batch.processor.PersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
static Logger logger=Logger.getLogger(BatchConfiguration.class);
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    /**
     * Defining reader method
     * If we need to read lines from an input file, we can use the FlatItemReader<T> class.
     * The FlatItemReader<T> class transforms lines into objects by using a LineMapper<T>.
     * 
     * Create a new FlatItemReader<StudentDTO> object. This reader can read lines from the Resource that is configured by invoking the 
     * setResource() method.
     * 
     * 
     * 
     * 
     * 
     * @return
     */
    @Bean
    public FlatFileItemReader<Person> reader() {
        logger.info(".inside reader method...........");
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource("persons.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName","email","age" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }
   
    /**
     * Configuring processor 
     * @return
     */
    @Bean
    public PersonItemProcessor processor() {
    	logger.info(".inside processor method...........");
        return new PersonItemProcessor();
    }
 /**
  * Defining writer method
  * We can write information to a database by using the JdbcBatchItemWriter<T> class.
  * 
  * If we are using named parameters and our parameter names are equal to the property names of the class that
  * contains the actual parameter values, we can provide the actual parameter values to the JdbcBatchItemWriter<T>
  * by using the BeanPropertyItemSqlParameterSourceProvider<T> class.
  * @return
  */
    @Bean
    public JdbcBatchItemWriter<Person> writer() {
    	logger.info(".inside writer method...........");
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO person (first_name, last_name,email,age) VALUES (:firstName, :lastName,:email,:age)");
        writer.setDataSource(dataSource);
        return writer;
    }

    /**
     * Defining importUserJob method
     * @param listener
     * @return
     */
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
    	logger.info(".inside importUserJob method...........");
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }
   
    /**
     * Defining Step
     * @return
     */
    @Bean
    public Step step1() {
    	logger.info(".inside importUserJob method...........");
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}