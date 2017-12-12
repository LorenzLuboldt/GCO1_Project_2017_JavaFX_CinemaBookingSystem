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

public class ScreeningDAOImpl implements ScreeningDAO {
	
	// *** 1. GET ALL SCREENINGS ***
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
	          System.out.println("getAllScreenings() --> connection is closed");
	          connection.close();
	        }

	        if( results != null )
	        {
	        	System.out.println("getAllScreenings() --> results are closed");
	          results.close();
	        }
	      }
	      catch( Exception exe )
	      {
	    	  System.out.println("getAllScreenings() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    System.out.println("All screenings have been retrieved from DB and stored in screeningList");
	    return screeningList;
	  }
	
	
	// *** 2. GET UPCOMING SCREENINGS (RANKED IN ORDER) ***
		public ObservableList<Screening> getUpcomingScreenings()	{
			
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
		          System.out.println("getAllScreenings() --> connection is closed");
		          connection.close();
		        }

		        if( results != null )
		        {
		        	System.out.println("getAllScreenings() --> results are closed");
		          results.close();
		        }
		      }
		      catch( Exception exe )
		      {
		    	  System.out.println("getAllScreenings() --> error has been caught");
		        exe.printStackTrace();
		      }

		    }
		    System.out.println("All screenings have been retrieved from DB and stored in screeningList");
		    return screeningList;
		  }
	
	// *** 2. GET SPECIFIC SCREENING ***
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
	    	System.out.println(query);
				    
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
	        	System.out.println("getScreening() --> connection is closed");
	          connection.close();
	        }

	        if( results != null )
	        {
	        	System.out.println("getScreening() --> results is closed");
	          results.close();
	        }
	      }
	      catch( Exception exe )
	      {
	    	  System.out.println("getScreening() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    return screening;
	  }
	
	
	// *** X. SEARCH SCREENINGS BY FILM ***
		public ObservableList<Screening> searchScreeningsByFilm(int screeningID)	{
			
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
		    	String query = "SELECT * FROM screening WHERE screening_id='" + screeningID + "'";
					    
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
		          System.out.println("searchScreeningsByFilm() --> connection is closed");
		          connection.close();
		        }

		        if( results != null )
		        {
		        	System.out.println("searchScreeningsByFilm() --> results are closed");
		          results.close();
		        }
		      }
		      catch( Exception exe )
		      {
		    	  System.out.println("searchScreeningsByFilm() --> error has been caught");
		        exe.printStackTrace();
		      }

		    }
		    System.out.println("All screenings have been retrieved from DB and stored in screeningList");
		    return screeningList;
		  }
	
	
		// *** SEARCH SCREENINGS BY DATE ***
				public ObservableList<Screening> searchScreeningsByDate(String dateID)	{
					
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
							    
//				    	screening_id, time_string, film_title, ticket_status
				    	
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
				          System.out.println("searchScreeningsByFilm() --> connection is closed");
				          connection.close();
				        }

				        if( results != null )
				        {
				        	System.out.println("searchScreeningsByFilm() --> results are closed");
				          results.close();
				        }
				      }
				      catch( Exception exe )
				      {
				    	  System.out.println("searchScreeningsByFilm() --> error has been caught");
				        exe.printStackTrace();
				      }

				    }
				    System.out.println("All screenings have been retrieved from DB and stored in screeningList");
				    return screeningList;
				  }
			
		
	// *** 3. ADD SCREENING ***	
	public void addScreening(String dateID, int yearID, int monthID, int dayID, String timeString, String filmTitle) 	{
		
		int timeInt, availableSeats;
		String ticketStatus;
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    // Initialize available seats
	    availableSeats = 16;	 
	    
	    // Initialize ticket status:
	    ticketStatus = "Tickets available";
	    
	    // Initialize timeInt
	    
	    timeInt = 0; // if case no switch case fits
	    
	    switch(timeString)	{
	    	case "2:00 PM" :
	    		timeInt = 2;
	    		break;
	    	case "3:00 PM" :
	    		timeInt = 3;
	    		break;
	    	case "4:00 PM" :
	    		timeInt = 4;
	    		break;
	    	case "5:00 PM" :
	    		timeInt = 5;
	    		break;
	    	case "6:00 PM" :
	    		timeInt = 6;
	    		break;
	    	case "7:00 PM" :
	    		timeInt = 7;
	    		break;
	    	case "8:00 PM" :
	    		timeInt = 8;
	    		break;
	    	case "9:00 PM" :
	    		timeInt = 9;
	    		break;
	    	case "10:00 PM" :
	    		timeInt = 10;
	    		break;
	    	case "11:00 PM" :
	    		timeInt = 11;
	    		break;
	    }
	    	  
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
		
		    	sameScreeningList.add(screening);
		    }
		        
		   boolean sameCheck = sameScreeningList.isEmpty();
		   
		    
		    // IF THE SCREENING HASN'T BEEN TAKEN, PURSUE THE ADD METHOD
		    if(sameCheck)	{
		    
		    
				// SQL query, stored in String
		    	String query = "INSERT INTO screening (date_id, year_id, month_id, day_id, time_int, time_string, film_title, available_seats, ticket_status) VALUES ('" + dateID + "', " + yearID + "," + monthID + "," + 
		    			dayID + "," + timeInt + ",'" + timeString + "', '"+ filmTitle + "'," + availableSeats + ", '" + ticketStatus + "')";
		     
			    // Run query
			    st.executeUpdate(query);
			    System.out.println("Screening has been added to DB");
		    }
			
		    
		    // OTHERWISE, DON'T ADD
		    else	{
			    System.out.println("+++ This film slot is already taken. +++");
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
	  }
	  
	// *** 4. UPDATE FILM ***
