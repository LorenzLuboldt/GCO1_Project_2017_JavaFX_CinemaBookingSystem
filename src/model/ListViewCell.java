package model;

import java.io.IOException;

import controller.MovieListViewRowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<Film> {

	

@Override
public void updateItem(Film film, boolean empty) {

	super.updateItem(film, empty);

	if (film != null) {
		MovieListViewRowController controller = null;
		
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/view/MovieListViewRow.fxml"));

			try {
				fxmlLoader.load();

			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{

				controller = fxmlLoader.getController();
				controller.setFilm(film);
				controller.populateCells();

				System.out.println("finally block is always executed");}
		
			

		if (controller != null) {
			setGraphic(controller.getContainer());
		}

	} else {
		setGraphic(null);
	}
}
}
