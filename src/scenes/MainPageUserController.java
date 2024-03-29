package scenes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import communication.DataCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import refund.NotificationsObservable;
import refund.Observable;

public class MainPageUserController {
	@FXML
	private ImageView notificationsButton;
	
	@FXML
	private Label userBalance;
	

	User user;
	ChangeScenes scener;
	Observable notificationsObservable = new NotificationsObservable();
	
	public MainPageUserController() {
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
		scener = new ChangeScenes();
	}
	
	@FXML 
	public void initialize() throws SQLException {
		userBalance.setText(Integer.toString(user.getBalance()));
		
		notificationsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "Notifications.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		notificationsObservable.subscribe(user);
		ResultSet res = user.readNotification();
		int counter = 0;
		while(res.next()) {
			System.out.println(res.getString("RefundID"));
			counter++;
		}
		if(counter != 0) {
			notificationsButton.setVisible(true);
		}		
		res.close();
	}

	@FXML
	public void imageHandler() {
	
	}
	
	@FXML
	public void moveToServices(ActionEvent event) throws IOException {
		scener.changeScene(event, "ServicesView.fxml");
	}
	
	@FXML 
	public void goToWallet(ActionEvent event) throws IOException {
		scener.changeScene(event, "UpdateBalance.fxml");

	}
	
	@FXML
	public void createNewRefund(ActionEvent event) throws IOException{
		scener.changeScene(event, "CreateRefund.fxml");
		
	}
}
