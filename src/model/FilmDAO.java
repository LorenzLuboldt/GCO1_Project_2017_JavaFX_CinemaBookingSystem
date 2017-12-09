package model;

import javafx.collections.ObservableList;

/** 
 * Purpose:
 * The DAO interface lists methods to CRUD (create/add, get, update, delete) the data in the 
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

public interface FilmDAO {
	public ObservableList<Film> getAllFilms();
	public Film getFilm(String filmTitle);
	public void addFilm(String filmTitle, String filmDescription, String filmGenre, String filmCastMembers, String filmDirector, String filmTrailer); // method throws error: can't find column film_genre in DB --> makes no sense
	public void updateFilm(int filmID, String filmTitle, String filmDescription, String filmGenre, String filmCastMembers, String filmDirector, String filmTrailer);
	public void deleteFilm(Film film);
}
