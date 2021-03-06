package controller;

import java.io.File;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;

/**
 * Purpose: Controls the elements in the specified row layout for the list view
 * and populates the individual elements (Image, text etc.) with data from
 * database 
 * Tutorials & resources:
 * https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml,
 * https://stackoverflow.com/questions/10699655/combo-box-key-value-pair-in-javafx-2/10700642#10700642,
 * https://stackoverflow.com/questions/41319752/listview-setcellfactory-with-generic-label
 * 
 * @author Lorenz
 *
 */
public class MovieListViewRowControllerManager {

	// Declares elements inserted into the  list view row layout
	@FXML
	HBox container;
	@FXML
	Label filmTitle;
	@FXML
	Label filmGenre;
	@FXML
	ImageView filmPoster;
	@FXML
	Label filmActors;
	@FXML
	Label filmDirector;
	@FXML
	TextArea filmDescription;

	private Film film;
	FilmDAO filmDAO = new FilmDAOImpl();

	/**
	 * Returns container containing individual elements to the list view
	 * @return
	 */
	public HBox getContainer() {
		return container;
	}

	//Setters & Getters for film and screening objects
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	/**
	 * Purpose: Populates declared elements with respective information from the film object (linked to film table in Database)
	 */
	public void populateCells() {

		// Sets single film (in the list view controller, method will iterate through each film listing)
		film.getFilmTitle();
		// Fills film title label with corresponding film title
		filmTitle.setText(film.getFilmTitle());
		// Fills genre label with corresponding film genre
		filmGenre.setText(film.getFilmGenre());
		// Fills actor label with corresponding film actors
		filmActors.setText(film.getFilmCastMembers());
		// Fills director label with corresponding film director
		filmDirector.setText(film.getFilmDirector());
		// Fills film description text area with corresponding film desription from database
		filmDescription.setText(film.getFilmDescription());
		// Fills imageView with corresponding image from local resource folder
		int filmID = film.getFilmId();
		String imgPath = filmDAO.getFilmImagePath(filmID);
		// Set ImageView to display image

		File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		File file = new File(jarFile.getParentFile().getParent(), "images/" + imgPath);

		Image img = new Image(file.toURI().toString());
		filmPoster.setImage(img);
	}
}
