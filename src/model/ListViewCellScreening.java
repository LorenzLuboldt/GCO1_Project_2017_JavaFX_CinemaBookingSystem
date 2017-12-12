package model;

import java.io.IOException;

import controller.MovieListViewRowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ListViewCellScreening extends ListCell<Film> {

	

@Override
public void updateItem(Film film, boolean empty) {
	System.out.println(10);

	super.updateItem(film, empty);
	System.out.println(11);

	if (film != null) {
		MovieListViewRowController controller = null;
		
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/view/MovieListViewRow.fxml"));
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
