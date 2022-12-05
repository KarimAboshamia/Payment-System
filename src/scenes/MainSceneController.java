package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.event.ActionEvent;

public class MainSceneController {
	ChangeScenes scener = new ChangeScenes();
	@FXML
	public void handlerLogin(ActionEvent event) {
		try {
			scener.changeScene(event, "LoginScene.fxml");
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML 
	public void handlerSignUp(ActionEvent event) {
		try {
			scener.changeScene(event, "SignUpScene.fxml");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
