package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;

public class ManagerScreeningsViewController implements Initializable {

	@FXML
	private Label userLbl2;
	@FXML
	private Button downloadAllBookingData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadSeatingMap();
		
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
		String fileName = "resources/allScreeningsData.txt";
		ScreeningDAO screeningDAO = new ScreeningDAOImpl();

		ObservableList<Screening> output = screeningDAO.getAllScreenings();
		
		try {
			
			PrintWriter outputStream = new PrintWriter (fileName);
			
			for(int i = 0; i < output.size(); i++) {
				System.out.println("2");
				Screening s = output.get(i);
				outputStream.println(s.getScreeningID() + "," + s.getFilmTitle() + "," + s.getDateID() + "," + s.getTimeInt() + "," + s.getAvailableSeats());
			}
			outputStream.close();

			System.out.println("Done");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}