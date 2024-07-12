package programsUsingTestNg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ToWorkWithDataProvider {

	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][3];
		objArr[0][0] = "Deepak";
		objArr[0][1] = "HR";
		objArr[0][2] = 9036564554l;
		objArr[1][0] = "Sam";
		objArr[1][1] = "Hp";
		objArr[1][2] = 9611804554l;
		objArr[2][0] = "John";
		objArr[2][1] = "Smith";
		objArr[2][2] = 6361947165l;
		return objArr;
	}
	
	@Test(dataProvider = "getData")
	public void createContactTest(String Firstname,String Lastname,long PhoneNo)
	{
		System.out.println("Firstname : "+Firstname+" , Lastname : "+Lastname+" , phone No : "+PhoneNo);
		
		
	}
}
