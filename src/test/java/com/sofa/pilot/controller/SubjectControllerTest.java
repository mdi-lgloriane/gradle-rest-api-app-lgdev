package com.sofa.pilot.controller;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.sofa.pilot.dto.SubjectInput;
import com.sofa.pilot.dto.SubjectOutput;
import com.sofa.pilot.service.CrudService;

class SubjectControllerTest {
	@Mock
	private CrudService<SubjectInput, SubjectOutput> crudService;

	@Test
	void givenCrudServiceWhenConstructorIsInvokedThenCru() {
		SubjectController subjectController = new SubjectController(crudService);

		assertSame(crudService, subjectController.getCrudService());
	}
}
