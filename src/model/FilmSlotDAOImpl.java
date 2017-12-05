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

public class FilmSlotDAOImpl implements FilmSlotDAO {
	
	// *** 1. GET ALL FILMS ***
	public ObservableList<FilmSlot> getAllFilmSlots()	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store filmslots from DB
	    ObservableList<FilmSlot> filmSlotList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM filmslot";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	FilmSlot filmslot = new FilmSlot();
		    	
		    	filmslot.setFilmSlotID(results.getInt("filmslot_id"));
		    	filmslot.setFilmID(results.getInt("film_id")); 
		    	filmslot.setScreeningTime(results.getString("screening_time"));
		    	filmslot.setWeekDay(results.getString("week_day"));
		
		    	filmSlotList.add(filmslot);
		    }
		 
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while fetching filmslot data: ");
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
	    return filmSlotList;
	  }
	
	// *** 2. GET SPECIFIC FILMSLOT ***
	public FilmSlot getFilmSlot(int filmSlotID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    FilmSlot filmslot = null;
	    
	    try	{
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM film WHERE filmslot_id = " + "'" + filmSlotID + "'";
	    	System.out.println(query);
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	filmslot = new FilmSlot();
		    	filmslot.setFilmSlotID(results.getInt("filmslot_id"));
		    	filmslot.setFilmID(results.getInt("film_id")); 
		    	filmslot.setScreeningTime(results.getString("screening_time"));
		    	filmslot.setWeekDay(results.getString("week_day"));
            }
                        
        } catch (SQLException e) {
            System.err.println("While searching the film " + filmSlotID  + " an error occurred: ");
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
	    return filmslot;
	  }
	

	// *** 3. UPDATE FILM ***
	public void updateFilmSlot(int filmSlot_ID, int film_ID)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "UPDATE filmslot SET film_id=" + "'" + film_ID + "'" + "where filmslot_id=" + "'" + filmSlot_ID + "'"; // maybe delete the ' ' around filmSlot_ID
	    	System.out.println("Record WILL be updated for film number: " + film_ID);
	    	
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been updated for film number: " + film_ID);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while updating film number: " + film_ID);
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

