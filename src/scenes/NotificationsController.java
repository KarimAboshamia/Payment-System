package scenes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import communication.DataCommunicator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class NotificationsController {
	User user;
	@FXML 
	GridPane grid;
	@FXML
	ImageView backImage;
	
	ChangeScenes scener = new ChangeScenes();
	
	public NotificationsController(){
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();		
	}
	
	//Display list with all user transactions
	@FXML
	public void initialize() throws SQLException {
		//Getting Back Image handler
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "MainPageUser.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     });
		
		
		//Get list of all usertransactions using  transaction manger
		user.notifications();
		ResultSet res = user.readNotification();
		System.out.println(res.getString("RefundID"));

		int counter = 0;
	
		while(res.next()) {
			

			Label refundLabel = new Label(res.getString("RefundID"));
			System.out.println(res.getString("RefundID"));
			Label stateLabel = null;
			if(res.getString("State").equals("1")) {
				 stateLabel = new Label("Accepted");
			} else {
				 stateLabel = new Label("Rejected");
			}
			
			Button btn = new Button("Read");
			btn.setMinHeight(25);
			btn.setId((res.getString("RefundID")));
			
			btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					user.markRead(btn.getId());
					try {
						scener.changeSceneWithMouse(event, "Notifications.fxml");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					event.consume();
		     });			
			
			
			
			
			
			
			grid.add(refundLabel, 0, counter);
			grid.add(stateLabel, 1, counter);
			
			grid.add(btn, 2, counter);
			
			
			RowConstraints newRow = new RowConstraints();
			newRow.setPrefHeight(30);
			newRow.setVgrow(Priority.SOMETIMES);
			newRow.setMinHeight(10);
			
			grid.getRowConstraints().add(newRow);
			counter++;
		}
	}
}
