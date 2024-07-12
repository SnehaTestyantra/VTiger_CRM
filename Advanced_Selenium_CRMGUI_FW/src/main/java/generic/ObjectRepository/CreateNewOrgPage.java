package generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrgPage {
	WebDriver driver = null;

	public CreateNewOrgPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgNameEdit;

	@FindBy(name = "button")
	private WebElement saveBtn;

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	//orgname
	public void createOrg(String orgName) {
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}
	@FindBy(id = "phone")
	private WebElement phoneNum;

	public WebElement getPhoneNum() {
		return phoneNum;
	}
	//orgname , phone
	public void createOrg(String orgName,String phoneNumber) {
		orgNameEdit.sendKeys(orgName);
		phoneNum.sendKeys(phoneNumber);
//		saveBtn.click();
	}
	//orgname,ind,type
	@FindBy(name = "industry")
	private WebElement industryDD;

	@FindBy(name = "accounttype")
	private WebElement typeDD;

	public void createOrg(String orgName,String industry,String type) {
		orgNameEdit.sendKeys(orgName);
		Select sel = new Select(industryDD);
		sel.selectByVisibleText(industry);
		Select sel1 = new Select(typeDD);
		sel1.selectByValue(type);
		saveBtn.click();
	}









}
