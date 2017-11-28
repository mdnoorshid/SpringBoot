package com.reactiveworks.learning.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeManagment")
public class Employee implements Serializable {
   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "employee_id")
	private Integer employeeId;
	@Column(name = "employee_first_name", length = 20, nullable = false)
	private String employeeFirstName;
	@Column(name = "employee_second_name", length = 50, nullable = false)
	private String employeeSecondName;
	@Column(name = "employee_DOB", nullable = false)
	private Date employeeDOB;
	@Column(name = "employee_email")
	private String employeeEmail;
	@Column(name = "employee_designation", length = 30, nullable = false)
	private String employeeDesignation;

	public Employee(Integer employeeId, String employeeFirstName, String employeeSecondName, Date employeeDOB,
			String employeeEmail, String employeeDesignation) {
		this.employeeId = employeeId;
		this.employeeFirstName = employeeFirstName;
		this.employeeSecondName = employeeSecondName;
		this.employeeDOB = employeeDOB;
		this.employeeEmail = employeeEmail;
		this.employeeDesignation = employeeDesignation;
	}
	
	public Employee() {
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeSecondName() {
		return employeeSecondName;
	}

	public void setEmployeeSecondName(String employeeSecondName) {
		this.employeeSecondName = employeeSecondName;
	}

	public Date getEmployeeDOB() {
		return employeeDOB;
	}

	public void setEmployeeDOB(Date employeeDOB) {
		this.employeeDOB = employeeDOB;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}
}
