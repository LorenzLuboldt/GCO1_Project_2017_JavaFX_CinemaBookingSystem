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

public interface ScreeningDAO {
	
	// Information retrieval and search methods
	public ObservableList<Screening> getAllScreenings(); // tested
	public ObservableList<Screening> getUpcomingScreenings(); // tested --> more extensive testing required
	public ObservableList<Screening> searchScreeningsByDate(String dateID); // tested 
	public ObservableList<Screening> searchScreeningsByFilm(int filmID);  // tested
	public Screening getScreening(int screeningID); // tested
	
	
	// Creation, delete and update methods
	public void addScreening(String dateID, int yearID, int monthID, int dayID,  String timeString, String filmTitle); // Tested
//	public void updateScreening(Screening screening);
	public void deleteScreening(Screening screening); // tested
	public void updateAvailableSeats(int screeningID);
}
