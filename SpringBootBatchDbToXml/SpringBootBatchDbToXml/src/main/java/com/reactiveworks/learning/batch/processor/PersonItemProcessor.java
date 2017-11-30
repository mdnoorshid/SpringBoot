package com.reactiveworks.learning.batch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.reactiveworks.learning.batch.model.Person;

/**
 * Defining Processor Class
 * 
 * @author Md Noorshids
 *
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
	static Logger logger = Logger.getLogger(PersonItemProcessor.class);

	@Override
	public Person process(Person person) throws Exception {
        logger.info("Processing the Person Object...............");;
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();
		Person transformedPerson = new Person();
		transformedPerson.setFirstName(firstName);
		transformedPerson.setLastName(lastName);
		transformedPerson.setPersonId(person.getPersonId());
		transformedPerson.setAge(person.getAge());
		transformedPerson.setEmail(person.getEmail());
		return transformedPerson;
	}
}
