package model;

import javafx.beans.property.*; 

/** 
 * Explanation:
 * This file holds all columns of the BOOKING table. It contains private variables for each column,
 * a constructor to initialize them, and getters and setters for each.
 * The variables are created as properties.
 * 
 * References: 
 * http://www.swtestacademy.com/database-operations-javafx/
 * https://www.youtube.com/watch?v=H1mePFyqqiE&t=18s
 * 
 * @author Michael Aring
 * @version 24/11/2017
 */


public class Booking {
	
	// variables (correspond to table columns)
	private IntegerProperty booking_ID;
	private IntegerProperty cust_ID;
	private IntegerProperty seat_ID;
	private IntegerProperty screening_ID;
	
	// constructor
	public Booking()	{
		this.booking_ID = new SimpleIntegerProperty();
		this.cust_ID = new SimpleIntegerProperty();
		this.seat_ID = new SimpleIntegerProperty();
		this.screening_ID = new SimpleIntegerProperty();
	}
	
	
	// *** BOOKING ID ***
	
		// getter
	public int getBookingID()	{
		return booking_ID.get();
	}
	
		// setter
	public void setBookingID(int bookingID)	{
		this.booking_ID.set(bookingID);
	}
	
		// property
	public IntegerProperty bookingIDProperty()	{
		return booking_ID;
	}

	
	// *** CUST ID ***
	
		// getter
	public int getCustID()	{
		return cust_ID.get();
	}
	
		// setter
	public void setCustID(int custID)	{
		this.cust_ID.set(custID);
	}
	
		// property
	public IntegerProperty custIDProperty()	{
		return cust_ID;
	}
	
	// *** BOOKING ID ***
	
		// getter
	public int getSeatID()	{
		return seat_ID.get();
	}
	
		// setter
	public void setSeatID(int seatID)	{
		this.seat_ID.set(seatID);
	}
	
		// property
	public IntegerProperty seatIDProperty()	{
		return seat_ID;
	}

	
	// *** SCREENING ID ***
	
		// getter
	public int getScreeningID()	{
		return screening_ID.get();
	}
	
		// setter
	public void setScreeningID(int screeningID)	{
		this.screening_ID.set(screeningID);
	}
	
		// property
	public IntegerProperty screeningIDProperty()	{
		return screening_ID;
	}
}