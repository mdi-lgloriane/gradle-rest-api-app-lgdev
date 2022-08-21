package com.sofa.pilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofa.pilot.dto.StudentInput;
import com.sofa.pilot.dto.StudentOutput;
import com.sofa.pilot.service.CrudService;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController extends BaseCrudController<StudentInput, StudentOutput> {

	@Autowired
	public StudentController(CrudService<StudentInput, StudentOutput> crudService) {
		super(crudService);
	}
}