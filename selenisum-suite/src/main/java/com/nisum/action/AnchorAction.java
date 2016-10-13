package com.nisum.action;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.util.Option;

public class AnchorAction extends AbstractAction{

	
	@Override
	public boolean execute(PageElement element,WebDriver driver,TestCase testCase) {
		String elementAttribute ="name";
		String elementAttributeValue = element.getPageElementName();
		try{
			if(element.getPageElementId() != null){
				elementAttribute ="id";
				elementAttributeValue= element.getPageElementId();
			}

			List<WebElement> webElemenList = extractObject(elementAttribute, elementAttributeValue, driver);
			//webElemenList.get(0).clear();
			//webElemenList.get(0).sendKeys((element.getPageElementValue()==null?"":element.getPageElementValue()));

			webElemenList.get(0).click();
		}catch(Exception e){
			testCase.getTestSteps().add(addTestStep("Link click: " + elementAttributeValue
					, "Link should be clicked", "Link Not Clicked", "FAIL"));
			return false;
		}
		testCase.getTestSteps().add(addTestStep("Link click: " + elementAttributeValue
				, "Link should be clicked", "Link Clicked", "PASS"));
		return true;
	}
	

}
