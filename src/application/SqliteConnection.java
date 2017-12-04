package application;
import java.sql.*;

/**
 * SqliteConnection.java establishes a connection with our SQLite Database (DB).
 * A Connection is invoked whenever the DB is used in other classes.
 * Our DB is called 'cinemabooking.db'
 * 
 * @author Lorenz Luboldt
 */

	public class SqliteConnection {
		
		// Method returns Connection data type
		public static Connection Connector() {
			
			try {
			Class.forName("org.sqlite.JDBC"); // returns Class object associated with org.sqlite.JDBC
			
			// Create an instance of a Connection and instantiate it, passing the location of the DB
			Connection conn = DriverManager.getConnection("jdbc:sqlite:cinemabooking.db");
					return conn;
			}
			
			catch (Exception e) {
				System.out.println(e);
				return null; // whenever an exception / error is caught
			}
		}
	   	} 