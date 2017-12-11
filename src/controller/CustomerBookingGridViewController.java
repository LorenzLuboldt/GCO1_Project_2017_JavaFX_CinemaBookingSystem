package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;

/**
 * References for seating map: 
 * https://docs.oracle.com/javafx/2/api/javafx/scene/layout/GridPane.html
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
 * 
 * @author Michael
 *
 */


public class CustomerBookingGridViewController {
	
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
	
	
	public void loadSeatsButtonPushed() throws NullPointerException	{
		System.out.println("___________________LOAD SEATS BUTTON PUSHED:_____________________");
		
		seatingMap.getColumnConstraints().add(new ColumnConstraints(75)); // column 1 is 100 wide
	    seatingMap.getColumnConstraints().add(new ColumnConstraints(75));
		
		for(int col = 1; col <= 4; col++)	
		{
			for(int row = 1; row <= 4; row++)	
			{    
				
				// load the image
				Image image = new Image("/../bin/icons/seat-free.png");		
				ImageView seat = new ImageView();
				seat.setImage(image);
				
			    GridPane.setRowIndex(seat, row);
			    GridPane.setColumnIndex(seat, col);
			    
				 // Add children to seating map
				 seatingMap.getChildren().addAll(seat);
				 System.out.println("Seat added for: " + col + "," + row);
			}
		}
		
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
