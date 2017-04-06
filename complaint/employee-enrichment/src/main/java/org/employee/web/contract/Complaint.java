package org.employee.web.contract;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
@ToString
public class Complaint {
	private Long id;

	private String crn = "";

	private ComplaintType complaintType;

	private Complainant complainant = new Complainant();

	private ComplaintStatus status;

	private Long assignee;

	private Long location;

	private String details;

	private String landmarkDetails;

	private Double longitude;

	private Double latitude;

	private Long department;

	private String comments;

	private Long createdBy;

	private Date createdDate;

	private Long lastModifiedBy;

	private Date lastModifiedDate;

}