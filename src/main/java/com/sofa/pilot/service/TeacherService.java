package com.sofa.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.sofa.pilot.domain.Teacher;
import com.sofa.pilot.dto.TeacherInput;
import com.sofa.pilot.dto.TeacherOutput;

@Service
public class TeacherService extends BaseCrudService<Teacher, TeacherInput, TeacherOutput> {

	@Autowired
	public TeacherService(MongoRepository<Teacher, String> mongoRepository) {
		super(mongoRepository, Teacher.class);
	}
}