package com.nisum.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "report")
public class Report {

	@Id
	private String reportId;
	private String reportName;
	private String projectName;
	private Date reportDate;
	private List<TestSuite> testSuites;

	public List<TestSuite> getTestSuites() {
		if (this.testSuites == null) {
			this.testSuites = new ArrayList<TestSuite>();
		}
		return testSuites;
	}

	public void setTestSuite(List<TestSuite> testSuites) {

		this.testSuites = testSuites;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
