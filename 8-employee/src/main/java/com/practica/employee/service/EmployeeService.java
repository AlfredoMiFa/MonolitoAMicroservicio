package com.practica.employee.service;

import java.util.List;

import com.practica.employee.exception.model.Employee;


public interface EmployeeService {
	
	Employee retrieveById(Long id);

	List<Employee> retrieveAll();

	Employee register(Employee employee);

	Employee update(Employee employee);

	Employee partialUpdate(long employeeId, Employee partialEmployee);

	Employee delete(Employee employee);

	List<Employee> retrieveByFirstName(String firstName);

	List<Employee> retrieveByFirstNameAndLastName(String firstName, String lastName);

	Employee retrieveByEmployeeNumber(String employeeNumber);
	
}
