package databaseConnection_DDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;



public class SampleUnitTestToCheckProjectInBackend {

	@Test

	public void projectCheckTest() throws SQLException
	{
		
		String expectedProjName = "ProjectName_GUI";
		boolean flag= false;
		Connection conn=null;
		try {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connection established!!!");
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("select * from project");
		
		while(result.next())
		{
			String actualProjName = result.getString(4);
			if(expectedProjName.equals(actualProjName))
			{
				flag=true;
				System.out.println(expectedProjName+" is available ==> PASS");
			}
		}
		}
		catch(Exception e) {
		if(flag==false)
		{
			System.out.println(expectedProjName+" is not available ==> FAIL");
			Assert.fail();
		}
		System.out.println("handled exception");
		}
		finally {
		conn.close();
		System.out.println("connection closed!!!");
		}
	}
}

