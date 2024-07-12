package programsUsingTestNg;

import org.testng.annotations.Test;

public class ToWorkWithPriority {
	//we should not create dependency
//	@Test(priority = 1)
	@Test
	public void createContactTest()
	{
		System.out.println("executed createContact with ==> HDFC");
	}

//	@Test(priority = 2)
	@Test
	public void modifyContactTest()
	{
//		System.out.println("executed modifyContact from ==> HDFC>>ICICI");
		System.out.println("excute query ,insert contact in DB ==> ICICI");
		System.out.println("executed modifyContact from ==> ICICI>>YES BANK");
	}
	
//	@Test(priority = 3)
	@Test
	public void deleteContactTest()
	{
		System.out.println("excute query ,insert contact in DB ==> UPI");
		System.out.println("executed deleteContact ==> UPI");
	}
}
