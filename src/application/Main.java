package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerDAO;
import model.CustomerDAOImpl;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		// ________________________TESTING AREA FOR DAO METHODS:_____________________________________
		CustomerDAO customerDAO = new CustomerDAOImpl();
			
		customerDAO.addCustomer(5, "Jason", "Gekton", "jk@a.com");
		
		Customer Lorenz = customerDAO.getCustomer(1);
		String PersonalInfo = Lorenz.getFirstName() + Lorenz.getLastName() + Lorenz.getCustEmail();
		System.out.println(PersonalInfo);
		
		customerDAO.deleteCustomer(4);
		
		// ________________________TESTING AREA OVER____________________________________
	}
}
