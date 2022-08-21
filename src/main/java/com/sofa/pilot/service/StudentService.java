package com.sofa.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.sofa.pilot.domain.Student;
import com.sofa.pilot.dto.StudentInput;
import com.sofa.pilot.dto.StudentOutput;

@Service
public class StudentService extends BaseCrudService<Student, StudentInput, StudentOutput> {

	@Autowired
	public StudentService(MongoRepository<Student, String> mongoRepository) {
		super(mongoRepository, Student.class);
	}
}
