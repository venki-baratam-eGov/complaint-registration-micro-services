package org.complaint.domain.service;

import org.complaint.persistence.complaint.repository.CrnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrnGeneratorService {

	private CrnRepository crnRepository;

	@Autowired
	public CrnGeneratorService(CrnRepository crnRepository) {
		this.crnRepository = crnRepository;
	}

	public String generate() {
		return crnRepository.getCrn().getValue();
	}
}