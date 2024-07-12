package extentReports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import generic.baseTest.BaseClass;

//@Listeners(generic.ListenerUtility.ListenerImpClass.class)
public class SampleReport_ListenerTest {
	public ExtentReports report;
	public ExtentTest test;
	
	@BeforeSuite
	public void configBS()
	{
//		step1 :spark report Config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
//		step2 : add env info and create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
	}
	
	@AfterSuite
	public void configAS()
	{
		report.flush();
	}
	@Test
	public void createContactTest ()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888");
		TakesScreenshot tks=(TakesScreenshot)driver;
		
		String filePath = tks.getScreenshotAs(OutputType.BASE64);
		
		test = report.createTest("createContactTest");
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "navigate toncontact page");
		test.log(Status.INFO, "create contact");
		if("HdDFC".equals("HDFC"))
		{
			test.log(Status.PASS, "Contact is created");
		}
		else {
			test.addScreenCaptureFromBase64String(filePath, "errorFile");
		}
		driver.close();
	}
}
