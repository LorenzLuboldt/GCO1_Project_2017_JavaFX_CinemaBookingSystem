package model;
import java.sql.*;

import application.SqliteConnection;

/**
 * Purpose: Class provides methods in order to check whether customer or manager is registered with a username & password combination in the underlying database tables. 
 * In this "Model" class query statements to compare database entries with the provided login data are written.
 * @author Lorenz
 *
 * Tutorials & resources: https://www.youtube.com/watch?v=NWcFTTbKbLs&index=31&list=PLS1QulWo1RIaUGP446_pWLgTZPiFizEMq, http://www.sqlitetutorial.net/sqlite-java/create-database/ 
 */
public class LoginModel {

	Connection connection;
	
	public LoginModel () {
		connection = SqliteConnection.Connector();
	if (connection == null) System.exit(1);	
	}
	
	public boolean isDBconnected(){
		try {
			System.out.println("LoginModel is checking whether SQLite Connection is not closed.");
			return !connection.isClosed();
		}
		catch (SQLException e) {
			System.out.println("LoginModel caught Exception " + e + "while running isDBconnected().");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLoginCustomer(String user, String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		// SQL Query searching for specific username/password combination in the customerLogin tab
		String query = "SELECT * FROM customerLogin WHERE user_name = ? AND password = ?";
		
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			
			else{
				return false; 
			}
		}
		
		catch (Exception e) {
			System.out.println("Login model (CUSTOMER) caught exception: " + e);
			return false;
		}
		
		finally {
			preparedStatement.close();
			resultSet.close();
			System.out.println("Login model (CUSTOMER) closed preparedStatement and resultSet. ");
		}
	}
	
	public boolean isLoginManager(String user, String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM managerLogin WHERE user_name = ? AND password = ?";
		
		try{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			
			else{
				return false; 
			}
		}
		
		catch (Exception e) {
			System.out.println("Login model (MANAGER) caught exception: "  + e);
			return false;
		}
		
		finally {
			preparedStatement.close();
			resultSet.close();
			System.out.println("Login model (MANAGER) closed preparedStatement and resultSet. ");
			
		}
	}
}
