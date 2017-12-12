package controller;

import java.io.File;
import java.sql.SQLException;

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
	
	//Creates a Film object
	FilmDAO filmDAO = new FilmDAOImpl();
	//Creates a Screening object
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();

	
	public Screening setScreening(Screening screening) {
		return screening;
	}



	public void initialize() {

		showScreeningDetails.setOnAction(event -> showScreeningDetailPage(event));
		
	}
	
	// Populates declared elements with respective information from the film object (linked to film table in Database)
	public void populateCells() throws SQLException, ClassNotFoundException {
		System.out.println(18);

		String filmtitle = screening.getFilmTitle();		
		System.out.println(filmtitle);
		
		System.out.println(19);

		Film film = filmDAO.getFilm(filmtitle);
		System.out.println(20);

	//Fills film title label with corresponding film title
		filmTitle.setText(film.getFilmTitle());
		System.out.println(21);

	//Fills genre label with corresponding film genre
		screeningDate.setText(screening.getDateID() + "," + screening.getTimeString());	
		System.out.println(22);


	//Fills director label with corresponding film director
		seatAvailability.setText(screening.getAvailableSeats() + "/16 Seats available");	

	//Fills imageView with corresponding image from local resource folder
		
	// Set ImageView to display image
		File file = new File(System.getProperty("user.dir") + "/resources/films/" + film.getFilmImage());
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
