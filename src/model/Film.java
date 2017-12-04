package model;

import javafx.beans.property.*; 

/** 
 * Explanation:
 * This file holds all columns of the 'film' table. It contains private variables for each column,
 * a constructor to initialize them, and getters and setters for each.
 * The variables are created as properties.
 * 
 * "A Property notifies us when any variable such as name, last name, etc. is changed. 
 * This helps us keep the view in sync with the data." (SW Test Academy)
 * 
 * References DAO Methodology: 
 * http://www.swtestacademy.com/database-operations-javafx/
 * https://www.youtube.com/watch?v=H1mePFyqqiE&t=18s
 * 
 * @author Michael Aring
 * @version 04/12/2017
 */


public class Film {
	
	// variables (correspond to table columns)
	private StringProperty film_title;
	private StringProperty film_description;
	
	// constructor
	public Film()	{
		this.film_title = new SimpleStringProperty();
		this.film_description = new SimpleStringProperty();
	}
	
	
	// *** FILMTITLE ***
	
	// getter
	public String getFilmTitle()	{
		return film_title.get();
	}
	
	// setter
	public void setFilmTitle(String filmTitle)	{
		this.film_title.set(filmTitle);
	}
	
	// property
	public StringProperty filmTitleProperty()	{
		return film_title;
	}
	
	// *** FILMDESCRIPTION ***
	
	// getter
	public String getFilmDescription()	{
		return film_description.get();
	}
	
	// setter
	public void setFilmDescription(String filmDescription)	{
		this.film_description.set(filmDescription);
	}
	
	// property
	public StringProperty filmDescriptionProperty()	{
		return film_description;
	}
}