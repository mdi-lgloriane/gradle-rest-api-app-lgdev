package com.sofa.pilot.domain;

import org.springframework.beans.BeanUtils;

import com.sofa.pilot.dto.TeacherInput;
import com.sofa.pilot.dto.TeacherOutput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher extends BaseUserDomain<TeacherOutput> {

	private String department;

	public Teacher() {
	}

	public Teacher(TeacherInput teacherInput) {
		BeanUtils.copyProperties(teacherInput, this);
	}

	@Override
	public TeacherOutput generateOutput() {
		TeacherOutput teacherOutput = new TeacherOutput();
		BeanUtils.copyProperties(this, teacherOutput);
		return teacherOutput;
	}
}
