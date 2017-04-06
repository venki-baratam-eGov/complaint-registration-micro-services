package org.complaint.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.complaint.persistence.complaint.service.ComplaintService;
import org.complaint.web.contract.Complaint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class EmployeePopulatedConsumer {

	@Autowired
	private ComplaintService complaintService;

	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeePopulatedConsumer.class);

	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {
			"${kafka.topics.complaint.employee.populated.name}" })

	public void listen(ConsumerRecord<String, Complaint> record) {
		LOGGER.info("key:" + record.key() + ":" + "value:" + record.value());
		complaintService.save(new org.complaint.persistence.complaint.entity.Complaint(record.value()));
	}
}
