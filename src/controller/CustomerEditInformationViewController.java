package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	@FXML
	private Label userLbl;
		
	
	//Configure the customer table
	@FXML private TableView<Customer> customer_data;
	@FXML private TableColumn<Customer, String> firstname;
	@FXML private TableColumn<Customer, String> surname;
	@FXML private TableColumn<Customer, String> emailAddress;
	@FXML private TableColumn<Customer, String> customerID;


	/**
	 * This method will allow the user to double click on a cell and update the first name of theperson
	 * Sources: https://www.youtube.com/watch?v=z4LVoLg6ch0
	 * @author Lorenz
	 * @param user
	 */
	
	public void changeFirstNameCellEvent(CellEditEvent edittedCell) {
		personSelected = customer_data.getSelectionModel().getSelectedItem();
		personSelected.setFirstName(edittedCell.getNewValue().toString());
	}

	public void changeLastNameCellEvent(CellEditEvent edittedCell) {
		personSelected = customer_data.getSelectionModel().getSelectedItem();
		personSelected.setLastName(edittedCell.getNewValue().toString());
	}
	
	public void changeEmailAdressCellEvent(CellEditEvent edittedCell) {
		personSelected = customer_data.getSelectionModel().getSelectedItem();
		personSelected.setCustEmail(edittedCell.getNewValue().toString());
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
	
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
public void initialize(URL location, ResourceBundle resources) {
		
		//Connects table column to respective table column in database
		firstname.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		surname.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		emailAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("custEmail"));
		customerID.setCellValueFactory(new PropertyValueFactory<Customer, String>("custID"));


		//load data from Customer Table
		final ObservableList<Customer> customerData = customerDAO.getAllCustomers();
		customer_data.setItems(customerData);
		
		//Update customer data table to allow for the the customer data fields to be editable
		
		customer_data.setEditable(true);
		firstname.setCellFactory(TextFieldTableCell.forTableColumn());
		surname.setCellFactory(TextFieldTableCell.forTableColumn());
		emailAddress.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	// Populates the table in this view 
	public void EditCustomerData(ActionEvent event) {
		//Set up the columns in the table		
		customerDAO.updateCustomer(personSelected.getCustEmail(), personSelected.getFirstName(), personSelected.getLastName());
	}
	
}
