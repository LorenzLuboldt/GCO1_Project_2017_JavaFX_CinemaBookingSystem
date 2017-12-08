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
	public ObservableList<Screening> searchScreeningsByDate();
	public ObservableList<Screening> searchScreeningsByFilm();
	public Screening getScreening(String screeningID);
	
	
	// CREATION AND UPDATE METHODS
	public void addScreening(String dateID, String timeID, String dateTimeID, int filmID, int availableSeats);
	public void updateScreening(int screeningID, String dateID, String timeID, String dateTimeID, int filmID, int availableSeats);
	public void deleteScreening(int screeningID);
	public void editScreening(int screeningID, String dateID, String timeID, String dateTimeID, int filmID, int availableSeats);
	public void updateAvailableSeats(int screeningID);
}
