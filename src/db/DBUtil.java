package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static synchronized Connection getConnection() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/math_facts";
		String username = "math_user";
		String password = "sesame";
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
		
	}

}
