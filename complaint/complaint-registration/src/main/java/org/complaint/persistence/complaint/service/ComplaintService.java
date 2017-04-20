
package org.complaint.persistence.complaint.service;

import java.util.Date;
import java.util.List;

import org.complaint.domain.service.CrnGeneratorService;
import org.complaint.kafka.ComplaintValidatedProducer;
import org.complaint.persistence.complaint.entity.Complaint;
import org.complaint.persistence.complaint.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ComplaintService {

	@Autowired
	private CrnGeneratorService crnGeneratorService;

	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private ComplaintTypeService complaintTypeService;

	@Autowired
	private ComplaintStatusService complaintStatusService;

	@Value("${kafka.topics.complaint.validated.name}")
	private String complaintValidatedTopic;

	@Value("${kafka.topics.complaint.validated.key}")
	private String complaintValidatedKey;

	@Autowired
	private ComplaintValidatedProducer complaintValidatedProducer;

	public Complaint getById(Long id) {

		return complaintRepository.findOne(id);
	}

	public List<Complaint> getAll() {

		return complaintRepository.findAll();
	}

	public org.complaint.web.contract.Complaint push(final org.complaint.web.contract.Complaint complaint) {

		populateCrn(complaint);

		setAuditableFields(complaint);

		try {
			complaintValidatedProducer.sendMessage(complaintValidatedTopic, complaintValidatedKey, complaint);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return complaint;
	}

	@Transactional
	public Complaint save(final Complaint complaint) {

		populateComplaintType(complaint);

		populateComplaintStatus(complaint);

		complaintRepository.save(complaint);

		return complaint;
	}

	@Transactional
	public Complaint update(Complaint complaint) {

		Complaint newComplaint = complaintRepository.getOne(complaint.getId());

		newComplaint.setComments(complaint.getComments());

		updateComplaintStatus(newComplaint, complaint.getStatus().getName());

		complaintRepository.save(newComplaint);

		return newComplaint;
	}

	private void updateComplaintStatus(Complaint complaint, String name) {
		if (name != null && !name.isEmpty())
			complaint.setStatus(complaintStatusService.getByName(name));
	}

	private void populateComplaintStatus(Complaint complaint) {
		if (complaint.getStatus() != null && complaint.getStatus().getName() != null
				&& !complaint.getStatus().getName().isEmpty())
			complaint.setStatus(complaintStatusService.getByName(complaint.getStatus().getName()));

	}

	private void populateComplaintType(Complaint complaint) {

		if (complaint.getComplaintType() != null && complaint.getComplaintType().getCode() != null
				&& !complaint.getComplaintType().getCode().isEmpty())
			complaint.setComplaintType(complaintTypeService.getByCode(complaint.getComplaintType().getCode()));

	}

	private void populateCrn(org.complaint.web.contract.Complaint complaint) {
		complaint.setCrn(crnGeneratorService.generate());
	}

	private void setAuditableFields(final org.complaint.web.contract.Complaint complaint) {
		if (complaint.getCreatedBy() == null)
			complaint.setCreatedBy(1L);
		if (complaint.getCreatedDate() == null)
			complaint.setCreatedDate(new Date());
		complaint.setLastModifiedDate(new Date());
		complaint.setLastModifiedBy(1L);
	}

}
