package com.sofa.pilot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sofa.pilot.domain.Subject;

public interface SubjectRepository extends MongoRepository<Subject, String> {
}
