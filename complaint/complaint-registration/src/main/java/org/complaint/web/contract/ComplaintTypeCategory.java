package org.complaint.web.contract;

import java.util.List;

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
public class ComplaintTypeCategory {
	private Long id;

	private String name;

	private String description;

	private List<ComplaintType> complaintTypes;

}
