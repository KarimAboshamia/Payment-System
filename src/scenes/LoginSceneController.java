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


	@FXML
	public void login(ActionEvent event) {
		AppUser user = authObj.login(email.getText(), pass.getText()); 
		//System.out.println(user.getEmail());
		if( user == null) {
			
			message.setText("Wrong Email or Password");
			
		}else {
			//Redirect to next page
			try {
			
				
				DataCommunicator communicator = DataCommunicator.getCommunicator();
				communicator.setUser(user);
				
				System.out.println("Here");

				Parent root = FXMLLoader.load(getClass().getResource("MainPageUser.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.close();
				
				System.out.println(communicator.getUser().getUsername());
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
