package com.sofa.pilot.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseSubjectDto {

	@NotEmpty
	private String name;
	@NotEmpty
	private String grade;
	@NotEmpty
	private String description;
}
