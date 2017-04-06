package org.complaint.transform;

import org.complaint.web.contract.Complaint;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ComplaintDeserializer extends JsonDeserializer<Complaint> {

	public ComplaintDeserializer() {
		super(Complaint.class);
	}
}
