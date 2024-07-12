package generic.ListenerUtility;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import generic.baseTest.BaseClass;
import generic.webDriverUtility.UtilityClassObject;

public class ListenerImpClass implements ITestListener,ISuiteListener{

	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report configuration");
		//		step1 :spark report Config
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		//		step2 : add env info and create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//		System.out.println("===="+result.getMethod().getMethodName()+"====Start");
		test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"====Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//		System.out.println("===="+result.getMethod().getMethodName()+"====End");
		
//		test.log(Status.INFO, "read data from excel");
		test.log(Status.INFO, "click on organization using OR");
		test.log(Status.INFO, "click on create organization using OR");
		test.log(Status.INFO, "Enter all details and create new org using OR");
		test.log(Status.INFO, "verify header msg expected result");
		test.log(Status.PASS, result.getMethod().getMethodName()+"====Completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();		
		TakesScreenshot tks=(TakesScreenshot)BaseClass.sDriver;
		String filePath = tks.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"Failed");
		

		//		time based screenshot


	}
}
