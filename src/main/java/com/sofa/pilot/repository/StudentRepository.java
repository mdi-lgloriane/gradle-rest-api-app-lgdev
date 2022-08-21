package com.sofa.pilot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sofa.pilot.domain.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
}
