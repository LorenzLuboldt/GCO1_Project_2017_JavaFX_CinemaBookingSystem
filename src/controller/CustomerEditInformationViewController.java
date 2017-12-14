package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerDAO;
import model.CustomerDAOImpl;

/**
 * Purpose: Controller loads customer data from database. Customer is able to
 * edit customer data so that it is automatically changed throughout booking
 * processes
 * 
 * @author Lorenz
 */
public class CustomerEditInformationViewController implements Initializable {

	Customer personSelected = new Customer();
	CustomerDAO customerDAO = new CustomerDAOImpl();

	// Instance variables to edit customer data
	@FXML
	private Button saveChangesButton;
	@FXML
	private TextField editedFirstNameTextField;
	@FXML
	private TextField editedLastNameTextField;
	@FXML
	private TextField editedEmailTextField;

	// Instance variable to display customer data
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label successNotification;
	@FXML
	Label errorNotification;
	@FXML
	private Label userLbl;

	/**
	 * Purpose: Initialises display of user name in header and loads placeholder
	 * for table view of screenings available
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GetCustomer(LoginController.username);
		loadCustomerInfo();
	}

	/**
	 * Purpose: Displays the name of the Manager currently logged in.
	 * 
	 * @param user
	 */
	public void GetCustomer(String user) {
		userLbl.setText("User " + user);
	}

	/**
	 * Purpose: Loads actual customer data from database
	 */
	public void loadCustomerInfo() {
		Customer cus = customerDAO.getCustomer(1);

		firstNameLabel.setText(cus.getFirstName());
		lastNameLabel.setText(cus.getLastName());
		emailLabel.setText(cus.getCustEmail());
	}

	/**
	 * Purpose: When button clicked, newly inserted customer data by customer is
	 * written into database and automatically updated throughout the
	 * application
	 * 
	 * @param event
	 */
	public void saveChangesButtonPushed() {

		// Declare variables and create customer object
		String editedEmail, editedFirstName, editedLastName;
		Customer cus = customerDAO.getCustomer(1);

		// Check if fields have been edited: if not, keep old value
		editedEmail = (editedEmailTextField.getText().length() == 0) ? cus.getCustEmail()
				: editedEmailTextField.getText();
		editedFirstName = (editedFirstNameTextField.getText().length() == 0) ? cus.getFirstName()
				: editedFirstNameTextField.getText();
		editedLastName = (editedLastNameTextField.getText().length() == 0) ? cus.getLastName()
				: editedLastNameTextField.getText();

		// Update customer data in data base for customer id 1
		try {
			customerDAO.updateCustomer(1, editedEmail, editedFirstName, editedLastName);
			successNotification.setText("Successfully saved changes");
		} catch (Exception e) {

			errorNotification.setText("An error occurred.");
			e.printStackTrace();
		}

		// Display updated info to user
		loadCustomerInfo();

		// @TODO Pop-up to user that informs her that changes have been saved
	}

	/**
	 * Purpose: When button clicked, customer gets back to LoginView (Logout)
	 * clicked.
	 * 
	 * @param event
	 */
	public void SignOut(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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

	/**
	 * Purpose: When button clicked, customer gets back to dashboard view where
	 * all Movies are displayed and where the customer can select options to
	 * start booking process
	 * 
	 * @param event
	 */
	public void toDashboard(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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

	
	/**
	 * Purpose: When button clicked, customer gets back to dashboard view where
	 * all Movies are displayed and where the customer can select options to
	 * start booking process
	 * 
	 * @param event
	 */
	public void BackToDashboard(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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

	/**
	 * Purpose: When button clicked, customer gets to view where to is able to
	 * view future and past bookings in two different tables and can delete
	 * future bookings.
	 * 
	 * @param event
	 */
	public void goToBookingsHistory(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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
