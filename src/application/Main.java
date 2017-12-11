package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Booking;
import model.BookingDAO;
import model.BookingDAOImpl;
import model.Customer;
import model.CustomerDAO;
import model.CustomerDAOImpl;
import model.FilmDAO;
import model.FilmDAOImpl;
import model.Screening;
import model.ScreeningDAO;
import model.ScreeningDAOImpl;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


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
		
//		System.out.println("_____________START OF NEW TEST: ___________________");
		
		
		
//		
//		BookingDAO bookingDAO = new BookingDAOImpl();
//		bookingDAO.addBooking(1, "A01", 1);
//		bookingDAO.addBooking(2, "A02", 8);
//		bookingDAO.addBooking(1, "A02", 3);
//		bookingDAO.addBooking(3, 3, 3);
//		ObservableList<Booking> ol = bookingDAO.getAllBookings();
//		Booking spec = bookingDAO.getBooking(2);
//		bookingDAO.deleteBooking(3);
//		boolean First = bookingDAO.checkSeatAvailability("A01", 1);
//		boolean Second = bookingDAO.checkSeatAvailability("A01", 2);
//		System.out.println("First: " + First + ", Second: " + Second);
//		bookingDAO.getCustomerBookings(1);
//		
		
		
//		FilmDAO filmDAO = new FilmDAOImpl();
//		filmDAO.addFilm("The Devil Wears Prada", "New York fashion movie", "Comedy / girly / romance / idk", "Actor 1 and actress 2", "name of the filmDirector", "link to filmTrailer");
		
			
//		customerDAO.addCustomer(5, "Jason", "Gekton", "jk@a.com");
//		
		
//		CustomerDAO customerDAO  = new CustomerDAOImpl();
//		
//		customerDAO.addCustomer("custEmail", "custFirstName", "custLastName");
//		Customer Lorenz = customerDAO.getCustomer(6);
//		String PersonalInfo = Lorenz.getFirstName() + Lorenz.getLastName() + Lorenz.getCustEmail();
//		System.out.println(PersonalInfo);
		
//		
//		Film f = filmDAO.getFilm("The Dark Knight");
//		filmDAO.deleteFilm(f);
//		
//		ScreeningDAO screeningDAO = new ScreeningDAOImpl();
//		
//		screeningDAO.addScreening("2018-01-15", 2018, 02, 10, 15, 2);
//		
//		Screening a = new Screening();
//		a.setScreeningID(2);
//		ObservableList<Screening> ol = screeningDAO.getUpcomingScreenings();
//		
//		Booking first = ol.get(0);
//		Booking second = ol.get(1);
//		Booking third = ol.get(2);
//		int firstdate = first.getCustID();
//		int seconddate = second.getSeatID();
//		int thirddate = third.getScreeningID();
//		
//		System.out.println(firstdate + ", " +  seconddate + ", " + thirddate);	
		
		
		
		
		
		// ________________________TESTING AREA OVER____________________________________
	}
}
