package org.location.domain.repository;

import org.location.domain.model.Boundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoundaryRepository {

	private RestTemplate restTemplate;
	private String locationServiceUrl;

	@Autowired
	public BoundaryRepository(RestTemplate restTemplate, @Value("${location.service.url}") String locationServiceUrl) {

		this.restTemplate = restTemplate;
		this.locationServiceUrl = locationServiceUrl;
	}

	public Boundary findByLongitudeAndLatitude(Double longitude, Double latitude) {
		return restTemplate.getForObject(locationServiceUrl, Boundary.class, longitude, latitude);
	}
}