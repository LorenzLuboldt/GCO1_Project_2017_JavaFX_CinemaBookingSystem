package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController implements Initializable{
	public LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label isConnected;
	
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
	
	public void LoginCheck(ActionEvent event) {
		try {
			if(loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
				isConnected.setText("Valid Username and Password");
			}
			else{
				isConnected.setText("Invalid Username and/or Password");
			}
		} catch (SQLException e) {
			isConnected.setText("Invalid Username and/or Password");
			e.printStackTrace();
		}
	}

}
