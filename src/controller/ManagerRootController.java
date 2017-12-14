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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ListViewCellScreening;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;

/**
 * Purpose: Landing page for the Employee after successful login. Shows all
 * future screenings sorted by date & time. Provides links to several other
 * views with cinema managing options
 * 
 * @author Lorenz
 *
 */
public class ManagerRootController implements Initializable {

	@FXML
	private Label userLbl2;
	@FXML
	private ListView<Screening> allScreeningList;
	private List<Screening> screeningList = new ArrayList<>();
	// Creates observable list for connecting screening list to final ListView
	private ObservableList<Screening> observableList = FXCollections.observableArrayList();
	// Creates a Screening object
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();

	/**
	 * Purpose: Initialises the ListView and populates it with all current
	 * future screenings.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setListView();
	}

	/**
	 * Purpose: Setup Movie ListView displaying all current Screenings with
	 * customised ListView cells Tutorials & resources:
	 * https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
	 */
	private void setListView() {

		// Retrieve all upcoming screenings from the database via DAO Model
		try {
			screeningList = screeningDAO.getUpcomingScreenings();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Fill the Observable List with items pulled from the database
		observableList.setAll(screeningList);

		// Fill ListView with content
		allScreeningList.setItems(observableList);

		// Allow for custom display of the ListView Items, link to class
		// ListViewCellScreening, which populates ListView with customised Cell
		allScreeningList.setCellFactory(ListView -> new ListViewCellScreening());
	}

	/**
	 * Purpose: Displays the name of the Manager currently logged in.
	 * 
	 * @param user
	 */
	public void GetManager(String user) {
		userLbl2.setText("Welcome, " + user + "!");
	}

	/**
	 * Purpose: Makes the connection to ManagerMoviesView when button clicked.
	 * 
	 * @param event
	 */
	public void GoToMovieSelection(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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

	/**
	 * Purpose: Makes the connection to ManagerStatisticsView when button
	 * clicked.
	 * 
	 * @param event
	 */
	public void GoToStatistics(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ManagerStatisticsView.fxml").openStream());
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
}