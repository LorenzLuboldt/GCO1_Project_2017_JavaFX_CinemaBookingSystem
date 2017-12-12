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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Booking;
import model.BookingDAO;
import model.BookingDAOImpl;

public class CustomerBookingHistoryViewController implements Initializable{
	@FXML
	private Label userLbl;
	
	//Configure film table
	@FXML private TableView<Booking> myBookingsTable;
	@FXML private TableColumn<Booking, String> date_id_column;
	@FXML private TableColumn<Booking, String> time_string_column;
	@FXML private TableColumn<Booking, String> film_title_column;
	@FXML private TableColumn<Booking, String> seat_id_column;
	@FXML private TableColumn<Booking, Integer> booking_id_column;
	
	

	// DAO objects to query database
	BookingDAO bookingDAO = new BookingDAOImpl();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadMyBookingsTable();
		
	}
	
	
	public void loadMyBookingsTable()	{
		
		// Set column values
		date_id_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("dateID"));
		time_string_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("timeString"));
		film_title_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("filmTitle"));
		seat_id_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("seatID"));
		booking_id_column.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingID"));
		
		// Query database to retrieve list of screenings on selected date
		final ObservableList<Booking> bookingList = bookingDAO.getCustomerBookings(1);
		
		// Fill table with screenings
		myBookingsTable.setItems(bookingList);
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

}
