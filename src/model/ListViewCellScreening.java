package model;

import java.io.IOException;
import java.sql.SQLException;

import controller.ScreeningListViewRowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;


/**
 * Purpose: Helper class containing the updateItemMethod that loads the corresponding FXML view
 * to show all upcoming screenings to the manager.
 * 
 * @author: Michael Aring
 * 
 */

public class ListViewCellScreening extends ListCell<Screening> {

/**
 * UpdateItem Method loads the screening view and calls the populateCells method that fills each row
 * in the list view with information about one upcoming screening.
 * 
 */
@Override
public void updateItem(Screening screening, boolean empty) {

	super.updateItem(screening, empty);

	if (screening != null) {
		ScreeningListViewRowController screeningController = null;
		
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/view/ScreeningListViewRow.fxml"));

			try {
				fxmlLoader.load();

			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{

				screeningController = fxmlLoader.getController();

				screeningController.setScreening(screening);

				try {
					screeningController.populateCells();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			

		if (screeningController != null) {
			setGraphic(screeningController.getContainer());
		}

	} else {
		setGraphic(null);
	}
}
}