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

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

public class ManagerAddScreeningViewController implements Initializable {
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
		
		screeningDAO.addScreening(datePicker.getValue().toString(), year , month ,screeningTimeComboBox.getValue().toString(), day, filmID);
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<String> screeningTimeList = FXCollections.observableArrayList("14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00");
		screeningTimeComboBox.setItems(screeningTimeList);
		
		ObservableList<Film> allFilms = filmDAO.getAllFilms();
		
		for (int x = 0; x < allFilms.size(); ++x) {
			
		StringProperty f = allFilms.get(x).filmTitleProperty();
			
			
		}
		
		ObservableList<String> currentFilmTitles = filmDAO.getAllFilms();
		currentFilmsAvailableComboBox.setItems(currentFilmTitles);

	}
}
