package application;

import java.sql.*;

public class CreateTable {

	/**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/Lorenz/Desktop/repos/GCO1_Project_2017_JavaFX_CinemaBookingSystem/cinemabooking.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE customer " +
                "(username TEXT PRIMARY KEY NOT NULL," +
                " password CHAR(50) NOT NULL, " + ")"; 
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
    }
}
