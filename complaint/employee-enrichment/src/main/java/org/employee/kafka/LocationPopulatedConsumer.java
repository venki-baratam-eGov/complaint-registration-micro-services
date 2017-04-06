package org.employee.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.employee.domain.service.EmployeeService;
import org.employee.web.contract.Complaint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

public class LocationPopulatedConsumer {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeePopulatedProducer employeePopulatedProducer;

	@Value("${kafka.topics.complaint.employee.populated.name}")
	private String complaintEmployeePopulatedTopic;

	@Value("${kafka.topics.complaint.employee.populated.key}")
	private String complaintEmployeePopulatedKey;

	public static final Logger LOGGER = LoggerFactory.getLogger(LocationPopulatedConsumer.class);

	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {
			"${kafka.topics.complaint.location.populated.name}" })

	public void listen(ConsumerRecord<String, Complaint> record) {
		LOGGER.info("key:" + record.key() + ":" + "value:" + record.value());

		Complaint complaint = populateAssignee(record.value());

		employeePopulatedProducer.sendMessage(complaintEmployeePopulatedTopic, complaintEmployeePopulatedKey,
				complaint);

	}

	private Complaint populateAssignee(org.employee.web.contract.Complaint complaint) {
		complaint.setAssignee(employeeService.getById(1L).getId());

		return complaint;
	}
}
