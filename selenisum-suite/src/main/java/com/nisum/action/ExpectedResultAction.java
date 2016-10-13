package com.nisum.action;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;

public class ExpectedResultAction extends AbstractAction {


	@Override
	public boolean execute(PageElement expectedResultElement,WebDriver driver,TestCase testCase) {
		boolean retVal =false;
		try{
		//if("OBJECT_FIND".equals(expectedResultElement.getPageElementType())){
			List<WebElement> webElemenList = extractObject(expectedResultElement.getPageElementType(), expectedResultElement.getPageElementValue(),
					driver);
			if(webElemenList != null && webElemenList.size() >0){
				testCase.getTestSteps().add(addTestStep("Expected Result : Find element : " + expectedResultElement.getPageElementValue()
						, "Element should exist", "Element exist", "PASS"));
				retVal = true;
			} else{
				testCase.getTestSteps().add(addTestStep("Expected Result : Find element : " + expectedResultElement.getPageElementValue()
						, "Element should exist", "Element does not exist", "FAIL"));
			}

		}catch(Exception e){
			testCase.getTestSteps().add(addTestStep("Expected Result : Find element : " + expectedResultElement.getPageElementValue()
					, "Element should exist", "Element does not exist", "FAIL"));
			return false;
		}
		
		//webElemenList.get(0).clear();
		//webElemenList.get(0).sendKeys((element.getPageElementValue()==null?"":element.getPageElementValue()));
		
		
		return retVal;
	}	
	
}
