package application;

import java.sql.*;

public class SQLiteDataBaseCreation {

	 /**
     * Connect to a new database
     *
     * @param fileName the database file name
     */
	
	public static void createNewDatabase(String fileName) {
		 
        String url = "jdbc:sqlite:C:/Users/Lorenz/Desktop/repos/GCO1_Project_2017_JavaFX_CinemaBookingSystem/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewDatabase("cinemabooking.db");
    }
}
