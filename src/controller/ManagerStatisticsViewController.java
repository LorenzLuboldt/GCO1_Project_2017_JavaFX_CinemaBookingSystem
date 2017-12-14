package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Screening;
import model.Film;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;

/**
 * Purpose: This controller controls the view on which the manager can analyze 
 * past screenings and see, how well they sold.
 * 
 * @author Michael
 *
 */

public class ManagerStatisticsViewController implements Initializable {
	@FXML
	private Label userLbl2;
	
	// Configure table of past screenings
	@FXML private TableView<Screening> statisticsTable;
	@FXML private TableView<Film> filmStatisticsTable;
	
	// Columns
	@FXML private TableColumn<Screening, String> date_id_column;
	@FXML private TableColumn<Screening, String> time_string_column;
	@FXML private TableColumn<Screening, String> film_title_column;
	@FXML private TableColumn<Screening, Integer> available_seats_column;
	@FXML private TableColumn<Screening, Integer> booked_seats_column;
	@FXML private TableColumn<Screening, String> occupancy_rate_column;

	// Create DAO objects to query database
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	
/**
 * Purpose: Initialises the name of user logged in
 */
@Override
public void initialize(URL location, ResourceBundle resources) {
	GetManager(LoginController.username);
	loadStatisticsTable();
}

/**
 * Purpose: Displays the name of the employee currently logged in.
 * 
 * @param user
 */
public void GetManager(String user) {
	userLbl2.setText("User: " + user);
}

	public void loadStatisticsTable()	{
		
		// Set column values for futureBookingsTable
		date_id_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("dateID"));
		time_string_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("timeString"));
		film_title_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("filmTitle"));
		available_seats_column.setCellValueFactory(new PropertyValueFactory<Screening, Integer>("availableSeats"));
		booked_seats_column.setCellValueFactory(new PropertyValueFactory<Screening, Integer>("bookedSeats"));
		occupancy_rate_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("occupancyRate"));
		
		// Load screening data from data base
		ObservableList<Screening> pastScreeningList = screeningDAO.getPastScreenings();
		
		statisticsTable.setItems(pastScreeningList);
		
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
	 * Purpose: When button clicked, manager gets back to LoginView (Logout)
	 * clicked.
	 * 
	 * @param event
	 */
	@FXML
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
	public void backToMovieOverview(ActionEvent event) {
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
}
