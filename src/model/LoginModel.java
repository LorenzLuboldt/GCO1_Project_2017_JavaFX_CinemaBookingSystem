package model;
import java.sql.*;

import application.SqliteConnection;
public class LoginModel {

	Connection connection;
	
	public LoginModel () {
		connection = SqliteConnection.Connector();
	if (connection == null) System.exit(1);	
	}
	
	public boolean isDBconnected(){
		try {
			return !connection.isClosed();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin(String user, String password) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
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
			return false;
		}
		
		finally {
			preparedStatement.close();
			resultSet.close();
			
		}
	}
}
