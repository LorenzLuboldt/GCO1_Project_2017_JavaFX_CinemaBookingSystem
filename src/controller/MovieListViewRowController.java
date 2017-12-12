package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;

/**
 * Purpose: Controls the elements in the specified row layout for the list view and populates the individual elements (Image, text etc.) with data from database
 * Sources: https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml, https://stackoverflow.com/questions/10699655/combo-box-key-value-pair-in-javafx-2/10700642#10700642, https://stackoverflow.com/questions/41319752/listview-setcellfactory-with-generic-label 
 * @author Lorenz
 *
 */

public class MovieListViewRowController {

	
	//Declares elements of the list view row layout
	@FXML
	HBox container;

	public HBox getContainer() {
		return container;
	}

	@FXML
	Label filmTitle;
	@FXML
	Label filmGenre;
	@FXML
	ImageView filmPoster;
	@FXML
	Button showFilmDetails;
	@FXML
	Label filmActors;
	@FXML
	Label filmDirector;
	@FXML
	TextArea filmDescription;
	
	
	private Film film;
	
	//Creates a Film object
	FilmDAO filmDAO = new FilmDAOImpl();
	

	
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public void initialize() {

		showFilmDetails.setOnAction(event -> showMovieDetailPage(event));
		
	}
	
	// Populates declared elements with respective information from the film object (linked to film table in Database)
	public void populateCells() {
		
	// Sets 
		film.getFilmTitle();
	//Fills film title label with corresponding film title
		filmTitle.setText(film.getFilmTitle());
	//Fills genre label with corresponding film genre
		filmGenre.setText(film.getFilmGenre());	

	//Fills actor label with corresponding film actors
		filmActors.setText(film.getFilmCastMembers());	

	//Fills director label with corresponding film director
		filmDirector.setText(film.getFilmDirector());	

	//Fills director label with corresponding film director
		filmDescription.setText(film.getFilmDescription());		

	//Fills imageView with corresponding image from local resource folder
		int filmID = film.getFilmId();
		String imgPath = filmDAO.getFilmImagePath(filmID);
	// Set ImageView to display image
		File file = new File(System.getProperty("user.dir") + "/resources/films/" + imgPath);
		//final Image imageFile = new Image(System.getProperty("user.dir") + "/../resources/films/" + imgPath);
		Image img = new Image(file.toURI().toString());
		filmPoster.setImage(img);
		
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
