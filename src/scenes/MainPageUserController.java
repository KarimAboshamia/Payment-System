package scenes;

import java.io.IOException;

import application.User;
import communication.DataCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceManager;

public class MainPageUserController {
	@FXML
	private ImageView notificationsButton;
	
	@FXML
	private Label userBalance;
	

	User user;
	
	public MainPageUserController() {
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
	}
	
	@FXML 
	public void initialize() {
		System.out.println("Running");
		userBalance.setText(Integer.toString(user.getBalance()));
	}

	@FXML
	public void imageHandler() {
	
	}
	
	@FXML
	public void moveToServices(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ServicesView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML 
	public void goToWallet(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("UpdateBalance.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
