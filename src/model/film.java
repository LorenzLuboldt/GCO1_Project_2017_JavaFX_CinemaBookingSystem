package model;

import javafx.beans.property.*; // "contains the interfaces that define the most generic form of observability."
// I don't really understand why we need them.

/** 
 * Explanation:
 * This file holds all columns of the CUSTOMER table. It contains private variables for each column,
 * a constructor to initialize them, and getters and setters for each.
 * The variables are created as properties.
 * 
 * References: 
 * http://www.swtestacademy.com/database-operations-javafx/
 * 
 * @author Michael Aring
 * @version 24/11/2017
 */


public class film {
	
	// variables (correspond to table columns)
	private StringProperty film_title;
	private StringProperty film_description;
	
	// constructor
	public film()	{
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