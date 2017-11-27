package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class CustomerRootController implements Initializable {
	@FXML
	private Label userLbl;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public void GetCustomer(String user) {
		// TODO Auto-generated method stub
		userLbl.setText(user);
	}

}
