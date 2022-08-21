package com.sofa.pilot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseTeacherDto extends BaseUserDto {
	private String department;
}
