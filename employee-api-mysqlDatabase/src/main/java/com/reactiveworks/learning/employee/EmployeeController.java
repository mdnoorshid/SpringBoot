package com.reactiveworks.learning.employee;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	static Logger logger=Logger.getLogger(EmployeeController.class);
	@Autowired
	EmployeeService employeeService;

	/*
	 * To get All employee
	 */
	@RequestMapping("/employees")
	public List<Employee> employeesEmployees() {
		return employeeService.getAllEmployees();
	}

	/**
	 * To get a particular employee
	 * 
	 * @param empId
	 * @return
	 */
	@RequestMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable("id") String empId) {
		Employee emp = employeeService.getEmployee(empId);
		return emp;
	}

	/**
	 * To add an Employee through POST
	 * 
	 * @param emp
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/employees")
	public void addEmployee(@RequestBody Employee emp) { //To Map the Employee instance we provide through RequestBody annotation
		employeeService.addEmployee(emp);
	}
	
	/**
	 * To delete an Employee
	 * @param id
	 */
	@RequestMapping(method=RequestMethod.DELETE , value="employees/{empid}")
	public void deleteEmployee(@PathVariable("empid") String id){
		employeeService.deleteAnEmployee(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="employees/{empid}")
	public void updateEmployee(@PathVariable("empid") String empId,@RequestBody Employee emp){
		logger.debug("....calling updateEmployee method");
		employeeService.updateAnEmployee(empId, emp);
	}

}
