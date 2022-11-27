package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainSceneController {
	
	@FXML
	private AnchorPane rootPane;

	// Event Listener on Button.onAction
	@FXML
	public void handlerLogin(ActionEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		    rootPane.getChildren().setAll(pane);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
