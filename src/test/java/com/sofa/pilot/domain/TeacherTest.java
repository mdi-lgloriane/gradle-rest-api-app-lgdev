package com.sofa.pilot.domain;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.sofa.pilot.dto.TeacherInput;
import com.sofa.pilot.dto.TeacherOutput;

class TeacherTest {

	@Test
	void givenTeacherWhenGenerateOutputIsInvokedThenTeacherOutputIsCreated() {
		TeacherInput teacherInput = new TeacherInput();
		teacherInput.setFirstName(MockDomain.NAME);
		Teacher teacher = new Teacher(teacherInput);
		teacher.setId(MockDomain.ID);

		TeacherOutput teacherOutput = teacher.generateOutput();

		assertSame(teacherInput.getFirstName(), teacherOutput.getFirstName());
		assertSame(MockDomain.ID, teacherOutput.getId());
	}

	@Test
	void givenTeacherWhenGenerateOutputIsInvokedThenTeacherOutputIdIsNull() {
		Teacher teacher = new Teacher();

		TeacherOutput teacherOutput = teacher.generateOutput();

		assertNull(teacherOutput.getId());
	}
}
