

package org.employee.persistence.service;

import java.util.List;

import org.employee.persistence.entity.Department;
import org.employee.persistence.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department getByCode(String code) {

		return departmentRepository.findByCode(code);
	}

	public List<Department> getAll() {

		return departmentRepository.findAll();
	}

}
