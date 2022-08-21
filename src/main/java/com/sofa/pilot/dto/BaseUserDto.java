package com.sofa.pilot.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseUserDto {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String middleName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String birthdate;
}