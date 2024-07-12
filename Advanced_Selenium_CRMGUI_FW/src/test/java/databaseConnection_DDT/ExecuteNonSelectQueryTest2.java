package databaseConnection_DDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteNonSelectQueryTest2 {

	public static void main(String[] args) throws SQLException {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connection established!!!!");
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate("insert into project values('TY_PROJ_2134','TOM','26-06-2024','FB','On going','100')");
		System.out.println(result);
		
		conn.close();
		System.out.println("Connection closed!!!!");

	}

}
