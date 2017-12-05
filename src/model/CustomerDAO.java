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

public interface CustomerDAO {
	public ObservableList<Customer> getAllCustomers();
	public Customer getCustomer(int custID);
	public void addCustomer(int custID, String cust_email, String cust_firstname, String cust_lastname);
	public void updateCustomer(String cust_email, String cust_firstname, String cust_lastname);
	public void deleteCustomer(int custID);
}