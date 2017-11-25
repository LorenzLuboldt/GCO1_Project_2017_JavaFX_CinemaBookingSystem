package application;
import java.sql.*;
public class LoginModel {

	Connection connection;
	
	public LoginModel () {
		connection.SQLiteConnection.Connector();
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
}
