package generic.baseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameter;

import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.databaseUtility.DataBaseUtility;
import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.UtilityClassObject;
import generic.webDriverUtility.WebDriverUtility;

public class BaseClass {
	public DataBaseUtility dLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public WebDriver driver = null;
	public WebDriverUtility wLib = new WebDriverUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public static WebDriver sDriver= null;
//	public ExtentReports report;
//	public ExtentTest test;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS()
	{
		System.out.println("Connect to db ,report config");
		
////		step1 :spark report Config
//		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
//		spark.config().setDocumentTitle("CRM test suite results");
//		spark.config().setReportName("CRM report");
//		spark.config().setTheme(Theme.DARK);
////		step2 : add env info and create test
//		report=new ExtentReports();
//		report.attachReporter(spark);
//		report.setSystemInfo("OS","Windows-10");
//		report.setSystemInfo("BROWSER", "CHROME-100");
		
		dLib.getDbConnection();
	}

	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC(String browser) throws IOException
	{
		System.out.println("Launch the browser");
//		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String BROWSER = browser;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sDriver=driver;
		UtilityClassObject.setDriver(driver);
		wLib.waitForPageToLoad(driver);
		wLib.maximiseTheWindow(driver);
	}
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws IOException
	{
		System.out.println("Login");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		driver.get(URL);
		lp.loginToApp(USERNAME, PASSWORD);
	}
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("LogOut");
		HomePage hp = new HomePage(driver);
		hp.logOut();
		
	}
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("Close the browser");
		driver.quit();
		
	}
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS()
	{
		System.out.println("Close the DB ,Report backUP");
//		report.flush();
		dLib.closeDbConnection();
		
	}
}
