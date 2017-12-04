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
 * The screening schedule is the same every day the cinema is open. For each screening time (one hour time slot),
 * the employee can allocate a film from the film list. 
 * 
 * References: 
 * http://www.swtestacademy.com/database-operatäions-javafx/
 * https://www.youtube.com/watch?v=H1mePFyqqiE&t=18s
 * 
 * @author Michael Aring
 * @version 04/12/2017
 */


public class ScreeningSchedule {
	
	// variables (correspond to table columns)
	private StringProperty screening_time; // example value "3 pm"
	private StringProperty film_scheduled; // example value "James Bond", taken from  table
	
	// constructor
	public ScreeningSchedule()	{
		this.screening_time = new SimpleStringProperty();
		this.film_scheduled = new SimpleStringProperty();
	}
	
	// *** SCREENING TIME ***
		// getter
	public String getScreeningTime()	{
		return screening_time.get();
	}
	
		// setter
	public void setScreeningTime(String screeningTime)	{
		this.screening_time.set(screeningTime);
	}
	
		// property
	public StringProperty screeningTimeProperty()	{
		return screening_time;
	}
	
	// *** FILM SCHEDULED ***
		// getter
	public String getFilmScheduled()	{
		return film_scheduled.get();
	}
	
		// setter
	public void setFilmScheduled(String filmScheduled)	{
		this.film_scheduled.set(filmScheduled);
	}
	
		// property
	public StringProperty filmScheduledProperty()	{
		return film_scheduled;	
	}
}

