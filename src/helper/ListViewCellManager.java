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
	System.out.println(10);

	super.updateItem(film, empty);
	System.out.println(11);

	if (film != null) {
		MovieListViewRowControllerCustomer controller = null;
		
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/view/MovieListViewRowManager.fxml"));
		System.out.println(12);

			try {
				fxmlLoader.load();
				System.out.println(13);

			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				System.out.println(14);

				controller = fxmlLoader.getController();
				controller.setFilm(film);
				controller.populateCells();
				System.out.println(15);

				System.out.println("finally block is always executed");}
		
			
			System.out.println(16);

		if (controller != null) {
			setGraphic(controller.getContainer());
		}
		System.out.println(15);

	} else {
		System.out.println(20);
		setGraphic(null);
	}
}
}
