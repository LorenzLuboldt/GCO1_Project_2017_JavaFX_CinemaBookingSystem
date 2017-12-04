package model;

import javafx.beans.property.*; // "contains the interfaces that define the most generic form of observability."
// I don't really understand why we need them.

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
	private StringProperty cust_ID;
	private StringProperty sur_name;
	private StringProperty first_name;
	private StringProperty e_mail;
	
	// constructor
	public Customer()	{
		this.cust_ID = new SimpleStringProperty();
		this.sur_name = new SimpleStringProperty();
		this.first_name = new SimpleStringProperty();
		this.e_mail = new SimpleStringProperty();
	}
	
	// *** CUST ID ***
	
		// getter
	public String getCustID()	{
		return cust_ID.get();
	}
	
		// setter
	public void setCustID(String custID)	{
		this.cust_ID.set(custID);
	}
	
		// property
	public StringProperty custIdProperty()	{
		return cust_ID;
	}
	
	
	// *** SURNAME ***

		// getter
	public String getSurName()	{
		return sur_name.get();
	}
	
		// setter
	public void setSurName(String surName)	{
		this.sur_name.set(surName);
	}
	
		// property
	public StringProperty surNameProperty()	{
		return sur_name;
	}
	
	// *** FIRSTNAME ***
	
		// getter
	public String getFirstName()	{
		return first_name.get();
	}
	
		// setter
	public void setFirstName(String firstName)	{
		this.first_name.set(firstName);
	}
	
		// property
	public StringProperty firstNameProperty()	{
		return first_name;
	}
	
	// *** EMAIL ***
	
		// getter
	public String getEmail()	{
		return e_mail.get();
	}
	
		// setter
	public void setEmail(String email)	{
		this.e_mail.set(email);
	}
	
		// property
	public StringProperty emailProperty()	{
		return e_mail;
	}
}
