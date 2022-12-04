package scenes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Admin;
import communication.DataCommunicator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import refund.RefundManager;

public class AdminRefundsController {
	
	@FXML
	ImageView backImage;
	@FXML
	GridPane grid;
	
	Admin admin;
	RefundManager manager;
	ChangeScenes scener;
	
	public AdminRefundsController(){
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		admin = (Admin) communicator.getUser();
		manager = new RefundManager();
		scener = new ChangeScenes();
		
	}
	
	
	@FXML
	public void initialize() throws SQLException {
		//Getting Back Image handler
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				try {
					scener.changeSceneWithMouse(event, "MainPageAdmin.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     });
		
		
		//Get list of all usertransactions using  transaction manger
		ResultSet res = manager.getRef();
		int counter = 0;
		
		while(res.next()) {
			
				Button accept = new Button("Accept");
				accept.setMinHeight(25);
				accept.setId((res.getString("RefundID")));
			
				
				Button reject = new Button("Reject");
				reject.setMinHeight(25);
				reject.setId((res.getString("RefundID")));
				
				
				accept.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
						try {
							admin.changeState("1", accept.getId());
							scener.changeSceneWithMouse(event, "AdminRefunds.fxml");
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						event.consume();
			     });
				
				reject.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
						try {
							admin.changeState("-1", reject.getId());
							scener.changeSceneWithMouse(event, "AdminRefunds.fxml");

						} catch (SQLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}					
						event.consume();
				});			
			
				
	
				Label serviceLabel = new Label(res.getString("Service"));
				Label amountLabel = new Label(res.getString("Amount"));
				
				
				
				
				grid.add(serviceLabel, 0, counter);
				grid.add(amountLabel, 1, counter);
				grid.add(accept, 2, counter);
				grid.add(reject, 3, counter);
				
				
				RowConstraints newRow = new RowConstraints();
				newRow.setPrefHeight(30);
				newRow.setVgrow(Priority.SOMETIMES);
				newRow.setMinHeight(10);
				
				grid.getRowConstraints().add(newRow);
				counter++;
		}
		
		if(counter == 0) {
			Label nullLabel = new Label("No Refund Requests");
			grid.add(nullLabel, 0, counter);
		}		
	}

}
