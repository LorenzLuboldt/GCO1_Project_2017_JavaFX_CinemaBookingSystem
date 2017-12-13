package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.ManagerRootController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.LoginModel;

/**
 * Purpose: LoginController class handles business logic for the login process
 * as it initiates either customer or manager root views, depending on the
 * option chosen (customer login or employee log in)
 * 
 * @author Lorenz Tutorials & resources:
 *         https://www.youtube.com/watch?v=NWcFTTbKbLs&index=31&list=PLS1QulWo1RIaUGP446_pWLgTZPiFizEMq
 */
public class LoginController implements Initializable {

	// Instance of class LoginModel
	public LoginModel loginModel = new LoginModel();

	@FXML
	private Label isConnected;

	@FXML

	private TextField txtUsername; // Name of text field is equal to the name
									// assigned in the ID section of the Scene
									// Builder

	@FXML
	private PasswordField txtPassword; // Name of text field is equal to the name
									// assigned in the ID section of the Scene
									// Builder

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (loginModel.isDBconnected()) {
			isConnected.setText("Welcome to Nero Cinema");
		} else {
			isConnected.setText("Not connected");
		}
	}

	
	
	
	
// tests whether user-name and password are correct and opens up start
// application view
	/**
	 * Purpose: Controls the login process for customer by testing whether
	 * user-name and password are correct. If yes it initiates the root
	 * borderPane and sets the initial view in its center.
	 * 
	 * @param event
	 */
	public void LoginCheckCustomer(ActionEvent event) {
		try {
			if (loginModel.isLoginCustomer(txtUsername.getText(), txtPassword.getText())) {
				isConnected.setText("Valid Username and Password");

				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/view/CustomerRoot.fxml").openStream());
				CustomerRootController customerRootController = (CustomerRootController) loader.getController();
				customerRootController.GetCustomer(txtUsername.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();

			} else {
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
	 * Purpose: Controls the login process for managers by testing whether
	 * user-name and password are correct. If yes it initiates the root
	 * borderPane and sets the initial view in its center.
	 * 
	 * @param event
	 */
	public void LoginCheckManager(ActionEvent event) {
		try {
			if (loginModel.isLoginManager(txtUsername.getText(), txtPassword.getText())) {
				isConnected.setText("Valid Username and Password");

				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/view/ManagerRoot.fxml").openStream());
				ManagerRootController managerRootController = (ManagerRootController) loader.getController();
				managerRootController.GetManager(txtUsername.getText());
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();

			} else {
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
}
