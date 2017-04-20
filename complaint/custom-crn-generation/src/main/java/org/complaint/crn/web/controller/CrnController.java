package org.complaint.crn.web.controller;

import org.complaint.crn.domain.service.CrnGeneratorService;
import org.complaint.crn.web.contract.ComplaintRegistrationNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crn")
public class CrnController {

	private CrnGeneratorService crnGeneratorService;

	public CrnController(CrnGeneratorService crnGeneratorService) {
		this.crnGeneratorService = crnGeneratorService;
	}

	@GetMapping()
	public ComplaintRegistrationNumber getCrn() {
		return new ComplaintRegistrationNumber(crnGeneratorService.generate());
	}
}