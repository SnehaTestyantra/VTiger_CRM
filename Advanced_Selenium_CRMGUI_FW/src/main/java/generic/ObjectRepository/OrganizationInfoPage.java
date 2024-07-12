package generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver = null;

	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	create org
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;

	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement orgVerify;

	public WebElement getOrgVerify() {
		return orgVerify;
	}
	
//  org With Ind
	@FindBy(id = "dtlview_Industry")
	private WebElement industry;

	
	@FindBy(id = "dtlview_Type")
	private WebElement type;

	public WebElement getIndustry() {
		return industry;
	}


	public WebElement getType() {
		return type;
	}
	
	//org with phno
	@FindBy(id = "dtlview_Phone")
	private WebElement phoneVerify;

	public WebElement getPhoneVerify() {
		return phoneVerify;
	}

	//contact
	@FindBy(id = "dtlview_Last Name")
	private WebElement lastNameVerify;

	public WebElement getlastNameVerify() {
		return lastNameVerify;
	}
	
	//contact with support date
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement startDateVerify;

	public WebElement getstartDateVerify() {
		return startDateVerify;
	}
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement endDateVerify;

	public WebElement getendDateVerify() {
		return endDateVerify;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
