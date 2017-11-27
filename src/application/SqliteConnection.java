package application;
import java.sql.*;
	
	public class SqliteConnection {
		
		public static Connection Connector() {
			
			try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:cinemabooking.db");
					return conn;
			}
			
			catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
	   	}