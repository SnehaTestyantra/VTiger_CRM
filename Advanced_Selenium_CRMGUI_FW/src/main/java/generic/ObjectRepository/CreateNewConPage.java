package generic.ObjectRepository;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.webDriverUtility.JavaUtility;
import generic.webDriverUtility.WebDriverUtility;

public class CreateNewConPage {

	WebDriver driver = null;

	public CreateNewConPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameEdit;

	@FindBy(name = "button")
	private WebElement saveBtn;

	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createContact(String lastName)
	{
		lastNameEdit.sendKeys(lastName);
		saveBtn.click();
	}
	
	//contact with support
	@FindBy(name = "support_start_date")
	private WebElement startDateEdit;
	
	@FindBy(name = "support_end_date")
	private WebElement endDateEdit;

	public WebElement getStartDateEdit() {
		return startDateEdit;
	}

	public WebElement getEndDateEdit() {
		return endDateEdit;
	}
	
	public void createContactWithSupportDate(String lastName,String startDate,String endDate)
	{
		lastNameEdit.sendKeys(lastName);
		startDateEdit.clear();
		startDateEdit.sendKeys(startDate);
		endDateEdit.clear();
		endDateEdit.sendKeys(endDate);
		saveBtn.click();
		
	}
	
	//contact with Org
	@FindBy(xpath = "//input[@name='account_name']/../img")
	private WebElement addOrgImg;

	@FindBy(name = "search_text")
	private WebElement searchbox;
	
	@FindBy(name = "search_field")
	private WebElement searchDD;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	public WebElement getAddOrgImg() {
		return addOrgImg;
	}

	public WebElement getSearchbox() {
		return searchbox;
	}

	public WebElement getSearchDD() {
		return searchDD;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	@FindBy(xpath = "//a[text()='\"+orgName+\"']")
	private WebElement selectOrg;
	
	public WebElement getSelectOrg() {
		return selectOrg;
	}

	public void addContactWithOrg(String orgName)
	{
		String parentWindow = driver.getWindowHandle();
		addOrgImg.click();
		//swith to child window
		Set<String> allWindow = driver.getWindowHandles();
		allWindow.remove(parentWindow);
		for(String curWindow : allWindow)
		{
			driver.switchTo().window(curWindow);
			searchbox.sendKeys(orgName);
			searchBtn.click();
			driver.findElement(By.linkText(orgName)).click();
		}
		driver.switchTo().window(parentWindow);
		
		
	}
	
	
	
	
}
