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
	private IntegerProperty film_id;
	private StringProperty film_title;
	private StringProperty film_description;
	private StringProperty film_genre;
	private StringProperty film_cast_members;
	private StringProperty film_director;
	private StringProperty film_trailer;
	
	
	// constructor
	public Film()	{
		this.film_id = new SimpleIntegerProperty();
		this.film_title = new SimpleStringProperty();
		this.film_description = new SimpleStringProperty();
		this.film_genre = new SimpleStringProperty();
		this.film_cast_members = new SimpleStringProperty();
		this.film_director = new SimpleStringProperty();
		this.film_trailer = new SimpleStringProperty();
	}
	
	
	// *** FILM ID ***
	
	// getter
	public int getFilmId() {
		return film_id.get();
	}

	// setter
	public void setFilmId(int filmId) {
		this.film_id.set(filmId);
	}
	
	// property
	public IntegerProperty filmIdProperty()	{
		return film_id;
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
	
	
	// *** FILM DESCRIPTION ***
	
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
	
	
	// *** FILM GENRE ***
	
	// getter
	public String getFilmGenre()	{
		return film_genre.get();
	}
	
	// setter
	public void setFilmGenre(String filmGenre)	{
		this.film_genre.set(filmGenre);
	}
	
	// property
	public StringProperty filmGenreProperty()	{
		return film_genre;
	}
	
	
	// *** FILM CAST MEMBERS ***
	
	// getter
	public String getFilmCastMembers()	{
		return film_cast_members.get();
	}
	
	// setter
	public void setFilmCastMembers(String filmCastMembers)	{
		this.film_cast_members.set(filmCastMembers);
	}
	
	// property
	public StringProperty filmCastMembers()	{
		return film_cast_members;
	}
	
	
	// *** FILM DIRECTOR ***
	
	// getter
	public String getFilmDirector()	{
		return film_director.get();
	}
	
	// setter
	public void setFilmDirector(String filmDirector)	{
		this.film_director.set(filmDirector);
	}
	
	// property
	public StringProperty filmDirector()	{
		return film_director;
	}
	
	// *** FILM TRAILER ***
	
	// getter
	public String getFilmTrailer()	{
		return film_trailer.get();
	}
	
	// setter
	public void setFilmTrailer(String filmTrailer)	{
		this.film_trailer.set(filmTrailer);
	}
	
	// property
	public StringProperty filmTrailer()	{
		return film_trailer;
	}
}