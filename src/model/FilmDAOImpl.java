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
		    	
		    	film.setFilmId(results.getInt("film_id"));
		    	film.setFilmTitle(results.getString("film_title"));
		    	film.setFilmDescription(results.getString("film_description")); 
		    	film.setFilmGenre(results.getString("film_genre"));
		    	film.setFilmCastMembers(results.getString("film_cast_members"));
		    	film.setFilmDirector(results.getString("film_director"));
		
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
	          System.out.println("getAllFilms() --> connection is closed");
	          connection.close();
	        }

	        if( results != null )
	        {
	        	System.out.println("getAllFilms() --> results are closed");
	          results.close();
	        }
	      }
	      catch( Exception exe )
	      {
	    	  System.out.println("getAllFilms() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    System.out.println("All films have been retrieved from DB and stored in filmList");
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
            	
            	film.setFilmId(results.getInt("film_id"));
		    	film.setFilmTitle(results.getString("film_title"));
		    	film.setFilmDescription(results.getString("film_description")); 
		    	film.setFilmGenre(results.getString("film_genre"));
		    	film.setFilmCastMembers(results.getString("film_cast_members"));
		    	film.setFilmDirector(results.getString("film_director"));
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
	        	System.out.println("getFilm() --> connection is closed");
	          connection.close();
	        }

	        if( results != null )
	        {
	        	System.out.println("getFilm() --> results is closed");
	          results.close();
	        }
	      }
	      catch( Exception exe )
	      {
	    	  System.out.println("getFilm() --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    return film;
	  }
	
	// *** 3. ADD FILM ***	
	public void addFilm(String filmTitle, String filmDescription, String filmImage, String filmGenre, String filmCastMembers, String filmDirector) 	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "INSERT INTO film (film_title, film_description, film_image, film_genre, film_cast_members, film_director)" + 
			"VALUES ('" + filmTitle + "', '" + filmDescription + "', '" + filmImage + "', '" + filmGenre + "', '" + filmCastMembers + "', '" + filmDirector
			 + "')";
	     
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Film has been added to DB");
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
	public void updateFilm(int filmID, String filmTitle, String filmDescription, String filmGenre, String filmCastMembers, String filmDirector)	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "UPDATE film SET film_title='" + filmTitle + "', film_description=" + "'" + filmDescription + "', film_genre='" + filmGenre + "', film_cast_members='"
			+ filmCastMembers + "', film_director='" + filmDirector + "' WHERE film_id=" + filmID;
	    	
	    	System.out.println("Record WILL be updated for film id: " + filmID);
	    	
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been updated for film id: " + filmID);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while updating new film id: " + filmID);
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
	public void deleteFilm(Film film)	{
		
		
		System.out.println("Delete film method has been invoked.");
		String film_title = film.getFilmTitle();
		String film_description = film.getFilmDescription();
		System.out.println("Film title is " + film_title);
		System.out.println("Film description is: " + film_description);
		
		int film_id = film.getFilmId();
		System.out.println("film id is:" + film_id);
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM film WHERE film_id=" +film_id;
				    
		    // Run query
		    st.executeUpdate(query);
		    System.out.println("Record has been deleted for film ID: " + film_id);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while deleting film ID: " + film_id);
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
	
	public String getFilmImagePath(int filmID)	{
		String imagePath = null;
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    ResultSet results = null;
	    
	    try	{
		    st = connection.createStatement();
	    	
	    	
			// SQL query, stored in String
	    	String query = "SELECT film_image FROM film WHERE film_id =" + filmID;
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
            while(results.next())	{
            	imagePath = (results.getString("film_image"));
            }
                        
        } catch (SQLException e) {
            System.err.println("While searching the film image for " + filmID + "an error occured.");
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
	    	  System.out.println("search Film Image --> error has been caught");
	        exe.printStackTrace();
	      }

	    }
	    return imagePath;
	  }
}

