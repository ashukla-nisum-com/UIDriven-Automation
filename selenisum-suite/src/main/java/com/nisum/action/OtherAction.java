package com.nisum.action;

import org.openqa.selenium.WebDriver;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.util.Option;

public class OtherAction extends AbstractAction{

	

	@Override
	public boolean execute(PageElement element,WebDriver driver,TestCase testCase) {
		// TODO Auto-generated method stub
		try{
			
		}catch(Exception e){
			testCase.getTestSteps().add(addTestStep("Enter value in text field : " + "elementAttributeValue"
					, "Value should be entered in text field", "Value Not Entered", "FAIL"));
			return false;
		}
		testCase.getTestSteps().add(addTestStep("Enter value in text field : " + "elementAttributeValue"
				, "Value should be entered in text field", "Value Entered", "PASS"));
		return true;
	}
	

}
