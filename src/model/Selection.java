 package model;

import javafx.beans.property.*; 

/** 
 * Explanation:
 * This file holds all columns of the 'selection' table. It contains private variables for each column,
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


public class Selection {
	
	// variables (correspond to table columns)
	private IntegerProperty selection_id;
	private StringProperty seat_id; // stores the seat as a string such as "A1"
	private IntegerProperty screening_id;
	
	// constructor
	public Selection()	{
		this.selection_id = new SimpleIntegerProperty();
		this.seat_id = new SimpleStringProperty();
		this.screening_id = new SimpleIntegerProperty();
	}
	
	
	// *** SELECTION ID ***
	
	// getter
	public int getSelectionID() {
		return selection_id.get();
	}

	// setter
	public void setSelectionID(int selectionID) {
		this.selection_id.set(selectionID);
	}
	
	// property
	public IntegerProperty selectionIDProperty()	{
		return selection_id;
	}
	
	
	// *** SEAT ID ***
	
	// getter
	public String getSeatID()	{
		return seat_id.get();
	}
	
	// setter
	public void setSeatID(String seatID)	{
		this.seat_id.set(seatID);
	}
	
	// property
	public StringProperty seatIDProperty()	{
		return seat_id;
	}
	
	// *** SELECTION ID ***
	
	// getter
	public int getScreeningID() {
		return screening_id.get();
	}

	// setter
	public void setScreeningID(int screeningID) {
		this.screening_id.set(screeningID);
	}
	
	// property
	public IntegerProperty screeningIDProperty()	{
		return screening_id;
	}
	
}