//	public void updateFilm(int filmID, String filmTitle, String filmDescription)	{
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
//	    	String query = "UPDATE film SET film_title='" + filmTitle + "', film_description=" + "'" + filmDescription + "'" + "where film_id=" + "'" + filmID + "'";
//	    	System.out.println("Record WILL be updated for film id: " + filmID);
//	    	
//		    // Run query
//		    st.executeUpdate(query);
//		    System.out.println("Record has been updated for film id: " + filmID);
//
//	    }
//	    catch( SQLException e )
//	    {
//	    	System.err.println("Exception occured while updating new film id: " + filmID);
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
	
	// *** 5. DELETE SCREENING ***
	public void deleteScreening(int screeningID)	{
		
		
		System.out.println("Delete screening method has been invoked.");
		
//		int screening_id = screening.getScreeningID();
//		System.out.println("screening id is:" + screening_id);
		
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
		    System.out.println("Record has been deleted for screening ID: " + screeningID);

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
	
	
	// *** UPDATE AVAILABLE SEATS FOR SPECIFIC SCREENING ***
		public void updateAvailableSeats(int screeningID)	{
			
			int updatedAvailableSeats;
			String ticketStatus;
			
			// Establish database connection:
			Connection connection = SqliteConnection.Connector();
		    Statement st = null;
		    
		    ResultSet results = null;
		    Screening screening = null;
		    
		    try	{
		    	
			    st = connection.createStatement();
		    	
				// 1. Retrieve selected screening from database
		    	String query = "SELECT * FROM screening WHERE screening_id = " + "'" + screeningID + "'";
		    	System.out.println(query);
					    
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
	            	
	            }
	            
	            // Update number of seats
	            updatedAvailableSeats = screening.getAvailableSeats() - 1;
	            
	            // Update ticket status
	            if (updatedAvailableSeats > 0)
	            {
	            	ticketStatus = "Tickets available";
	            }
	            else
	            {
	            	ticketStatus = "Sold out";
	            }
	            
	            System.out.println("Update number of seats will be: " + updatedAvailableSeats);
	            
	            
	            // Save updated number of seats and ticket status to database
	            String newQuery = "UPDATE screening SET available_seats=" + updatedAvailableSeats + "WHERE screening_id=" + screeningID;
	            st.executeUpdate(newQuery);
	            
	            String thirdQuery = "UPDATE screening SET ticket_status='" + ticketStatus + "WHERE screening_id=" + screeningID;
	            st.executeUpdate(thirdQuery);
	            
	            System.out.println("Seat number and ticket status have been updated");
	                        
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
		        	System.out.println("getScreening() --> connection is closed");
		          connection.close();
		        }

		        if( results != null )
		        {
		        	System.out.println("getScreening() --> results is closed");
		          results.close();
		        }
		      }
		      catch( Exception exe )
		      {
		    	  System.out.println("getScreening() --> error has been caught");
		        exe.printStackTrace();
		      }

		    }
		  }
		
		
}

