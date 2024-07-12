package ContactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;

public class createContactWithSupportDate {

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
		String lastName = eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNumber();
		
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

				//		  click on contacts
				driver.findElement(By.linkText("Contacts")).click(); 

				//		  click on create contacts
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

				//        Enter all details and create new contacts
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				String startDate = jLib.getSystemDateYYYYDDMM();
				String endDate = jLib.getRequiredDateYYYYDDMM(30);
				driver.findElement(By.name("button")).click();
		
				//verify startDAte
				String actualStartDate = driver.findElement(By.name("support_start_date")).getText();
				if(actualStartDate.equals(lastName))
				{
					System.out.println(actualStartDate+" info is verified");
				}
				else
				{
					System.out.println(actualStartDate+" info is verified");
				}
		
		
				//verify endDAte
				String actualEndDate = driver.findElement(By.name("support_end_date")).getText();
				if(actualEndDate.equals(lastName))
				{
					System.out.println(actualEndDate+" info is verified");
				}
				else
				{
					System.out.println(actualEndDate+" info is verified");
				}
		System.out.println(startDate +" "+endDate);
				//logout
				driver.quit();
		
		

	}

}
