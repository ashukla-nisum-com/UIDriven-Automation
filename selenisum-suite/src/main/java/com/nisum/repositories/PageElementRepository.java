package com.nisum.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nisum.domain.PageElement;

@Repository
public interface PageElementRepository extends MongoRepository<PageElement, String> {

}
