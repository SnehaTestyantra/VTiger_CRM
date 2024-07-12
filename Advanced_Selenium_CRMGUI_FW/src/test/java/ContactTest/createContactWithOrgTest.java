package ContactTest;

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

public class createContactWithOrgTest {

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
		String orgName = eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
		String contactLastName = eLib.getDataFromExcel("contact", 7, 3);
		
		
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
				
				//ORGANIZATION MODULE
//				Login
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

				//CONTACT MODULE

				//		  click on contact
				driver.findElement(By.linkText("Contacts")).click(); 

				//		  click on create contact
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

				//        Enter all details and create new contact
				driver.findElement(By.name("lastname")).sendKeys(contactLastName);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				wLib.switchToTabOnURL(driver, "module=Accounts");
				driver.findElement(By.name("search_text")).sendKeys(orgName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				wLib.switchToTabOnURL(driver, "Contacts&action");
				driver.findElement(By.name("button")).click();
				
				//verify contactLastName
				String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
				if(actualLastName.equals(contactLastName))
				{
					System.out.println(actualLastName+" info is verified");
				}
				else
				{
					System.out.println(actualLastName+" info is verified");
				}
				//verify orgNAme
				String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(actualLastName.trim().equals(orgName))
				{
					System.out.println(actualOrgName+" info is verified");
				}
				else
				{
					System.out.println(actualOrgName+" info is verified");
				}

	}

}
