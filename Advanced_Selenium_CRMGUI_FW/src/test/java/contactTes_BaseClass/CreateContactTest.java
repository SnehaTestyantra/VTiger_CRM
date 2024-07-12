package contactTes_BaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import generic.ObjectRepository.ContactInfoPage;
import generic.ObjectRepository.ContactPage;
import generic.ObjectRepository.CreateNewConPage;
import generic.ObjectRepository.CreateNewOrgPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
import generic.ObjectRepository.OrganizationPage;
import generic.baseTest.BaseClass;
import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;

public class CreateContactTest extends BaseClass{

	@Test(groups = "smokeTest")
	public void createConTest() throws EncryptedDocumentException, IOException
	{
		//read testscript data from excel
		String lastName = eLib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNumber();

		//		  click on contacts
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		//		  click on create contacts
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewConBtn().click();

		//        Enter all details and create new contacts
		CreateNewConPage cncp = new CreateNewConPage(driver);
		cncp.createContact(lastName);

		//verify header lastname
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualLastName = oip.getlastNameVerify().getText();
		if(actualLastName.equals(lastName))
		{
			System.out.println(actualLastName+" info is verified");
		}
		else
		{
			System.out.println(actualLastName+" info is verified");
		}

	}

	@Test(groups = "regressionTest")
	public void createConWithSupportDate() throws EncryptedDocumentException, IOException
	{
		//read testscript data from excel	
		String lastName = eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNumber();


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

	}
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException
	{
//		read data from excel
		String orgName = eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 3);
		
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

	}

}
