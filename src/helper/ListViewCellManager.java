package helper;

import java.io.IOException;

import controller.MovieListViewRowControllerCustomer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import model.Film;

/**
 * Purpose: This class serves as connector between the customised & populated
 * list cell and the root controller where the actual list view is placed It
 * loads the customised cell into the list view scene on the manager side
 * 
 * @author Lorenz
 *
 */
public class ListViewCellManager extends ListCell<Film> {

	

@Override
public void updateItem(Film film, boolean empty) {

	super.updateItem(film, empty);

	if (film != null) {
		MovieListViewRowControllerCustomer controller = null;
		
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/view/MovieListViewRowManager.fxml"));

			try {
				fxmlLoader.load();

			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{

				controller = fxmlLoader.getController();
				controller.setFilm(film);
				controller.populateCells();				
			}
	

		if (controller != null) {
			setGraphic(controller.getContainer());
		}

	} else {
		setGraphic(null);
	}
}
}
