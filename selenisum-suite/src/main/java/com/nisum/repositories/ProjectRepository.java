package com.nisum.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nisum.domain.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
