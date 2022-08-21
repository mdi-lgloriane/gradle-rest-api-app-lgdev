package com.sofa.pilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofa.pilot.dto.SubjectInput;
import com.sofa.pilot.dto.SubjectOutput;
import com.sofa.pilot.service.CrudService;

@RestController
@RequestMapping(path = "/api/subjects")
public class SubjectController extends BaseCrudController<SubjectInput, SubjectOutput> {

	@Autowired
	public SubjectController(CrudService<SubjectInput, SubjectOutput> crudService) {
		super(crudService);
	}
}
