package org.complaint.domain.service;

import org.complaint.persistence.complaint.repository.CrnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrnGeneratorService {

	@Autowired
	private CrnRepository crnRepository;

	public String generate() {
		return crnRepository.getCrn().getValue();
	}
}