package application;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	/**
	 *
	 * @author sqlitetutorial.net
	 */
	public class SQLiteJDBCDriverConnection {
	     /**
	     * Connect to the database
	     */
	    public static void connect() {
	        Connection conn = null;
	        try {
	            // db parameters
	            String url = "jdbc:sqlite:C:\\Users\\Lorenz\\Desktop\\repos\\GCO1_Project_2017_JavaFX_CinemaBookingSystem\\cinemabooking.db";
	            // create a connection to the database
	            conn = DriverManager.getConnection(url);
	            
	            System.out.println("Connection to SQLite has been established.");
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            try {
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
	    }
	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        connect();
	    }
	}
