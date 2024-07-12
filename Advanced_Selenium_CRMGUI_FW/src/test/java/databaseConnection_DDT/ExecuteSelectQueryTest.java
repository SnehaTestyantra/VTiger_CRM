package databaseConnection_DDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteSelectQueryTest {

	public static void main(String[] args) throws SQLException {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connection established!!!!");
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("select * from project");
		
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3)+" "+result.getString(4)+" "+result.getString(5)+" "+result.getString(6));
		}
		conn.close();
		System.out.println("Connection closed!!!!");

	}

}
