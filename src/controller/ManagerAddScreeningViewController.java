package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class ManagerAddScreeningViewController implements Initializable {
	
	// Create instance variables
	@FXML
	private Label userLbl2;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField filmTrailerLinkTextField;
	@FXML
	private ComboBox<String> screeningTimeComboBox;
	@FXML
	private ComboBox<String> currentFilmsAvailableComboBox;
	@FXML Label successNotification;
	
	@FXML Label errorNotification;

	// DAO objects to query database
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	FilmDAO filmDAO = new FilmDAOImpl();

	
	// Event Listener on Button.onAction
	@FXML
	public void toDashboard(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
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
	public void backToMovieOverview(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ManagerMoviesView.fxml").openStream());
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
	public void addScreeningButtonPushed(ActionEvent event)	{
		
		// ScreeningDAO method adds Screening Time to DB. 
		// Parameters are the user inputs from the text fields on the screen
		int day = datePicker.getValue().getDayOfMonth();
		int month =datePicker.getValue().getMonthValue();
		int year = datePicker.getValue().getYear();
		
		
		try {
			
			screeningDAO.addScreening(datePicker.getValue().toString(), year , month, day, screeningTimeComboBox.getValue(), currentFilmsAvailableComboBox.getValue());
			successNotification.setText("Successfully Added Screening Slot.");
		}
		
		catch (Exception e) {
			
			errorNotification.setText("Overlap With Other Screening");
			e.printStackTrace();
	}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<String> screeningTimeList = FXCollections.observableArrayList("2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM");
		screeningTimeComboBox.setItems(screeningTimeList);
		
		ObservableList<Film> allFilms = filmDAO.getAllFilms();
		ObservableList<String> currentFilmTitle = FXCollections.observableArrayList();
		
		for (int x = 0; x < allFilms.size(); ++x) {
			
		String f = allFilms.get(x).getFilmTitle();
		currentFilmTitle.add(f);
			
		}
		
		currentFilmsAvailableComboBox.setItems(currentFilmTitle);

	}
}
