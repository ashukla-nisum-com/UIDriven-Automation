package com.nisum.util;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.nisum.service.WebDriverService;

public class Generic_Functions {
	
	private WebDriverService driverScript;
	
	public Generic_Functions(WebDriverService driverScript){
		this.driverScript = driverScript;
	}
	
	public  void keywordDriver(String vKeyword,Xls_Reader xtd,String vModuleName,int m,HtmlResult hr) throws Throwable
	{
		String vObjExclValue,ObjString,vExp,vVal;
		int vObjCnt,vObjIndex;
		List<WebElement> vObjAct;
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(WebDriverService.getPath("/Objects/Objects.properties"));
		prop.load(fis);
		switch (vKeyword)
		{
		case "Fn_LaunchApp":
			
			boolean retVal = Fn_LaunchApp();
			writeLaunchAppinReport(retVal,hr);
			break;
		case "Fn_Wait":
			Fn_Wait();
			break;	
		case "Fn_VerifyTitle":
			vExp=xtd.getCellData(vModuleName, "Expected", m);
			Fn_VerifyTitle(vExp,hr);
			break;
		case "Fn_ObjectExist":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);					
			ObjString=prop.getProperty(vObjExclValue);
			vObjCnt=ObjectExist(ObjString);
			Fn_ObjectExist(vObjCnt,hr);
			break;
		case "Fn_SetValue":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vVal=xtd.getCellData(vModuleName, "Value", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			Fn_SetValue(vObjAct,vVal,vObjCnt,vObjIndex,hr);
			break;
		
		case "Fn_SelectValue":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vVal=xtd.getCellData(vModuleName, "Value", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			Fn_SelectValue(vObjAct,vVal,vObjCnt,vObjIndex,hr);
			break;
		case "Fn_Click":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));	
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			Fn_Click(vObjAct,vObjCnt,vObjIndex,hr);
			break;
		case "Fn_MouseMove":			
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));	
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			 Fn_MouseMove(vObjAct,vObjCnt,hr);	
			break;
		default:
			System.out.println("Wrong Keyword");
		}
	}
	
	public  void Fn_Wait()
	{
		driverScript.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public  void Fn_VerifyTitle(String vExp,HtmlResult hr) throws Throwable
	{
		String actTitle=driverScript.driver.getTitle();
		if(actTitle.equals(vExp))
		{
			System.out.println("PASS");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_VerifyTitle", "Title should be :"+vExp, "Title matched successfully :"+actTitle, "PASS");
		}
		else
		{
			System.out.println("FAIL");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_VerifyTitle", "Title should be :"+vExp, "Title not matched :"+actTitle, "FAIL");
		}
	}
	
	public  boolean Fn_LaunchApp()
	{
		boolean retVal = false;
		driverScript.driver.get(driverScript.vProjectUrl);
		Window window = driverScript.driver.manage().window();
		if(window != null){
			window.maximize();
			retVal = true;
		}
		return retVal;
		//WebDriverService.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public  void writeLaunchAppinReport(boolean isLaunched,HtmlResult hr) throws Throwable
	{
		if(isLaunched){
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_LaunchApp", "Browser Should be launched",
					"Brower launched successfully", "PASS");
		}else{
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_LaunchApp", "Browser Should be launched",
					"Brower Not launched ", "FAIL");
		}
	}
	
	
	
	public  void Fn_ObjectExist(int vObjCnt,HtmlResult hr) throws Throwable
	{
		if(vObjCnt==1)
		{
			System.out.println("PASS");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_ObjectExist", "Object should be exist", "Object exist", "PASS");
		}
		else
		{
			System.out.println("FAIL");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_ObjectExist", "Object should be exist", "Object does not exist", "FAIL");
		}
	}
	
	public  void Fn_SetValue(List<WebElement> vObj,String vVal,int vobjCnt,int vObjIndex,HtmlResult hr) throws Throwable
	{
		if(vobjCnt>0)
		{
			vObj.get(vObjIndex).clear();
			vObj.get(vObjIndex).sendKeys(vVal);
			System.out.println("PASS");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_SetValue", vVal+": should be entered successfully", "Value entered successfully", "PASS");
		}
		else
		{
			System.out.println("FAIL");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_SetValue", vVal+": should be entered successfully", "Object does not found", "FAIL");
		}
		
	}
	
		public  void Fn_SelectValue(List<WebElement> vObj,String vVal,int vobjCnt,int vObjIndex,HtmlResult hr) throws Throwable
	{
		if(vobjCnt>0)
		{
			
			vObj.get(vObjIndex).sendKeys(vVal);
			System.out.println("PASS");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_SelectValue", vVal+": should be selected successfully", "Value selected successfully", "PASS");
		}
		else
		{
			System.out.println("FAIL");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_SelectValue", vVal+": should be selected successfully", "Object does not found", "FAIL");
		}
		
	}
	
	public  void Fn_Click(List<WebElement> vObj,int vobjCnt,int objIndex,HtmlResult hr) throws Throwable
	{
		if(vobjCnt>0)
		{
			
			vObj.get(objIndex).click();
			System.out.println("PASS");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_Click", "object should be clickable", "Object selected successfully", "PASS");
		}
		else
		{
			System.out.println("FAIL");
			hr.fgInsertResult(driverScript.ResFilePath, "Fn_Click", "object should be clickable", "Object does not found", "FAIL");

		}
		
	}
	public  void Fn_MouseMove(List<WebElement> vObj,int vObjCnt,HtmlResult hr) throws Throwable
	{						
		if(vObjCnt>0)
		{
			Actions act=new Actions(driverScript.driver);
			act.moveToElement((WebElement) vObj).perform();			
			Thread.sleep(3000);
			System.out.println("Pass");
			hr.fgInsertResult(driverScript.ResFilePath, "Move Mouse", "Expected", "Actual", "PASS");
		}
		else
		{
			System.out.println("Fail");
			hr.fgInsertResult(driverScript.ResFilePath, "Move Mouse", "Expected", "Actual", "FAIL");
		}			
	}


	public  int ObjectExist(String vObjString)
	{
		int vObjCnt=0;			
		String[] arr=vObjString.split("@@@");	
		switch(arr[0])
		{
		case "xpath":
			vObjCnt=driverScript.driver.findElements(By.xpath(arr[1])).size();
			break;
		case "id":
			vObjCnt=driverScript.driver.findElements(By.id(arr[1])).size();
			break;
		case "name":
			vObjCnt=driverScript.driver.findElements(By.name(arr[1])).size();
			break;
		case "linkText":
			vObjCnt=driverScript.driver.findElements(By.linkText(arr[1])).size();
			break;
		case "partialLinkText":
			vObjCnt=driverScript.driver.findElements(By.partialLinkText(arr[1])).size();
			break;
		case "cssSelector":
			vObjCnt=driverScript.driver.findElements(By.cssSelector(arr[1])).size();
			break;	
		}		
		return vObjCnt;
	}

	
	public  List<WebElement> ExtractObject(String vObjString)
	{
		List<WebElement> vFindObj = null;
		
		String[] arr=vObjString.split("@@@");	
		switch(arr[0])
		{
		case "xpath":
			vFindObj=driverScript.driver.findElements(By.xpath(arr[1]));
			break;
		case "id":
			vFindObj=driverScript.driver.findElements(By.id(arr[1]));
			break;
		case "name":
			vFindObj=driverScript.driver.findElements(By.name(arr[1]));
			break;
		case "linkText":
			vFindObj=driverScript.driver.findElements(By.linkText(arr[1]));
			break;
		case "partialLinkText":
			vFindObj=driverScript.driver.findElements(By.partialLinkText(arr[1]));
			break;
		case "cssSelector":
			vFindObj=driverScript.driver.findElements(By.cssSelector(arr[1]));
			break;
		}	
		
		
		
		return vFindObj;
	}



}

