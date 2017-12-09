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
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;
import model.Screening;

public class ManagerRootController implements Initializable {
	@FXML
	private Label userLbl2;
	
	@FXML private ListView<Screening> upcomingScreeningsList; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void GetManager(String user) {
		// TODO Auto-generated method stub
		userLbl2.setText(user);
	}
	
	
	// INCOMPLETE METHOD BELOW
	// Populates the table in this view 
//	public void FillUpcomingScreeningsListView(ActionEvent event) {
//		//Set up the columns in the table
//		film_title_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmTitle"));
//		film_description_column.setCellValueFactory(new PropertyValueFactory<Film, String>("filmDescription"));
//
//		//load data from 
//		final ObservableList<Film> filmList = filmDAO.getAllFilms();
//		tableView.setItems(filmList);
//	}
	
	//Goes to Movie Overview Page
	public void GoToMovieSelection(ActionEvent event) {
		try {	
			System.out.println("1 Was successfull");

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
	
	public void GoToBookings(ActionEvent event) {
		try {	
	((Node) event.getSource()).getScene().getWindow().hide();
	Stage primaryStage = new Stage();
	FXMLLoader loader = new FXMLLoader();
	Pane root = loader.load(getClass().getResource("/view/ManagerBookingsView.fxml").openStream());
	Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.setResizable(false);
	primaryStage.show();
		} catch (Exception e) {
			
		}

	}

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
}

