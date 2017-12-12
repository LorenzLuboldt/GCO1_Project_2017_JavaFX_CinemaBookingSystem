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

public class SelectionDAOImpl implements SelectionDAO {
	
	// *** GET SELECTED SEATS ***
	public ObservableList<Selection> getSelection()	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
		Statement st = null;
	    
	    // Create ObservableList to store films from DB
	    ObservableList<Selection> selectedSeatsList = FXCollections.observableArrayList();
	    
	    ResultSet results = null;
		
	    try
	    {
		    st = connection.createStatement();
	    	
			// SQL query, stored in String
	    	String query = "SELECT * FROM selection";
				    
		    // Run query and save results in ResultSet
		    results = st.executeQuery(query);
		    
		    while(results.next())	{
		    	
		    	// create and instantiate a customer object
		    	Selection selection = new Selection();
		    	selection.setSelectionID(results.getInt("selection_id"));
		    	selection.setSeatID(results.getString("seat_id"));
		    	selection.setScreeningID(results.getInt("screening_id"));
		
		    	selectedSeatsList.add(selection);
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
	    return selectedSeatsList;
	  }
	
	// *** ADD SELECTED SEAT ***	
	public void addSelectedSeat(String seatID) 	{
		
		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "INSERT INTO selection (seat_id)" + 
			"VALUES ('" + seatID + "')";
	     
		    // Run query
		    st.executeUpdate(query);
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while adding selected Seat.");
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
	
	
	// *** ADD SELECTED SCREENING ***
	public void addSelectedScreening(int screeningID)	{

		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;   
	  
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "INSERT INTO selection (screening_id)" + 
			"VALUES (" + screeningID + ")";
	     
		    // Run query
		    st.executeUpdate(query);
	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while adding selected Screening.");
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
	  
	// *** DELETE SELECTION ***
	public void deleteSelection()	{

		// Establish database connection:
		Connection connection = SqliteConnection.Connector();
	    Statement st = null;
	    
	    try
	    {
		    st = connection.createStatement();
		    
			// SQL query, stored in String
	    	String query = "DELETE FROM selection";
				    
		    // Run query
		    st.executeUpdate(query);

	    }
	    catch( SQLException e )
	    {
	    	System.err.println("Exception occured while deleting selected seats.");
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

