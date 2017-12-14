package controller;

import java.io.File;
import java.sql.SQLException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
import model.SelectionDAO;
import model.SelectionDAOImpl;

/**
 * Purpose: Controls the elements in the specified row layout for the list view and populates the individual elements (Image, text etc.) with data from database
 * Sources: https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml, https://stackoverflow.com/questions/10699655/combo-box-key-value-pair-in-javafx-2/10700642#10700642, https://stackoverflow.com/questions/41319752/listview-setcellfactory-with-generic-label 
 * @author Lorenz
 *
 */

public class ScreeningListViewRowController {

	
	//Declares elements of the list view row layout
	@FXML
	HBox container;

	public HBox getContainer() {
		return container;
	}

	@FXML
	Label filmTitle;
	@FXML
	Label screeningDate;
	@FXML
	ImageView filmPoster;
	@FXML
	Button showScreeningDetails;
	@FXML
	Label seatAvailability;
	
	private Screening screening;
	private Film film;
	
	// Create DAO objects
	FilmDAO filmDAO = new FilmDAOImpl();
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	SelectionDAO selectionDAO = new SelectionDAOImpl();

	public Screening getScreening() {
		return screening;
	}
	
	public void setScreening(Screening screening) {
		this.screening = screening;
	}
	
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}


	public void initialize() {

		showScreeningDetails.setOnAction(event -> showScreeningDetailPage(event));
		
	}
	
	// Populates declared elements with respective information from the film object (linked to film table in Database)
	public void populateCells() throws SQLException, ClassNotFoundException {

		String filmtitle = screening.getFilmTitle();		
		

		Film film = filmDAO.getFilm(filmtitle);

	//Fills film title label with corresponding film title
		filmTitle.setText(film.getFilmTitle());

	//Fills genre label with corresponding film genre
		screeningDate.setText(screening.getDateID() + ", " + screening.getTimeString());	


	//Fills director label with corresponding film director
		seatAvailability.setText(screening.getBookedSeats() + "/16 Seats booked");	

	//Fills imageView with corresponding image from local resource folder
		int filmID = film.getFilmId();
		String imgPath = filmDAO.getFilmImagePath(filmID);
		File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		File file = new File(jarFile.getParentFile().getParent(), "images/" + imgPath);
		//final Image imageFile = new Image(System.getProperty("user.dir") + "/../resources/films/" + imgPath);
		Image img = new Image(file.toURI().toString());
		filmPoster.setImage(img);
		
	}
	
	
	
	/**
	 * Purpose: Button links to new Movie Detail View of the respective film
	 * @author Lorenz
	 */
	@FXML
	private void showScreeningDetailPage(ActionEvent event) {
		
		selectionDAO.addSelectedScreening(screening.getScreeningID());
		
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ManagerScreeningsView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			
		}
	}

}