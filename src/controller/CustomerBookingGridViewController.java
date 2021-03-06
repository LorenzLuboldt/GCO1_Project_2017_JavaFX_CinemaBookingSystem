package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.BookingDAO;
import model.BookingDAOImpl;
import model.CustomerDAO;
import model.CustomerDAOImpl;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
import model.Selection;
import model.SelectionDAO;
import model.SelectionDAOImpl;

/**
 * Purpose: Controller for View on which the user can select a seat for a
 * previously chosen film screening.
 * 
 * References for seating map:
 * https://docs.oracle.com/javafx/2/api/javafx/scene/layout/GridPane.html
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
 * https://stackoverflow.com/questions/25550518/add-eventhandler-to-imageview-contained-in-tilepane-contained-in-vbox
 * 
 * @author Michael
 *
 */
public class CustomerBookingGridViewController implements Initializable {

	@FXML
	private GridPane seatingMap;

	@FXML
	private Label userLbl;
	@FXML
	private Label filmTitleLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label timeLabel;

	// Create objects for database access
	SelectionDAO selectionDAO = new SelectionDAOImpl();
	BookingDAO bookingDAO = new BookingDAOImpl();
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	CustomerDAO customerDAO = new CustomerDAOImpl();

	/**
	 * Purpose: Initialises display of user name in header and loads data for
	 * seating map and screening details
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GetCustomer(LoginController.username);
		loadScreeningDetails();
		loadSeatingMap();
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
	 * Purpose: Loads screening details for selected screening in the previous
	 * view
	 */
	public void loadScreeningDetails() {

		// Check for booked seats --> SCREENING ID
		ObservableList<Selection> screeningsList = selectionDAO.getSelection();
		int screeningID = screeningsList.get(0).getScreeningID();

		// Load screening details for selected screening and set text fields
		Screening selectedScreening = screeningDAO.getScreening(screeningID);
		filmTitleLabel.setText(selectedScreening.getFilmTitle());
		dateLabel.setText(selectedScreening.getDateID());
		timeLabel.setText(selectedScreening.getTimeString());
	}

	/**
	 * Purpose: Loads the seating map
	 */
	public void deselectSeatsButtonPushed() {
		loadSeatingMap();
	}

	/**
	 * Purpose: Method recreates clickable seating map from the screening chosen
	 * from the screening list in the root view (button "show details" clicked)
	 * and stores it into javaFX elements.
	 */
	public void loadSeatingMap() throws NullPointerException {
		// Check for booked seats --> SCREENING ID
		ObservableList<Selection> screeningsList = selectionDAO.getSelection();
		int screeningID = screeningsList.get(0).getScreeningID();


		// Delete all existing selections from seat table
		selectionDAO.deleteSelection();

		// Create and build all seats
		ImageView seatA1 = new ImageView();
		String seatIdA1 = "A1";
		buildSeat(screeningID, seatIdA1, seatA1, 1, 1);

		ImageView seatA2 = new ImageView();
		String seatIdA2 = "A2";
		buildSeat(screeningID, seatIdA2, seatA2, 1, 2);

		ImageView seatA3 = new ImageView();
		String seatIdA3 = "A3";
		buildSeat(screeningID, seatIdA3, seatA3, 1, 3);

		ImageView seatA4 = new ImageView();
		String seatIdA4 = "A4";
		buildSeat(screeningID, seatIdA4, seatA4, 1, 4);

		ImageView seatB1 = new ImageView();
		String seatIdB1 = "B1";
		buildSeat(screeningID, seatIdB1, seatB1, 2, 1);

		ImageView seatB2 = new ImageView();
		String seatIdB2 = "B2";
		buildSeat(screeningID, seatIdB2, seatB2, 2, 2);

		ImageView seatB3 = new ImageView();
		String seatIdB3 = "B3";
		buildSeat(screeningID, seatIdB3, seatB3, 2, 3);

		ImageView seatB4 = new ImageView();
		String seatIdB4 = "B4";
		buildSeat(screeningID, seatIdB4, seatB4, 2, 4);

		ImageView seatC1 = new ImageView();
		String seatIdC1 = "C1";
		buildSeat(screeningID, seatIdC1, seatC1, 3, 1);

		ImageView seatC2 = new ImageView();
		String seatIdC2 = "C2";
		buildSeat(screeningID, seatIdC2, seatC2, 3, 2);

		ImageView seatC3 = new ImageView();
		String seatIdC3 = "C3";
		buildSeat(screeningID, seatIdC3, seatC3, 3, 3);

		ImageView seatC4 = new ImageView();
		String seatIdC4 = "C4";
		buildSeat(screeningID, seatIdC4, seatC4, 3, 4);

		ImageView seatD1 = new ImageView();
		String seatIdD1 = "D1";
		buildSeat(screeningID, seatIdD1, seatD1, 4, 1);

		ImageView seatD2 = new ImageView();
		String seatIdD2 = "D2";
		buildSeat(screeningID, seatIdD2, seatD2, 4, 2);

		ImageView seatD3 = new ImageView();
		String seatIdD3 = "D3";
		buildSeat(screeningID, seatIdD3, seatD3, 4, 3);

		ImageView seatD4 = new ImageView();
		String seatIdD4 = "D4";
		buildSeat(screeningID, seatIdD4, seatD4, 4, 4);
	}

