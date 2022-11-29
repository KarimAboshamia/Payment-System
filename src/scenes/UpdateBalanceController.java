package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import application.User;
import communication.DataCommunicator;
import javafx.event.ActionEvent;

public class UpdateBalanceController {

	User user;
	
	@FXML
	TextField Amount;
	@FXML
	TextField Card;
	@FXML
	PasswordField PIN;
	@FXML
	ImageView backImage;
	
	@FXML
	public void initialize() {
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("MainPageUser.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	     });
	}
	
	

	public UpdateBalanceController() {
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
	}
	
	@FXML
	public void addBalance(ActionEvent event) {
		
		System.out.println("Setting Balance");
		user.setBalance(Card.getText(), Integer.parseInt(PIN.getText()), Integer.parseInt(Amount.getText()));
		
	}
	
}
