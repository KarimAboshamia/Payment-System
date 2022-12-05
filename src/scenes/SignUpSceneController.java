package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import managers.AuthenticationManager;

import java.io.IOException;

import javafx.event.ActionEvent;

public class SignUpSceneController {
	
	private AuthenticationManager authObj = new AuthenticationManager();
	ChangeScenes scener = new ChangeScenes();

	@FXML
	private TextField email;
	@FXML 
	private TextField user3;
	@FXML
	private PasswordField pass;
	@FXML
	private Label message;
	@FXML
	public void signupHandler(ActionEvent event) {
		int signupResult = authObj.signUp(email.getText(), user3.getText(), pass.getText(), 0);
		if(signupResult == 1) {
			//Sign up message
			//message.setText("Signed Up Successfully, redirecting...");
			
			Parent root;
			try {
				scener.changeScene(event, "MainScene.fxml");
			

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} else if(signupResult == 0){
			message.setText("Username already used...");
		} else {
			message.setText("Email already used...");
		}
		
	}
}
