

package org.location.persistence.service;

import java.util.List;

import org.location.persistence.entity.Boundary;
import org.location.persistence.repository.BoundaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BoundaryService {

	@Autowired
	private BoundaryRepository boundaryRepository;

	public Boundary getByLongitudeAndLatitude(Double longitude, Double latitude) {

		return boundaryRepository.findByLongitudeAndLatitude(longitude, latitude);
	}

	public List<Boundary> getAll() {

		return boundaryRepository.findAll();
	}

}
