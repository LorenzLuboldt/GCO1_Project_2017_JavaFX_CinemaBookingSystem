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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;

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
//	private ImageView seat_A1 = new ImageView("http://www.pvhc.net/img5/qgzhvcgxvgymswayxxdb.jpg");
//	private ImageView seat_A2;
//	private ImageView seat_A3;
//	private ImageView seat_A4;
//	private ImageView seat_B1;
//	private ImageView seat_B2;
//	private ImageView seat_B3;
//	private ImageView seat_B4;
//	private ImageView seat_C1;
//	private ImageView seat_C2;
//	private ImageView seat_C3;
//	private ImageView seat_C4;
//	private ImageView seat_D1;
//	private ImageView seat_D2;
//	private ImageView seat_D3;
//	private ImageView seat_D4;
	
	// Create other instance variables
	@FXML
	private Label userLbl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadSeatingMap();
	}
	
	
	public void deselectSeatsButtonPushed()	{
		loadSeatingMap();
		System.out.println("User deselected seat.");
	}
		
	public void loadSeatingMap() throws NullPointerException	{
		System.out.println("___________________LOAD SEATS BUTTON PUSHED:_____________________");
		
		// Create and build all seats
		ImageView seatA1 = new ImageView();
		buildSeat(seatA1, 1, 1);
		
		ImageView seatA2 = new ImageView();
		buildSeat(seatA2, 1, 2);
		
		ImageView seatA3 = new ImageView();
		buildSeat(seatA3, 1, 3);
		
		ImageView seatA4 = new ImageView();
		buildSeat(seatA4, 1, 4);
		
		ImageView seatB1 = new ImageView();
		buildSeat(seatB1, 2, 1);
		
		ImageView seatB2 = new ImageView();
		buildSeat(seatB2, 2, 2);
		
		ImageView seatB3 = new ImageView();
		buildSeat(seatB3, 2, 3);
		
		ImageView seatB4 = new ImageView();
		buildSeat(seatB4, 2, 4);
		
		ImageView seatC1 = new ImageView();
		buildSeat(seatC1, 3, 1);
		
		ImageView seatC2 = new ImageView();
		buildSeat(seatC2, 3, 2);
		
		ImageView seatC3 = new ImageView();
		buildSeat(seatC3, 3, 3);
		
		ImageView seatC4 = new ImageView();
		buildSeat(seatC4, 3, 4);
		
		ImageView seatD1 = new ImageView();
		buildSeat(seatD1, 4, 1);
		
		ImageView seatD2 = new ImageView();
		buildSeat(seatD2, 4, 2);
		
		ImageView seatD3 = new ImageView();
		buildSeat(seatD3, 4, 3);
		
		ImageView seatD4 = new ImageView();
		buildSeat(seatD4, 4, 4);
				
	}
	
	public void buildSeat(final ImageView seatFree, int col, int row)	{
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
		    	 
		    	 // Add selected seat icon
			   	 Image seatSelectedImg = new Image("/../bin/icons/seat-selected.png");
			   	 ImageView seatSelected = new ImageView();
			   	 seatSelected.setImage(seatSelectedImg);
			   
				 GridPane.setRowIndex(seatSelected,row);
				 GridPane.setColumnIndex(seatSelected, col);
				
				 seatingMap.getChildren().addAll(seatSelected);
		    	 
		    	 int seatID = 15;
		    	 System.out.println("User selected seat: " + seatID);
		 	}
		});   // closes EventHandler 	     
	}
	
	// Event Listener on Button.onAction
	@FXML
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
