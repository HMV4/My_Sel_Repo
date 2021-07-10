package com.TestBase;
import java.io.FileNotFoundException;

import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.reports.extentReport;

import Keywords.ApplicationKeywords;

public class Testbase {

	// Create reference of application keywords class to intialize driver to maintain same single session till script ends
	public ApplicationKeywords app;
	public ExtentReports Extreports;
	public ExtentTest extentTest;
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context) throws FileNotFoundException, InterruptedException
	{
		System.out.println("====BEFORE TEST ANNOTATION ========	");
		
      // Single object session for all the test i.e @Test		
		  // app = new ApplicationKeywords(); 
		//app = new ApplicationKeywords();
		  context.setAttribute("app", app);
		 //Initialize reports before every test starts 
		  Extreports = extentReport.getReports();
		  
		 extentTest= Extreports.createTest(context.getCurrentXmlTest().getName());
		 
		 extentTest.log(Status.INFO,"Starting Test"+context.getCurrentXmlTest().getName());
 		// Inside application keyword class object sent reporting  
		 
	
		 //Default login everytime
		 app = new ApplicationKeywords(extentTest);
		 app.OpenBrowser("chrome");
		 app.defaultLogin();
	
		 app.setReport(extentTest);
		 
     // Setting context for extent report and Test so that we can maintain same session of object to get single report
		 
		 context.setAttribute("Extreports",Extreports);
		 context.setAttribute("extentTest",extentTest);
		 //context.setAttribute("applicationKeywords",app);
		 context.setAttribute("app", app);
		  
	}
	
	//Runs for all the test before starting each test case
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context)
	{
		System.out.println("====BEFORE METHOD ANNOTATION ========	");
		// Initialize app object so that we can avoid repeatation in @Test
		app = (ApplicationKeywords) context.getAttribute("app");

		// Intialize report object and extent test object to avoid multiple session and carries same session for test
		Extreports = (ExtentReports) context.getAttribute("Extreports");
		extentTest = (ExtentTest) context.getAttribute("extentTest");
		
		String criticalFailure = (String) context.getAttribute("criticalFailure");
		  
		
		 if(criticalFailure !=null && criticalFailure.equals("Y"))
		 {   
			 //Extent report log
			 extentTest.log(Status.SKIP,"criticalFailure in step");
			 
			 //TestNG log
			 throw new SkipException("criticalFailure in step");
		 }
		
		
		
	}
	
	
	
	// Runs after ending or finishing every test suite to get the report 
	@AfterTest
	public void quit()
	{
		
		app.assertAll();
		
		if(Extreports !=null)
		{
			
			Extreports.flush();
			
		}
	}
}
