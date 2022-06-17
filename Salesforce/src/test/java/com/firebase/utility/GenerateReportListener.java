package com.firebase.utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.firebase.base.BaseTest;

import org.testng.ITestListener;

public class GenerateReportListener implements ITestListener  {

	ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	public GenerateReports report=GenerateReports.getInstance();
	
	

	public void onStart(ITestContext context) {
		System.out.println("inside GenerateReportsListener onStart() method creating report");
		
		report.startExtentReport();
	}

	
	public void onTestStart(ITestResult result) {
		// testcaseName = testName;
		System.out.println("inside GenerateReportsListener onTestStart() method creating test report");
	//	report.startSingleTestReport(result.getMethod().getMethodName());
	//	System.out.println("onTestStart completed");

	}


	public void onTestSuccess(ITestResult result) {
		System.out.println(" inside onTestSuccess listener method");
		report.logTestpassed(result.getMethod().getMethodName() );
		
	}

	@Parameters("browser")
	public void onTestFailure(ITestResult result,String browser) {
	//	Object currentClass=result.getInstance();
		WebDriver driver=BaseTest.getDriverInstance();
		String screenshotFileName= result.getMethod().getMethodName();
		String screenshotPath=CommonUtilities.takescreenshot(driver, screenshotFileName);
		report.logTestFailed(result.getMethod().getMethodName());
		try {
			report.attachScreeshot(screenshotPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void onTestSkipped(ITestResult result) {
		report.logTestSkipped(result.getMethod().getMethodName());
	}


	public void onFinish(ITestContext context) {
		report.endReport();
	}
}
