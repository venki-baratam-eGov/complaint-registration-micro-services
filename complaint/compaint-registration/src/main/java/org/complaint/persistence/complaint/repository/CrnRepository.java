

package org.complaint.persistence.complaint.repository;

import org.complaint.domain.model.ComplaintRegistrationNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrnRepository {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${crn.service.url}")
	private String crnServiceUrl;

	public ComplaintRegistrationNumber getCrn() {
		return restTemplate.getForObject(crnServiceUrl, ComplaintRegistrationNumber.class);
	}
}