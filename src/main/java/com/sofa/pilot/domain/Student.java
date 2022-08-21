package com.sofa.pilot.domain;

import org.springframework.beans.BeanUtils;

import com.sofa.pilot.dto.StudentInput;
import com.sofa.pilot.dto.StudentOutput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends BaseUserDomain<StudentOutput> {

	private String grade;
	private String schoolYear;
	private String section;

	public Student() {
	}

	public Student(StudentInput studentInput) {
		BeanUtils.copyProperties(studentInput, this);
	}

	public StudentOutput generateOutput() {
		StudentOutput studentOutput = new StudentOutput();
		BeanUtils.copyProperties(this, studentOutput);
		return studentOutput;
	}
}
