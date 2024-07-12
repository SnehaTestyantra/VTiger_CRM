package Reports;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class ToWorkWithReporterLog {

	@Test
	public void homePageTest()
	{
		Reporter.log("Test Start");
		Reporter.log("Step-1");
		Reporter.log("Step-2", true);
		Reporter.log("Step-3", true);
		Reporter.log("Step-4", true);
		Reporter.log("Step-5", true);
	}
}
