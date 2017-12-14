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
	public ObservableList<Booking> getAllBookings();
	public ObservableList<Booking> getCustomerBookings(int custID);	// returns list of bookings of a specific customer
	public Booking getBooking(int bookingID);
	public void addBooking(int custID, String seatID, int screeningID);
	public void deleteBooking(int bookingID); 
	public boolean checkSeatAvailability(String seatID, int screeningID); // Checks if seat has been booked for specific screening already
}