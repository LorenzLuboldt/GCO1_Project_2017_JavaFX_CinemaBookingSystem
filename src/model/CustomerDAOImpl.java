package model;

import java.sql.*;

import application.SqliteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** 
 * Purpose:
 * The DAO interface lists methods to CRUD (create, get, update, delete) the data in the 
 * corresponding table. DAO serves to separate to decouple persistence storage (DB) information
 * from the rest of the application.
 * 
 * References:
 * http://www.swtestacademy.com/database-operations-javafx/
 * https://www.youtube.com/watch?v=H1mePFyqqiE&t=18s
 * 
 * @author Michael Aring
 * @version 04/12/17
 *
 */
public class CustomerDAOImpl implements CustomerDAO {
	
	
	/**
	 * Purpose: Method queries data base and retrieves an observable list of all customers.
	 */
	public ObservableList<Customer> getAllCustomers()	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store customers from DB
	    ObservableList<Customer> custList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM customer";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Customer customer = new Customer();
		    	
		    	// customer.setCustID(results.getInt("cust_ID"));
		    	customer.setCustEmail(results.getString("cust_email"));
		    	customer.setFirstName(results.getString("cust_firstname"));
		    	customer.setLastName(results.getString("cust_lastname"));
		
		    	custList.add(customer);
		    }
		 
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while fetching customer data: ");
	    	e.printStackTrace();
	    }

	    finally
	    {
	      try
	      {
	        if( connection != null )
	        {
	          connection.close();
	        }

	        if( results != null )
	        {
	          results.close();
	        }
	      }
	      catch( Exception exe )
	      {
	        exe.printStackTrace();
	      }

	    }
	    return custList;
	  }
	
	/**
	 * Purpose: Method queries data base and retrieves one customer as an object, identified by the customer ID
	 */
	public Customer getCustomer(int custID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    Customer customer = null;
	    
	    try	{
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM customer WHERE cust_id = " + "'" + custID + "'";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	customer = new Customer();
		    	customer.setCustEmail(results.getString("cust_email"));
		    	customer.setFirstName(results.getString("cust_firstname"));
		    	customer.setLastName(results.getString("cust_lastname"));
            }
            
                        
        } catch (SQLException e) {
            System.err.println("While searching the customer " + custID  + " an error occurred: ");
            e.printStackTrace();
        }
	    finally
	    {
	      try
	      {
	        if( connection != null )
	        {
	          connection.close();
	        }

	        if( results != null )
	        {
	          results.close();
	        }
	      }
	      catch( Exception exe )
	      {
	        exe.printStackTrace();
	      }

	    }
	    return customer;
	  }
	
	  
	/**
	 * Purpose: Method updates customer information for one specific customer in the data base
	 */
	public void updateCustomer(int custID, String custEmail, String custFirstName, String custLastName)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "UPDATE customer SET cust_email=" + "'" + custEmail + "'," 
			+ "cust_firstname=" + "'" + custFirstName + "'," + "cust_lastname=" + "'" + custLastName +"' WHERE cust_id=" + custID;
	    	
		    // Run query
		    st.executeUpdate(query);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while updating new customer: " + custFirstName + " " + custLastName);
	    	e.printStackTrace();
	    }

	    finally
	    {
	      try
	      {
	        if( connection != null )
	        {
	          connection.close();
	        }
	      }
	      catch( Exception e )
	      {
	        e.printStackTrace();
	      }
	    }
	}
	
	/**
	 * Purpose: Method deletes a customer in the database, provided a customer ID
	 */
	public void deleteCustomer(int custID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM customer WHERE cust_id=" + "'" + custID + "'";
				    
		    // Run query
		    st.executeUpdate(query);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while deleting customer: " + custID);
	    	e.printStackTrace();
	    }

	    finally
	    {
	      try
	      {
	        if( connection != null )
	        {
	          connection.close();
	        }
	      }
	      catch( Exception e )
	      {
	        e.printStackTrace();
	      }
	    }
	}
	
	/**
	 * Purpose: Method queries data base and returns true if user name password combination is correct, false if incorrect.
	 */
	public boolean checkPassword(String userName, String passWord)	{

	Boolean check = false;
	
	// Establish database connection:
	Connection connection = SqliteConnection.Connector();
    Statement st = null;
    
    ResultSet results = null;
    Customer customer = null;
    
    try	{
	    st = connection.createStatement();
    	
	    // Create ObservableList to store customers from DB
	    ObservableList<Customer> custList = FXCollections.observableArrayList();
	    
		// SQL query, stored in String
    	String query = "SELECT * FROM customer WHERE cust_username = " + "'" + userName + "' AND cust_password= '" + passWord + "'";
			    
	    // Run query and save results in ResultSet
	    results = st.executeQuery(query);
	    
        while(results.next())	{
        	customer = new Customer();
	    	customer.setCustEmail(results.getString("cust_email"));
	    	customer.setFirstName(results.getString("cust_firstname"));
	    	customer.setLastName(results.getString("cust_lastname"));
	    	customer.setUserName(results.getString("cust_username"));
	    	customer.setPassWord(results.getString("cust_password"));

		    	custList.add(customer);
            }
            
            
            if(custList.size()>0)	{
            	check = true;
            	
            }
            else {
            	check = false;
            }
            
                        
        } catch (SQLException e) {

            e.printStackTrace();
        }
	    finally
	    {
	      try
	      {
	        if( connection != null )
	        {
	          connection.close();
	        }

	        if( results != null )
	        {
	          results.close();
	        }
	      }
	      catch( Exception exe )
	      {
	        exe.printStackTrace();
	      }
	    }
	    return check;
	  }
	
}