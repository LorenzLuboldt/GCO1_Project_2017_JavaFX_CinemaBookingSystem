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
	
	// INFORMATION RETRIEVAL AND SEARCH METHODS
	public ObservableList<Screening> getAllScreenings();
	public ObservableList<Screening> getUpcomingScreenings();
	public ObservableList<Screening> searchScreeningsByDate(String dateID); // Tested 
	public ObservableList<Screening> searchScreeningsByFilm(int filmID); // Tested but gives incorrect return
	public Screening getScreening(int screeningID);
	
	
	// CREATION, DELETION AND UPDATE METHODS
	public void addScreening(String dateID, int yearID, int monthID,  int timeID, int dayID, int filmID); // Tested, still need add constraint!
//	public void updateScreening(Screening screening);
	public void deleteScreening(Screening screening); // tested
//	public void updateAvailableSeats(int screeningID);
}
