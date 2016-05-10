package com.nisum.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



import java.util.stream.Stream;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nisum.service.WebDriverService;

public class TestScenarios {

	public static String vProjectUrl, ResFilePath, vProjectName, vModuleName,
			vTCName, vTCDesc;
	public static int failval, Cntflag, passval, failcnt, actfails;

	public static void main(String[] arg) throws Exception {
		System.out.println(getTestSuite());
	}

	public static String getTestSuite() throws Exception {
		String vProjectRun, vBrowserType, vDBName, vDBuser, vDBpassword, vModulesRun, vModuleFiles, vTestDataFiles, vTCRun, vTDSteps, vKeyword;
		int ProjectCnt, ModuleCnt, vTCCnt, vTDCnt, flag, vRowNum;
		JsonObject jObjectMain = new JsonObject();

		Xls_Reader xr = new Xls_Reader(
				WebDriverService.getPath("/DriverFiles/ProjectDriver.xlsx"));
		ProjectCnt = xr.getRowCount("Projects");
		for (int i = 2; i <= ProjectCnt; i++) {
			vProjectRun = xr.getCellData("Projects", "Run", i);
			if (vProjectRun.equalsIgnoreCase("ON")) {
				vProjectName = xr.getCellData("Projects", "ProjectName", i);
				vProjectUrl = xr.getCellData("Projects", "ProjectUrl", i);
				vModuleFiles = xr.getCellData("Projects", "ModuleFiles", i);
				vTestDataFiles = xr.getCellData("Projects", "TestDataFiles", i);

				Xls_Reader xm = new Xls_Reader(
						WebDriverService.getPath("/DriverFiles/ModuleDriver/"
								+ vModuleFiles));
				Xls_Reader xtd = new Xls_Reader(
						WebDriverService
								.getPath("/TestDataFiles/" + vTestDataFiles));
				ModuleCnt = xr.getRowCount(vProjectName);

				for (int j = 2; j <= ModuleCnt; j++) {
					vModulesRun = xr.getCellData(vProjectName, "Run", j);
					if (vModulesRun.equalsIgnoreCase("ON")) {
						vModuleName = xr.getCellData(vProjectName,
								"ModuleName", j);
						System.out.println(vModuleName);
						JsonArray jArray = new JsonArray();
						jObjectMain.add(vModuleName, jArray);
						vTCCnt = xm.getRowCount(vModuleName);
						for (int k = 2; k <= vTCCnt; k++) {
							vTCRun = xm.getCellData(vModuleName, "Run", k)
									.trim();
							if (vTCRun.equalsIgnoreCase("ON")) {
								vTCName = xm.getCellData(vModuleName, "TCName",
										k).trim();
								vTDCnt = xtd.getRowCount(vModuleName);
								for (int m = 2; m <= vTDCnt; m++) {
									vTDSteps = xtd.getCellData(vModuleName,
											"TC_Steps_Description", m);
									if (vTCName.equals(vTDSteps)) {
										JsonObject jObj = new JsonObject();
										jObj.addProperty("test_case", vTCName);
										jArray.add(jObj);

										System.out.println(" I know : "
												+ vTCName);
									}
								}
							}
						}
					}
				}
			}
		}
		return jObjectMain.toString();
	}


	public static String  getReports(String reportPath) throws Exception {
		JsonArray  reports = new JsonArray();
		Files.walk(Paths.get(reportPath)).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		        System.out.println(filePath);
		        JsonObject  jObject = new JsonObject();
		        jObject.addProperty("report_name" , filePath.getFileName().toString());
		        reports.add(jObject);
		    }
		});
		
		return reports.toString();
	}
	
	
	
		
	
}
