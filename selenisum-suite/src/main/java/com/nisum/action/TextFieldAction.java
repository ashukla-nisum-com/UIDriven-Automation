package com.nisum.action;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.util.Option;

public class TextFieldAction extends AbstractAction{

	

	@Override
	public boolean execute(PageElement element,WebDriver driver,TestCase testCase) {
		// TODO Auto-generated method stub
				String elementAttribute ="name";
				String elementAttributeValue = element.getPageElementName();
				
		try{
		
		if(element.getPageElementId() != null){
			elementAttribute ="id";
			elementAttributeValue= element.getPageElementId();
		}
		
		List<WebElement> webElemenList = extractObject(elementAttribute, elementAttributeValue, driver);
		webElemenList.get(0).clear();
		webElemenList.get(0).sendKeys((element.getPageElementValue()==null?"":element.getPageElementValue()));
		}catch(Exception e){
			testCase.getTestSteps().add(addTestStep("Enter value in text field : " + elementAttributeValue
					, "Value should be entered in text field", "Value Not Entered", "FAIL"));
			return false;
		}
		testCase.getTestSteps().add(addTestStep("Enter value in text field : " + elementAttributeValue
				, "Value should be entered in text field", "Value Entered", "PASS"));
		return true;
	}
	
	

}
