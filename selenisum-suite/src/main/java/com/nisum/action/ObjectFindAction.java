package com.nisum.action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.nisum.domain.PageElement;
import com.nisum.domain.Report;
import com.nisum.domain.TestCase;

public class ObjectFindAction extends AbstractAction{



	@Override
	public boolean execute(PageElement element,WebDriver driver,TestCase testCase) {
		try{
			int i = ObjectExist(element, driver);
			
			if(i > 0 ){
				testCase.getTestSteps().add(addTestStep("Find element : " + element.getPageElementType()
						, "Element should exist", "Element exist", "PASS"));
			}else{
				testCase.getTestSteps().add(addTestStep("Find element : " + element.getPageElementType()
						, "Element should exist", "Element does not exist", "FAIL"));
			}

		}catch(Exception e){
			testCase.getTestSteps().add(addTestStep("Find element : " + element.getPageElementType()
					, "Element should exist", "Element does not exist", "FAIL"));
			return false;
		}
		
		
		return true;
	}

	
	public  int ObjectExist(PageElement element,WebDriver driver)
	{
		int vObjCnt=0;			
		switch(element.getPageElementType())
		{
		/*case "xpath":
			//vObjCnt=driver.findElements(By.xpath(arr[1])).size();
			break;
		case "id":
			vObjCnt=driver.findElements(By.id(element.getPageElementId())).size();
			break;
		case "name":
			vObjCnt=driver.findElements(By.name(element.getPageElementName())).size();
			break;
		case "linkText":
			vObjCnt=driver.findElements(By.linkText(arr[1])).size();
			break;
		case "partialLinkText":
			vObjCnt=driver.findElements(By.partialLinkText(arr[1])).size();
			break;
		case "cssSelector":
			vObjCnt=driver.findElements(By.cssSelector(arr[1])).size();
			break;*/	
		case "div":
			vObjCnt=driver.findElements(By.id(element.getPageElementId())).size();
			break;
		case "a":
			vObjCnt=driver.findElements(By.linkText(element.getPageElementType())).size();
			break;
		}		
		return vObjCnt;
	}

}
