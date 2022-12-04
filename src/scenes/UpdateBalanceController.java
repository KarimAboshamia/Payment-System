package scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	Label msg;
	
	ChangeScenes scener = new ChangeScenes();
	@FXML
	public void initialize() {
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "MainPageUser.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}

	     });
	}
	
	

	public UpdateBalanceController() {
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
	}
	
	@FXML
	public void addBalance(ActionEvent event) {
		String returnMSG = user.setBalance(Card.getText(), Integer.parseInt(PIN.getText()), Integer.parseInt(Amount.getText()));
		msg.setText(returnMSG);
		
		
		
	}
	
}
