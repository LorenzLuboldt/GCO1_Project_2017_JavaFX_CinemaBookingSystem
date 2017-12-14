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
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.SQLException;
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
 * References:
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
	
	// @Lorenz: Instance variables to choose files
	@FXML private Label successNotification;
	@FXML private Label errorNotification;
	@FXML private Button chooseFile;
	@FXML private Button addFilmButtonPushed;
	
	// @Lorenz: Instance variables to create new Film objects
		@FXML private TextField filmTitleTextField;
		@FXML private TextArea filmDescriptionTextArea;
		@FXML private TextField filmActorsTextField;
		@FXML private TextField filmDirectorTextField;
		@FXML private ComboBox<String> filmGenreComboBox;
		@FXML private ListView<File> fileList;		
	
	
	@FXML
	private Label userLbl2;

	//File Chooser for uploading image to application & database
	public void chooseFile(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		
//		ProtectionDomain pd = Main.class.getProtectionDomain();
//		CodeSource cs = pd.getCodeSource();
//		URL location = cs.getLocation();
		
		
//		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//		fileChooser.setInitialDirectory(new File(location));
		fileChooser.setInitialDirectory(new File("."));

		fileChooser.getExtensionFilters().addAll();
		selectedFile = fileChooser.showOpenDialog(null);
		
		if(selectedFile != null) {
			fileList.getItems().add(selectedFile);
			checkIfFileChosen = true;
		}
		else {
			System.out.println("File is not valid");
		}	
	}
	
	
	// @Lorenz: method to enable manager to add film to database
	public void addFilmButtonPushed(ActionEvent event)	{
		
		// Upload Film poster and copy to destination
		String imgPath = uploadFile();
		
		// Add Film object to DB
		
		try {
		filmDAO.addFilm(filmTitleTextField.getText(), filmDescriptionTextArea.getText(), imgPath, filmGenreComboBox.getValue().toString(),filmActorsTextField.getText(), filmDirectorTextField.getText());
		successNotification.setText("Successfully Added Film.");

		} catch (Exception e) {
			
			errorNotification.setText("Please Fill All Input Fields");
			e.printStackTrace();
	}
	}

	
	public void toDashboard(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
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
	
	// Event Listener on Button.onAction
	@FXML
	public void SignOut(ActionEvent event) {
		try {	
			((Node)event.getSource()).getScene().getWindow().hide();
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
	
	public void backToMovieOverview(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
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
	
	public void linkToAddScreeningTimeView(ActionEvent event) {
	try {	
		((Node)event.getSource()).getScene().getWindow().hide();
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
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> genreList = FXCollections.observableArrayList("Action", "Adventure", "Animation","Biography", "Comedy","Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", "Musical", "Mistery", "Romance", "Sci-Fi", "Sport", "Thriller", "War", "Western");
		filmGenreComboBox.setItems(genreList);
	}
	
	
	// _________________________SUPPORTING METHODS____________________________________
	
	// upload file method
	public String uploadFile()	{
		String imgPath = "UPLOAD FAILED"; // Initial value, will be overwritten if upload succeeds
		
		if(checkIfFileChosen = true)	{
			// Obtain source path
			String sourcePath = selectedFile.getPath();
			System.out.println(sourcePath);
			
			File source = new File(sourcePath);
			
			// Create destination path for the copied file			
			long imgID = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L; // Creates random 10-digit number
			imgPath = imgID + ".jpg";
			
			File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			File dest = new File(jarFile.getParentFile().getParent(), "images/" + imgID + ".jpg");
			
		// ________OBSOLETE: _____________________________________________________________________
			
//			File jarFile = (new File(".")).getAbsolutePath();
			
//			jarFile.getAbsolutePath();
//			File parentDirectory = jarFile.getParentFile();
//			File imagesFolder = new File(parentDirectory, "images");
//			File dest = new File(imagesFolder, imgPath);
					
//			File dest = new File(System.getProperty("user.dir") + "/resources/films/" + imgPath);
			
			// ________________________________________________________________________________________

			
			// Copy the file from source path to destination path
			try {
				copyFileUsingFileChannels(source, dest);
			} catch (IOException e) {
				System.out.println("An IO Exeception was detected when copying files.");
				e.printStackTrace();
			}
			System.out.println("File Copied");	
						
			// TODO: Display 'Upload successful' message to user
		}
		else {
			System.out.println("### Display to user: PLEASE SELECT PNG FILE TO UPLOAD. ###");
		}
		return imgPath;
	}
	
	// copy file method
	public void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} catch (IOException e) {
			System.err.print(e);
			
		}
		finally{
			inputChannel.close();
			outputChannel.close();
		}
	}

}
	
