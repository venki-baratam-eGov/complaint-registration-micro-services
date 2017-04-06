package org.location.web.contract;

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
public class ComplaintType {

	private Long id;

	private String name;

	private String code;

	private Long department;

	private String description;

	private Boolean active;

	private Long createdBy;

	private Date createdDate;

	private Long lastModifiedBy;

	private Date lastModifiedDate;

	private ComplaintTypeCategory category;

}
