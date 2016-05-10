package com.nisum.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class TestCase {

	@Id
	private String testCaseId;
	private String testCaseName;
	private String testCaseDesc;
	private List<TestStep> testSteps;
	private List<PageElement> pageElement;
	private Long time;
	private String expectedResult;
	private String pageName;
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getTestCaseDesc() {
		return testCaseDesc;
	}

	public void setTestCaseDesc(String testCaseDesc) {
		this.testCaseDesc = testCaseDesc;
	}

	public List<TestStep> getTestSteps() {
		if (this.testSteps == null) {
			this.testSteps = new ArrayList<TestStep>();
		}

		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

	public List<PageElement> getPageElement() {
		return pageElement;
	}

	public void setPageElement(List<PageElement> pageElement) {
		this.pageElement = pageElement;
	}
}
