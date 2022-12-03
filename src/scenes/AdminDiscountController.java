package scenes;

import java.io.IOException;

import application.Admin;
import communication.DataCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
		
		choices.getItems().add("Transaction");
		choices.getItems().add("Overall");
	}
	
	@FXML
	public void submit(ActionEvent event) {
		float discountR = Float.parseFloat(discountRat.getText());
		if(choices.getSelectionModel().getSelectedItem().equals("Transaction")) {
			System.out.println("Adding");
			admin.addTransactionDiscount(discountR);
			
		}else if (choices.getSelectionModel().getSelectedItem().equals("Overall")) {
			admin.addOverallDiscount(discountR);
		}
		
		result.setText("Added Successfully");
		discountRat.clear();
		
		
	}

}
