package com.Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyTestNGListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********TestPassed******"+ result.getName());
		//System.out.println( result.getThrowable().getMessage());
		/*
		 * ExtentTest extentTest=(ExtentTest)
		 * result.getTestContext().getAttribute("extentTests");
		 * extentTest.log(Status.INFO, result.getThrowable().getMessage());
		 */
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********TestSuccess******"+ result.getName());
		System.out.println( result.getThrowable().getMessage());
		/*
		 * ExtentTest extentTest=(ExtentTest)
		 * result.getTestContext().getAttribute("extentTests");
		 * extentTest.log(Status.PASS, result.getName()+"TEST SUCCESS TESTLISTENER");
		 */
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********TesFailure******"+ result.getName());
		//System.out.println( result.getThrowable().getMessage());
		/*
		 * ExtentTest extentTest=(ExtentTest)
		 * result.getTestContext().getAttribute("extentTests");
		 * extentTest.log(Status.FAIL, result.getName()+"Failed")
		 */;
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("********TestSkipped******"+ result.getName());
		//System.out.println( result.getThrowable().getMessage());
		/*
		 * ExtentTest extentTest=(ExtentTest)
		 * result.getTestContext().getAttribute("extentTests");
		 * extentTest.log(Status.SKIP, result.getName()+"TEST SKIPPED TESTLISTENER");
		 */
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
