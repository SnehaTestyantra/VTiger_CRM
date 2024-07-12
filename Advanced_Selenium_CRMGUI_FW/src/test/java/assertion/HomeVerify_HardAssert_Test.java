package assertion;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class HomeVerify_HardAssert_Test {

	@Test
	public void homePageTest()
	{
		System.out.println("Test Start");
		String expectedPage = "Home";
//		Launch a browser
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");
//		Login
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
//		verify expected result
		String actualTitle = driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
//		if(actualTitle.trim().equals(expectedPage))
//		{
//			System.out.println(actualTitle+"info is verified");
//		}
//		else
//		{
//			System.out.println(actualTitle+"info is not verified");
//		}
		
//	    using assert
		Assert.assertEquals(actualTitle, expectedPage);
		System.out.println("info is verified");
		
//		Logout
		driver.quit();
		System.out.println("TestEnd");
	}
	
	@Test
	public void LogoTest()
	{
		System.out.println("Test Start");
//		Launch a browser
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");
//		Login
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
//		verify expected result
        boolean status = driver.findElement(By.xpath("//img[@src='test/logo/vtiger-crm-logo.gif']")).isEnabled();
//		if(status)
//		{
//			System.out.println("Logo is verified");
//		}
//		else
//		{
//			System.out.println("Logo is not verified");
//		}
		
//	    using assert
		Assert.assertTrue(status);
		System.out.println("Logo is verified");
		
//		Logout
		driver.quit();
		System.out.println("TestEnd");
	}
}
