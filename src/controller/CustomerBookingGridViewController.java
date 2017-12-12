package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Booking;
import model.BookingDAO;
import model.BookingDAOImpl;
import model.Film;
import model.Selection;
import model.SelectionDAO;
import model.SelectionDAOImpl;

/**
 * References for seating map: 
 * https://docs.oracle.com/javafx/2/api/javafx/scene/layout/GridPane.html
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
 * https://stackoverflow.com/questions/25550518/add-eventhandler-to-imageview-contained-in-tilepane-contained-in-vbox
 * 
 * @author Michael
 *
 */


public class CustomerBookingGridViewController implements Initializable{
	
	// Create instance variables for seating map
	@FXML
	private GridPane seatingMap;
	
	// Create other instance variables
	@FXML
	private Label userLbl;
	static String seatID;
		
	// DAO objects to query database
	SelectionDAO selectionDAO = new SelectionDAOImpl();
	BookingDAO bookingDAO = new BookingDAOImpl();
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		loadSeatingMap();		
		
	}
	
	
	public void deselectSeatsButtonPushed()	{
		loadSeatingMap();
	}
		
	// Loads new seating map
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
			Image seatFreeImg = new Image("/../bin/icons/seat-free.png");	
			
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
				   	 Image seatSelectedImg = new Image("/../bin/icons/seat-selected.png");
				   	 ImageView seatSelected = new ImageView();
				   	 seatSelected.setImage(seatSelectedImg);
				   
					 GridPane.setRowIndex(seatSelected,row);
					 GridPane.setColumnIndex(seatSelected, col);
					
					 seatingMap.getChildren().addAll(seatSelected);
			    	 
					 // Store seat ID in selection cache
			    	 selectionDAO.addSelectedSeat(seatID, screeningID);
			    	 System.out.println("User selected seat: " + seatID);
			 	}
			});   // closes EventHandler 	     
		}
		else {
			Image seatBlockedImg = new Image("/../bin/icons/seat-blocked.png");	
			seatFree.setImage(seatBlockedImg);
			System.out.println("BLOCKED IMAGE");
			
			GridPane.setRowIndex(seatFree, row);
			GridPane.setColumnIndex(seatFree, col);
			
			seatingMap.getChildren().addAll(seatFree);
		}
	}
	
	// TODO delete this method if no longer needed
	// Returns a seat ID to be stored in the selection cache
	public String computeSeatID(int col, int row)	{
		
		String seatID = null; // return variable
		
		if(col == 1)
		{
			switch(row)	{
			case 1 : seatID = "A1";	
				break;
			case 2 : seatID = "A2";
				break;
			case 3 : seatID = "A3";
				break;
			case 4 : seatID = "A4";
				break;
			}
		}
		else if (col == 2)
		{
			switch(row)	{
			case 1 : seatID = "B1";	
				break;
			case 2 : seatID = "B2";
				break;
			case 3 : seatID = "B3";
				break;
			case 4 : seatID = "B4";
				break;
			}
		}
		else if (col == 3)
		{
			switch(row)	{
			case 1 : seatID = "C1";	
				break;
			case 2 : seatID = "C2";
				break;
			case 3 : seatID = "C3";
				break;
			case 4 : seatID = "C4";
				break;
			}
		}	
		else if (col == 4)
		{
			switch(row)	{
			case 1 : seatID = "D1";	
				break;
			case 2 : seatID = "D2";
				break;
			case 3 : seatID = "D3";
				break;
			case 4 : seatID = "D4";
				break;
			}
		}	
		return seatID;
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void confirmBookingButtonPushed(ActionEvent event) {
		
		// Retrieve seat IDs stored in selection table
		ObservableList<Selection> selectionList = selectionDAO.getSelection();
		int index = selectionList.size();
		
		for(int i = 0; i < index; i++)
		{
			BookingDAO b = new BookingDAOImpl();
			Booking bo = new Booking();
			
			
			Selection s = new Selection();
			
//			TableView screeningsTable = new TableView();
//			int screeningId;
//			
//			screeningId = screeningsTable.getSelectionModel().getSelectedItem().getId();
			
			s = selectionList.get(i);			
			b.addBooking(1, s.getSeatID(), s.getScreeningID());
		}
		
		// Clean up selection table for next booking
		selectionDAO.deleteSelection();
		
		// Send user to 'My bookings'
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
	
	public void toSelectFilmForBooking(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
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
	
}
