package programsUsingTestNg;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.fileUtility.ExcelUtility;

public class ToGetDataFromExcel_DP {

	@DataProvider
	public Object[][] getdata() throws EncryptedDocumentException, IOException {
		ExcelUtility eLib = new ExcelUtility();
		int rowCount = eLib.getRowCount("DP");
		Object[][] objArr =  new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++)
		{
			objArr[i][0] = eLib.getDataFromExcel("DP", i+1, 0);
			objArr[i][1] = eLib.getDataFromExcel("DP", i+1, 1);
		}
		return objArr;
	}
	
	@Test(dataProvider = "getdata")
	public void getProductInfo(String brandName,String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
//		search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
//		capture product info
		String path = "//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div/div/div/a/span/span[2]/span[2]" ;
		String price = driver.findElement(By.xpath(path)).getText();
		System.out.println(price);
		
		
	}
}