	/**
	 * Purpose: Builds the full functionality of each seat icon the screening
	 * list in the root view (button "show details" clicked) and stores it into
	 * javaFX elements
	 */
	public void buildSeat(int screeningID, String seatID, final ImageView seatFree, int col, int row) {

		boolean seatIsFree = bookingDAO.checkSeatAvailability(seatID, screeningID);


		if (seatIsFree) {
			Image seatFreeImg = new Image("/res/icons/seat-free.png");

			seatFree.setImage(seatFreeImg);

			GridPane.setRowIndex(seatFree, row);
			GridPane.setColumnIndex(seatFree, col);

			seatingMap.getChildren().addAll(seatFree);

			// Make seat icon clickable
			seatFree.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					// Remove unselected seat icon
					seatingMap.getChildren().remove(seatFree);

					// Add "selected seat" icon
					Image seatSelectedImg = new Image("/res/icons/seat-selected.png");
					ImageView seatSelected = new ImageView();
					seatSelected.setImage(seatSelectedImg);

					GridPane.setRowIndex(seatSelected, row);
					GridPane.setColumnIndex(seatSelected, col);

					seatingMap.getChildren().addAll(seatSelected);

					// Store seat ID in selection cache
					selectionDAO.addSelectedSeat(seatID, screeningID);
				}
			}); // closes EventHandler
		} else {
			Image seatBlockedImg = new Image("/res/icons/seat-blocked.png");
			seatFree.setImage(seatBlockedImg);

			GridPane.setRowIndex(seatFree, row);
			GridPane.setColumnIndex(seatFree, col);

			seatingMap.getChildren().addAll(seatFree);
		}
	}

	/**
	 * Purpose: Saves selection of seats if button is clicked
	 * 
	 * @param event
	 */
	@FXML
	public void confirmBookingButtonPushed(ActionEvent event) {

		// Retrieve seat IDs stored in selection table
		ObservableList<Selection> selectionList = selectionDAO.getSelection();
		int numTicketsSelected = selectionList.size();
		
		// Check for booked seats --> SCREENING ID
		ObservableList<Selection> screeningsList = selectionDAO.getSelection();
		int screeningID = screeningsList.get(0).getScreeningID();
		
		// Load screening details for selected screening and set text fields
		Screening selectedScreening = screeningDAO.getScreening(screeningID);
		
		
		
		// Alert shows all information about booking
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(null);
		alert.setHeaderText("Booking Successful!");
		alert.setContentText("Film:  " + selectedScreening.getFilmTitle() 
		+ "\nDate:  " + selectedScreening.getDateID()
		+ "\nTime:  " + selectedScreening.getTimeString()
		+ "\nTickets:  " + numTicketsSelected
		+ "\nName:  " + customerDAO.getCustomer(1).getFirstName() + " " + customerDAO.getCustomer(1).getLastName());

		alert.showAndWait();
		
		
		// Add seats to booking table and update ticket availability
		for(int i = 0; i < numTicketsSelected; i++)
		{
			BookingDAO b = new BookingDAOImpl();

			Selection s = new Selection();

			s = selectionList.get(i);
			b.addBooking(1, s.getSeatID(), s.getScreeningID());
			screeningDAO.decreaseAvailableSeats(s.getScreeningID());
		}

		// Clean up selection table for next booking
		selectionDAO.deleteSelection();

		/**
		 * Purpose: When button clicked, customer gets to view where to is able to view
		 * future and past bookings in two different tables and can delete future
		 * bookings.
		 * @param event
		 */
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

	/**
	 * Purpose: When button clicked, customer gets back to view where to select
	 * a film when button is clicked clicked.
	 * 
	 * @param event
	 */
	public void toSelectFilmForBooking(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerBookingProcessView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
		}
	}

	/**
	 * Purpose: When button clicked, customer gets to account settings view
	 * where to optionally change personal information
	 * 
	 * @param event
	 */
	public void toAccountSettings(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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

}
