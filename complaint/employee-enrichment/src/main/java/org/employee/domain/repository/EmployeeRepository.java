package org.employee.domain.repository;

import org.employee.domain.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeRepository {

	private RestTemplate restTemplate;
	private String employeeServiceUrl;

	@Autowired
	public EmployeeRepository(RestTemplate restTemplate, @Value("${employee.service.url}") String employeeServiceUrl) {

		this.restTemplate = restTemplate;
		this.employeeServiceUrl = employeeServiceUrl;
	}

	public Employee findById(Long id) {
		return restTemplate.getForObject(employeeServiceUrl, Employee.class, id);
	}
}