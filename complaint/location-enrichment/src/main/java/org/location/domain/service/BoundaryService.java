package org.location.domain.service;

import org.location.domain.model.Boundary;
import org.location.domain.repository.BoundaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoundaryService {

	private BoundaryRepository boundaryRepository;

	@Autowired
	public BoundaryService(BoundaryRepository boundaryRepository) {
		this.boundaryRepository = boundaryRepository;
	}

	public Boundary getByLongitudeAndLatitude(Double longitude, Double latitude) {

		return boundaryRepository.findByLongitudeAndLatitude(longitude, latitude);
	}

}