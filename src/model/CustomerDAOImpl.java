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
	
	// *** 1. GET ALL CUSTOMERS ***
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
		    	
		    	customer.setCustID(results.getInt("cust_ID"));
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
	
	// *** 2. GET SPECIFIC CUSTOMER ***
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
	    	System.out.println(query);
				    
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
	
	// *** 3. ADD CUSTOMER ***	
	public void addCustomer(int custID, String custEmail, String custFirstName, String custLastName) 	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "INSERT INTO customer (cust_id, cust_email, cust_firstname, cust_lastname)" 
			+ "VALUES ('" + custID + "', '" + custEmail + "', '" + custFirstName + "', '" + custLastName + "')";
	     
		    // Run query
		    st.executeUpdate(query);
		    
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while adding new customer " + custID);
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
	  
	// *** 4. UPDATE CUSTOMER ***
	public void updateCustomer(String custEmail, String custFirstName, String custLastName)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "UPDATE customer SET cust_email=" + "'" + custEmail + "'," 
			+ "cust_firstname=" + "'" + custFirstName + "'," + "cust_lastname=" + "'" + custLastName +"';";
	    	
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been updated for customer: " + custFirstName + " " + custLastName);

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
	
	// *** 5. DELETE CUSTOMER ***
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
		    System.out.println("Record has been deleted for customer ID: " + custID);

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
}