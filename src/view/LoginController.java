package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.LoginModel;

// Class implements imported FXML Initializable interface
public class LoginController implements Initializable{
	public LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label isConnected; // label type is imported from Java FX scene control (import), label is passed as fxid to SceneBuilder
	
	@FXML
	
	private TextField txtUsername; //Name of text field is equal to the name assigned in the ID section of the Scene Builder
	
	@FXML
	private TextField txtPassword; //Name of text field is equal to the name assigned in the ID section of the Scene Builder
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(loginModel.isDBconnected()){
			isConnected.setText("Connected");
		}
		else{
			isConnected.setText("Not connected");
		}
	}
	
	// tests whether user-name and password are correct and opens up start application view
		/**
		 * Purpose: Controls the login process for employees by testing whether
		 * user-name and password are correct. If yes it initiates the root
		 * borderPane and sets the initial view in its center.
		 * 
		 * @param event
		 */
	public void LoginCheck(ActionEvent event) {
		try {
			if(loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
				isConnected.setText("Valid Username and Password");
				
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/view/CustomerRoot.fxml").openStream());
				CustomerRootController customerRootController = (CustomerRootController)loader.getController();
				customerRootController.GetCustomer(txtUsername.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
			}
			else{
				isConnected.setText("Invalid Username and/or Password");
			}
		} catch (SQLException e) {
			isConnected.setText("Invalid Username and/or Password");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Purpose: Controls the login process for employees by testing whether
	 * user-name and password are correct. If yes it initiates the root
	 * borderPane and sets the initial view in its center.
	 * 
	 * @param event
	 */

}
