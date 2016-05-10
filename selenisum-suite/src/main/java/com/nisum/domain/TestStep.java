package com.nisum.domain;

public class TestStep {

	private String testStepId;
	private String testStepName;
	private String action;
	private String expected;
	private String actual;
	private String status;

	public String getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(String testStepId) {
		this.testStepId = testStepId;
	}

	public String getTestStepName() {
		return testStepName;
	}

	public void setTestStepName(String testStepName) {
		this.testStepName = testStepName;
	}

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
