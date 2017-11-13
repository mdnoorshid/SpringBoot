package com.reactiveworks.learning.employee;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * This is the entity class , when we marked this class as Entity using JPA then class name will 
 * become as Table name and member variables will become columns.Every instance instance of this class
 * will be in rows.
 * This is the default configuration
 * @author ReactiveWorks
 *
 */
@Entity
public class Employee implements Serializable {

@Id	
private String id;
private String name;
private String designation;
public Employee(String id, String name, String designation) {
	super();
	this.id = id;
	this.name = name;
	this.designation = designation;
}
public Employee(){
	
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}

}
