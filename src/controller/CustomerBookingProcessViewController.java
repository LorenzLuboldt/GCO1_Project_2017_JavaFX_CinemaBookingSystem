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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * Purpose: This controller manages the following sequence. A user can select a date in DatePicker and 
 * get a list of available screenings on that date displayed in a table view. She can then select one
 * of the screenings and click confirm to be taken to the seating map (new view and controller).
 *  
 * @author Michael
 *
 */

public class CustomerBookingProcessViewController implements Initializable{
	
	// DAO objects to query database
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	Screening screening = new Screening();
	
	
	// Declare instance variables
	@FXML private Label userLbl;
	@FXML private DatePicker datePicker;
	
	//Configure movie list
//	@FXML private ListView<Screening> screeningList;
	
	//Configure film table
	@FXML private TableView<Screening> tableView;
//	@FXML private TableColumn<Screening, Integer> screening_id_column;
	@FXML private TableColumn<Screening, String> time_id_column;
	@FXML private TableColumn<Screening, String> film_title_column;
	@FXML private TableColumn<Screening, String> ticket_status_column;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tableView.setPlaceholder(new Label("Choose a date first."));
	}
	
	
	// User wants to see list of films for given date
	public void showFilmsButtonPushed(ActionEvent event)	{				
		
		// Show updated films in table (same as above method ShowMovieSelection(ActionEvent))
//		screening_id_column.setCellValueFactory(new PropertyValueFactory<Screening, Integer>("screeningID"));
		time_id_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("timeString"));
		film_title_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("filmTitle"));
		ticket_status_column.setCellValueFactory(new PropertyValueFactory<Screening, String>("ticketStatus"));
		
		final ObservableList<Screening> screeningList = screeningDAO.searchScreeningsByDate(datePicker.getValue().toString());
		
		// TODO: Delete this later
		screening = screeningList.get(0);
		String time = screening.getTimeString();
		String title = screening.getFilmTitle();
		String status = screening.getTicketStatus();
		System.out.println(time + ", " + title + ", " +  status);
		
		tableView.setItems(screeningList);
		
//		tableView.getColumns().setAll(time_id_column, film_title_column, ticket_status_column);
		System.out.println("Never printed this part before");	
	}
	
	
	public void chooseSeatsButtonPushed(ActionEvent event)	{
		
	}
	
	// @Michael: method to enable manager to add film to database and see updated film list
//	public void addFilmButtonPushed()	{
//		
//		// filmDAO method adds film to DB. 
//		// Parameters are the user inputs from the text fields on the screen
//		filmDAO.addFilm(filmTitleTextField.getText(), filmDescriptionTextField.getText());
//		
//		// Empty text fields
//		filmTitleTextField.clear();
//		filmDescriptionTextField.clear();
//		
//		
//		// Show updated films in table (same as above method ShowMovieSelection(ActionEvent))
//		film_title_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmTitle"));
//		film_description_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmDescription"));
//
//		//load data from 
//		final ObservableList<Film> filmList = filmDAO.getAllFilms();
//		tableView.setItems(filmList);
//	}
	
	
	
	// Event Listener on Button.onAction
	@FXML
	public void toAccountSettings(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerEditInformationView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			
		}
	}

	// Event Listener on Button.onAction
	@FXML
	public void SignOut(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
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
	// Event Listener on Button.onAction
	@FXML
	public void toDashboard(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerRoot.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			
		}
	}
	
	//Brings User to booking history view
	public void goToBookingsHistory(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
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
	
	// Event Listener on Button.onAction
	@FXML
	public void toSelectSeatOptions(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/CustomerBookingGridView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			
		}
	}

}
