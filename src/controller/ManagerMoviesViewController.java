package controller;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Film;

public class ManagerMoviesViewController implements Initializable {
	@FXML
	private Label userLbl;
	
	//Configure movie table
	@FXML private TableView<Film> tableView;
	@FXML private TableColumn<Film, String> film_title_column;
	@FXML private TableColumn<Film, String> film_description_column;

	public void GetCustomer(String user) {
		// TODO Auto-generated method stub
		userLbl.setText(user);
	}
	
	public void ShowMovieSelection(ActionEvent event) {
		
		//Set up the columns in the table
		film_title_column.setCellValueFactory(new PropertyValueFactory<Film, String>("film_title"));
		film_description_column.setCellValueFactory(new PropertyValueFactory<Film, String>("film_description"));

		//load dummy data
		tableView.setItems(getAllFilms());
		
	}
	
	public ObservableList<Film> getAllFilms() {
		ObservableList<Film> films = FXCollections.observableArrayList();
		return films;
		
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
		primaryStage.show();
	} catch (Exception e) {
		
	}
	}
	
	@Override
public void initialize(URL location, ResourceBundle resources) {


	}
}
