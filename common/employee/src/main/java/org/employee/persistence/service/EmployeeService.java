

package org.employee.persistence.service;

import java.util.List;

import org.employee.persistence.entity.Employee;
import org.employee.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee getById(Long id) {

		return employeeRepository.findOne(id);
	}

	public List<Employee> getAll() {

		return employeeRepository.findAll();
	}
}
