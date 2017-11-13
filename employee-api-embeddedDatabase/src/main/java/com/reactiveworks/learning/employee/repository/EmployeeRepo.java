package com.reactiveworks.learning.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.reactiveworks.learning.employee.Employee;
public interface EmployeeRepo extends CrudRepository<Employee, String> {

}
