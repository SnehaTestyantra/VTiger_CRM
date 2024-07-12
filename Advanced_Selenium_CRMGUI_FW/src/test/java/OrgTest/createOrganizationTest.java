package OrgTest;


import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;

public class createOrganizationTest {

	public static void main(String[] args) throws IOException {
		WebDriver driver=null;

		//		read data from properties file
		FileUtility fLib = new FileUtility();
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		//		generate random num
		JavaUtility jLib = new JavaUtility();

		//		read data from excel file
		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		//		navigate to browser
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
		driver.findElement(By.name("button")).click();
		
		// verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName))
		{
			System.out.println(orgName+" is created ==> PASS");
		}
		else
		{
			System.out.println(orgName+" is not created ==> FAIL");
		}	
		
		// verify header orgNAme info Expected result
		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actualOrgName.equals(orgName))
		{
			System.out.println(orgName+" info is created ==> PASS");
		}
		else
		{
			System.out.println(orgName+" info is not created ==> FAIL");
		}

		//logout
		driver.quit();

	}

}
