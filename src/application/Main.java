package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
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
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
		
		// ________________________TESTING AREA FOR DAO METHODS:_____________________________________
//		FilmDAO filmDAO = new FilmDAOImpl();
			
//		customerDAO.addCustomer(5, "Jason", "Gekton", "jk@a.com");
//		
//		Customer Lorenz = customerDAO.getCustomer(1);
//		String PersonalInfo = Lorenz.getFirstName() + Lorenz.getLastName() + Lorenz.getCustEmail();
//		System.out.println(PersonalInfo);
//		
//		Film f = filmDAO.getFilm("The Dark Knight");
//		filmDAO.deleteFilm(f);
		
		ScreeningDAO screeningDAO = new ScreeningDAOImpl();
		
//		screeningDAO.addScreening("2018-01-15", 2018, 02, 10, 15, 2);
		
//		Screening a = new Screening();
//		a.setScreeningID(2);
		ObservableList<Screening> ol = screeningDAO.getUpcomingScreenings();
		Screening first = ol.get(0);
		Screening second = ol.get(1);
		Screening third = ol.get(2);
		String firstdate = first.getDateID();
		String seconddate = second.getDateID();
		String thirddate = third.getDateID();
		
		System.out.println(firstdate + ", " +  seconddate + ", " + thirddate);
		
		
		
		
		
		
		
		// ________________________TESTING AREA OVER____________________________________
	}
}
