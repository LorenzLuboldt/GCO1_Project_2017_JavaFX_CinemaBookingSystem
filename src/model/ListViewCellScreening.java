package model;

import java.io.IOException;
import java.sql.SQLException;

import controller.ScreeningListViewRowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ListViewCellScreening extends ListCell<Screening> {

	

@Override
public void updateItem(Screening screening, boolean empty) {
	System.out.println(10);

	super.updateItem(screening, empty);
	System.out.println(11);

	if (screening != null) {
		ScreeningListViewRowController screeningController = null;
		
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/view/ScreeningListViewRow.fxml"));
		System.out.println(12);

			try {
				fxmlLoader.load();
				System.out.println(13);

			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				System.out.println(14);

				screeningController = fxmlLoader.getController();
				System.out.println(15);

				screeningController.setScreening(screening);
				System.out.println(16);

				try {
					screeningController.populateCells();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(17);

				System.out.println("finally block is always executed");}
		
			
			System.out.println(33);

		if (screeningController != null) {
			setGraphic(screeningController.getContainer());
		}
		System.out.println(34);

	} else {
		System.out.println(35);
		setGraphic(null);
	}
}
}
