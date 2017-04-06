package org.complaint.persistence.complaint.repository;

import org.complaint.persistence.complaint.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Long> {

	ComplaintStatus findByName(String name);
}
