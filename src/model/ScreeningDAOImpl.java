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
		    	screening.setTimeID(results.getInt("time_id")); 
		    	screening.setDateTimeID(results.getString("date_time_id"));
		    	screening.setFilmID(results.getInt("film_id"));
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
			    	screening.setTimeID(results.getInt("time_id")); 
			    	screening.setDateTimeID(results.getString("date_time_id"));
			    	screening.setFilmID(results.getInt("film_id"));
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
		    	screening.setTimeID(results.getInt("time_id")); 
		    	screening.setDateTimeID(results.getString("date_time_id"));
		    	screening.setFilmID(results.getInt("film_id"));
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
			    	screening.setTimeID(results.getInt("time_id")); 
			    	screening.setDateTimeID(results.getString("date_time_id"));
			    	screening.setFilmID(results.getInt("film_id"));
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
	
	
		// *** X. SEARCH SCREENINGS BY DATE ***
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
				    	String query = "SELECT * FROM screening WHERE date_id='" + dateID + "'";
							    
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
					    	screening.setTimeID(results.getInt("time_id")); 
					    	screening.setDateTimeID(results.getString("date_time_id"));
					    	screening.setFilmID(results.getInt("film_id"));
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
			
		
	// *** 3. ADD SCREENING ***	
	public void addScreening(String dateID, int yearID, int monthID, int dayID,  int timeID, int filmID) 	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    // Create the unique date time ID
	    String dateTimeID = new StringBuilder(dateID).append(timeID).toString();
	    System.out.println("dateTimeID is : " + dateTimeID);
	  
	    // Initialize available seats
	    int availableSeats = 24;	    
	    
	    try
	    {
		    st = connection.createStatement();
		    
		    // CHECK IF DATE DOESN'T EXIST IN DATABASE YET
		    ObservableList<Screening> sameScreeningList = FXCollections.observableArrayList();
		    ResultSet results = null;

		    String test = "SELECT * FROM screening WHERE date_id='" + dateID + "' AND time_id=" + timeID;
		    
		    results = st.executeQuery(test);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Screening screening = new Screening();
		    	
		    	screening.setScreeningID(results.getInt("screening_id"));
		    	screening.setDateID(results.getString("date_id"));
		    	screening.setYearID(results.getInt("year_id"));
		    	screening.setMonthID(results.getInt("month_id"));
		    	screening.setDayID(results.getInt("day_id"));
		    	screening.setTimeID(results.getInt("time_id")); 
		    	screening.setDateTimeID(results.getString("date_time_id"));
		    	screening.setFilmID(results.getInt("film_id"));
		    	screening.setAvailableSeats(results.getInt("available_seats"));
		
		    	sameScreeningList.add(screening);
		    }
		    
		   Screening s = sameScreeningList.get(0);
		   
		   int i = s.getFilmID();
		   
		    
		   boolean sameCheck = sameScreeningList.isEmpty();
		   System.out.println("Film ID of first element:" + i + "__________" + sameCheck);
		   
		    
		    // IF THE SCREENING HASN'T BEEN TAKEN, PURSUE THE ADD METHOD
		    if(sameCheck)	{
		    
		    
				// SQL query, stored in String
		    	String query = "INSERT INTO screening (date_id, year_id, month_id, day_id, time_id, date_time_id, "
		    			+ "film_id, available_seats)" + "VALUES ('" + dateID + "', " + yearID + "," + monthID + "," + 
		    			dayID + "," + timeID + "," + dateTimeID + "," + filmID + "," + availableSeats + ")";
		     
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
	    	System.err.println("Exception occured while adding new screening " + dateTimeID);
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
	public void deleteScreening(Screening screening)	{
		
		
		System.out.println("Delete screening method has been invoked.");
		
		int screening_id = screening.getScreeningID();
		System.out.println("screening id is:" + screening_id);
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM screening WHERE screening_id=" +screening_id;
				    
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been deleted for screening ID: " + screening_id);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while deleting screening ID: " + screening_id);
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
			    	screening.setTimeID(results.getInt("time_id")); 
			    	screening.setDateTimeID(results.getString("date_time_id"));
			    	screening.setFilmID(results.getInt("film_id"));
			    	screening.setAvailableSeats(results.getInt("available_seats"));
	            	
	            }
	            
	            // 2. Update number of seats
	            int updatedAvailableSeats = screening.getAvailableSeats() - 1;
	            
	            System.out.println("Update number of seats will be: " + updatedAvailableSeats);
	            
	            
	            // 3. Save updated number of seats to database
	            
	            String newQuery = "UPDATE screening SET available_seats=" + updatedAvailableSeats + "WHERE screening_id=" + screeningID;
	            st.executeUpdate(newQuery);
	            
	            System.out.println("Seat number has been updated");
	                        
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

