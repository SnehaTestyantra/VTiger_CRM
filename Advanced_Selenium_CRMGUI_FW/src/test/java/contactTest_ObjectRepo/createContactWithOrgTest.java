package contactTest_ObjectRepo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import generic.ObjectRepository.ContactInfoPage;
import generic.ObjectRepository.ContactPage;
import generic.ObjectRepository.CreateNewConPage;
import generic.ObjectRepository.CreateNewOrgPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
import generic.ObjectRepository.OrganizationPage;
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
		String lastName = eLib.getDataFromExcel("contact", 7, 3);
		
		
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
//				using method and best practice
				LoginPage lp=new LoginPage(driver);
				lp.loginToApp("admin", "password");
				

				//		  click on organization using OR
				HomePage hp = new HomePage(driver);
				hp.getOrgLink().click();
//				hp.navigateToCampaignPage();
				
				//		  click on create organization using OR
				OrganizationPage op = new OrganizationPage(driver);
				op.getCreateNewOrgBtn().click();
				
				//        Enter all details and create new org using OR
				CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
				cnop.createOrg(orgName);
				
				
				// verify header msg expected result
				OrganizationInfoPage oip = new OrganizationInfoPage(driver);
				String actualHeaderMsg=oip.getHeaderMsg().getText();
				ContactInfoPage cip = new ContactInfoPage(driver);
				String expOrg=cip.getExpectedOrg().getText();
				if(actualHeaderMsg.contains(orgName))
				{
					System.out.println(orgName+" is created ==> PASS");
				}
				else
				{
					System.out.println(orgName+" is not created ==> FAIL");
				}	
				
				

				//CONTACT MODULE

//				  click on contacts
				hp = new HomePage(driver);
				hp.getContactLink().click();
				//		  click on create contacts
				ContactPage cp = new ContactPage(driver);
				cp.getCreateNewConBtn().click();

				//        Enter all details and create new contacts
				CreateNewConPage cncp = new CreateNewConPage(driver);
				cncp.getLastNameEdit().sendKeys(lastName);
				cncp.getAddOrgImg().click();
    			cncp.addContactWithOrg(orgName);
				cncp.getSaveBtn().click();
				
				//verify contactLastName
				//verify header lastname
				 oip = new OrganizationInfoPage(driver);
				String actualLastName = oip.getlastNameVerify().getText();
				if(actualLastName.equals(lastName))
				{
					System.out.println(actualLastName+" info is verified");
				}
				else
				{
					System.out.println(actualLastName+" info is verified");
				}
				//verify orgNAme
				// verify header orgNAme info Expected result
				//ContactInfoPage cip = new ContactInfoPage(driver);
				String actualOrgName= cip.getOrgInConVerify().getText();
				System.out.println("captured actual org");
				
				if(actualOrgName.contains(expOrg))
				{
					System.out.println(expOrg+" info is created ==> PASS");
				}
				else
				{
					System.out.println(expOrg+" info is not created ==> FAIL");
				}

				hp.logOut();
				driver.quit();
	}

}
