package com.sofa.pilot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseStudentDto extends BaseUserDto {
	private String grade;
	private String schoolYear;
	private String section;
}
