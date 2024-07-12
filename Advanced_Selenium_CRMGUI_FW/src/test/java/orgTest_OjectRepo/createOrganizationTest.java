package orgTest_OjectRepo;


import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import generic.ObjectRepository.CreateNewOrgPage;
import generic.ObjectRepository.HomePage;
import generic.ObjectRepository.LoginPage;
import generic.ObjectRepository.OrganizationInfoPage;
import generic.ObjectRepository.OrganizationPage;
import generic.fileUtility.ExcelUtility;
import generic.fileUtility.FileUtility;
import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;

public class createOrganizationTest {

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

		//		Login using object repository
        //      using Public access specifier in @FindBy and without getters
//		LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
//		lp.usernameEdt.sendKeys("admin");
//		lp.passwordEdt.sendKeys("password");
//		lp.loginBtn.click();
		
//		using Private access specifier in @FindBy and with getters
//		LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
//		lp.getUsernameEdt().sendKeys("admin");
//		lp.getPasswordEdt().sendKeys("password");
//		lp.getLoginBtn().click();
		
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

		//logout
		hp.logOut();
		driver.quit();

	}

}
