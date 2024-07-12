package databaseConnection_DDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class CreateProjectAndVerifyDataInDBWithGUI {

	public static void main(String[] args) throws SQLException {
		String expectedProjName="INS_3583";
		WebDriver driver = new ChromeDriver();
		driver.get("http://106.51.90.215:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
//		login to application
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
//		click on "create project"
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		
//		fill the credentials
		driver.findElement(By.name("projectName")).sendKeys(expectedProjName);
		driver.findElement(By.name("createdBy")).sendKeys("Myna");
		
		WebElement status = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		Select sel=new Select(status);
		sel.selectByValue("On Going");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
//		verify the project in DB using JDBC
		boolean flag=false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connection Established!!!");
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("select * from project");
		
		while(result.next())
		{
			String actualProjName=result.getString(4);
			if(actualProjName.equals(expectedProjName))
			{
				flag=true;
				System.out.println(expectedProjName+" is available in DB ==> PASS");
			}
		}
		if(flag==false)
		{
			System.out.println(expectedProjName+" is not available in DB ==> FAIL");
		}
		
		conn.close();
		System.out.println("Connection closed!!!");
		
		
		

	}

}
