package com.reactiveworks.learning.employee;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactiveworks.learning.employee.repository.EmployeeRepo;

/**
 * Service Class of Employee
 * 
 * @author Niyamat
 *
 */
@Service
public class EmployeeService {
	static Logger logger = Logger.getLogger(EmployeeService.class);
/*	List<Employee> employeeList = new ArrayList<>(Arrays.asList(new Employee("1", "Md Noorshid", "Software Developer"),
			new Employee("2", "Raja", "Software Developer"), new Employee("3", "Deelipa", "Software Developer")));
*/
	@Autowired
	private EmployeeRepo employeeRepo;
	
	/**
	 * To get all Employees
	 * 
	 * @return List of Employee
	 */
	public List<Employee> getAllEmployees() {
		//return employeeList;
		List<Employee>empList=new ArrayList<>();
		employeeRepo.findAll().forEach(empList::add);
		return empList;
	}

	/**
	 * To get an Employee
	 * 
	 * @return Employee of the particular Id
	 */
	public Employee getEmployee(String empId) {
		/*Employee emp = employeeList.stream().filter(e -> e.getId().equalsIgnoreCase(empId)).findFirst().get();
		return emp;*/
		return employeeRepo.findById(empId).get();
	}

	/**
	 * To add an Employee
	 */
	public void addEmployee(Employee emp) {
		//employeeList.add(emp);
		employeeRepo.save(emp);
	}

	/**
	 * To remove an Employee
	 * 
	 * @param empId
	 */
	public void deleteAnEmployee(String empId) {
		//employeeList.removeIf(e -> e.getId().equalsIgnoreCase(empId));
		employeeRepo.deleteById(empId);
	}
    
	/**
	 * To update an employee
	 * @param empId
	 * @param emp
	 */
	public void updateAnEmployee(String empId, Employee emp){
		logger.debug("...inside updateAnEmployee");
	  /*  for(int i=0;i<employeeList.size();i++){
		   Employee e=employeeList.get(i);
		   if(e.getId().equalsIgnoreCase(empId)){
			   employeeList.set(i, emp);
			   return;
		   }
	 }*/
		employeeRepo.save(emp);
	}

}
