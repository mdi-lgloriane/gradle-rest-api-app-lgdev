package com.sofa.pilot.controller;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.sofa.pilot.dto.TeacherInput;
import com.sofa.pilot.dto.TeacherOutput;
import com.sofa.pilot.service.CrudService;

class TeacherControllerTest {

	@Mock
	private CrudService<TeacherInput, TeacherOutput> crudService;

	@Test
	void givenTeacherControllerWhenConstructorIsInvokedThenCrudServiceIsSaved() {
		TeacherController teacherController = new TeacherController(crudService);

		assertSame(crudService, teacherController.getCrudService());
	}
}
