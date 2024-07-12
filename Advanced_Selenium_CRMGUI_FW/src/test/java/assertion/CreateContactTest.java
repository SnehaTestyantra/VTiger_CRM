package assertion;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import generic.ObjectRepository.ContactPage;
import generic.ObjectRepository.CreateNewConPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
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
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("admin", "password");

		//		  click on contacts
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		//		  click on create contacts
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewConBtn().click();

		//        Enter all details and create new contacts
		CreateNewConPage cncp = new CreateNewConPage(driver);
		cncp.createContact(lastName);
		
//		verify headermsg
		String actualHeaderMsg = cp.getHeaderMsg().getText();
		boolean status = actualHeaderMsg.contains(lastName);
		Assert.assertEquals(status, true);
		System.out.println(actualHeaderMsg+"info is verified");
		
		//verify header lastname
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualLastName = oip.getlastNameVerify().getText();
		SoftAssert assertObj = new SoftAssert();
		assertObj.assertEquals(actualLastName, lastName);
		System.out.println(actualLastName+"info is verified");
		assertObj.assertAll();
//		if(actualLastName.equals(lastName))
//		{
//			System.out.println(actualLastName+" info is verified");
//		}
//		else
//		{
//			System.out.println(actualLastName+" info is verified");
//		}
		
		//logout
		hp.logOut();
		driver.quit();

	}

}
