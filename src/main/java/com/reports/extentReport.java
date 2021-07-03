package com.reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReport {
	
	static ExtentReports reports;
	static ExtentTest test;
	public static String snapshotFolder;
	
	
	public static ExtentReports getReports()
	{
		if(reports == null)
		{
		reports = new ExtentReports();
		
		// Dynamic Report and snapshot folder creation
		Date date= new Date();
		 //Store snapsshot
		String Snapshot =date.toString().replaceAll(":", "-")+"//Snapshots";
		snapshotFolder=System.getProperty("user.dir")+"//Reports//"+Snapshot;
		
		//Store Reports
		String reportFolder=System.getProperty("user.dir")+"//Reports//"+date.toString().replaceAll(":", "-");
		
		//File creates snapsshots folder under REPORTS folder
		File file = new File(snapshotFolder);
		file.mkdirs();
		
		//ExtentSpark used to set fields and title,theme, logo ,UI,format
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportFolder);
		sparkReport.config().setReportName("Regression Report");
		sparkReport.config().setDocumentTitle("AUTOMATION REPORTS");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setEncoding("utf-8");
		reports.attachReporter(sparkReport);
		}
		
		return reports;
		
	}

}
