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

public class CreateContactTest {

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
		String lastName = eLib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNumber();
		
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
		driver.findElement(By.name("button")).click();
		
		//verify header lastname
		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actualLastName.equals(lastName))
		{
			System.out.println(actualLastName+" info is verified");
		}
		else
		{
			System.out.println(actualLastName+" info is verified");
		}
		
		//logout
		driver.quit();

	}

}
