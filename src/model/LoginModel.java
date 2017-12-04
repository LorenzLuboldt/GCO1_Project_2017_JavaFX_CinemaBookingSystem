package model;
import java.sql.*;

import application.SqliteConnection;
public class LoginModel {

	Connection connection; // creating an instance of DB connection
	
	public LoginModel () {
		connection = SqliteConnection.Connector(); // calling the Connector method we created in SqliteConnection.java
	if (connection == null) System.exit(1);	// error handling
	}
	
	public boolean isDBconnected(){
		try {
			return !connection.isClosed(); // returns true if connection to DB is not closed 
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false; // if connection to DB is closed
		}
	}
	
	public boolean isLogin(String user, String password) throws SQLException {
		
		// create and initialize preparedStatement object
		PreparedStatement preparedStatement = null;
		
		// create and initialize ResultSet object
		ResultSet resultSet = null;
		
		// SQL query
		String query = "SELECT * FROM customerLogin WHERE user_name = ? AND password = ?";
		
		try{
			preparedStatement = connection.prepareStatement(query); // prepareStatement method takes SQL query string as input
			preparedStatement.setString(1, user); // 1 is the index, 'user' is the parameter
			preparedStatement.setString(2, password); // 2 is the index, 'password' is the parameter
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			
			else{
				return false; 
			}
		}
		
		catch (Exception e) {
			return false;
		}
		
		finally {
			preparedStatement.close();
			resultSet.close();
			
		}
	}
}
