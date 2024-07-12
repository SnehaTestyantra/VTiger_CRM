package extentReports;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import generic.baseTest.BaseClass;

public class SampleReportTest extends BaseClass{
    
	public ExtentReports report;
	public ExtentTest test;
	@Test
	public void createContactTest(Method mtd)
	{
		String method=mtd.getName();
		 test = report.createTest(method);
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "navigate toncontact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC"))
		{
			test.log(Status.PASS, "Contact is created");
		}
		else {
			test.log(Status.FAIL, "contact is not created");
		}
	}
	@Test
	public void createContactWithOrg()
	{
		 test = report.createTest("createContactWithOrg");
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "navigate toncontact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC"))
		{
			test.log(Status.PASS, "Contact is created");
		}
		else {
			test.log(Status.FAIL, "contact is not created");
		}
	}
	@Test
	public void createContactWithPhNo()
	{
		 test = report.createTest("createContactWithPhNo");
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "navigate toncontact page");
		test.log(Status.INFO, "create contact");
		if("HDFC".equals("HDFC"))
		{
			test.log(Status.PASS, "Contact is created");
		}
		else {
			test.log(Status.FAIL, "contact is not created");
		}
	}
	
}
