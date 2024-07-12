package assertion;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomeVerify_SoftAssert_Test {

	@Test
	public void homePageTest()
	{
		System.out.println("Start");
		SoftAssert s_assert = new SoftAssert();
		System.out.println("Step-1");
		System.out.println("Step-2");
		s_assert.assertEquals("Home", "Home");//softAssert
//		Assert.assertEquals("Home", "Home");//HardAssert
		System.out.println("Step-3");
		s_assert.assertEquals("Home-CRM", "Home-CRM");
//		Assert.assertEquals("Home-CRM", "Home-CR");
		System.out.println("Step-4");
		s_assert.assertTrue(false);
		System.out.println("Step-5");
		s_assert.assertAll();
		System.out.println("End");
	}
}
