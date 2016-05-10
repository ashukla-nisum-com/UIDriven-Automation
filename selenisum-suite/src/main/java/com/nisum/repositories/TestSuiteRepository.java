package com.nisum.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nisum.domain.TestSuite;

@Repository
public interface TestSuiteRepository extends MongoRepository<TestSuite, String> {

}
