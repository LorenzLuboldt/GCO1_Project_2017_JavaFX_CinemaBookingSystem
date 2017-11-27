package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.SqlUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class customerDAO {
	
	// method to receive customer information
	public static ObservableList<customer> getCustInfo() throws SQLException, ClassNotFoundException	{
		
		String selector = "SELECT * FROM customers";
		ResultSet queryResults = SqlUtil.dbExecuteQuery(selector);
		ObservableList<customer> custList;
		return custList;
	}

}
