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



public class ManagerRootController implements Initializable {
	@FXML
	private Label userLbl2;
	
	@FXML private ListView<Screening> allScreeningList; 
	
	private List<Screening> screeningList = new ArrayList<>();
	private ObservableList<Screening> observableList = FXCollections.observableArrayList();
	
	//Creates a Screening object
	ScreeningDAO screeningDAO = new ScreeningDAOImpl();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setListView();
	}
	
	/**
	 * Purpose: Setsup Movie ListView displaying all current Movies with the custom ListRows
	 * Source: https://stackoverflow.com/questions/19588029/customize-listview-in-javafx-with-fxml
	 * @author Lorenz
	 */
	private void setListView() {
		
	//Retrieve all film entries from the database
	try {
		screeningList = screeningDAO.getUpcomingScreenings();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
		
	//Fill the Observable List with items pulled from the database
	
	observableList.setAll(screeningList);
	
	//Fill ListView with content
	
	allScreeningList.setItems(observableList);
	
	//Allow for custom display of the ListView Items 

	allScreeningList.setCellFactory(ListView -> new ListViewCellScreening());

		
	}
	
	public void GetManager(String user) {
		// TODO Auto-generated method stub
		userLbl2.setText("Welcome, "+ user + "!");
	}
	
	
	//Goes to Movie Overview Page
	public void GoToMovieSelection(ActionEvent event) {
		try {	
			System.out.println("1 Was successful");

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