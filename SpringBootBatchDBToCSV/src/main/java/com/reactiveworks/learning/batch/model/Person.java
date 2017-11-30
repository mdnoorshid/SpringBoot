package com.reactiveworks.learning.batch.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -6402068923614583448L;
	private Integer personId;
	private String firstName;
	private String lastName;
	private String email;
	private Integer age;

	public Person() {
	}

	public Person(String firstName, String lastName, String email, Integer age, Integer personId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", age=" + age + "]";
	}
}
