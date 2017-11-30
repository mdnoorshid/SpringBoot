package com.reactiveworks.learning.batch.step;

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
		logger.info("Processing the Person Object...............");
		;
		final Integer age = person.getAge();
		logger.info("age===> "+age);
		Person transformedPerson = null;
		if (age >=15 && age <=20) {
			transformedPerson=new Person();
			String firstName = person.getFirstName().toUpperCase();
			String lastName = person.getLastName().toUpperCase();
			transformedPerson.setFirstName(firstName);
			transformedPerson.setLastName(lastName);
			transformedPerson.setPersonId(person.getPersonId());
			transformedPerson.setAge(person.getAge());
			transformedPerson.setEmail(person.getEmail());
		}else {
			return null;
		}
		return transformedPerson;
	}
}
