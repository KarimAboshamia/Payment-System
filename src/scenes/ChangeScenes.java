package scenes;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChangeScenes {
	
	public void changeScene(ActionEvent event, String sceneName) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(sceneName));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void changeSceneWithMouse(MouseEvent event, String sceneName) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(sceneName));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}

}
