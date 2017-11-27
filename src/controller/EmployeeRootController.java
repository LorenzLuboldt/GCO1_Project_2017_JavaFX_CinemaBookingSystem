package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import javafx.scene.control.Label;

public class EmployeeRootController implements Initializable {

	@FXML
	private Label userLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void initialize(String User) {

		userLabel.setText(User);
	}
	
	
	
	
}
