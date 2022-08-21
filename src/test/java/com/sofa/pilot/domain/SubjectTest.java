package com.sofa.pilot.domain;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.sofa.pilot.dto.SubjectInput;
import com.sofa.pilot.dto.SubjectOutput;

class SubjectTest {

	@Test
	void givenSubjectWhenGenerateOutputIsInvokedThenSubjectOutputIsCreated() {
		SubjectInput subjectInput = new SubjectInput();
		subjectInput.setName(MockDomain.NAME);
		Subject subject = new Subject(subjectInput);
		subject.setId(MockDomain.ID);

		SubjectOutput subjectOutput = subject.generateOutput();

		assertSame(subjectInput.getName(), subjectOutput.getName());
		assertSame(MockDomain.ID, subjectOutput.getId());
	}

	@Test
	void givenSubjectWhenGenerateOutputIsInvokedThenSubjectOutputIdIsNull() {
		Subject subject = new Subject();

		SubjectOutput subjectOutput = subject.generateOutput();

		assertNull(subjectOutput.getId());
	}
}
