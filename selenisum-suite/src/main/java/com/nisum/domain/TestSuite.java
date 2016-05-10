package com.nisum.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TestSuite")
public class TestSuite {

	@Id
	private String testSuiteId;
	private String testSuiteName;
	private String testSuiteDesc;
	private String pageName;
	
	private List<TestCase> testCases;
	private Long time;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(String testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public String getTestSuiteDesc() {
		return testSuiteDesc;
	}

	public void setTestSuiteDesc(String testSuiteDesc) {
		this.testSuiteDesc = testSuiteDesc;
	}

	public List<TestCase> getTestCases() {
		if (this.testCases == null) {
			this.testCases = new ArrayList<TestCase>();
		}

		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

}
