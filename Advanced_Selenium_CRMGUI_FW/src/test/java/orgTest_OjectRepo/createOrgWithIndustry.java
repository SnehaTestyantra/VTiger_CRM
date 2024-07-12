package orgTest_OjectRepo;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.MoreObjects.ToStringHelper;

import generic.ObjectRepository.CreateNewOrgPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
import generic.ObjectRepository.OrganizationPage;
import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;

public class createOrgWithIndustry {

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
		String orgName = eLib.getDataFromExcel("org",4 , 2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

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


		//logout
		hp.logOut();
		driver.quit();

	}

}
