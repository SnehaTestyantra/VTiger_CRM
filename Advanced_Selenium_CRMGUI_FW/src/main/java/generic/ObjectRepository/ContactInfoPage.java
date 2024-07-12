package generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	WebDriver driver = null;

	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "dtlview_Organization Name")
	private WebElement expectedOrg;
	

	public WebElement getExpectedOrg() {
		return expectedOrg;
	}
	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']")
	private WebElement orgInConVerify;

	public WebElement getOrgInConVerify() {
		return orgInConVerify;
	}
	

}
