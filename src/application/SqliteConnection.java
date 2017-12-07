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
			
			Connection conn = null;
			
			try {
			Class.forName("org.sqlite.JDBC"); // returns Class object associated with org.sqlite.JDBC
			
			// Create an instance of a Connection and instantiate it, passing the location of the DB
			conn = DriverManager.getConnection("jdbc:sqlite:cinemabooking.db");
			System.out.println("Connection to SQLite has been established.");
					return conn;
			}
			
			catch (Exception e) {
				System.out.println(e);
				return null; // whenever an exception / error is caught
			}
			
			// @Michael: added part below to close connection and prevent [SQLITE_BUSY] exception
//			finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//            	System.out.println("An SQL Excpetion was detected: ");
//            	System.out.println(ex.getMessage());
//            }
        }
		}