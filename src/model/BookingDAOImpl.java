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

public class BookingDAOImpl implements BookingDAO {
	
	// *** 1. GET ALL CUSTOMERS ***
	public ObservableList<Booking> getAllBookings()	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store customers from DB
	    ObservableList<Booking> bookingList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM booking";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Booking booking = new Booking();
		    	
		    	booking.setBookingID(results.getInt("booking_ID"));
		    	booking.setCustID(results.getInt("cust_ID"));
		    	booking.setSeatID(results.getInt("seat_ID"));
		    	booking.setScreeningID(results.getInt("screening_ID"));

		    	bookingList.add(booking);
		    }
		 
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while fetching booking  data: ");
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
	    return bookingList;
	  }
	
	// *** 2. GET SPECIFIC BOOKING ***
	public Booking getBooking(int bookingID) {
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    Booking booking = null;
	    
	    try	{
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM booking WHERE booking_id = " + "'" + bookingID + "'";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	booking = new Booking();
            	
            	booking.setCustID(results.getInt("cust_ID"));
		    	booking.setSeatID(results.getInt("seat_ID"));
		    	booking.setScreeningID(results.getInt("screening_ID"));
            }            
                        
        } catch (SQLException e) {
            System.err.println("While searching the booking " + bookingID  + " an error occurred: ");
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
	    return booking;
	  }
	
	// *** 3. ADD BOOKING ***	
	public void addBooking(int custID, int seatID, int screeningID) 	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "INSERT INTO booking (cust_id, seat_id, screening_id)" 
			+ "VALUES ('" + custID + "', '" + seatID + "', '" + screeningID + "')";
	     
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("New booking has been added to DB.");
		    
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while adding new booking " + custID + ", " + screeningID);
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
//	public void updateCustomer(String custEmail, String custFirstName, String custLastName)	{
//		
//		// Establish database connection:
//		Connection connection = SqliteConnection.Connector();
//	    Statement st = null;
//	    
//	    try
//	    {
//		    st = connection.createStatement();
//		    
//			// SQL query, stored in String
//	    	String query = "UPDATE customer SET cust_email=" + "'" + custEmail + "'," 
//			+ "cust_firstname=" + "'" + custFirstName + "'," + "cust_lastname=" + "'" + custLastName +"';";
//	    	
//		    // Run query
//		    st.executeUpdate(query);
//		    System.out.println("Record has been updated for customer: " + custFirstName + " " + custLastName);
//
//	    }
//	    catch( SQLException e )
//	    {
//	    	System.err.println("Exception occured while updating new customer: " + custFirstName + " " + custLastName);
//	    	e.printStackTrace();
//	    }
//
//	    finally
//	    {
//	      try
//	      {
//	        if( connection != null )
//	        {
//	          connection.close();
//	        }
//	      }
//	      catch( Exception e )
//	      {
//	        e.printStackTrace();
//	      }
//	    }
//	}
	
	// *** 5. DELETE BOOKING ***
	public void deleteBooking(int bookingID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM booking WHERE booking_id=" + "'" + bookingID + "'";
				    
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been deleted for Booking ID: " + bookingID);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while deleting booking: " + bookingID);
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