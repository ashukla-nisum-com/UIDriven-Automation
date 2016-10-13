package com.nisum.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.nisum.domain.PageElement;
import com.nisum.domain.Project;
import com.nisum.domain.TestCase;
import com.nisum.domain.TestSuite;

public class TestExecuterTest {
	
	public static String domainName = "http://www.safeway.com";
	public static String  pageUrl = "ShopStores/OSSO-Login.page";;
	
	
	@Test
	public void testLogin() throws Exception{
		Project pro = new Project();
		List<TestSuite> tsList = new ArrayList<TestSuite>();
		pro.setTestSuite(tsList);
		TestSuite ts = new TestSuite();
		tsList.add(ts);
		
		List<TestCase> tcList = new ArrayList<TestCase>();
		TestCase tc = new TestCase();
		PageElement pe = new PageElement();
		pe.setPageElementType("a");
		pe.setPageElementValue("Sign Out");
		tc.setExpectedResultElement(pe);
		tcList.add(tc);
		ts.setTestCases(tcList);
		
		pro.setDomainName(domainName);
		pro.setPageURL(pageUrl);
		
		
		ts.setTestSuiteName("Login Page Test Suite");
		
		
		
		
		tc.setTestCaseName("Login Page Test Case");
		List<PageElement> pageList = new ArrayList<PageElement>();
		tc.setPageElement(pageList);
		
		PageElement element1 = new PageElement();
		element1.setPageElementId("userId");
		element1.setPageElementType("input");
		element1.setPageElementAttributeType("text");
		element1.setPageElementValue("qatest@gmail.com");
		
		PageElement element2 = new PageElement();
		element2.setPageElementName("fakePassword");
		element2.setPageElementType("input");
		element2.setPageElementAttributeType("text");
		//element2.setPageElementValue("");
		
		PageElement element3 = new PageElement();
		element3.setPageElementId("password");
		element3.setPageElementType("input");
		element3.setPageElementAttributeType("text");
		element3.setPageElementValue("ab12ab12");
		
		PageElement element4 = new PageElement();
		element4.setPageElementId("SignInBtn");
		element4.setPageElementType("a");
		
		
		
		pageList.add(element1);
		//pageList.add(element2);
		pageList.add(element3);
		pageList.add(element4);
		String path = getClass().getClassLoader().getResource("chromedriver.exe").getPath();
		System.setProperty("webdriver.chrome.driver", 
				path);
		WebDriver driver = new ChromeDriver();
		TestExecuter te = new TestExecuter();
		te.execute(pro, driver);
	}

}
