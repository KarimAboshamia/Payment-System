package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainPageUserController {
	@FXML
	private ImageView notificationsButton;
	User user;

	
	public MainPageUserController() {
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
		System.out.println(user.getUsername());
	}

	@FXML
	public void imageHandler() {
	
	}
	
}
