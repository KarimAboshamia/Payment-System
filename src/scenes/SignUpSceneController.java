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

import java.io.IOException;

import auth.AuthenticationManager;
import javafx.event.ActionEvent;

public class SignUpSceneController {
	
	private AuthenticationManager authObj = new AuthenticationManager();

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
				root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

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
