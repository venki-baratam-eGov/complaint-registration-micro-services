package org.location.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.location.domain.service.BoundaryService;
import org.location.web.contract.Complaint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

public class ComplaintValidatedConsumer {

	@Autowired
	private BoundaryService boundaryService;

	@Autowired
	private LocationPopulatedProducer locationPopulatedProducer;

	@Value("${kafka.topics.complaint.location.populated.name}")
	private String complaintLocationPopulatedTopic;

	@Value("${kafka.topics.complaint.location.populated.key}")
	private String complaintLocationPopulatedKey;

	public static final Logger LOGGER = LoggerFactory.getLogger(ComplaintValidatedConsumer.class);

	@KafkaListener(containerFactory = "kafkaListenerContainerFactory", topics = {
			"${kafka.topics.complaint.validated.name}" })

	public void listen(ConsumerRecord<String, Complaint> record) {
		LOGGER.info("key:" + record.key() + ":" + "value:" + record.value());

		Complaint complaint = populateLocation(record.value());

		locationPopulatedProducer.sendMessage(complaintLocationPopulatedTopic, complaintLocationPopulatedKey,
				complaint);

	}

	private Complaint populateLocation(Complaint complaint) {
		complaint.setLocation(
				boundaryService.getByLongitudeAndLatitude(complaint.getLongitude(), complaint.getLatitude()).getId());

		return complaint;
	}
}
