package generic.webDriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {

	
//	maximise
	public void maximiseTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
//	wait commands
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
//	Switch to windows
	public void switchToTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String actualTitle = driver.getTitle();
			if(actualTitle.contains(partialTitle))
			{
				break;
			}
		}
	}
	
	public void switchToTabOnURL(WebDriver driver,String partialURL)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String actualURL = driver.getTitle();
			if(actualURL.contains(partialURL))
			{
				break;
			}
		}
	}
	
//	switch to frames
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
//	switch to alert
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
//	drop down (Select class)
	public void Select(WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void Select(WebElement element,String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
//	Action class
	public void mouseMoveOnElement(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	

	
}
