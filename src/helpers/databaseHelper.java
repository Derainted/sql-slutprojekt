package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseHelper {
	
	public static String driver = "jdbc";
	public static String dbType = "mysql";
	public static String server = "localhost";
	public static int portNo = 3306;
	
	public static Connection DbConnect(String database) {
		String constr = driver + ":" + 
				dbType + "://" + 
				server +  ":" + portNo +
				 "/" + database;
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(constr, "root", "Ojala990829");
		} catch (SQLException e) {
			System.out.println("Something went wrong! Database cant connect");
			e.printStackTrace();
		}

		return conn;
	}

}
