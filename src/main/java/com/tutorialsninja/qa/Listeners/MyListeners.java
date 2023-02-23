package com.tutorialsninja.qa.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

public void onStart(ITestContext context) {
		 extentReport=ExtentReporter.generateExtentReport();
		
	}

	public void onTestStart(ITestResult result) {
	
		extentTest=extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+"Started Execuing");
		
	}

	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS, result.getName()+"got sucessfully executed");
		
	}

	public void onTestFailure(ITestResult result) {
	
		System.out.println("Screenshot taken");
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationScreenshotPath=Utilities.captureScreenshot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+"got failed");
	
		
		System.out.println(result.getName()+"got failed");
	
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,result.getName()+"got skipped");
		
		
		
	}

	

	public void onFinish(ITestContext context) {
          extentReport.flush();
          String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
          File extentReport=new File(pathOfExtentReport);
          try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
