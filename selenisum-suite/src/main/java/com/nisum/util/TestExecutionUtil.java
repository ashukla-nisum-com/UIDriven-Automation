package com.nisum.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nisum.domain.Report;
import com.nisum.domain.TestCase;
import com.nisum.domain.TestStep;
import com.nisum.domain.TestSuite;
import com.nisum.repositories.ReportRepository;
import com.nisum.service.WebDriverService;

public class TestExecutionUtil {
	
	public static long timeDiffernce(String dateStart,String dateStop,DateFormat dateFormat) throws Exception {
		//HH converts hour in 24 hours format (0-23), day calculation
		Date d1=dateFormat.parse(dateStart);
		Date d2=dateFormat.parse(dateStop);
		long diff = d2.getTime() - d1.getTime(); //in milliseconds
		long diffSeconds = diff / 1000 % 60;
		return diffSeconds;

	}

	public static Report getIntializedReport() {
		Report report = new Report();
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
		Date date = new Date();		
		String reportId=dateFormat.format(date);
		report.setReportId(reportId);
		report.setReportDate(date);
		return report;
	}
	
}	



