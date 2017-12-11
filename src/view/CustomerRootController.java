package view;

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
import model.Film;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.ListViewCell;

public class CustomerRootController implements Initializable {
	@FXML
	private Label userLbl;
	@FXML
	private ListView<Film> allFilmsList;
	
	private List<Film> filmList = new ArrayList<>();
	private ObservableList<Film> observableList = FXCollections.observableArrayList();
	
	//Creates a Film object
		FilmDAO filmDAO = new FilmDAOImpl();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setListView();
		System.out.println(1);
	}
	
	

	/**
	 * Purpose: Setsup Movie ListView displaying all current Movies with the custom ListRows
	 * Source: https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
	 * @author Lorenz
	 */
	private void setListView() {
		
	//Retrieve all film entries from the database
	try {
		filmList = filmDAO.getAllFilms();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
		
	//Fill the Observable List with items pulled from the database
	
	observableList.setAll(filmList);
	
	//Fill ListView with content
	
	allFilmsList.setItems(observableList);
	
	//Allow for custom display of the ListView Items 
	System.out.println(8);

	allFilmsList.setCellFactory(ListView -> new ListViewCell());
	System.out.println(9);

		
	}
	
	
	public void GetCustomer(String user) {
		userLbl.setText(user);
	}
	
	public void toAccountSettings(ActionEvent event) {
		try {	
	((Node) event.getSource()).getScene().getWindow().hide();
	Stage primaryStage = new Stage();
	FXMLLoader loader = new FXMLLoader();
	Pane root = loader.load(getClass().getResource("/view/CustomerEditInformationView.fxml").openStream());
	Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
		} catch (Exception e) {
			
		}

	}
	
	public void BookTickets(ActionEvent event) {
		try {	
	((Node) event.getSource()).getScene().getWindow().hide();
	Stage primaryStage = new Stage();
	FXMLLoader loader = new FXMLLoader();
	Pane root = loader.load(getClass().getResource("/view/CustomerBookingProcessView.fxml").openStream());
	Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
	primaryStage.setScene(scene);
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
	
	public void goToHarryPotterButton(ActionEvent event)	{
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

