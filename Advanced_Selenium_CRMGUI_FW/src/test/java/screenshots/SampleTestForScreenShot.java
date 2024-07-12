package screenshots;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenShot {

	@Test
	public void amazonTest() throws IOException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		File src = edriver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./errorShots/test.png"));
	}
}
