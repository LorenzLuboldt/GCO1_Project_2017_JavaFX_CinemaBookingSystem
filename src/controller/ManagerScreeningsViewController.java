package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.chart.*;
import javafx.scene.Group;


// Import models
import model.BookingDAO;
import model.BookingDAOImpl;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
import model.Selection;
import model.SelectionDAO;
import model.SelectionDAOImpl;

public class ManagerScreeningsViewController implements Initializable {

	// Create instance variables
	@FXML private Label userLbl2;
	@FXML private Button downloadAllBookingData;
	@FXML private GridPane seatingMap;
	@FXML private PieChart pieChart; 
	
	@FXML private Label filmTitleLabel;
	@FXML private Label dateLabel;
	@FXML private Label timeLabel;	
	@FXML private Label successNotification;

	// Create DAO Objects for Data base access
	SelectionDAO selectionDAO = new SelectionDAOImpl();
	BookingDAO bookingDAO = new BookingDAOImpl();
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		loadPieChart();
		loadScreeningDetails();
		loadSeatingMap();

		
	}
	
	
	
	public void loadPieChart() {

		// Check for booked seats --> SCREENING ID
		ObservableList<Selection> screeningsList = selectionDAO.getSelection();
		int screeningID = screeningsList.get(0).getScreeningID();
		Screening s = screeningDAO.getScreening(screeningID);

		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data(s.availableSeats() + "Available Seats", s.getAvailableSeats()),
                new PieChart.Data(s.bookedSeats() + "Booked Seats", s.getBookedSeats()));
        
        pieChart.setTitle("Occupancy Rate");
        pieChart.setData(pieChartData);
       
	}
	
	public void loadScreeningDetails()	{
		
		// Check for booked seats --> SCREENING ID
		ObservableList<Selection> screeningsList = selectionDAO.getSelection();
		int screeningID = screeningsList.get(0).getScreeningID();
		
		// Load screening details for selected screening and set text fields
		Screening selectedScreening = screeningDAO.getScreening(screeningID);
		filmTitleLabel.setText(selectedScreening.getFilmTitle());
		dateLabel.setText(selectedScreening.getDateID());
		timeLabel.setText(selectedScreening.getTimeString());
	}
	
	public void loadSeatingMap() throws NullPointerException	{
		
		// Check for booked seats --> SCREENING ID
		ObservableList<Selection> screeningsList = selectionDAO.getSelection();
		int screeningID = screeningsList.get(0).getScreeningID();
		
		System.out.println(screeningID);
		
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

	// Builds the full functionality of each seat icon
	public void buildSeat(int screeningID, String seatID, final ImageView seatFree, int col, int row)	{
		
		boolean seatIsFree = bookingDAO.checkSeatAvailability(seatID, screeningID);
		
		System.out.println(seatIsFree);
		
		if(seatIsFree)	{
			Image seatFreeImg = new Image("/res/icons/seat-free.png");	
			
			seatFree.setImage(seatFreeImg);
			
			GridPane.setRowIndex(seatFree, row);
			GridPane.setColumnIndex(seatFree, col);
			
			seatingMap.getChildren().addAll(seatFree);     
		}
		else {
			Image seatBlockedImg = new Image("/res/icons/seat-blocked.png");	
			seatFree.setImage(seatBlockedImg);
			System.out.println("BLOCKED IMAGE");
			
			GridPane.setRowIndex(seatFree, row);
			GridPane.setColumnIndex(seatFree, col);
			
			seatingMap.getChildren().addAll(seatFree);
		}
	}

	public void GetManager(String user) {
		// TODO Auto-generated method stub
		userLbl2.setText(user);
	}

	// Manager is able to logout at this page
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

	// Manager is able to go back to overview page to select another option
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
	 * Purpose: Writing all the Screenings available in a text file
	 * 
	 * Sources:
	 * https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html,
	 * https://docs.oracle.com/javase/7/docs/api/java/io/PrintWriter.html
	 * 
	 * @author Lorenz
	 * @param event
	 */
	
	
	public void downloadBookingData(ActionEvent event) {
System.out.println("Method started");

		String fileName = "resources/allScreeningsData.csv";
		ScreeningDAO screeningDAO = new ScreeningDAOImpl();


		ObservableList<Screening> output = screeningDAO.getAllScreenings();
		
		try {
			
			PrintWriter outputStream = new PrintWriter (fileName);
		
			outputStream.println("ScreeningID" + "," + "FilmTitle" + "," + "Date" + "," + "Time" + "," + "AvailableSeats" + "," + "OccupancyRate");
			for(int i = 0; i < output.size(); i++) {
				System.out.println("2");
				Screening s = output.get(i);
				outputStream.println(s.getScreeningID() + "," + s.getFilmTitle() + "," + s.getDateID() + "," + s.getTimeString() + "," + s.getAvailableInfo() + "," + s.getOccupancyRate());
			}
			outputStream.close();

			successNotification.setText("Download successful.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}