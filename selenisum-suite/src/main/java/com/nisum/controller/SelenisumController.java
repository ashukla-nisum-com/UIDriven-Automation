package com.nisum.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.util.JSON;
import com.nisum.domain.PageElement;
import com.nisum.domain.Project;
import com.nisum.domain.Report;
import com.nisum.service.WebDriverService;
import com.nisum.util.TestScenarios;
import com.thoughtworks.selenium.webdriven.commands.Type;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
public class SelenisumController {

	@Autowired
	private ServletContext context;

	@Autowired
	private WebDriverService driverScript;

	@ApiOperation(value = "Service brings all the Pageelemnts and tag names")
	@RequestMapping(value = "/getElements", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<PageElement>> getElements(@ApiParam(value = "input") 
											@RequestParam("input") String input) {
		input = URLDecoder.decode(input);
		WebDriver browser = new FirefoxDriver();
		browser.get(input);

		List<WebElement> link = browser.findElements(By.xpath("//*[@id]"));

		List<PageElement> pageElements = new ArrayList<PageElement>();
		for (WebElement ele : link) {
			PageElement element = new PageElement();
			element.setPageElementId(ele.getAttribute("id"));
			element.setPageElementType(ele.getTagName());
			pageElements.add(element);
		}

		return new ResponseEntity<List<PageElement>>(pageElements, HttpStatus.OK);
	}

	@ApiOperation(value = "Service brings all the Test Scenarios")
	@RequestMapping(value = "/getTestScenario", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> getTestScenario(@ApiParam(value = "input")  @RequestParam("input") String input) throws Exception {
		return new ResponseEntity<String>(TestScenarios.getTestSuite(), HttpStatus.OK);
	}

	@ApiOperation(value = "Service execuites the Tests")
	@RequestMapping(value = "/executeTest", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Project> executeTest(@ApiParam(value = "input") @RequestBody Project project) {
		String report = "No Report Generated";
		System.out.println(project);
		try {
			String reportPath = context.getRealPath("");

			report = driverScript.main(project + "Results");

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@ApiOperation(value = "Service brings executed test reports")
	@RequestMapping(value = "/report", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Report>> getReports(@ApiParam(value = "input")  @RequestParam("input") String input) throws Exception {
		Iterable<Report> reportItr = driverScript.getReports();
		List<Report> reports = new ArrayList<Report>();
		reportItr.forEach(report -> reports.add(report));
		return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
	}

}
