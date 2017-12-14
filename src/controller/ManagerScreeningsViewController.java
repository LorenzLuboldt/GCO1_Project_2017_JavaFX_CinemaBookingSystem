package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.chart.*;

// Import models
import model.BookingDAO;
import model.BookingDAOImpl;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
import model.Selection;
import model.SelectionDAO;
import model.SelectionDAOImpl;

/**
 * Purpose: This class provides a detail for for a single screening the employee
 * selects on the previous page (employee root view).The employee (manager) can
 * view explicit booked and available seats, graphical summary of it and can
 * download all screening data as csv text file.
 * 
 * @author Lorenz
 *
 */
public class ManagerScreeningsViewController implements Initializable {

	@FXML
	private Label userLbl2;
	@FXML
	private Button downloadAllBookingData;
	@FXML
	private GridPane seatingMap;
	@FXML
	private PieChart pieChart;
	@FXML
	private Label filmTitleLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private Label successNotification;

	// Create DAO Objects for data base access
	SelectionDAO selectionDAO = new SelectionDAOImpl();
	BookingDAO bookingDAO = new BookingDAOImpl();
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();

	/**
	 * Purpose: Initialises the content of pie chart, seating map and screening
	 * details if the view opens
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadPieChart();
		loadScreeningDetails();
		loadSeatingMap();
		GetManager(LoginController.username);
	}

	/**
	 * Purpose: Displays the name of the Manager currently logged in.
	 * 
	 * @param user
	 */
	public void GetManager(String user) {
		userLbl2.setText("User: " + user);
	}

	/**
	 * Purpose: Method pulls the pie chart data from the screening chosen from
	 * the screening list in the root view (button "show details" clicked)
	 */
	public void loadPieChart() {

		// Check for booked seats --> SCREENING ID
		ObservableList<Selection> screeningsList = selectionDAO.getSelection();
		int screeningID = screeningsList.get(0).getScreeningID();
		Screening s = screeningDAO.getScreening(screeningID);

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data(s.getAvailableSeats() + " Available Seats", s.getAvailableSeats()),
				new PieChart.Data(s.getBookedSeats() + " Booked Seats", s.getBookedSeats()));

		pieChart.setTitle("Occupancy Rate");
		pieChart.setData(pieChartData);

	}

	/**
	 * Purpose: Method pulls the screening details from the screening chosen
	 * from the screening list in the root view (button "show details" clicked)
	 * and stores it into javaFX elements
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
	 * Purpose: Method recreates non-clickable seating map from the screening
	 * chosen from the screening list in the root view (button "show details"
	 * clicked) and stores it into javaFX elements.
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
		} else {
			Image seatBlockedImg = new Image("/res/icons/seat-blocked.png");
			seatFree.setImage(seatBlockedImg);
			System.out.println("BLOCKED IMAGE");

			GridPane.setRowIndex(seatFree, row);
			GridPane.setColumnIndex(seatFree, col);

			seatingMap.getChildren().addAll(seatFree);
		}
	}

	/**
	 * Purpose: When button clicked, manager gets back to LoginView (Logout)
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
	 * Purpose: When button clicked, manager gets back to landing page (manager
	 * dashboard) clicked.
	 * 
	 * @param event
	 */
	public void toDashboard(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ManagerRoot.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

	/**
	 * Purpose: Writing all the Screenings (future&past) available in a CSV text
	 * file
	 * 
	 * Tutorials & resources:
	 * https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html,
	 * https://docs.oracle.com/javase/7/docs/api/java/io/PrintWriter.html
	 * https://www.youtube.com/watch?v=WEZRc0GoP3E&t=458s
	 * 
	 * @param event
	 */
	public void downloadBookingData(ActionEvent event) {
		System.out.println("Method started");

		String fileName = "resources/allScreeningsData.csv";
		ScreeningDAO screeningDAO = new ScreeningDAOImpl();

		ObservableList<Screening> output = screeningDAO.getAllScreenings();

		try {
			PrintWriter outputStream = new PrintWriter(fileName);

			outputStream.println("ScreeningID" + "," + "FilmTitle" + "," + "Date" + "," + "Time" + ","
					+ "AvailableSeats" + "," + "OccupancyRate");
			for (int i = 0; i < output.size(); i++) {
				System.out.println("2");
				Screening s = output.get(i);
				outputStream.println(s.getScreeningID() + "," + s.getFilmTitle() + "," + s.getDateID() + ","
						+ s.getTimeString() + "," + s.getAvailableInfo() + "," + s.getOccupancyRate());
			}
			outputStream.close();

			successNotification.setText("Download successful.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}