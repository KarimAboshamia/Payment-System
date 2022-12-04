package scenes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import balance.BalanceManagerInterface;
import balance.WalletBalanceManager;
import communication.DataCommunicator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class CreateRefundController {
	
	User user;
	@FXML 
	GridPane grid;
	BalanceManagerInterface manager;
	@FXML
	ImageView backImage;
	@FXML
	Label displaymsg;
	
	ChangeScenes scener = new ChangeScenes();
	
	public CreateRefundController(){
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		user = (User) communicator.getUser();
		manager = new WalletBalanceManager();
		
	}
	
	//Display list with all user transactions
	@FXML
	public void initialize() throws SQLException {
		//Getting Back Image handler
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "MainPageUser.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
	     });
		
		
		//Get list of all usertransactions using  transaction manger
		ResultSet res = manager.getTransactions(user.getUsername());
		int counter = 0;
	
		while(res.next()) {
			
			Button btn = new Button("Refund");
			btn.setMinHeight(25);
			btn.setId((res.getString("TransactionID")));
			
			
			btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				//OnClicking refund -> a new refund is created based on refund id
					try {
						user.requestRefund(btn.getId());
						displaymsg.setText("Refund Request Sent");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					event.consume();
		     });			
			

			Label serviceLabel = new Label(res.getString("Service"));
			Label amountLabel = new Label(res.getString("Amount"));
			
			
			
			
			grid.add(serviceLabel, 0, counter);
			grid.add(amountLabel, 1, counter);
			grid.add(btn, 2, counter);
			
			
			RowConstraints newRow = new RowConstraints();
			newRow.setPrefHeight(30);
			newRow.setVgrow(Priority.SOMETIMES);
			newRow.setMinHeight(10);
			
			grid.getRowConstraints().add(newRow);
			counter++;
		}
		
		Label nullLabel = new Label("No Current Transactions to refund");
		if(counter == 0)
			grid.add(nullLabel, 0, counter);
		}
	
	

}
