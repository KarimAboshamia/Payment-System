package scenes;

import java.io.IOException;

import application.Admin;
import communication.DataCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AdminDiscountController {
	
	ChangeScenes scener = new ChangeScenes();
	DataCommunicator communicator;
	Admin admin;
	
	@FXML
	ImageView backImage;
	
	@FXML
	ChoiceBox choices;
	
	@FXML
	TextField discountRat;
	@FXML
	Label result;
	@FXML
	GridPane serviceName;
	TextField f = new TextField();

	
	public AdminDiscountController() {
		communicator = DataCommunicator.getCommunicator();
		admin = (Admin) communicator.getUser();
	}
	
	@FXML
	public void initialize() {
		
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "MainPageAdmin.fxml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     });
		
		choices.setValue("Transaction");
		choices.getItems().add("Transaction");
		choices.getItems().add("Service");
		choices.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			
			 if (choices.getSelectionModel().getSelectedItem().equals("Service")) {
				 f.setPromptText("Service Name");
				 serviceName.add(f, 0, 0);
			} else {
				serviceName.getChildren().remove(f);
			}
	     });
	}
	
	@FXML
	public void submit(ActionEvent event) {
		float discountR = Float.parseFloat(discountRat.getText());
		if(choices.getSelectionModel().getSelectedItem().equals("Transaction")) {
			admin.addTransactionDiscount(discountR);
			
		}else if (choices.getSelectionModel().getSelectedItem().equals("Service")) {
			admin.addServiceDiscount(discountR, ((TextField)serviceName.getChildren().get(0)).getText());
		}
		
		result.setText("Added Successfully");
		discountRat.clear();
		
		
	}

}
