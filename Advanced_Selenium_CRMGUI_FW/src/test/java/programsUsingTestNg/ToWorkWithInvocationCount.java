package programsUsingTestNg;

import org.testng.annotations.Test;

public class ToWorkWithInvocationCount {

	
	@Test(invocationCount = 10)
	public void createOrderTest()
	{
		System.out.println("execute createOrderTest ==> 123");
	}
	
	@Test(enabled = false)
	public void billingOrderTest() {
		System.out.println("execute billingOrderTest ==> 123 "); //will not participate in execution
	}
}
