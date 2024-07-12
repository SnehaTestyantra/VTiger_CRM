package generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {

	Connection conn;
//	First Way : Not mostly used
	public void getDbConnection(String url,String username,String password) 
	{
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn=DriverManager.getConnection(url,username,password);
		}
		catch (Exception e) {
			
		}
	}
	
//	Second way : Best practice
	public void getDbConnection()
	{
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn=DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333","root@%","root");
		}
		catch (Exception e) {
			
		}
	}
	
	public ResultSet executeSelectQuery(String query)
	{
		ResultSet result = null;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		}
		catch (Exception e) {
			
		}
		return result;
	}
	
	public int executeNonSelectQuery(String query)
	{
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		}
		catch (Exception e) {
			 
		}
		return result;
	}
	
	public void closeDbConnection()
	{
		try {
			conn.close();
		}
		catch (Exception e) {
			
		}
	}
}
