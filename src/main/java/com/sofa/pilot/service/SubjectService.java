package com.sofa.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.sofa.pilot.domain.Subject;
import com.sofa.pilot.dto.SubjectInput;
import com.sofa.pilot.dto.SubjectOutput;

@Service
public class SubjectService extends BaseCrudService<Subject, SubjectInput, SubjectOutput> {

	@Autowired
	public SubjectService(MongoRepository<Subject, String> mongoRepository) {
		super(mongoRepository, Subject.class);
	}
}
