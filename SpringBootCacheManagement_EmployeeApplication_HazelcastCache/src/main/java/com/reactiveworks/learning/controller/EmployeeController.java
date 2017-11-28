package com.reactiveworks.learning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactiveworks.learning.entity.Employee;
import com.reactiveworks.learning.service.EmployeeService;

@RestController
@RequestMapping(value = "/employeemanagement")
public class EmployeeController {
    
	@Autowired
	EmployeeService employeeService;

	@GetMapping(value="/getemployeebyid/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer id) {
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping(value="/createemployee")
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeService.createEmployee(employee);
	}
	
	@DeleteMapping(value="/deleteemployee/{id}")
	public void deleteEmployee(@PathVariable("id") Integer empId){
		employeeService.deleteEmployee(empId);	
	}
	
	@PutMapping(value="/updateemployee/{id}")
	public Employee updateEmployee(@PathVariable("id") Integer id,@RequestBody Employee employee){
		return employeeService.updateEmployee(id, employee);
	}
	
	@GetMapping(value="/getall")
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployees();
		
	}

}
