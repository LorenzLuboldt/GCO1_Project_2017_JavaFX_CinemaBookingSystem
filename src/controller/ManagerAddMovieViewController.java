package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.FilmDAO;
import model.FilmDAOImpl;

public class ManagerAddMovieViewController implements Initializable {
	
	
	FilmDAO filmDAO = new FilmDAOImpl();
	
	// @Lorenz: Instance variables to choose files
	@FXML private Button chooseFile;
	@FXML private Button addFilmButtonPushed;
	
	// @Lorenz: Instance variables to create new Film objects
		@FXML private TextField filmTitleTextField;
		@FXML private TextArea filmDescriptionTextArea;
		@FXML private TextField filmActorsTextField;
		@FXML private TextField filmDirectorTextField;
		@FXML private ComboBox<String> filmGenreComboBox;
		@FXML private ListView<String> fileList;
		@FXML private TextField filmTrailerLinkTextField;
		
		
	
	@FXML
	private Label userLbl2;

	//File Chooser for uploading image to application & database
	public void chooseFile(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("C:\\Users\\Lorenz\\Desktop\\repos\\GCO1_Project_2017_JavaFX_CinemaBookingSystem\\resources"));
		fileChooser.getExtensionFilters().addAll(

				new ExtensionFilter("Image Files", "*. png"));
		File selectedFile = fileChooser.showOpenDialog(null);
		
		System.out.println("1");
		
		if(selectedFile != null) {
			fileList.getItems().add(selectedFile.getName());
		}
		else {
			System.out.println("File is not valid");
		}
		
		
	}
	
	// @Lorenz: method to enable manager to add film to database
	public void addFilmButtonPushed(ActionEvent event)	{
		
		// filmDAO method adds film to DB. 
		// Parameters are the user inputs from the text fields on the screen
		filmDAO.addFilm(filmTitleTextField.getText(), filmDescriptionTextArea.getText(), filmGenreComboBox.getValue().toString(),filmActorsTextField.getText(), filmDirectorTextField.getText(),  filmTrailerLinkTextField.getText());

	}
	
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
	
	public void linkToAddScreeningTimeView(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/view/ManagerAddScreeningView.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> genreList = FXCollections.observableArrayList("Action", "Adventure", "Animation","Biography", "Comedy","Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", "Musical", "Mistery", "Romance", "Sci-Fi", "Sport", "Thriller", "War", "Western");
		filmGenreComboBox.setItems(genreList);
	}
	
}
