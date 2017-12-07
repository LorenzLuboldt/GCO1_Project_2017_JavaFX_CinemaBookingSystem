package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;

/**
 * Purpose: This controller is connected to the ManagerMoviesView, where the staff member can add a new film in the sreening schedule
 * and is also able to view an up-to date list of all movies shown at least once.
 * This controller connects movie table to tableView in the view & populates the tableView, once the show movie selection button is clicked.
 * @author Lorenz
 *
 */
public class ManagerMoviesViewController implements Initializable {
	
	FilmDAO filmDAO = new FilmDAOImpl();
	@FXML
	private Label userLbl2;
	
	//Configure film table
	@FXML private TableView<Film> tableView;
	@FXML private TableColumn<Film, String> film_title_column;
	@FXML private TableColumn<Film, String> film_description_column;
	
	// @Michael: Instance variables to create new Film objects
	@FXML private TextField filmTitleTextField;
	@FXML private TextField filmDescriptionTextField;
	
	// @Michael: Instance variables to edit Film objects
//	@FXML private Button popUpEditFilmButton;
//	@FXML private Button cancelFilmEditButton;
//	@FXML private TextField editFilmTitleTextField;
//	@FXML private TextField editFilmDescriptionTextField;
	
//	@FXML private DatePicker screeningDatePicker; not used as Film object doesn't contain a date variable
	
	

	public void GetManager(String user) {
		// TODO Auto-generated method stub
		userLbl2.setText(user);
	}
	
	// Populates the table in this view 
	public void ShowMovieSelection(ActionEvent event) {
		//Set up the columns in the table
		film_title_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmTitle"));
		film_description_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmDescription"));

		//load data from 
		final ObservableList<Film> filmList = filmDAO.getAllFilms();
		tableView.setItems(filmList);
	}

	// Manager is able to logout at this page
	public void SignOut(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/view/LoginView.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}
	
//	public void popUpEditFilmButtonPushed(ActionEvent event) throws IOException {
//		 
//		Stage stage = null;
//		Parent root;
//		
//		if(event.getSource()==popUpEditFilmButton) {
//			stage = new Stage();
//			root = FXMLLoader.load(getClass().getResource("EditFilmDetailsPopUp.fxml"));
//			stage.setScene(new Scene(root));
//			stage.setTitle("Edit Film");
//			stage.initModality(Modality.APPLICATION_MODAL);
//			stage.initOwner(popUpEditFilmButton.getScene().getWindow());
//			stage.showAndWait();
//		 }
//		 else {
//		   stage = (Stage)cancelFilmEditButton.getScene().getWindow();
//		   stage.close();
//		  }
//	 
//	}
	
	public void goToFilmEdit(ActionEvent event) {
		try {	
	((Node) event.getSource()).getScene().getWindow().hide();
	Stage primaryStage = new Stage();
	FXMLLoader loader = new FXMLLoader();
	Pane root = loader.load(getClass().getResource("/view/EditFilmDetailsPopUpfxml").openStream());
	Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
		} catch (Exception e) {
			
		}

	}
	
	// Manager is able to go back to overview page to select another option
	public void toDashboard(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/view/ManagerRoot.fxml").openStream());
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}
	@Override
public void initialize(URL location, ResourceBundle resources) {
		


	}

	// @Michael: method to enable manager to add film to database and see updated film list
	public void addFilmButtonPushed()	{
		
		// filmDAO method adds film to DB. 
		// Parameters are the user inputs from the text fields on the screen
		filmDAO.addFilm(filmTitleTextField.getText(), filmDescriptionTextField.getText());
		
		// Empty text fields
		filmTitleTextField.clear();
		filmDescriptionTextField.clear();
		
		
		// Show updated films in table (same as above method ShowMovieSelection(ActionEvent))
		film_title_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmTitle"));
		film_description_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmDescription"));

		//load data from 
		final ObservableList<Film> filmList = filmDAO.getAllFilms();
		tableView.setItems(filmList);
	}
	
	public void deleteFilmButtonPushed()	{
		
		// delete selected film from DB
		filmDAO.deleteFilm(tableView.getSelectionModel().getSelectedItem());
		
		// Show updated films in table (same as above method ShowMovieSelection(ActionEvent))
		film_title_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmTitle"));
		film_description_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmDescription"));

		//load data from 
		final ObservableList<Film> filmList = filmDAO.getAllFilms();
		tableView.setItems(filmList);
	}
	
}
	
	
