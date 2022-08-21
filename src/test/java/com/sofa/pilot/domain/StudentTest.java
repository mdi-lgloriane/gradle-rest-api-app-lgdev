package com.sofa.pilot.domain;

import static com.sofa.pilot.domain.MockDomain.ID;
import static com.sofa.pilot.domain.MockDomain.NAME;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.sofa.pilot.dto.StudentInput;
import com.sofa.pilot.dto.StudentOutput;

class StudentTest {

	@Test
	void givenStudentWhenGenerateOutputIsInvokedThenStudentOutputIsCreated() {
		StudentInput studentInput = new StudentInput();
		studentInput.setFirstName(NAME);
		Student student = new Student(studentInput);
		student.setId(ID);

		StudentOutput studentOutput = student.generateOutput();

		assertSame(studentInput.getFirstName(), studentOutput.getFirstName());
		assertSame(ID, studentOutput.getId());
	}

	@Test
	void givenStudentWhenGenerateOutputIsInvokedThenStudentOutputIdIsNull() {
		Student student = new Student();

		StudentOutput studentOutput = student.generateOutput();

		assertNull(studentOutput.getId());
	}
}
