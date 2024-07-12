package programsUsingTestNg;

import org.testng.annotations.Test;

public class ToWorkWithDependsOnMethods {
//	@Test
//	public void createOrderTest()
//	{
//		System.out.println("execute createOrderTest ==> 123");
//		String str = null;
//		System.out.println(str.equals("123"));
//	}
//	
//	@Test(dependsOnMethods = "createOrderTest")
//	public void billingOrderTest() {
//		System.out.println("execute billingOrderTest ==> 123 "); ///skipped
//	}

	@Test
	public void createContactTest()
	{
		System.out.println("executed createContact with ==> HDFC");
	}

	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest()
	{
		System.out.println("executed modifyContact from ==> HDFC>>ICICI");
	}
	
	@Test(dependsOnMethods = "modifyContactTest")
	public void deleteContactTest()
	{
		System.out.println("executed deleteContact ==> ICICI");
	}
}
