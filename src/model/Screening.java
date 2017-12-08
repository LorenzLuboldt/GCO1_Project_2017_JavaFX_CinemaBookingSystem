package model;

import javafx.beans.property.*;

/** 
 * Explanation:
 * This file holds all columns of the 'screening_schedule' table. It contains private variables for each column,
 * a constructor to initialize them, and getters and setters for each.
 * The variables are created as properties.
 * 
 * "A Property notifies us when any variable such as name, last name, etc. is changed. 
 * This helps us keep the view in sync with the data." (SW Test Academy)
 * 
 * 
 * References: 
 * http://www.swtestacademy.com/database-operatäions-javafx/
 * https://www.youtube.com/watch?v=H1mePFyqqiE&t=18s
 * 
 * @author Michael Aring
 * @version 04/12/2017
 */


public class Screening {
	
	// variables (correspond to table columns)
	private IntegerProperty screening_id; // unique id
	private StringProperty date_id;
	private StringProperty time_id;
	private StringProperty date_time_id;
	private IntegerProperty film_id; // foreign key corresponds to 'Film' table
	private IntegerProperty available_seats; // keeps count of the number of available seats for a particular screening

	
	// constructor
	public Screening()	{
		this.screening_id = new SimpleIntegerProperty();
		this.date_id = new SimpleStringProperty();
		this.time_id = new SimpleStringProperty();
		this.date_time_id = new SimpleStringProperty(); 
		this.film_id = new SimpleIntegerProperty();
		this.available_seats = new SimpleIntegerProperty();
	}
	
	// *** SCREENING ID ***
		// getter
	public int getScreeningID()	{
		return screening_id.get();
	}
	
		// setter
	public void setScreeningID(int screeningID)	{
		this.screening_id.set(screeningID);
	}
	
		// property
	public IntegerProperty screeningID()	{
		return screening_id;
	}	
	
	// *** DATE ID ***
		// getter
	public String getDateID()	{
		return date_id.get();
	}
	
		// setter
	public void setDateIDTime(String dateID)	{
		this.date_id.set(dateID);
	}
	
		// property
	public StringProperty dateIDProperty()	{
		return date_id;
	}
	
	// *** TIME ID ***
		// getter
	public String getTimeID()	{
		return time_id.get();
	}
	
		// setter
	public void setTimeIDTime(String timeID)	{
		this.time_id.set(timeID);
	}
	
		// property
	public StringProperty timeIDProperty()	{
		return time_id;
	}
	
	// *** DATE TIME ID ***
		// getter
	public String getDateTimeID()	{
		return date_time_id.get();
	}
	
		// setter
	public void setDateTimeIDTime(String dateTimeID)	{
		this.date_time_id.set(dateTimeID);
	}
	
		// property
	public StringProperty dateTimeIDProperty()	{
		return date_time_id;
	}
	
	// *** FILM ID ***
			// getter
		public int getFilmID()	{
			return film_id.get();
		}
		
			// setter
		public void setFilmID(int filmID)	{
			this.film_id.set(filmID);
		}
		
			// property
		public IntegerProperty filmID()	{
			return film_id;
		}	
		
		
	// *** AVAILABLE SEATS ***
			// getter
		public int getAvailableSeats()	{
			return available_seats.get();
		}
		
			// setter
		public void setAvailableSeats(int availableSeats)	{
			this.available_seats.set(availableSeats);
		}
		
			// property
		public IntegerProperty availableSeats()	{
			return available_seats;
		}	

}

