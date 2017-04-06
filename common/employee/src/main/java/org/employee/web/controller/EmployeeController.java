package org.employee.web.controller;

import java.util.List;

import org.employee.persistence.entity.Employee;
import org.employee.persistence.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("_search")
	@ResponseBody
	public ResponseEntity<?> search() {
		return getSuccessResponseForSearch(employeeService.getAll());
	}

	@GetMapping("getById")
	public Employee getboundary(@RequestParam(name = "id", required = true) Long id) {
		return employeeService.getById(id);
	}

	public ResponseEntity<?> getSuccessResponseForSearch(List<Employee> employees) {
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

}