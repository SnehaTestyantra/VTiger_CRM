package orgTest_OjectRepo;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class deleteOrgTest {
	public static void main(String[] args) throws IOException {
		WebDriver driver=null;

		//		read data from properties file
		FileUtility fLib = new FileUtility();
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
//		String USERNAME = fLib.getDataFromPropertiesFile("username");
//		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		//		generate random num
		JavaUtility jLib = new JavaUtility();

		//		read data from excel file
		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		//		navigate to browser
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
		
//		using method and best practice
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("admin", "password");
		

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
		
		
		// verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeaderMsg=oip.getHeaderMsg().getText();
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

//		To go back to org page
		hp.getOrgLink().click();
		
//		search for org
		op.getSearchEdit().sendKeys(orgName);
		wLib.Select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		 
//		In dynamic web table select and delete org
		WebElement deleteOrg = driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']"));
		deleteOrg.click();
		
		//handle the exception
		wLib.switchToAlertAndAccept(driver);
		
		//logout
		hp.logOut();
		driver.quit();

		
		
		
		
	}

}
