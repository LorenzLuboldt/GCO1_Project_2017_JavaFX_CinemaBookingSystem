package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;

/**
 * Purpose: This class enables to add screenings including all important
 * information in the SQLite database, corresponding to available films only
 * 
 * @author Lorenz
 *
 */
public class ManagerAddScreeningViewController implements Initializable {

	@FXML
	private Label userLbl2;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField filmTrailerLinkTextField;
	@FXML
	private ComboBox<String> screeningTimeComboBox;
	@FXML
	private ComboBox<String> currentFilmsAvailableComboBox;
	@FXML
	Label successNotification;

	@FXML
	Label errorNotification;

	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	FilmDAO filmDAO = new FilmDAOImpl();

	/**
	 * Purpose: Method populates combobox elements and sets user name to the
	 * logged in user
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		GetManager(LoginController.username);
		ObservableList<String> screeningTimeList = FXCollections.observableArrayList("2:00 PM", "3:00 PM", "4:00 PM",
				"5:00 PM", "6:00 PM", "7:00 PM", "8:00 PM", "9:00 PM", "10:00 PM", "11:00 PM");
		screeningTimeComboBox.setItems(screeningTimeList);

		ObservableList<Film> allFilms = filmDAO.getAllFilms();
		ObservableList<String> currentFilmTitle = FXCollections.observableArrayList();

		for (int x = 0; x < allFilms.size(); ++x) {

			String f = allFilms.get(x).getFilmTitle();
			currentFilmTitle.add(f);
		}
		currentFilmsAvailableComboBox.setItems(currentFilmTitle);
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
	 * Purpose: Method to enable manager to add screening to database when
	 * button clicked
	 * 
	 * @param event
	 */
	public void addScreeningButtonPushed(ActionEvent event) {

		// ScreeningDAO method adds Screening Time to DB.
		// Parameters are the user inputs from the text fields on the screen
		int day = datePicker.getValue().getDayOfMonth();
		int month = datePicker.getValue().getMonthValue();
		int year = datePicker.getValue().getYear();

		try {
			screeningDAO.addScreening(datePicker.getValue().toString(), year, month, day,
					screeningTimeComboBox.getValue(), currentFilmsAvailableComboBox.getValue());
			successNotification.setText("Successfully Added Screening Slot.");
		} catch (Exception e) {
			errorNotification.setText("Overlap With Other Screening");
			e.printStackTrace();
		}
	}
}
