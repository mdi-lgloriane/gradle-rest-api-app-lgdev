package com.sofa.pilot.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseDomain<O> implements Domain<O> {

	@Id
	private String id;
	private String createdBy;
	private String dateCreated;
	private String modifiedBy;
	private String dateModified;
}
