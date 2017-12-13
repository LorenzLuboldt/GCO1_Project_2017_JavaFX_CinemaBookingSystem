package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import model.Booking;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;

public class CustomerBookingHistoryViewController implements Initializable{
	@FXML
	private Label userLbl;
	
	//Configure 2 tables: one for future bookings, one for past bookings
	@FXML private TableView<Booking> futureBookingsTable;
	@FXML private TableView<Booking> pastBookingsTable;
	
	// Columns of futureBookingsTable
	@FXML private TableColumn<Booking, String> date_id_column;
	@FXML private TableColumn<Booking, String> time_string_column;
	@FXML private TableColumn<Booking, String> film_title_column;
	@FXML private TableColumn<Booking, String> seat_id_column;
	@FXML private TableColumn<Booking, Integer> booking_id_column;
	
	// Columns of pastBookingsTable
	@FXML private TableColumn<Booking, String> past_date_id_column;
	@FXML private TableColumn<Booking, String> past_time_string_column;
	@FXML private TableColumn<Booking, String> past_film_title_column;
	@FXML private TableColumn<Booking, String> past_seat_id_column;
	@FXML private TableColumn<Booking, Integer> past_booking_id_column;

	// Create DAO objects to query database
	BookingDAO bookingDAO = new BookingDAOImpl();
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadMyBookingsTables(); // returns 2 tables in view
		
	}
	
	
	public void loadMyBookingsTables()	{
		
		// Set column values for futureBookingsTable
		date_id_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("dateID"));
		time_string_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("timeString"));
		film_title_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("filmTitle"));
		seat_id_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("seatID"));
		booking_id_column.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingID"));
		
		// Set column values for pastBookingsTable
		past_date_id_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("dateID"));
		past_time_string_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("timeString"));
		past_film_title_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("filmTitle"));
		past_seat_id_column.setCellValueFactory(new PropertyValueFactory<Booking, String>("seatID"));
		past_booking_id_column.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bookingID"));
		
		// Query database to retrieve list of bookings for customer
		final ObservableList<Booking> bookingList = bookingDAO.getCustomerBookings(1);
		
		// Create 2 observable lists to store future and past bookings
		final ObservableList<Booking> futureBookingList = FXCollections.observableArrayList();;
		final ObservableList<Booking> pastBookingList = FXCollections.observableArrayList();;
		
		// Validate time of of screening and sort booking to future or past bookings list
		for(int i = 0; i < bookingList.size(); i++)	{
			
			Booking booking = bookingList.get(i);
			
			// Validate time and add to screening list only, if date today is today or in the future
	    	String screeningTime = booking.getTimeInt() + ":00";
	    	LocalTime time = LocalTime.parse(screeningTime);
	    	LocalDate date = LocalDate.parse(booking.getDateID());
	    	
	    	// If screening is in the future, add to future list
	    	if (date.isEqual(LocalDate.now()) && time.isAfter(LocalTime.now()) || date.isAfter(LocalDate.now()))	{
	    		futureBookingList.add(booking);
	    	}
	    	// If screening is not in the future, add to past list
	    	else {
	    		pastBookingList.add(booking);
	    	}
		}
		
		// Fill tables with respective screenings
		futureBookingsTable.setItems(futureBookingList);
		pastBookingsTable.setItems(pastBookingList);
		
	}
	
	public void deleteBookingButtonPushed(ActionEvent event)	{
		
		// Update seat statistics for specific screening
		Booking booking = bookingDAO.getBooking(futureBookingsTable.getSelectionModel().getSelectedItem().getBookingID());
		screeningDAO.increaseAvailableSeats(booking.getScreeningID());
		
		// Delete selected booking from DB
		bookingDAO.deleteBooking(futureBookingsTable.getSelectionModel().getSelectedItem().getBookingID());
		
		// Reload tables in view with updated values
		loadMyBookingsTables();
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
