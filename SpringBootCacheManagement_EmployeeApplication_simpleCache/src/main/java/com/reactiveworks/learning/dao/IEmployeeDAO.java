package com.reactiveworks.learning.dao;

import org.springframework.data.repository.CrudRepository;

import com.reactiveworks.learning.entity.Employee;

public interface IEmployeeDAO extends CrudRepository<Employee, Integer> {

}
