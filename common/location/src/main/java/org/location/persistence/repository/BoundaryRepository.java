

package org.location.persistence.repository;

import org.location.persistence.entity.Boundary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoundaryRepository extends JpaRepository<Boundary, Long> {

	Boundary findByLongitudeAndLatitude(Double longitude, Double latitude);
}
