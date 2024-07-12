package contactTest_ObjectRepo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import generic.ObjectRepository.ContactPage;
import generic.ObjectRepository.CreateNewConPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
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

//				Login
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
				String startDate = jLib.getSystemDateYYYYDDMM();
				String endDate = jLib.getRequiredDateYYYYDDMM(30);
				
				cncp.createContactWithSupportDate(lastName, startDate, endDate);
				System.out.println("successfiully created");
		
				//verify contact
				OrganizationInfoPage oip = new OrganizationInfoPage(driver);
				String header= oip.getHeaderMsg().getText();
				if(header.contains(lastName)) {
					System.out.println(lastName+" info is verified");
				}else {
					System.out.println(lastName+" info is not verified");
				}
				//verify startDAte
				
				String actualStartDate = oip.getstartDateVerify().getText();
				if(actualStartDate.equals(startDate))
				{
					System.out.println(actualStartDate+" info is verified");
				}
				else
				{
					System.out.println(actualStartDate+" info is verified");
				}
		
		
				//verify endDAte
				String actualEndDate = oip.getendDateVerify().getText();
				if(actualEndDate.equals(endDate))
				{
					System.out.println(actualEndDate+" info is verified");
				}
				else
				{
					System.out.println(actualEndDate+" info is verified");
				}
		        System.out.println(startDate +" "+endDate);
				//logout
		        hp.logOut();
				driver.quit();
		
		

	}

}
