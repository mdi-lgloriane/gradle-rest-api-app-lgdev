package com.sofa.pilot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sofa.pilot.domain.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
}
