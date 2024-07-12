package orgTest_OjectRepo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import generic.ObjectRepository.CreateNewOrgPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
import generic.ObjectRepository.OrganizationPage;
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

//				Login
				LoginPage lp = new LoginPage(driver);
				lp.loginToApp(USERNAME, PASSWORD);

				//		  click on organization
				HomePage hp = new HomePage(driver);
				hp.getOrgLink().click();

				//		  click on create organization
				OrganizationPage op = new OrganizationPage(driver);
				op.getCreateNewOrgBtn().click();

				//        Enter all details and create new org
				CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
				cnop.createOrg(orgName, phoneNo);
				
			
				//verify header phone
				OrganizationInfoPage oip = new OrganizationInfoPage(driver);
				String actualPhNo = oip.getPhoneVerify().getText();
				if(actualPhNo.equals(phoneNo))
				{
					System.out.println(actualPhNo+" info is created ==> PASS");
				}
				else
				{
					System.out.println(actualPhNo+" info is not created ==> FAIL");
				}
				
				//logout
				hp.logOut();
				driver.quit();
				

	}

}
