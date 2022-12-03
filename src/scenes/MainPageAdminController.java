package scenes;

import javafx.fxml.FXML;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainPageAdminController {

	
	ChangeScenes scener;
	public MainPageAdminController() {
		scener = new ChangeScenes();
	}
	
	@FXML
	public void listRefunds(ActionEvent event) throws IOException {
		scener.changeScene(event, "AdminRefunds.fxml");
	}

	
	@FXML
	public void addDiscount(ActionEvent event) throws IOException {
		scener.changeScene(event, "AdminDiscount.fxml");
	}
}
