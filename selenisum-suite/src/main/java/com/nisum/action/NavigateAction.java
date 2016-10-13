package com.nisum.action;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.util.Option;

public class NavigateAction extends AbstractAction{

	
	@Override
	public boolean execute(PageElement element,WebDriver driver,TestCase testCase) {
		String elementAttribute ="name";
		String elementAttributeValue = element.getPageElementName();
		
		if(element.getPageElementId() != null){
			elementAttribute ="id";
			elementAttributeValue= element.getPageElementId();
		}
		
		List<WebElement> webElemenList = extractObject(elementAttribute, elementAttributeValue, driver);
		//webElemenList.get(0).clear();
		//webElemenList.get(0).sendKeys((element.getPageElementValue()==null?"":element.getPageElementValue()));
		
		webElemenList.get(0).click();
		return true;
	}
	

}
