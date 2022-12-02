package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import application.AppUser;
import auth.AuthenticationManager;
import communication.DataCommunicator;
import javafx.event.ActionEvent;

public class LoginSceneController {

	// Event Listener on Button.onAction
	@FXML
	private TextField email;
	@FXML
	private PasswordField pass;
	@FXML
	private Label message;
	
	private AuthenticationManager authObj = new AuthenticationManager();
	ChangeScenes scener = new ChangeScenes();

	@FXML
	public void login(ActionEvent event) {
		AppUser user = authObj.login(email.getText(), pass.getText()); 
		if( user == null) {
			
			message.setText("Wrong Email or Password");
			
		}else {
			//Redirect to next page
			try {
				if(user.getPermission().equals("0")) {
				
					DataCommunicator communicator = DataCommunicator.getCommunicator();
					communicator.setUser(user);
					
					scener.changeScene(event, "MainPageUser.fxml");
				} else {
					DataCommunicator communicator = DataCommunicator.getCommunicator();
					communicator.setUser(user);
					scener.changeScene(event, "MainPageAdmin.fxml");
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
