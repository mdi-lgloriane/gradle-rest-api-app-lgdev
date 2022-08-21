package com.sofa.pilot.controller;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.sofa.pilot.dto.StudentInput;
import com.sofa.pilot.dto.StudentOutput;
import com.sofa.pilot.service.CrudService;

class StudentControllerTest {

	@Mock
	private CrudService<StudentInput, StudentOutput> crudService;

	@Test
	void givenCrudServiceWhenConstructorIsInvokedThenCru() {
		StudentController studentController = new StudentController(crudService);

		assertSame(crudService, studentController.getCrudService());
	}
}
