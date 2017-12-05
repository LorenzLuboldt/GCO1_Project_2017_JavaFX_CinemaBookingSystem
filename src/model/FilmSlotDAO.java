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

public interface FilmSlotDAO {
	public ObservableList<FilmSlot> getAllFilmSlots();
	public FilmSlot getFilmSlot(int filmSlot_ID);
	public void updateFilmSlot(int filmSlot_ID, int film_ID); // user can update a specific row (filmSlot_ID, unchanged) by changing the movie there (film_ID, changed)
}
