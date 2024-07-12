package Listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic.baseTest.BaseClass;

@Listeners(generic.ListenerUtility.ListenerImpClass.class)
public class InvoiceTest extends BaseClass{

	@Test
	public void createInvoiceTest()
	{
		System.out.println("execute createInvoiceTest");
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
//		boolean expTitle = actualTitle.contains("Home");
		Assert.assertEquals(actualTitle,"Home");
//		Assert.assertEquals(actualTitle, "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM");
		System.out.println("Step - 1");
		System.out.println("Step - 2");

	}
	@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println("execute createInvoiceWithContactTest");
		System.out.println("Step-1");
		System.out.println("Step-2");

	}
	
//	@Test(retryAnalyzer = generic.ListenerUtility.RetryListenerImp.class)
//	public void activateSim()
//	{
//		System.out.println("execute activateSim");
//		Assert.assertEquals(" ", "Login");
//		System.out.println("Step-1");
//		System.out.println("Step-2");
//	}
}
