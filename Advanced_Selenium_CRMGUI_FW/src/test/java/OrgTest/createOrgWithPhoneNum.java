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

public class createOrgWithPhoneNum {

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
		String orgName = eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNumber();
		String phoneNo = eLib.getDataFromExcel("org", 7, 3);
		
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
				driver.findElement(By.id("phone")).sendKeys(phoneNo);
				driver.findElement(By.name("button")).click();
				
				//verify header phone
				String actualPhNo = driver.findElement(By.id("dtlview_Phone")).getText();
				if(actualPhNo.equals(phoneNo))
				{
					System.out.println(actualPhNo+" info is created ==> PASS");
				}
				else
				{
					System.out.println(actualPhNo+" info is not created ==> FAIL");
				}
				
				//logout
				driver.quit();
				

	}

}
