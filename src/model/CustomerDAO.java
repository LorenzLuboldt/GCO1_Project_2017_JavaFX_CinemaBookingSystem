package model;

import java.sql.*;

import application.SqliteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** 
 * 
 * 
 * @author Michael Aring
 * @version 27/11/17
 *
 */

public class CustomerDAO {
	
	static Connection connection = SqliteConnection.Connector();
	
	// method retrieves customer information and stores it in ObservableList
	public ObservableList<Customer> getCustInfo()	{
		
		try	{
			
			// SQL query, stored in String
			String query = "SELECT * FROM users";
		
			 // Java statement
		    Statement st = connection.createStatement();
		    
		    // Run query and save results in ResultSet
		    ResultSet results = st.executeQuery(query);
		    
		    ObservableList<Customer> custList = FXCollections.observableArrayList();
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Customer cust = new Customer();
		    	
		    	cust.setFirstName(results.getString(FIRST_NAME)); // table still has to be created in SQL
		    	cust.setSurName(results.getString(SUR_NAME)); 
		    	cust.setEmail(results.getString(EMAIL));  
		
		    	custList.add(cust);
		    }
		    
		    return custList;
		}
		
		catch (Exception e)	{
			System.err.println("Exception occured while fetching customer data");
		    System.err.println(e.getMessage());
		}
	}
	
	// method updates customer information
	public static void UpdateCust(String firstName, String surName, String email, String custID)	{
		
		try {
			// SQL query stored in string
			String query = "UPDATE customers SET FIRST_NAME='" + firstName + "', SUR_NAME='" + surName + "', EMAIL='" + email 
					+ "' where CUST_ID='" + custID + "'" ;
			
			 // Java statement
		    Statement st = connection.createStatement();
		    
		    // Run update query
		    st.executeQuery(query);	
		}
		
		catch (Exception e)	{
			System.err.println("Exception occured while updating customer data");
		    System.err.println(e.getMessage());
		}
		
		
		
	}
}
