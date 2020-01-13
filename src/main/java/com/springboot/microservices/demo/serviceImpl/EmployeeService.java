package com.springboot.microservices.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.microservices.demo.entities.Employee;
import com.springboot.microservices.demo.repo.EmployeeRepository;
import com.springboot.microservices.demo.service.IEmployeeService;


@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void saveEmployee(Employee employee) {

		employeeRepository.save(employee);

	}

	@Override
	public List<Employee> getEmployeeByEmployeeDetails(String firstName, String lastName, String dept) {

		List<Employee> emplist = null;
		emplist = employeeRepository.findByFirstNameAndLastNameAndDepartment(firstName, lastName, dept);
		return emplist;
	}

	@Override
	public void deleteEmployeeByEmployeeId(Long Id) {

		employeeRepository.deleteById(Id);

	}

}
