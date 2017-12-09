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

public interface BookingDAO {
	public ObservableList<Booking> getAllBookings(); //y
	public Booking getBooking(int bookingID); //y
	public void addBooking(int custID, int seatID, int screeningID); //y
//	public void updateBooking(int bookingID, int custID, int seatID, int screeningID);
	public void deleteBooking(int bookingID); //y
}