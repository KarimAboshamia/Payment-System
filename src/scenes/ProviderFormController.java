package scenes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import application.User;
import communication.DataCommunicator;
import discount.Discount;
import discount.DiscountScenario;
import discount.OverDiscount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import providers.Provider;
import services.Service;

public class ProviderFormController {
	
	
	DataCommunicator communicator;
	ChangeScenes scener;
	Provider provider;
	DiscountScenario discountCalcObj = new DiscountScenario();
	Discount s = new OverDiscount();
	
	Vector<String> textFieldData;
	Map<String, List<String>> dropDownData;
	
	Map<String, String> textFieldInput;
	Map<String, String> dropDownInput;
	User user;
	Service service;
	int paymentMethod;
	String cardNumber;
	int pin;
	
	@FXML
	GridPane grid;
	
	@FXML
	ImageView backImage;
	
	@FXML
	AnchorPane anchorPane;
	
	@FXML
	Label transactionText;
	
	public ProviderFormController() {
		scener = new ChangeScenes();
		communicator = DataCommunicator.getCommunicator();
		provider = communicator.getProvider();
		user = (User) communicator.getUser();
		service = communicator.getService();
		textFieldData = provider.getTextFieldData();
		dropDownData = provider.getDropDownData();
		textFieldInput = new HashMap<>();
		dropDownInput = new HashMap<>();
		
	}
	
	@FXML
	public void initialize() {
		
		backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				scener.changeSceneWithMouse(event, "MainPageUser.fxml");
			} catch (IOException e) {
				e.printStackTrace();
			}
	     });
		
		
		//Add all text Fields
		int counter = 0;
		for(int i = 0; i < textFieldData.size(); i++) {
			TextField field = new TextField();
			field.setPromptText(textFieldData.get(i));
			grid.add(field, 0, counter);
			counter++;
		}
		
		//Add all dropdown Fields
		if(dropDownData != null) {
			for (Map.Entry<String, List<String>> key : dropDownData.entrySet()) {
				Label lb = new Label();
				lb.setText(key.getKey());
				grid.add(lb, 0, counter);
				counter++;
				ChoiceBox<String> choiceBox = new ChoiceBox<>();
				List<String> data = key.getValue();
				for(int i = 0; i < data.size(); i++) {
					choiceBox.getItems().add(data.get(i));
				}
				grid.add(choiceBox, 0, counter);
				counter++;
			}
		}
		
		//Radio buttons for options of payment
		ToggleGroup paymentGroup = new ToggleGroup();
	    RadioButton wallet = new RadioButton("Wallet");
	    wallet.setToggleGroup(paymentGroup);
	    RadioButton credit = new RadioButton("Credit Card");
	    credit.setToggleGroup(paymentGroup);
	    wallet.setSelected(true);
	    
	    if(service.getCachOnDelivery()) {
	    	RadioButton cacheOn = new RadioButton("Delivery");
	 	    cacheOn.setToggleGroup(paymentGroup);
		    grid.add(cacheOn, 2, counter);
	    }
	    grid.add(wallet, 0, counter);
	    grid.add(credit, 1, counter);
	    counter++;
	    
	    
	    final int c = counter;
	    final int c2 = counter+1;
	    TextField field = new TextField();
	    
	    TextField field2 = new TextField();
		//If credit card selected ask for credit card details
	    paymentGroup.selectedToggleProperty().addListener((observable, oldToggle, option) -> {
	    	if (option == credit) {
				field.setPromptText("Credit Details");
				grid.add(field, 0, c);  
				field2.setPromptText("PIN");
				grid.add(field2, 0, c2);
 	       } else {
 	    	   grid.getChildren().remove(field);
 	    	   grid.getChildren().remove(field2);
 	       }
	    });
	    counter+=2;
		
	    
	    //Check Discount
	    Button btn = new Button("Check Discount");
		btn.setMinHeight(25);		
		
		final int c3 = counter+1;
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			//Call discount and check discount object if there's an offer and display it 
			
			try {
				s = discountCalcObj.calcOverallDiscount(new OverDiscount(), user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Label discountRatio = new Label("Discount Ratio: " + s.getDiscount() + "%");
			System.out.println(s.getDiscount());
			
			grid.add(discountRatio, 0, c3);
			event.consume();
	     });	
		
		grid.add(btn, 0, counter);
		counter++;
		
		
		
		
	}
	
	@FXML
	public void submitForm(ActionEvent event) {
		
		for(Node node : anchorPane.getChildren()) {
			if(node instanceof GridPane) {
				for(Node node2 : ((GridPane) node).getChildren()) {
					if(node2 instanceof TextField) {
						if(((TextField)node2).getPromptText().equals("Amount")) {
							String balanceAfterOffer = Float.toString( Integer.parseInt(((TextField)node2).getText()) * (1 - (s.getDiscount()/100)) );
							textFieldInput.put(((TextField)node2).getPromptText(), balanceAfterOffer);	
						} else {
							textFieldInput.put(((TextField)node2).getPromptText(), ((TextField)node2).getText());	
						}
					} else if(node2 instanceof RadioButton) {
						if(((RadioButton)node2).isSelected() == true) {
							if(((RadioButton)node2).getText().equals("Wallet")) {
								paymentMethod = 0;
							} else if(((RadioButton)node2).getText().equals("Credit Card")) {
								paymentMethod = 2;
							} else {
								paymentMethod = 1;
							}	
						} 	
					}
				}				
			}
			
		}
		
		transactionText.setText(provider.handleUserData(textFieldInput, dropDownInput, user, service.getName(), paymentMethod));
		
		//User balance should be updated 
		
		
	}

}
