package org.complaint.web.controller.complaint;

import java.util.List;

import org.complaint.errorhandlers.ErrorHandler;
import org.complaint.persistence.complaint.service.ComplaintService;
import org.complaint.web.contract.Complaint;
import org.complaint.web.contract.ComplaintStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

	@Autowired
	private ErrorHandler errorHandler;

	@Autowired
	private ComplaintService complaintService;

	@PostMapping("_create")
	@ResponseBody
	public ResponseEntity<?> create(Complaint complaint, BindingResult bindingResult) {

		ResponseEntity<?> errorResponseEntity = validateComplaintRequest(complaint, bindingResult);
		if (errorResponseEntity != null)
			return errorResponseEntity;

		ComplaintStatus status = new ComplaintStatus();
		status.setName("PROCESSING");
		complaint.setStatus(status);
		
		complaintService.push(complaint);

		return new ResponseEntity<String>("Complaint created successfully," + "  and it is under processing......",
				HttpStatus.OK);
	}

	@PostMapping("/{id}/_update")
	@ResponseBody
	public ResponseEntity<?> update(org.complaint.persistence.complaint.entity.Complaint complaint,
			BindingResult bindingResult, @PathVariable(name = "id") Long id) {

		ResponseEntity<?> errorResponseEntity = validateComplaintRequestForUpdate(complaint, bindingResult);
		if (errorResponseEntity != null)
			return errorResponseEntity;
		complaint.setId(id);
		complaint = complaintService.update(complaint);
		return new ResponseEntity<String>(
				"Complaint updated successfully with the status: " + complaint.getStatus().getName(), HttpStatus.OK);
	}

	@GetMapping("_search")
	@ResponseBody
	public ResponseEntity<?> search() {
		return getSuccessResponseForSearch(complaintService.getAll());
	}

	@GetMapping("getById")
	@ResponseBody
	public ResponseEntity<?> getById(@RequestParam(name = "id") Long id) {
		return new ResponseEntity<org.complaint.persistence.complaint.entity.Complaint>(complaintService.getById(id),
				HttpStatus.OK);
	}

	private ResponseEntity<?> validateComplaintRequest(Complaint complaint, BindingResult bindingResult) {
		// validate input params that can be handled by annotations
		if (bindingResult.hasErrors()) {
			return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);
		}
		return null;
	}

	private ResponseEntity<?> validateComplaintRequestForUpdate(
			org.complaint.persistence.complaint.entity.Complaint complaint, BindingResult bindingResult) {
		// validate input params that can be handled by annotations
		if (bindingResult.hasErrors()) {
			return errorHandler.getErrorResponseEntityForBindingErrors(bindingResult);
		}
		return null;
	}

	public ResponseEntity<?> getSuccessResponseForSearch(
			List<org.complaint.persistence.complaint.entity.Complaint> complaintStatuss) {
		return new ResponseEntity<List<org.complaint.persistence.complaint.entity.Complaint>>(complaintStatuss,
				HttpStatus.OK);
	}

	public ResponseEntity<?> getSuccessResponseForCreate(Complaint complaint) {
		return new ResponseEntity<Complaint>(complaint, HttpStatus.OK);
	}

	public ResponseEntity<?> getSuccessResponseForUpdate(
			org.complaint.persistence.complaint.entity.Complaint complaint) {
		return new ResponseEntity<org.complaint.persistence.complaint.entity.Complaint>(complaint, HttpStatus.OK);
	}

}