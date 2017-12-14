package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.ListViewCell;

/**
 * Purpose: This controller is connected to the ManagerMoviesView, where the
 * staff member can add a new film in the screening schedule and is also able to
 * view an up-to date list of all movies shown at least once. This controller
 * connects movie database table to the list view & populates it querying the
 * database.
 * 
 * @author Lorenz
 *
 */
public class ManagerMoviesViewController implements Initializable {

	FilmDAO filmDAO = new FilmDAOImpl();
	@FXML
	private Label userLbl2;

	@FXML
	private TableView<Film> tableView;
	@FXML
	private TableColumn<Film, String> film_title_column;
	@FXML
	private TableColumn<Film, String> film_description_column;

	@FXML
	private TextField filmTitleTextField;
	@FXML
	private TextField filmDescriptionTextField;

	@FXML
	private Button popUpEditFilmButton;
	@FXML
	private Button cancelFilmEditButton;
	@FXML
	private TextField editFilmTitleTextField;
	@FXML
	private TextField editFilmDescriptionTextField;
	@FXML
	private ListView<Film> allFilmsList;

	private List<Film> filmList = new ArrayList<>();
	private ObservableList<Film> observableList = FXCollections.observableArrayList();

	/**
	 * Purpose: Initialises the user name on the page header and initialises the
	 * ListView defined in method below
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GetManager(LoginController.username);
		setListView();
	}

	/**
	 * Purpose: Displays the name of the Manager currently logged in.
	 * 
	 * @param user
	 */
	public void GetManager(String user) {
		userLbl2.setText("User: " + user);
	}

	/**
	 * Purpose: Sets up Movie ListView displaying all current Movies with the
	 * custom ListRows Source:
	 * https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
	 * 
	 * @author Lorenz
	 */
	private void setListView() {

		// Retrieve all film entries from the database
		try {
			filmList = filmDAO.getAllFilms();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Fill the Observable List with items pulled from the database
		observableList.setAll(filmList);
		// Fill ListView with content
		allFilmsList.setItems(observableList);
		// Populates individual list rows with customised list view cell
		allFilmsList.setCellFactory(ListView -> new ListViewCell());
	}

	/**
	 * Purpose: When button clicked, manager gets back to LoginView (Logout)
	 * clicked.
	 * 
	 * @param event
	 */
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

	/**
	 * Purpose: When button clicked, manager gets back to landing page (manager
	 * dashboard) clicked.
	 * 
	 * @param event
	 */
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
	 * Purpose: Links to the view where to add a film when button is clicked
	 * @param event
	 */
	public void LinkToAddFilm(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ManagerAddMovieView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
		}
	}

	
	/**
	 * Purpose: Links to the view where to add a new screening when button is clicked
	 * @param event
	 */
	public void linkToAddScreeningTime(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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
}
