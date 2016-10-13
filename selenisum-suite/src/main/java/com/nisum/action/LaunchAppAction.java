package com.nisum.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.util.Option;

public class LaunchAppAction extends AbstractAction{

	public static int counter =0;
	
	@Override
	public boolean execute(PageElement element,WebDriver driver,TestCase testCase) {
		counter++;
		boolean retVal =true;
		
		try{
			Window window = driver.manage().window();
			if(window != null){
				window.maximize();
			}
		}catch(Exception e){
			testCase.getTestSteps().add(addTestStep("Launch Browser " 
					, "Browser should be launched", "Browser not launched", "FAIL"));
			return false;
		}
		testCase.getTestSteps().add(addTestStep("Launch Browser " 
				, "Browser should be launched", "Browser launched", "PASS"));
		return retVal;
	}
	

}
