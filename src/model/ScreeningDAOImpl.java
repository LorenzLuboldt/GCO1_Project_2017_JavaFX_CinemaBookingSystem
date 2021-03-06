package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
public class ScreeningDAOImpl implements ScreeningDAO {
	
	/**
	 * Purpose: Method queries data base and retrieves an observable list of all screenings.
	 */
	public ObservableList<Screening> getAllScreenings()	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store films from DB
	    ObservableList<Screening> screeningList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM screening";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Screening screening = new Screening();
		    	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeInt(results.getInt("time_int")); 
		    	screening.setTimeString(results.getString("time_string"));
		    	screening.setFilmTitle(results.getString("film_title"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		    	screening.setBookedSeats(results.getInt("booked_seats"));
		    	screening.setAvailableInfo(results.getString("available_info"));
		    	screening.setOccupancyRate(results.getString("occupancy_rate"));
		    	screening.setTicketStatus(results.getString("ticket_status"));
		
		    	screeningList.add(screening);
		    }
		 
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while fetching screening data: ");
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
	    	  System.out.println("getAllScreenings() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    return screeningList;
	  }
	
	
	/**
	 * Purpose: Method queries data base and retrieves an observable list of all upcoming screenings.
	 */
	public ObservableList<Screening> getUpcomingScreenings()	{
		String t; // time
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store films from DB
	    ObservableList<Screening> screeningList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM screening ORDER BY year_id , month_id ASC, day_id ASC";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Screening screening = new Screening();
		    	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeInt(results.getInt("time_int")); 
		    	screening.setTimeString(results.getString("time_string"));
		    	screening.setFilmTitle(results.getString("film_title"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		    	screening.setBookedSeats(results.getInt("booked_seats"));
		    	screening.setAvailableInfo(results.getString("available_info"));
		    	screening.setOccupancyRate(results.getString("occupancy_rate"));
		    	screening.setTicketStatus(results.getString("ticket_status"));
		    	
		    	// Validate time and add to screening list only, if date today is today or in the future
		    	t = screening.getTimeInt() + ":00";
		    	LocalTime time = LocalTime.parse(t);
		    	LocalDate date = LocalDate.parse(screening.getDateID());
		    	if (date.isEqual(LocalDate.now()) && time.isAfter(LocalTime.now()) || date.isAfter(LocalDate.now()))	{
		    		screeningList.add(screening);
		    	}
		    }
		 
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while fetching screening data: ");
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
	    	  System.out.println("getAllScreenings() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    return screeningList;
	  }

	/**
	 * Purpose: Method queries data base and retrieves an observable list of screenings that were in the past.
	 */
		public ObservableList<Screening> getPastScreenings()	{
			String t; // time
			
			// Establish database connection:
			Connection connection = SqliteConnection.Connector();
			Statement st = null;
		    
		    // Create ObservableList to store films from DB
		    ObservableList<Screening> screeningList = FXCollections.observableArrayList();
		    
		    ResultSet results = null;
			
		    try
		    {
			    st = connection.createStatement();
		    	
				// SQL query, stored in String
		    	String query = "SELECT * FROM screening ORDER BY year_id , month_id ASC, day_id ASC";
					    
			    // Run query and save results in ResultSet
			    results = st.executeQuery(query);
			    
			    while(results.next())	{
			    	
			    	// create and instantiate a customer object
			    	Screening screening = new Screening();
			    	
			    	screening.setScreeningID(results.getInt("screening_id"));
			    	screening.setDateID(results.getString("date_id"));
			    	screening.setYearID(results.getInt("year_id"));
			    	screening.setMonthID(results.getInt("month_id"));
			    	screening.setDayID(results.getInt("day_id"));
			    	screening.setTimeInt(results.getInt("time_int")); 
			    	screening.setTimeString(results.getString("time_string"));
			    	screening.setFilmTitle(results.getString("film_title"));
			    	screening.setAvailableSeats(results.getInt("available_seats"));
			    	screening.setBookedSeats(results.getInt("booked_seats"));
			    	screening.setAvailableInfo(results.getString("available_info"));
			    	screening.setOccupancyRate(results.getString("occupancy_rate"));
			    	screening.setTicketStatus(results.getString("ticket_status"));
			    	
			    	// Validate time and add to screening list only, if date is not today or in the future
			    	t = screening.getTimeInt() + ":00";
			    	LocalTime time = LocalTime.parse(t);
			    	LocalDate date = LocalDate.parse(screening.getDateID());
			    	if (date.isEqual(LocalDate.now()) && time.isAfter(LocalTime.now()) || date.isAfter(LocalDate.now()))	{
			    	}
			    	else { screeningList.add(screening); }
			    }
			 
		    }
		    catch( SQLException e )
		    {
		    	System.err.println("Exception occured while fetching screening data: ");
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
		    	  System.out.println("getAllScreenings() --> error has been caught");
		        exe.printStackTrace();
		      }

		    }
		    return screeningList;
		  }

	
		
	/**
	 * Purpose: Method queries data base and retrieves a screening object based on a screening ID
	 */
	public Screening getScreening(int screeningID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    Screening screening = null;
	    
	    try	{
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM screening WHERE screening_id = " + "'" + screeningID + "'";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	screening = new Screening();
            	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeInt(results.getInt("time_int")); 
		    	screening.setTimeString(results.getString("time_string"));
		    	screening.setFilmTitle(results.getString("film_title"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		    	screening.setBookedSeats(results.getInt("booked_seats"));
		    	screening.setAvailableInfo(results.getString("available_info"));
		    	screening.setOccupancyRate(results.getString("occupancy_rate"));
		    	screening.setTicketStatus(results.getString("ticket_status"));
            	
            }
                        
        } catch (SQLException e) {
            System.err.println("While searching the screening " + screeningID  + " an error occurred: ");
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
	    return screening;
	  }
	
	
	
	
		/**
		 * Purpose: Method queries screenings table in data base and retrieves an observable list of screenings on a given date
		 * in the future.
		 * 
		 */
	
		// *** SEARCH SCREENINGS BY DATE ***
		public ObservableList<Screening> searchScreeningsByDate(String dateID)	{
		String t; // time
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store films from DB
	    ObservableList<Screening> screeningList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM screening WHERE date_id='" + dateID + "' ORDER BY time_int ASC";

	    	
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Screening screening = new Screening();
		    	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeInt(results.getInt("time_int")); 
		    	screening.setTimeString(results.getString("time_string"));
		    	screening.setFilmTitle(results.getString("film_title"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		    	screening.setBookedSeats(results.getInt("booked_seats"));
		    	screening.setAvailableInfo(results.getString("available_info"));
		    	screening.setOccupancyRate(results.getString("occupancy_rate"));
		    	screening.setTicketStatus(results.getString("ticket_status"));
		
		    	// Validate time and add to screening list only, if date today is today or in the future
		    	t = screening.getTimeInt() + ":00";
		    	LocalTime time = LocalTime.parse(t);
		    	LocalDate date = LocalDate.parse(screening.getDateID());
		    	if (date.isEqual(LocalDate.now()) && time.isAfter(LocalTime.now()) || date.isAfter(LocalDate.now()))	{
		    		screeningList.add(screening);
		    	}
		    }
		 
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while fetching screening data: ");
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
	    	  System.out.println("searchScreeningsByFilm() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    return screeningList;
	  }

	/**
	 * Purpose: Method adds a new screening to the data base.
	 */
	
	// *** ADD SCREENING ***	
	public boolean addScreening(String dateID, int yearID, int monthID, int dayID, String timeString, String filmTitle) 	{
		
		boolean checkIfSlotIsFree = true; 
		
		// Create variables not entered by user
		int timeInt, availableSeats, bookedSeats;
		String availableInfo, ticketStatus, occupancyRate;
		
	    // Compute information of variables not entered by user	    
	    timeInt = computeTimeInt(timeString);
	    availableSeats = 16;	 
	    bookedSeats = 0;
	    availableInfo = computeAvailableInfo(availableSeats);
	    occupancyRate = computeOccupancyRate(bookedSeats);
	    ticketStatus = computeTicketStatus(availableSeats);
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;     	  
	    try
	    {
		    st = connection.createStatement();
		    
		    // CHECK IF DATE DOESN'T EXIST IN DATABASE YET
		    ObservableList<Screening> sameScreeningList = FXCollections.observableArrayList();
		    ResultSet results = null;

		    String test = "SELECT * FROM screening WHERE date_id='" + dateID + "' AND time_int=" + timeInt;
		    
		    results = st.executeQuery(test);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Screening screening = new Screening();
		    	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeInt(results.getInt("time_int")); 
		    	screening.setTimeString(results.getString("time_string"));
		    	screening.setFilmTitle(results.getString("film_title"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		    	screening.setBookedSeats(results.getInt("booked_seats"));
		    	screening.setAvailableInfo(results.getString("available_info"));
		    	screening.setOccupancyRate(results.getString("occupancy_rate"));
		    	screening.setTicketStatus(results.getString("ticket_status"));
		
		    	sameScreeningList.add(screening);
		    }
		        
		   boolean sameCheck = sameScreeningList.isEmpty();
		   
		    
		    // IF THE SCREENING HASN'T BEEN TAKEN, PURSUE THE ADD METHOD
		    if(sameCheck)	{
		    
				// SQL query, stored in String
		    	String query = "INSERT INTO screening (date_id, year_id, month_id, day_id, time_int, time_string, film_title, available_seats, booked_seats, available_info, occupancy_rate, ticket_status) VALUES ('" + dateID + "', " + yearID + "," + monthID + "," + 
		    			dayID + "," + timeInt + ",'" + timeString + "', '"+ filmTitle + "'," + availableSeats + "," + bookedSeats + ",'" + availableInfo + "', '" + occupancyRate + "', '" + ticketStatus + "')";
		     
			    // Run query
			    st.executeUpdate(query);
			    return checkIfSlotIsFree;
		    }
			
		    
		    // OTHERWISE, DON'T ADD
		    else	{
			    checkIfSlotIsFree = false;
			    return checkIfSlotIsFree;
			}
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while adding new screening " + timeInt);
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
	    return checkIfSlotIsFree;
	  }
	  
	
	/**
	 * Purpose: Method deletes a screening given a specific screening ID
	 */
	public void deleteScreening(int screeningID)	{	
		
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM screening WHERE screening_id=" + screeningID;
				    
		    // Run query
		    st.executeUpdate(query);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while deleting screening ID: " + screeningID);
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
	 * Purpose: Supporting Method that decreases the number of available seats saved for a screening. It is called whenever a booking is made.
	 */
	public void decreaseAvailableSeats(int screeningID)	{
		
		// Create variables to be updated
		int availableSeats, bookedSeats;
		String availableInfo, ticketStatus, occupancyRate;
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    Screening screening = null;
	    
	    try	{
	    	
		    st = connection.createStatement();
	    	
			// 1. Retrieve selected screening from database
	    	String query = "SELECT * FROM screening WHERE screening_id = " + "'" + screeningID + "'";
				    
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	screening = new Screening();
            	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeInt(results.getInt("time_int")); 
		    	screening.setTimeString(results.getString("time_string"));
		    	screening.setFilmTitle(results.getString("film_title"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		    	screening.setBookedSeats(results.getInt("booked_seats"));
		    	screening.setAvailableInfo(results.getString("available_info"));
		    	screening.setOccupancyRate(results.getString("occupancy_rate"));
		    	screening.setTicketStatus(results.getString("ticket_status"));
            	
            }
            
    	    // Compute information of variables to be updated  
    	    availableSeats = screening.getAvailableSeats() - 1;;	 
    	    bookedSeats = screening.getBookedSeats() + 1;
    	    availableInfo = computeAvailableInfo(availableSeats);
    	    occupancyRate = computeOccupancyRate(bookedSeats);
    	    ticketStatus = computeTicketStatus(availableSeats);    		
            
            // Save updated variables to database
            String aQuery = "UPDATE screening SET available_seats=" + availableSeats + " WHERE screening_id=" + screeningID;
            st.executeUpdate (aQuery);
            
            String bQuery = "UPDATE screening SET booked_seats=" + bookedSeats + " WHERE screening_id=" + screeningID;
            st.executeUpdate(bQuery);
            
            String cQuery = "UPDATE screening SET available_info='" + availableInfo + "' WHERE screening_id=" + screeningID;
            st.executeUpdate(cQuery);
            
            String dQuery = "UPDATE screening SET occupancy_rate='" + occupancyRate + "' WHERE screening_id=" + screeningID;
            st.executeUpdate(dQuery);
            
            String eQuery = "UPDATE screening SET ticket_status='" + ticketStatus + "' WHERE screening_id=" + screeningID;
            st.executeUpdate(eQuery);
            
                        
        } catch (SQLException e) {
            System.err.println("While updating the screening " + screeningID  + " an error occurred: ");
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
	    	  System.out.println("udpateScreening() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	  }
	
	/**
	 * Purpose: Supporting Method that increases the number of available seats saved for a screening. It is called whenever a booking is deleted.
	 */
	public void increaseAvailableSeats(int screeningID)
	{
		// Create variables to be updated
		int availableSeats, bookedSeats;
		String availableInfo, ticketStatus, occupancyRate;
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    Screening screening = null;
	    
	    try	{
	    	
		    st = connection.createStatement();
	    	
			// 1. Retrieve selected screening from database
	    	String query = "SELECT * FROM screening WHERE screening_id = " + "'" + screeningID + "'";
				    
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	screening = new Screening();
            	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeInt(results.getInt("time_int")); 
		    	screening.setTimeString(results.getString("time_string"));
		    	screening.setFilmTitle(results.getString("film_title"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		    	screening.setBookedSeats(results.getInt("booked_seats"));
		    	screening.setAvailableInfo(results.getString("available_info"));
		    	screening.setOccupancyRate(results.getString("occupancy_rate"));
		    	screening.setTicketStatus(results.getString("ticket_status"));
            	
            }
            
    	    // Compute information of variables to be updated  
    	    availableSeats = screening.getAvailableSeats() + 1;;	 
    	    bookedSeats = screening.getBookedSeats() - 1;
    	    availableInfo = computeAvailableInfo(availableSeats);
    	    occupancyRate = computeOccupancyRate(bookedSeats);
    	    ticketStatus = computeTicketStatus(availableSeats);    		
            
            // Save updated variables to database
            String aQuery = "UPDATE screening SET available_seats=" + availableSeats + " WHERE screening_id=" + screeningID;
            st.executeUpdate (aQuery);
            
            String bQuery = "UPDATE screening SET booked_seats=" + bookedSeats + " WHERE screening_id=" + screeningID;
            st.executeUpdate(bQuery);
            
            String cQuery = "UPDATE screening SET available_info='" + availableInfo + "' WHERE screening_id=" + screeningID;
            st.executeUpdate(cQuery);
            
            String dQuery = "UPDATE screening SET occupancy_rate='" + occupancyRate + "' WHERE screening_id=" + screeningID;
            st.executeUpdate(dQuery);
            
            String eQuery = "UPDATE screening SET ticket_status='" + ticketStatus + "' WHERE screening_id=" + screeningID;
            st.executeUpdate(eQuery);
            
                        
        } catch (SQLException e) {
            System.err.println("While updating the screening " + screeningID  + " an error occurred: ");
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
	    	  System.out.println("udpateScreening() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	}
	
	// HELPER METHODS:
	
	public int computeTimeInt(String timeString)
	{
	    int timeInt = 0;
		
		switch(timeString)	{
    	case "2:00 PM" :
    		timeInt = 14;
    		break;
    	case "3:00 PM" :
    		timeInt = 15;
    		break;
    	case "4:00 PM" :
    		timeInt = 16;
    		break;
    	case "5:00 PM" :
    		timeInt = 17;
    		break;
    	case "6:00 PM" :
    		timeInt = 18;
    		break;
    	case "7:00 PM" :
    		timeInt = 19;
    		break;
    	case "8:00 PM" :
    		timeInt = 20;
    		break;
    	case "9:00 PM" :
    		timeInt = 21;
    		break;
    	case "10:00 PM" :
    		timeInt = 22;
    		break;
    	case "11:00 PM" :
    		timeInt = 23;
    		break;
		}
		
		return timeInt;
		
	}

	public String computeAvailableInfo(int availableSeats)
	{
		String availableInfo = availableSeats + "/16 seats available";
		return availableInfo;
	}
	
	public String computeOccupancyRate(int bookedSeats)
	{
		String occupancyRate = "0.00%";
		
		switch(bookedSeats)	{
			case(1) : 
				occupancyRate = "6.25%";
				break;
			case(2) : 
				occupancyRate = "12.50%";
				break;
			case(3) : 
				occupancyRate = "18.75%";
				break;
			case(4) : 
				occupancyRate = "25.00%";
				break;
			case(5) : 
				occupancyRate = "31.25%";
				break;
			case(6) : 
				occupancyRate = "37.50%";
				break;
			case(7) : 
				occupancyRate = "43.75%";
				break;
			case(8) : 
				occupancyRate = "50.00%";
				break;
			case(9) : 
				occupancyRate = "56.25%";
				break;
			case(10) : 
				occupancyRate = "62.50%";
				break;
			case(11) : 
				occupancyRate = "68.75%";
				break;
			case(12) : 
				occupancyRate = "75.00%";
				break;
			case(13) : 
				occupancyRate = "81.25%";
				break;
			case(14) : 
				occupancyRate = "87.50%";
				break;
			case(15) : 
				occupancyRate = "93.75%";
				break;
			case(16) : 
				occupancyRate = "100.00%";
				break;
		}
		return occupancyRate;
	}

	public String computeTicketStatus(int availableSeats)
	{
		String availableInfo = null;
		
		if (availableSeats == 0)	{
			availableInfo = "Sold out";
		}
		else {
			availableInfo = availableSeats + " Tickets available";
		}
		
		return availableInfo;
	}
}

