package com.nisum.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.domain.TestStep;
import com.nisum.domain.TestSuite;
import com.nisum.repositories.ReportRepository;
import com.nisum.service.WebDriverService;




public class HtmlResult {
	
	private ReportRepository reportRepository;
	
	private WebDriverService driverScript;

	public HtmlResult(ReportRepository reportRepository,WebDriverService driverScript){
		this.reportRepository = reportRepository;
		this.driverScript = driverScript;
	}
	
	public String ResFilePath;
	
	private Report report;
	
	//1 st
	public String createReport(String vProjectName,String reportPath) throws Throwable 
	{
		report = new Report();
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
		Date date = new Date();		
		String reportId=dateFormat.format(date);
		report.setReportId(reportId);
		report.setProjectName(vProjectName);
		report.setReportDate(date);
		return reportId;
	}

	public void setTestSuite(String testSuiteName) throws Throwable 
	{	
		TestSuite ts = new TestSuite();
		ts.setTestSuiteName(testSuiteName);
		report.getTestSuites().add(ts);
	}

	
	//@SuppressWarnings("resource")
	public void fgInsertStep(String ResFilePath) throws Throwable 
	{	
		TestCase tc = new TestCase();
		tc.setTestCaseName(driverScript.vTCName);
		tc.setTestCaseDesc(driverScript.vTCDesc);
		report.getTestSuites().get(report.getTestSuites().size()-1).getTestCases().add(tc);
		
	}
	
	@SuppressWarnings("resource")
	public void fgInsertResult(String ResFilePath,String vkeyword,String Expected,String Actual,String Result) throws Throwable
	{
		TestStep ts = new TestStep();
		ts.setAction(vkeyword);
		ts.setExpected(Expected);
		ts.setActual(Actual);
		ts.setStatus(Result);
		TestSuite testSuite = report.getTestSuites().get(report.getTestSuites().size() - 1); 
		testSuite.getTestCases().get(testSuite.getTestCases().size() - 1)
				.getTestSteps().add(ts);
	}

	public void WriteTCTime(String ResFilePath,long tctime) throws Throwable
	{
		TestSuite testSuite = report.getTestSuites().get(report.getTestSuites().size() - 1);
		if(testSuite.getTestCases().size() >0 ){
			testSuite.getTestCases().get(testSuite.getTestCases().size() - 1).setTime(tctime);
		}
	}
	
	public void writeTestSuiteTime(long tsTime) throws Throwable
	{
		TestSuite testSuite = report.getTestSuites().get(report.getTestSuites().size() - 1); 
		testSuite.setTime(tsTime);
	}
	
	public long timeDiffernce(String dateStart,String dateStop,DateFormat dateFormat) throws Throwable
	{
		//HH converts hour in 24 hours format (0-23), day calculation
		Date d1=null;
		Date d2=null;
		d1 = dateFormat.parse(dateStart);
		d2 = dateFormat.parse(dateStop);
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			long diffSeconds = diff / 1000 % 60;
		//	long diffMinutes = diff / (60 * 1000) % 60;
		//	long diffHours = diff / (60 * 60 * 1000) % 24;
		//	long diffDays = diff / (24 * 60 * 60 * 1000);
 
		return diffSeconds;
		
	}

	public void saveReport(){
		reportRepository.save(report);
	}

}	



