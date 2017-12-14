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
	
	
	/**
	 * Purpose: getAllBookings retrieves an ObservableList with all bookings as objects and their corresponding field values
	 * stored in variables.
	 */
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
		    	booking.setSeatID(results.getString("seat_ID"));
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

	
	/**
	 * Purpose: getCustomerBookings queries the data base and retrieves an ObservableList with all bookings made by a specific customer.
	 */
	public ObservableList<Booking> getCustomerBookings(int custID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store customers from DB
	    ObservableList<Booking> customerBookingList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT date_id, time_int, time_string, film_title, seat_id, booking_id "
	    	+ "FROM booking "
	    	+ "INNER JOIN screening ON booking.screening_id=screening.screening_id;"
	    	+ "WHERE "
	    	+ "booking.cust_id = " + custID + ";";
	    	
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Booking booking = new Booking();
		    	
		    	booking.setDateID(results.getString("date_ID"));
		    	booking.setTimeInt(results.getInt("time_int"));
		    	booking.setTimeString(results.getString("time_string"));
		    	booking.setFilmTitle(results.getString("film_title"));
		    	booking.setSeatID(results.getString("seat_id"));
		    	booking.setBookingID(results.getInt("booking_ID"));
		    	booking.setCustID(custID);

		    	customerBookingList.add(booking);
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
	    return customerBookingList;
	 }
	
	
	
	/**
	 * Purpose: getSpecificBookings queries the data base and retrieves a booking object provided a specific booking ID.
	 */
	public Booking getBooking(int bookingID) {
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    Booking booking = null;
	    
	    try	{
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM booking WHERE booking_id = " + bookingID;
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	booking = new Booking();
            	
            	booking.setCustID(results.getInt("cust_ID"));
		    	booking.setSeatID(results.getString("seat_ID"));
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
	

	/**
	 * Purpose: addBookings queries the data base and adds a new booking.
	 */
	public void addBooking(int custID, String seatID, int screeningID) 	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "INSERT INTO booking (cust_id, seat_id, screening_id)" 
			+ "VALUES (" + custID + ", '" + seatID + "', " + screeningID + ")";
	     
		    // Run query
		    st.executeUpdate(query);
		    
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

	
	/**
	 * Purpose: deleteBookings queries the data base and deletes a specific booking based on a booking ID.
	 */
	
	public void deleteBooking(int bookingID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM booking WHERE booking_id=" + bookingID;
				    
		    // Run query
		    st.executeUpdate(query);

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
	
	/**
	 * Purpose: checkSeatAvailability returns true if a seat is free and false if it is taken
	 */
	
	public boolean checkSeatAvailability(String seatID, int screeningID)	{
		
		boolean seatIsAvailable = true; // initialize variable
		
		// Establish database connection:
				Connection connection = SqliteConnection.Connector();
			    Statement st = null;   
			    
			 
			    try
			    {
				    st = connection.createStatement();
				    
				    // CHECK IF DATE DOESN'T EXIST IN DATABASE YET
				    ObservableList<Booking> sameBookingList = FXCollections.observableArrayList();
				    ResultSet results = null;

				    String test = "SELECT * FROM booking WHERE seat_id='" + seatID + "' AND screening_id=" + screeningID;
				    
				    results = st.executeQuery(test);
				    
				    while(results.next())	{
				    	
				    	// create and instantiate a customer object
				    	Booking booking = new Booking();
				    	
				    	booking.setBookingID(results.getInt("booking_ID"));
				    	booking.setCustID(results.getInt("cust_ID"));
				    	booking.setSeatID(results.getString("seat_ID"));
				    	booking.setScreeningID(results.getInt("screening_ID"));
	
				
				    	sameBookingList.add(booking);
				    }
				        
				   seatIsAvailable = sameBookingList.isEmpty(); // TRUE, if seat is still available for screeningID
				   
		
				   
			    }
			    catch( SQLException e )
			    {
			    	System.err.println("Exception occured while checking seat availability for seat: " + seatID);
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
				   return seatIsAvailable;
		}
}