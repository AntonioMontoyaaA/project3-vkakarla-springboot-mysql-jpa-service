package com.springboot.microservices.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.microservices.demo.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


	List<Employee> findByFirstNameAndLastNameAndDepartment(String firstName,String lastName, String dept);

}
