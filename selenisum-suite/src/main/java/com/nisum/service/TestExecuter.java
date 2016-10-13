package com.nisum.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nisum.action.ActionExecuter;
import com.nisum.action.ExpectedResultAction;
import com.nisum.action.LaunchAppAction;
import com.nisum.domain.PageElement;
import com.nisum.domain.Project;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.domain.TestSuite;
import com.nisum.repositories.ReportRepository;
import com.nisum.util.TestExecutionUtil;
@Component
public class TestExecuter {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private ActionExecuter actionExecuter;
	
	public boolean execute(Project project,WebDriver driver) throws Exception{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ExpectedResultAction expectedResultAction = new ExpectedResultAction();
		Report report = TestExecutionUtil.getIntializedReport();
		
		driver.get(project.getDomainName() + "/" + project.getPageURL());
		 
		for (TestSuite testSuite : project.getTestSuite()) {
			String testSuiteStartTime = dateFormat.format(new Date());
			report.getTestSuites().add(testSuite);
			
			for (TestCase testCase : testSuite.getTestCases()) {
				String testCaseStartTime = dateFormat.format(new Date());
				//Move Launch page
				LaunchAppAction luncahApp = new LaunchAppAction();
				luncahApp.execute(null, driver,testCase);
				
				for (PageElement element : testCase.getPageElement()){
					actionExecuter.execute(element,null,driver,testCase);
				}
				
				boolean result = expectedResultAction.execute(testCase.getExpectedResultElement(), driver,testCase);
				
				System.out.println("Expected Result : " + result +  " LaunchAppAction.counter: " +LaunchAppAction.counter);
				
        		
				String testCaseEndTime=dateFormat.format(new Date());
	    		testCase.setTime(TestExecutionUtil.timeDiffernce(testCaseStartTime, testCaseEndTime, dateFormat));
			}
			String testSuiteEndTime=dateFormat.format(new Date());
    		testSuite.setTime(TestExecutionUtil.timeDiffernce(testSuiteStartTime, testSuiteEndTime, dateFormat));
		}
		reportRepository.save(report);		
		
		return true;
	}
	

}
