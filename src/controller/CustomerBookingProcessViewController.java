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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
import model.SelectionDAO;
import model.SelectionDAOImpl;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * Purpose: This controller manages the following sequence. A user can select a date in DatePicker and 
 * get a list of available screenings on that date displayed in a table view. She can then select one
 * of the screenings and click confirm to be taken to the seating map (new view and controller).
 *  
 * @author Michael
 *
 */

public class CustomerBookingProcessViewController implements Initializable{

	// Declare instance variables
	@FXML private Label userLbl;
	@FXML private DatePicker datePicker;
	
	//Configure movie list
//	@FXML private ListView<Screening> screeningList;
	
	//Configure film table
	@FXML private TableView<Screening> tableView;
//	@FXML private TableColumn<Screening, Integer> screening_id_column;
	@FXML private TableColumn<Screening, String> time_id_column;
	@FXML private TableColumn<Screening, String> film_title_column;
	@FXML private TableColumn<Screening, String> ticket_status_column;
	
	// DAO objects to query database
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	Screening screening = new Screening();
	SelectionDAO selectionDAO = new SelectionDAOImpl();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tableView.setPlaceholder(new Label("No screenings on your selected dates."));
	}
	
	
	// User wants to see list of films for given date
	public void showFilmsButtonPushed(ActionEvent event)	{				
		
		// Set column values
		time_id_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("timeString"));
		film_title_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("filmTitle"));
		ticket_status_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("ticketStatus"));
		
		// Query database to retrieve list of screenings on selected date
		final ObservableList<Screening> screeningList = screeningDAO.searchScreeningsByDate(datePicker.getValue().toString());
		
		// Fill table with screenings
		tableView.setItems(screeningList);
		
		}
	
	public void chooseSeatsButtonPushed(ActionEvent event)	{
		
		// If user has not selected a screening, she can not advance
		if(tableView.getSelectionModel().getSelectedItem().equals(null)) {
			
			// Add flash message: "Click on the screening you would like to book."
		}
		
		// If user has selected a screening, she can advance to seat selection
		else{
		// Save selected screening to cache in data base
		Screening selectedScreening = tableView.getSelectionModel().getSelectedItem();
		selectionDAO.addSelectedScreening(selectedScreening.getScreeningID());
		
		// Take user to next page with seating map
			try 
			{	
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/view/CustomerBookingGridView.fxml").openStream());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();
			} 
			catch (Exception e) {	
			}
		}
	}
		
	
	
	// Event Listener on Button.onAction
	@FXML
	public void toAccountSettings(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerEditInformationView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			
		}
	}

	// Event Listener on Button.onAction
	@FXML
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
	// Event Listener on Button.onAction
	@FXML
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
	
	// Event Listener on Button.onAction
	@FXML
	public void toSelectSeatOptions(ActionEvent event) {
//		try {	
//			((Node)event.getSource()).getScene().getWindow().hide();
//			Stage primaryStage = new Stage();
//			FXMLLoader loader = new FXMLLoader();
//			Pane root = loader.load(getClass().getResource("/view/CustomerBookingGridView.fxml").openStream());
//			Scene scene = new Scene(root);
//			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.setResizable(false);
//			primaryStage.show();
//		} catch (Exception e) {
//			
//		}
	}

}
