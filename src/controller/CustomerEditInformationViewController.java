package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerDAO;
import model.CustomerDAOImpl;

public class CustomerEditInformationViewController implements Initializable {
	

	Customer personSelected = new Customer();
	CustomerDAO customerDAO= new CustomerDAOImpl();
	
	// Instance variables to edit customer data
	@FXML private Button saveChangesButton;
	@FXML private TextField editedFirstNameTextField;
	@FXML private TextField editedLastNameTextField;
	@FXML private TextField editedEmailTextField;
	
	// Instance variable to display customer data
	@FXML private Label firstNameLabel;
	@FXML private Label lastNameLabel;
	@FXML private Label emailLabel;
	
	@FXML private Label userLbl;

	
	/**
	 * This method will allow the user to double click on a cell and update the first name of the person
	 * Sources: https://www.youtube.com/watch?v=z4LVoLg6ch0
	 * @author Lorenz
	 * @param user
	 */
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		loadCustomerInfo();
	
	}
	
	
	public void loadCustomerInfo()	{
		Customer cus = customerDAO.getCustomer(1);
		
		firstNameLabel.setText(cus.getFirstName());
		lastNameLabel.setText(cus.getLastName());
		emailLabel.setText(cus.getCustEmail());
	}

	
	public void saveChangesButtonPushed()	{
		
		// Declare variables and create customer object
		String editedEmail, editedFirstName, editedLastName;
		Customer cus = customerDAO.getCustomer(1);
				
		// Check if fields have been edited: if not, keep old value
		editedEmail = (editedEmailTextField.getText().length() == 0) ? cus.getCustEmail() : editedEmailTextField.getText();
		editedFirstName = (editedFirstNameTextField.getText().length() == 0) ? cus.getFirstName() : editedFirstNameTextField.getText();
		editedLastName = (editedLastNameTextField.getText().length() == 0) ? cus.getLastName() : editedLastNameTextField.getText();
		
		// Update customer data in data base for customer id 1
		customerDAO.updateCustomer(1, editedEmail, editedFirstName, editedLastName);
		
		// Display updated info to user
		loadCustomerInfo();
		 
		// @TODO Pop-up to user that informs her that changes have been saved
	}
	
	
	public void GetCustomer(String user) {
		// TODO Auto-generated method stub
		userLbl.setText(user);
	}


	// Manager is able to logout at this page
	public void SignOut(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/view/LoginView.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}
	// Manager is able to go back to overview page to select another option
	

	public void toDashboard(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/view/CustomerRoot.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}

	
	public void BackToDashboard(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/view/CustomerRoot.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}

	
	//Brings User to booking history view
	public void goToBookingsHistory(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/view/CustomerBookingHistoryView.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}
	
}
