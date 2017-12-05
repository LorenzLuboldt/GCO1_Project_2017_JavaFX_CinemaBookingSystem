package model;

import javafx.beans.property.*;

/** 
 * Explanation:
 * This file holds all columns of the FILMSLOT table. It contains private variables for each column,
 * a constructor to initialize them, and getters and setters for each.
 * The variables are created as properties.
 * 
 * References: 
 * http://www.swtestacademy.com/database-operations-javafx/
 * https://www.youtube.com/watch?v=H1mePFyqqiE&t=18s
 * 
 * @author Michael Aring
 * @version 24/11/2017
 */


public class FilmSlot {
	
	// variables (correspond to table columns)
	private IntegerProperty filmslot_ID;
	private IntegerProperty film_ID;
	private StringProperty screening_time;
	private StringProperty week_day;
	
	// constructor
	public FilmSlot()	{
		this.filmslot_ID = new SimpleIntegerProperty();
		this.film_ID = new SimpleIntegerProperty();
		this.screening_time = new SimpleStringProperty();
		this.week_day = new SimpleStringProperty();
	}
	
	
	// *** FILMSLOT ID ***
	
		// getter
	public int getFilmSlotID()	{
		return filmslot_ID.get();
	}
	
		// setter
	public void setFilmSlotID(int custID)	{
		this.filmslot_ID.set(custID);
	}
	
		// property
	public IntegerProperty filmSlotIDProperty()	{
		return filmslot_ID;
	}
	
	
	// *** FK: FILM ID ***
		
		// getter
	public int getFilmID()	{
		return film_ID.get();
	}
	
		// setter
	public void setFilmID(int filmID)	{
		this.film_ID.set(filmID);
	}
	
		// property
	public IntegerProperty filmIDProperty()	{
		return film_ID;
	}
	
	
	// *** FK: SCREENING TIME ***
	
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
		
	
	// *** FK: WEEKDAY ***

		// getter
	public String getWeekDay()	{
		return week_day.get();
	}
	
		// setter
	public void setWeekDay(String weekDay)	{
		this.week_day.set(weekDay);
	}
	
		// property
	public StringProperty weekDayProperty()	{
		return week_day;
	}
}
	

