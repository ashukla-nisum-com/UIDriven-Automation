package com.nisum.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.domain.TestSuite;
import com.nisum.repositories.TestSuiteRepository;

@RestController
@RequestMapping("/testsuites")
public class TestSuiteController {
	private TestSuiteRepository testSuiteRepository;

	@Autowired
	public TestSuiteController(TestSuiteRepository testsuiteRepository) {
		this.testSuiteRepository = testsuiteRepository;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<TestSuite> create(@RequestBody TestSuite TestSuite) {
		TestSuite testSuite = testSuiteRepository.save(TestSuite);
		return new ResponseEntity<TestSuite>(testSuite, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TestSuite>> list() {
		List<TestSuite> lstTestSuite = this.testSuiteRepository.findAll();
		return new ResponseEntity(lstTestSuite, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<TestSuite>> get(@RequestParam("id") String id) {
		TestSuite testsuite = this.testSuiteRepository.findOne(id);
		return new ResponseEntity(testsuite, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<TestSuite> update(@RequestParam("id") long id, @RequestBody @Valid TestSuite TestSuite) {
		TestSuite testsuite = testSuiteRepository.save(TestSuite);
		return new ResponseEntity<TestSuite>(testsuite, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@RequestParam("id") String id) {
		this.testSuiteRepository.delete(id);
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
}
