package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
 * option chosen (customer login or employee log in). The class connects this
 * application to SQLite database which is used as a database. The project is
 * also based on the "Model-View-Controller" framework, and includes the
 * implementation of the DAO model, which separates database operations form the
 * business logic.
 * 
 * @author Lorenz Tutorials & resources:
 *         https://www.youtube.com/watch?v=NWcFTTbKbLs&index=31&list=PLS1QulWo1RIaUGP446_pWLgTZPiFizEMq,
 *         https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm,
 *         http://www.swtestacademy.com/database-operations-javafx/
 */
public class LoginController implements Initializable {

	// Instance of class LoginModel
	public LoginModel loginModel = new LoginModel();

	@FXML
	private Label isConnected;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;

	/**
	 * Purpose: Initialises flash message for user communicating whether
	 * database is connected or not.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (loginModel.isDBconnected()) {
			isConnected.setText("Welcome to Nero Cinema");
		} else {
			isConnected.setText("Not connected");
		}
	}

	/**
	 * Purpose: Controls the login process for customer by testing whether
	 * user-name and password are correct (compares user input to stored
	 * customer data in SQLite database). If yes it initiates the
	 * CustomerRoot.fxml.
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
	 * Purpose: Controls the login process for employee by testing whether
	 * user-name and password are correct (compares user input to stored
	 * employee data in SQLite database). If yes it initiates the
	 * ManagerRoot.fxml.
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
