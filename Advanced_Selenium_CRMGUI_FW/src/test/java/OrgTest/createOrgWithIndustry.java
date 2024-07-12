package OrgTest;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.MoreObjects.ToStringHelper;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;

public class createOrgWithIndustry {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		//to read data from properties file 
		FileUtility fLib = new FileUtility();
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		//to generate random num
		JavaUtility jLib = new JavaUtility();

		//read testscript data from excel
		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("org",4 , 2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		//navigate to browser
		WebDriverUtility wLib = new WebDriverUtility();
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		wLib.maximiseTheWindow(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(URL);

		//		Login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//		  click on organization
		driver.findElement(By.linkText("Organizations")).click(); 

		//		  click on create organization
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//        Enter all details and create new org
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement indDropDown = driver.findElement(By.name("industry"));
		Select sel = new Select(indDropDown);
		sel.selectByVisibleText(industry);

		WebElement typDropDown = driver.findElement(By.name("accounttype"));
		Select sel1 = new Select(typDropDown);
		sel1.selectByVisibleText(type);
		driver.findElement(By.name("button")).click();

		//verify header industry
		String actualInd = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actualInd.equals(industry))
		{
			System.out.println(actualInd+" info is verified ==> PASS");
		}
		else
		{
			System.out.println(actualInd+" info is not verified ==> FAIL");
		}

		//verify header type
		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if(actualType.equals(type))
		{
			System.out.println(actualType+" info is verified ==> PASS");
		}
		else
		{
			System.out.println(actualType+" info is not verified ==> FAIL");
		}


		//logout
		driver.quit();

	}

}
