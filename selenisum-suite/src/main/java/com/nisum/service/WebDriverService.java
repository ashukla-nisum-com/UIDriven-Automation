package com.nisum.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nisum.domain.Report;
import com.nisum.repositories.ReportRepository;
import com.nisum.util.Generic_Functions;
import com.nisum.util.HtmlResult;
import com.nisum.util.Xls_Reader;
@Service
public class WebDriverService {

	private final static Logger logger = LoggerFactory.getLogger(WebDriverService.class);
	@Autowired
	private ReportRepository reportRepository;
	
	public WebDriver driver;
	public String vProjectUrl,ResFilePath,vProjectName,vModuleName,vTCName,vTCDesc;
	public int failval,Cntflag,passval,failcnt,actfails;
	
	public static String getPath(String str) throws UnsupportedEncodingException{
		String path = WebDriverService.class.getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/com/nisum/service/");
		//("../../resources/DriverFiles/ProjectDriver.xlsx");
		logger.info(fullPath);
		logger.info(pathArr[0]);
		
		return pathArr[0]+str;
	}
	
	public String main(String reportPath) throws Throwable {
	    String vProjectRun,vBrowserType,vDBName,vDBuser,vDBpassword,vModulesRun,vModuleFiles,vTestDataFiles,vTCRun,vTDSteps,vKeyword;
		int ProjectCnt,ModuleCnt,vTCCnt,vTDCnt,flag,vRowNum;
		
		
		
	    Xls_Reader xr=new Xls_Reader(getPath("/DriverFiles/ProjectDriver.xlsx"));
        ProjectCnt=xr.getRowCount("Projects");
        for(int i=2;i<=ProjectCnt;i++)
        {
        	vProjectRun=xr.getCellData("Projects", "Run", i);
        	if(vProjectRun.equalsIgnoreCase("ON"))
        	{
        		
        		//TODO:: start Need to move below code generic 
        		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Nisum-User\\Downloads\\geckodriver-v0.10.0-win64\\geckodriver.exe");
        		//MarionetteDriverManager.getInstance().setup();
        		
        		//TODO:: Stop
        		String path = getClass().getClassLoader().getResource("chromedriver.exe").getPath();
        		System.setProperty("webdriver.chrome.driver", 	path);
        		driver=new ChromeDriver();
        		vProjectName=xr.getCellData("Projects", "ProjectName", i);
        		vProjectUrl=xr.getCellData("Projects", "ProjectUrl", i);
        		vModuleFiles=xr.getCellData("Projects", "ModuleFiles", i);
        		vTestDataFiles=xr.getCellData("Projects", "TestDataFiles", i);
        		vDBName=xr.getCellData("Projects", "DBName", i);
        		vDBuser=xr.getCellData("Projects", "DBUser", i);
        		vDBpassword=xr.getCellData("Projects", "DBPassword", i);
        		vBrowserType=xr.getCellData("Projects", "BrowerType", i);
        		logger.info(vProjectName+"||"+vProjectUrl+"||"+vBrowserType+"||"+vModuleFiles+"||"+vTestDataFiles);
        		
        		Xls_Reader xm=new Xls_Reader(getPath("/DriverFiles/ModuleDriver/"+vModuleFiles));
        		Xls_Reader xtd=new Xls_Reader(getPath("/TestDataFiles/"+vTestDataFiles));
        		ModuleCnt=xr.getRowCount(vProjectName);
        		
        		
        		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        		Date date = new Date();		
        		String ProjectStartTime=dateFormat.format(date);
        		//Html result code
        		Xls_Reader xres=new Xls_Reader(getPath("/Results/ExcelResult.xlsx"));
        		Cntflag=0;
        		actfails=0;
        		HtmlResult hr=new HtmlResult(reportRepository,this);
        		ResFilePath=hr.createReport(vProjectName,reportPath);
        		//hr.fg_OpenResultsFile(ResFilePath, vProjectName);
        		
        		for(int j=2;j<=ModuleCnt;j++)
        		{
        			vModulesRun=xr.getCellData(vProjectName, "Run", j);	
        			if(vModulesRun.equalsIgnoreCase("ON"))
        			{
        				vModuleName=xr.getCellData(vProjectName, "ModuleName", j);
        				logger.info(vModuleName);
        				vTCCnt=xm.getRowCount(vModuleName);
        				hr.setTestSuite(vModuleName);
        				String testSuiteStartTime=dateFormat.format(new Date());
        				for(int k=2;k<=vTCCnt;k++)
        				{
        					vTCRun=xm.getCellData(vModuleName, "Run", k).trim();        					
        					if(vTCRun.equalsIgnoreCase("ON"))
        					{
        						vTCName=xm.getCellData(vModuleName, "TCName", k).trim();
        						logger.info(vTCName);
        						Date t1 = new Date();		
        		        		String TCStartTime=dateFormat.format(t1);
        						Cntflag=Cntflag+1;
        						failcnt=0;
        						flag=0;
        						vRowNum=0;
        						vTDCnt=xtd.getRowCount(vModuleName);
        						for(int m=2;m<=vTDCnt;m++)
        						{
        							vTDSteps=xtd.getCellData(vModuleName, "TC_Steps_Description", m);
        							if(vTCName.equals(vTDSteps))
        							{
        								flag=1;
        								vRowNum=m;
        								vTCDesc=xtd.getCellData(vModuleName, "TC_Steps_Description", m-1).replaceAll("//", "");
        								logger.info(vTCDesc);
        								hr.fgInsertStep(ResFilePath);
        							}
        							if((flag==1) && (m>vRowNum))
        							{
        								if(vTDSteps.contains("//"))
        								{
        									break;
        								}
        								else
        								{
        									vKeyword=vTDSteps.trim();
        									logger.info(vKeyword);
        									Generic_Functions GF=new Generic_Functions(this);
        									GF.keywordDriver(vKeyword,xtd,vModuleName,m,hr);
        								}
        							}
        						}
        						
        						if(failcnt>0)
        						{
        							actfails=actfails+1;
        						}

        						Date t2 = new Date();		
        		        		String TCStopTime=dateFormat.format(t2);
        		        		long TCTimediff=hr.timeDiffernce(TCStartTime, TCStopTime, dateFormat);
        		        		hr.WriteTCTime(ResFilePath, TCTimediff);
        					}					
        					
        				}
        				
        				String testSuiteStopTime=dateFormat.format(new Date());
		        		long tsTimediff=hr.timeDiffernce(testSuiteStartTime, testSuiteStopTime, dateFormat);
		        		hr.writeTestSuiteTime(tsTimediff);
        			}
        			
        		}
        		
        		Date date1 = new Date();		
        		String ProjectStopTime=dateFormat.format(date1);
        		long ProjectexectionTime=hr.timeDiffernce(ProjectStartTime, ProjectStopTime, dateFormat);
        		//hr.fgCreateSummary(ResFilePath);
        			hr.saveReport();
        		//hr.fgInsertSummary(ResFilePath, Cntflag, Cntflag-actfails,actfails, passval, failval, "2  min");
        		//hr.fgInsertSummary(ResFilePath, Cntflag, passval, failval, Cntflag-actfails,actfails, ProjectexectionTime);
        	}
        }
        
        return ResFilePath;
	}

	public Iterable<Report> getReports(){
		return reportRepository.findAll(new Sort(Sort.Direction.DESC, "reportDate"));
	}
	
	
}
