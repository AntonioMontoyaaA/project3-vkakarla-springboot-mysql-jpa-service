package com.springboot.microservices.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservices.demo.entities.Employee;
import com.springboot.microservices.demo.serviceImpl.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@ApiOperation(value = "Service to save employee")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully Saved Employee.", response = EmployeeController.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = Exception.class),
			@ApiResponse(code = 404, message = "Unable to find contracts", response = Exception.class) })
	@PostMapping(value = "/saveEmployee")
	public ResponseEntity<Object> saveAllEmployees(@RequestBody Employee employee) {

		employeeService.saveEmployee(employee);

		return new ResponseEntity<Object>("Successfully Saved", HttpStatus.OK);
	}

	@ApiOperation(value = "Get Employees based on employee details")
	@ApiResponses({ @ApiResponse(code = 200, message = "Fatched All Employees.", response = EmployeeController.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = Exception.class),
			@ApiResponse(code = 404, message = "Unable to find contracts", response = Exception.class) })
	@GetMapping(value = "/getEmployees/firstName/{firstName}/lastName/{lastName}/department/{dept}")
	public ResponseEntity<Object> getEmployeeByEmployeeDetails(@RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String dept) {

		List<Employee> empList = null;

		empList = employeeService.getEmployeeByEmployeeDetails(firstName, lastName, dept);

		return new ResponseEntity<Object>(empList, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete employee", notes = "Delete Operation Based on given employee-id")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Successfully Deleted  Employee.", response = EmployeeController.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = Exception.class),
			@ApiResponse(code = 404, message = "Unable to find contracts", response = Exception.class) })
	@DeleteMapping(value = "/deleteEmployee/id/{id}")
	public ResponseEntity<Object> deleteAllEmployees(@PathVariable Long id) {

		employeeService.deleteEmployeeByEmployeeId(id);

		return new ResponseEntity<Object>("Successfully Deleted", HttpStatus.OK);
	}

}
