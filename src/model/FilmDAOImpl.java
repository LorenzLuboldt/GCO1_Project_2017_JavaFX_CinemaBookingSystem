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

public class FilmDAOImpl implements FilmDAO {
	
	// *** 1. GET ALL FILMS ***
	public ObservableList<Film> getAllFilms()	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store films from DB
	    ObservableList<Film> filmList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM film";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Film film = new Film();
		    	
		    	film.setFilmTitle(results.getString("film_title"));
		    	film.setFilmDescription(results.getString("film_description")); 
		
		    	filmList.add(film);
		    }
		 
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while fetching film data: ");
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
	    return filmList;
	  }
	
	// *** 2. GET SPECIFIC FILM ***
	public Film getFilm(String filmTitle)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    Film film = null;
	    
	    try	{
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM film WHERE film_title = " + "'" + filmTitle + "'";
	    	System.out.println(query);
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	film = new Film();
            	film.setFilmTitle(results.getString("film_title"));
            	film.setFilmDescription(results.getString("film_description"));
            }
                        
        } catch (SQLException e) {
            System.err.println("While searching the film " + filmTitle  + " an error occurred: ");
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
	    return film;
	  }
	
	// *** 3. ADD FILM ***	
	public void addFilm(String filmTitle, String filmDescription) 	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "INSERT INTO film (film_title, film_description)" + "VALUES ('" + filmTitle + "', '" + filmDescription + "')";
	     
		    // Run query
		    st.executeUpdate(query);
		    
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while adding new film " + filmTitle);
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
	public void updateFilm(String filmTitle, String filmDescription)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "UPDATE film SET film_description=" + "'" + filmDescription + "'" + "where film_title=" + "'" + filmTitle + "'";
	    	System.out.println("Record WILL be updated for film title: " + filmTitle);
	    	
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been updated for film title: " + filmTitle);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while updating new film: " + filmTitle);
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
	
	// *** 5. DELETE FILM ***
	public void deleteFilm(String filmTitle)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM film WHERE film_title=" + "'" + filmTitle + "'";
				    
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been deleted for film title: " + filmTitle);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while deleting film: " + filmTitle);
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

