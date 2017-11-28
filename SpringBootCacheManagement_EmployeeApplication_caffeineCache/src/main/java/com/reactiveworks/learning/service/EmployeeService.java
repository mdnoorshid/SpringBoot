package com.reactiveworks.learning.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.dao.IEmployeeDAO;
import com.reactiveworks.learning.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	IEmployeeDAO iEmployeeDAO;
  
	/**
	 * Here get method is cacheable and here putting result==null in unless
	 * This is to indicate cache not put null values result into cache
	 * @param id
	 * @return
	 */
	@Cacheable(value="employeeCache" ,key="#id",unless="#result==null")
	public Employee getEmployeeById(Integer id) {
		return iEmployeeDAO.findOne(id);
	}

	public Employee createEmployee(Employee employee) {
		return iEmployeeDAO.save(employee);
	}
	
	@CacheEvict(value="employeeCache" ,key="#id")
	public void deleteEmployee(Integer id){
		iEmployeeDAO.delete(id);
	}
	
	@CachePut(value="employeeCache" ,key="#id")
	public Employee updateEmployee(Integer id,Employee employee){
		String employeeFirstName = employee.getEmployeeFirstName();
		String employeeSecondName = employee.getEmployeeSecondName();
		Date employeeDOB = employee.getEmployeeDOB();
		String employeeEmail = employee.getEmployeeEmail();
		String employeeDesignation = employee.getEmployeeDesignation();
		employee=new Employee(id, employeeFirstName, employeeSecondName, employeeDOB, employeeEmail, employeeDesignation);
		return iEmployeeDAO.save(employee);
	}

	@Cacheable(value="employeeCache")
	public List<Employee> getAllEmployees(){
		List<Employee> employeeList= new ArrayList<Employee>();
		iEmployeeDAO.findAll().forEach(employeeList::add);
		return employeeList;
		
	}

}
