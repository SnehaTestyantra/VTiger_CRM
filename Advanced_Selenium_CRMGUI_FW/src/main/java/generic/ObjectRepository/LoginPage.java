package generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.webDriverUtility.WebDriverUtility;

public class LoginPage {
//	rule 1:create separate class
	
//	rule 2:object creation
	WebDriver driver=null;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(name="user_name")
	private WebElement usernameEdt;

	@FindBy(name="user_password")
	private WebElement passwordEdt;

	@FindBy(id="submitButton")
	private WebElement loginBtn;

//	@FindBy(name="user_name")
//	public WebElement usernameEdt;
//
//	@FindBy(name="user_password")
//	public WebElement passwordEdt;
//
//	@FindBy(id="submitButton")
//    public WebElement loginBtn;
	
//	rule 3:object initialization in Test script
	
//	rule 4:object encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
//	rule 5:object utilization
	public void loginToApp(String username,String password)
	{
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.waitForPageToLoad(driver);
		
		usernameEdt.sendKeys("admin");
		passwordEdt.sendKeys("password");
		loginBtn.click();
	}
}
