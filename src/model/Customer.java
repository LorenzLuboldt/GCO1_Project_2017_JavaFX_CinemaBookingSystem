package model;

import javafx.beans.property.*; 

/** 
 * Explanation:
 * This file holds all columns of the CUSTOMER table. It contains private variables for each column,
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


public class Customer {
	
	// variables (correspond to table columns)
	private IntegerProperty cust_ID;
	private StringProperty cust_email;
	private StringProperty cust_firstname;
	private StringProperty cust_lastname;
	
	// constructor
	public Customer()	{
		this.cust_ID = new SimpleIntegerProperty();
		this.cust_email = new SimpleStringProperty();
		this.cust_firstname = new SimpleStringProperty();
		this.cust_lastname = new SimpleStringProperty();
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
	public IntegerProperty custIdProperty()	{
		return cust_ID;
	}
	
	
	// *** EMAIL ***
		
		// getter
	public String getCustEmail()	{
		return cust_email.get();
	}
	
		// setter
	public void setCustEmail(String custEmail)	{
		this.cust_email.set(custEmail);
	}
	
		// property
	public StringProperty custEmailProperty()	{
		return cust_email;
	}
	
	
	// *** FIRSTNAME ***
	
		// getter
	public String getFirstName()	{
		return cust_firstname.get();
	}
	
		// setter
	public void setFirstName(String firstName)	{
		this.cust_firstname.set(firstName);
	}
	
		// property
	public StringProperty firstNameProperty()	{
		return cust_firstname;
	}
		
	
	// *** SURNAME ***

		// getter
	public String getLastName()	{
		return cust_lastname.get();
	}
	
		// setter
	public void setLastName(String lastName)	{
		this.cust_lastname.set(lastName);
	}
	
		// property
	public StringProperty lastNameProperty()	{
		return cust_lastname;
	}
}
	

