package com.sofa.pilot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseUserDomain<O> extends BaseDomain<O> {
	private String firstName;
	private String middleName;
	private String lastName;
	private String birthdate;
}
