package runtimeDDT_cmdline;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateOrg_UsingCMD {

	@Test
	public void parameterFromCMD() throws InterruptedException, EncryptedDocumentException, IOException
	{
//		read data from cmd line
		String BROWSER = System.getProperty("browser");
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");

//		generate random no
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
//		read testScript from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Sneha\\Desktop\\Data\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(1);
		String orgName = row.getCell(2).toString()+randomInt;
		
		ChromeOptions option= new ChromeOptions();
		option.addArguments("--incognito");
		WebDriver driver = null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver(option);
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
			driver = new ChromeDriver(option);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(URL);
		//		  Login 
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//		  click on organization
	driver.findElement(By.linkText("Organizations")).click(); 
		//		  click on create organization
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		driver.findElement(By.name("button")).click();
		
//		Logout
//		Actions action = new Actions(driver);
//		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
//		wait.until(ExpectedConditions.visibilityOf(administrator));
//		action.moveToElement(administrator).perform();
//		WebElement signout = driver.findElement(By.partialLinkText("Sign Out"));
//		action.moveToElement(signout).click().perform();
		
		driver.quit();

		 
	}
}
