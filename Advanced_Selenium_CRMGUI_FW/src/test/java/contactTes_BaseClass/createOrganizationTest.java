package contactTes_BaseClass;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic.ObjectRepository.CreateNewOrgPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
import generic.ObjectRepository.OrganizationPage;
import generic.baseTest.BaseClass;
import generic.fileUtility.ExcelUtility;
import generic.webDriverUtility.UtilityClassObject;
import generic.webDriverUtility.WebDriverUtility;

public class createOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException
	{
		//		read data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		//		  click on organization using OR
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		//		hp.navigateToCampaignPage();

		//		  click on create organization using OR
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		//        Enter all details and create new org using OR
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.createOrg(orgName);
//		Assert.fail();
//		cnop.getSaveBtn().click();


		// verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeaderMsg=oip.getHeaderMsg().getText();
//		boolean orgStatus = actualHeaderMsg.contains(orgName);
//		Assert.assertEquals(orgStatus, true);
//		Assert.assertEquals(actualHeaderMsg, orgName);
		
		
		if(actualHeaderMsg.contains(orgName))
		{
			System.out.println(orgName+" is created ==> PASS");
		}
		else
		{
			System.out.println(orgName+" is not created ==> FAIL");
		}	

		// verify header orgNAme info Expected result
		String actualOrgName= oip.getOrgVerify().getText();
		if(actualOrgName.equals(orgName))
		{
			System.out.println(orgName+" info is created ==> PASS");
		}
		else
		{
			System.out.println(orgName+" info is not created ==> FAIL");
		}
		

	}
	@Test(groups = "regressionTest")
	public void createOrgWithInd() throws EncryptedDocumentException, IOException
	{
		//read testscript data from excel
		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("org",4 , 2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		//				  click on organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		//		  click on create organization
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		//        Enter all details and create new org
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.createOrg(orgName, industry, type);



		//verify header industry
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualInd = oip.getIndustry().getText();
		if(actualInd.equals(industry))
		{
			System.out.println(actualInd+" info is verified ==> PASS");
		}
		else
		{
			System.out.println(actualInd+" info is not verified ==> FAIL");
		}

		//verify header type
		String actualType = oip.getType().getText();
		if(actualType.equals(type))
		{
			System.out.println(actualType+" info is verified ==> PASS");
		}
		else
		{
			System.out.println(actualType+" info is not verified ==> FAIL");
		}

	}
	@Test(groups = "regressionTest")
	public void createOrgWithPhNo() throws EncryptedDocumentException, IOException
	{
		//read testscript data from excel
		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNumber();
		String phoneNo = eLib.getDataFromExcel("org", 7, 3);

		//				  click on organization
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
	}
}
