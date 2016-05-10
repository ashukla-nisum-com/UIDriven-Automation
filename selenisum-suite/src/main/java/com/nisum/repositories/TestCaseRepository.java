package com.nisum.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nisum.domain.TestCase;

@Repository
public interface TestCaseRepository extends MongoRepository<TestCase, String> {

}
