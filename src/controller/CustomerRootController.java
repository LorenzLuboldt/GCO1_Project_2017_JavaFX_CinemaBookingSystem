package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import helper.ListViewCellCustomer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;

/**
 * Purpose: Root view of the user after login. Controller selects all current
 * available screenings from database and provides options to go to ticket
 * booking process, view booking history and edit user information
 * 
 * @author Lorenz
 *
 */
public class CustomerRootController implements Initializable {
	@FXML
	private Label userLbl;
	@FXML
	private ListView<Film> allFilmsList;
	private List<Film> filmList = new ArrayList<>();
	private ObservableList<Film> observableList = FXCollections.observableArrayList();
	// Creates a Film object
	FilmDAO filmDAO = new FilmDAOImpl();

	/**
	 * Purpose: Initialises display of user name in header and loads placeholder
	 * for table view of screenings available
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GetCustomer(LoginController.username);
		setListView();
	}

	/**
	 * Purpose: Displays the name of the Manager currently logged in.
	 * 
	 * @param user
	 */
	public void GetCustomer(String user) {
		userLbl.setText("User " + user);
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

		// Allow for custom display of the ListView Items

		allFilmsList.setCellFactory(ListView -> new ListViewCellCustomer());

	}

	/**
	 * Purpose: When button clicked, customer gets to account settings view
	 * where to optionally change personal information
	 * 
	 * @param event
	 */
	public void toAccountSettings(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerEditInformationView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

		}

	}

	/**
	 * Purpose: When button clicked, customer gets to view where he or she is
	 * able to book tickets for certain screenings
	 * 
	 * @param event
	 */
	public void BookTickets(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerBookingProcessView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
		}
	}

	/**
	 * Purpose: When button clicked, customer gets back to LoginView (Logout)
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
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

	/**
	 * Purpose: When button clicked, customer gets to view where to is able to
	 * view future and past bookings in two different tables and can delete
	 * future bookings.
	 * 
	 * @param event
	 */
	public void goToBookingsHistory(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerBookingHistoryView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
		}
	}
}
