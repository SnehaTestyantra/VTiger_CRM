package propertiesFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream("./configAppData/commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		WebDriver driver = null;
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
		  driver.get(URL);
		  driver.manage().window().maximize();
		  
		  //Login 
		  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		  driver.findElement(By.id("submitButton")).click();
		 
//		  //click on organization
		  driver.findElement(By.linkText("Organizations")).click(); 
		  driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		  driver.findElement(By.name("accountname")).sendKeys("TYSS_indiqube1");
		  Thread.sleep(2000);
		  driver.findElement(By.name("button")).click();
//		  driver.findElement(By.partialLinkText("Organization Information")).getText();
		  Thread.sleep(3000);
//		  driver.quit();
		 

	}

}
