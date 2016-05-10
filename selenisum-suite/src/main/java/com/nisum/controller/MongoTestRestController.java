package com.nisum.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** To be deleted later.Used for Testing **/

@RestController
@RequestMapping(value = "/mongo")
public class MongoTestRestController {

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<Map> getGreeting(@PathVariable String name) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("name", name);
		System.out.println("GET:" + name);
		return new ResponseEntity<Map>(m, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/postjson", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> postJsonTest(@RequestBody(required = true) String strPostRequest) {

		String strPost = strPostRequest;

		System.out.println("POST:" + strPost);

		return new ResponseEntity<String>(strPost, HttpStatus.CREATED);
	}

}