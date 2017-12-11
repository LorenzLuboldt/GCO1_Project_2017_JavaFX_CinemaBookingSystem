package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.FilmDAO;
import model.FilmDAOImpl;

/**
 * Purpose: Controls the elements in the specified row layout for the list view and populates the individual elements (Image, text etc.) with data from database
 * Sources: https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml, https://stackoverflow.com/questions/10699655/combo-box-key-value-pair-in-javafx-2/10700642#10700642, https://stackoverflow.com/questions/41319752/listview-setcellfactory-with-generic-label 
 * @author Lorenz
 *
 */

public class ListViewRowController implements Initializable {

	
	//Declares elements of the list view row layout
	@FXML
	private Label filmTitle;
	@FXML
	private Label filmGenre;
	@FXML
	private ImageView filmPoster;
	@FXML
	private Button showFilmDetails;

	//Creates a Film object
	FilmDAO filmDAO = new FilmDAOImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		showFilmDetails.setOnAction(event -> showMovieDetailPage(event));
		
	}
	
	// Populates declared elements with respective information from the film object (linked to film table in Database)
	public void populateCells() {
		
		
		
	}
	
	
	
	/**
	 * Purpose: Button links to new Movie Detail View of the respective film
	 * @author Lorenz
	 */
	@FXML
	private void showMovieDetailPage(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerFilmDetailsView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			
		}
	}

}
