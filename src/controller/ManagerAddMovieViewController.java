package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.FilmDAO;
import model.FilmDAOImpl;

/**
 * Purpose: This class enables to add a movie including all important
 * information in the SQLite database. File Chooser for uploading pictures into
 * resource folder is also included Tutorials & resources:
 * https://examples.javacodegeeks.com/core-java/io/file/4-ways-to-copy-file-in-java/
 * https://stackoverflow.com/questions/40317459/how-to-open-a-file-in-the-same-directory-as-the-jar-file-of-the-application
 * 
 * @author Michael
 *
 */
public class ManagerAddMovieViewController implements Initializable {

	FilmDAO filmDAO = new FilmDAOImpl();
	File selectedFile;
	boolean checkIfFileChosen = false;

	@FXML
	private Label successNotification;
	@FXML
	private Label errorNotification;
	@FXML
	private Button chooseFile;
	@FXML
	private Button addFilmButtonPushed;
	@FXML
	private TextField filmTitleTextField;
	@FXML
	private TextArea filmDescriptionTextArea;
	@FXML
	private TextField filmActorsTextField;
	@FXML
	private TextField filmDirectorTextField;
	@FXML
	private ComboBox<String> filmGenreComboBox;
	@FXML
	private ListView<File> fileList;
	@FXML
	private Label userLbl2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		GetManager(LoginController.username);
		ObservableList<String> genreList = FXCollections.observableArrayList("Action", "Adventure", "Animation",
				"Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History",
				"Horror", "Music", "Musical", "Mistery", "Romance", "Sci-Fi", "Sport", "Thriller", "War", "Western");
		filmGenreComboBox.setItems(genreList);
	}

	/**
	 * Purpose: Displays the name of the Manager currently logged in.
	 * 
	 * @param user
	 */
	public void GetManager(String user) {
		userLbl2.setText("User " + user);
	}

	/**
	 * Purpose: File Chooser for uploading image to application & database
	 * 
	 * @param event
	 */
	public void chooseFile(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setInitialDirectory(new File("."));

		fileChooser.getExtensionFilters().addAll();
		selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			fileList.getItems().add(selectedFile);
			checkIfFileChosen = true;
		} else {
			System.out.println("File is not valid");
		}
	}

	/**
	 * Purpose: Method to enable manager to add film to database when button
	 * clicked
	 * 
	 * @param event
	 */
	public void addFilmButtonPushed(ActionEvent event) {

		// Upload Film poster and copy to destination
		String imgPath = uploadFile();

		// Add Film object to DB
		try {
			filmDAO.addFilm(filmTitleTextField.getText(), filmDescriptionTextArea.getText(), imgPath,
					filmGenreComboBox.getValue().toString(), filmActorsTextField.getText(),
					filmDirectorTextField.getText());
			successNotification.setText("Successfully Added Film.");
		} catch (Exception e) {
			errorNotification.setText("Please Fill All Input Fields");
			e.printStackTrace();
		}
	}

	/**
	 * Purpose: When button clicked, manager gets back to landing page (manager
	 * dashboard) clicked.
	 * 
	 * @param event
	 */
	public void toDashboard(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ManagerRoot.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {

		}
	}

	/**
	 * Purpose: When button clicked, manager gets back to LoginView (Logout)
	 * clicked.
	 * 
	 * @param event
	 */
	public void SignOut(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
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

	/**
	 * Purpose: When button clicked, manager gets back to View/Edit Movie
	 * Overview clicked.
	 * 
	 * @param event
	 */
	public void backToMovieOverview(ActionEvent event) {
		try {
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

	/**
	 * Purpose: When button clicked, manager gets next to view where to add
	 * screening time clicked.
	 * 
	 * @param event
	 */
	public void linkToAddScreeningTimeView(ActionEvent event) {
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/ManagerAddScreeningView.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
		}
	}

	/**
	 * Purpose: Supporting methods for file upload method (get called in public
	 * void chooseFile) to handle upload into resource folder
	 * 
	 * @return
	 */
	public String uploadFile() {

		// Initial value, will be overwritten if upload succeeds
		String imgPath = "UPLOAD FAILED";
		if (checkIfFileChosen = true) {
			// Obtain source path
			String sourcePath = selectedFile.getPath();
			System.out.println(sourcePath);

			File source = new File(sourcePath);

			// Create destination path for the copied file
			// Creates random 10-digit number
			long imgID = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L; 
			imgPath = imgID + ".jpg";

			File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			File dest = new File(jarFile.getParentFile().getParent(), "images/" + imgID + ".jpg");

			try {
				copyFileUsingFileChannels(source, dest);
			} catch (IOException e) {
				System.out.println("An IO Exeception was detected when copying files.");
				e.printStackTrace();
			}
			System.out.println("File Copied");

		} else {
			System.out.println("### Display to user: PLEASE SELECT PNG FILE TO UPLOAD. ###");
		}
		return imgPath;
	}

	/**
	 * Purpose: Supporting methods for file upload method (get called in public
	 * void chooseFile) to handle upload into resource folder
	 * 
	 * @return
	 */
	@SuppressWarnings("resource")
	public void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} catch (IOException e) {
			System.err.print(e);

		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}

}
