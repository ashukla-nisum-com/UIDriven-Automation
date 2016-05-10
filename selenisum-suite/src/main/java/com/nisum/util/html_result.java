package com.nisum.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nisum.repositories.ReportRepository;
import com.nisum.service.WebDriverService;




public class html_result {
	private WebDriverService driverScript;
	public String ResFilePath;
	
	public html_result(WebDriverService driverScript){
		this.driverScript = driverScript;
	}

	
	//1 st
	public String CreateResultFileAndPath(String vProjectName,String reportPath) throws Throwable 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
		Date date = new Date();		
		String TempFileName=dateFormat.format(date);
		String NewFileName1=TempFileName.replace("/","_");
		String NewFileName2=NewFileName1.replace(" ","_");
		String NewFileName=NewFileName2.replace(":","_");
		System.out.println(NewFileName);
		ResFilePath =reportPath + "/"+vProjectName+"HtmlResult_"+NewFileName+".html";
		File f = new File(ResFilePath);
		f.createNewFile();
		return ResFilePath;
		
	}
	// 2nd step
	//@SuppressWarnings("resource")
	public void fg_OpenResultsFile(String ResFilePath,String Projectname) throws Throwable 
	{
		FileWriter w = new FileWriter(ResFilePath);
		BufferedWriter out = new BufferedWriter(w);
		out.write("<HTML><BODY>");
		out.newLine();
		out.write("<TABLE COLS=4 WIDTH='100%' STYLE='BORDER: BLACK 1PX SOLID; BACKGROUND-COLOR:WHITE;'>");
		out.newLine();
		out.write ("<TR><TD COLSPAN=6 BGCOLOR='CORAL'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>" + Projectname + "</B></FONT></TD></TR><TR>");
		out.write ("<TD WIDTH='15%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Description</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='20%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Expected</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='20%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Actual</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Pass</b></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Fail</b></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Time</b></FONT></TD>");
		out.newLine();
		out.write ("</TR>");
		out.newLine();
		out.flush();
	}
	
	//@SuppressWarnings("resource")
	public void fgInsertStep(String ResFilePath) throws Throwable 
	{
		FileWriter w = new FileWriter(ResFilePath,true);		
		BufferedWriter out = new BufferedWriter(w);
		out.write("<TR><TD COLSPAN=4><FONT FACE='VERDANA' COLOR='Navy' SIZE=2><B>" + "TCName : </B>" + driverScript.vTCName +"</FONT></TD></TR>");
		out.write("<BR><TR><TD COLSPAN=4><FONT FACE='VERDANA' COLOR='Navy' SIZE=2><B>Description : <B>"+driverScript.vTCDesc+ "</FONT></TD></TR>");
		out.newLine();		
		out.flush();
	}
	
	@SuppressWarnings("resource")
	public void fgInsertResult(String ResFilePath,String vkeyword,String Expected,String Actual,String Result) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter out = new BufferedWriter(w);
		out.write ("<TR BGCOLOR='#EEEEEE'>");
		out.newLine();	
		out.write ("<TD WIDTH='40%'><FONT FACE='VERDANA' SIZE=2>" + vkeyword + "</FONT></TD>");
		out.newLine();	
		out.write ("<TD WIDTH='25%'><FONT FACE='VERDANA' SIZE=2>" + Expected + "</FONT></TD>");
		out.newLine();	
		out.write ("<TD WIDTH='25%'><FONT FACE='VERDANA' SIZE=2>" + Actual + "</FONT></TD>");
		out.newLine();	
		if (Result.equals("PASS"))
		{
			
			 
			    out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='GREEN' SIZE=2><B>" + Result + "</B></FONT></TD>");
			    out.newLine();	
			    out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='GREEN' SIZE=2><B>" + " " + "</B></FONT></TD>");
				out.newLine();	
			
				driverScript.passval = driverScript.passval+1;
			 
		 }	 
		 else if (Result.equals("FAIL"))
		 {
			
			
			out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='GREEN' SIZE=2><B>" + " " + "</B></FONT></TD>");
			out.newLine();	
			out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='RED' SIZE=2><B>" + Result + "</B></FONT></TD>" ); 
			out.newLine();	
			driverScript.failcnt = driverScript.failcnt+1;
			driverScript.failval =driverScript.failval+1;
		     
			
		}
			
			out.flush();
	}
	
	
	public void fgCreateSummary(String ResFilePath) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter out = new BufferedWriter(w);
		out.write ("<TR><TD COLSPAN=6 BGCOLOR='GRAY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>" + "Total Summary" + "</B></FONT></TD></TR><TR>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Total # of TC's</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Validations Passed</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Validations Failed</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Total # of TC's Passed</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>Total # of TC's Failed</B></FONT></TD>");
		out.newLine();
		//out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>"+ "" + "</b></FONT></TD>");
		//out.newLine();
		out.write ("<TD WIDTH='10%' BGCOLOR='NAVY'><FONT FACE='VERDANA' COLOR='WHITE' SIZE=2><B>TotalTimeTaken(sec)</b></FONT></TD>");
		out.newLine();
		out.write ("</TR>");
		out.newLine();
		out.flush();
		out.close();
		w.close();
		   
	}
	
	public void fgInsertSummary(String strFilePath,int noofTC,int passtc,int failtc,int passval,int failval,long timeofexe) throws Throwable
	{
		FileWriter w = new FileWriter(strFilePath,true);
		BufferedWriter out = new BufferedWriter(w);
		out.write ("<TR BGCOLOR='#EEEEEE'>");
		out.newLine();
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' SIZE=2>"+"<B>" + noofTC + "</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='GREEN' SIZE=2>"+"<B>" + passtc + "</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='RED' SIZE=2>"+"<B>" + failtc + "</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='GREEN' SIZE=2>"+"<B>" + passval + "</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='RED' SIZE=2>"+"<B>" + failval + "</B></FONT></TD>");
		out.newLine();
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' SIZE=2>"+"<B>" + timeofexe + "</B></FONT></TD>");
		out.newLine();
		out.write ("</TR>");
		out.newLine();
		out.flush();
	}
	
	public void WriteTCTime(String ResFilePath,long tctime) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter out = new BufferedWriter(w);
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='GREEN' SIZE=2><B>"+tctime+"</B></FONT></TD>");
		out.newLine();
		out.flush();
	}
	
	@SuppressWarnings("resource")
	public void fgCloseFile(String ResFilePath) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter out = new BufferedWriter(w);
		out.write ("</TABLE>");
		out.newLine();
		out.write ("</BODY></HTML>");
		out.newLine();
		out.flush();
		
	}
	
	public void ExcelResult(Xls_Reader xres,String status)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		String DateTime=dateFormat.format(date);
		int rownum=xres.getRowCount(driverScript.vProjectName);
		xres.setCellData(driverScript.vProjectName, "ModuleName", rownum+1, driverScript.vModuleName);
		xres.setCellData(driverScript.vProjectName, "TCName", rownum+1, driverScript.vTCName);
		xres.setCellData(driverScript.vProjectName, "Description", rownum+1, driverScript.vTCDesc);
		xres.setCellData(driverScript.vProjectName, "Status", rownum+1, status);
		xres.setCellData(driverScript.vProjectName, "DateTime", rownum+1, DateTime);
	}
	
	public long timeDiffernce(String dateStart,String dateStop,DateFormat dateFormat) throws Throwable
	{
		
		 
		//HH converts hour in 24 hours format (0-23), day calculation
		Date d1=null;
		Date d2=null;
		d1 = dateFormat.parse(dateStart);
		d2 = dateFormat.parse(dateStop);
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			long diffSeconds = diff / 1000 % 60;
		//	long diffMinutes = diff / (60 * 1000) % 60;
		//	long diffHours = diff / (60 * 60 * 1000) % 24;
		//	long diffDays = diff / (24 * 60 * 60 * 1000);
 
//			System.out.print(diffDays + " days, ");
//			System.out.print(diffHours + " hours, ");
//			System.out.print(diffMinutes + " minutes, ");
//			System.out.print(diffSeconds + " seconds.");
 
		
		return diffSeconds;
		
	}
	
	
}	



