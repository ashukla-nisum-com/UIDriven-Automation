package com.nisum.action;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.domain.TestStep;
import com.nisum.util.Option;

public abstract class AbstractAction {
		
	public abstract boolean execute(PageElement element,WebDriver driver,TestCase testCase) ;
	
	public  List<WebElement> extractObject(String elementAttribute,String elementAttributeValue,WebDriver driver)
	{
		List<WebElement> vFindObj = null;
		
		//String[] arr=vObjString.split("@@@");	
		switch(elementAttribute)
		{
		case "xpath":
			vFindObj=driver.findElements(By.xpath(elementAttributeValue));
			break;
		case "id":
			vFindObj=driver.findElements(By.id(elementAttributeValue));
			break;
		case "name":
			vFindObj=driver.findElements(By.name(elementAttributeValue));
			break;
		case "a": //linktext
			vFindObj=driver.findElements(By.linkText(elementAttributeValue));
			break;
		case "partialLinkText":
			vFindObj=driver.findElements(By.partialLinkText(elementAttributeValue));
			break;
		case "cssSelector":
			vFindObj=driver.findElements(By.cssSelector(elementAttributeValue));
			break;
		}	
		return vFindObj;	
	}	

	public TestStep addTestStep(String action,String expected,String actual,String result) 	{
		TestStep ts = new TestStep();
		ts.setAction(action);
		ts.setExpected(expected);
		ts.setActual(actual);
		ts.setStatus(result);
		return ts;
	}

}
