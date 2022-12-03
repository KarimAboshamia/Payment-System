package scenes;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import application.User;
import communication.DataCommunicator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import providers.Provider;
import services.Service;

public class ProviderFormController {
	
	
	DataCommunicator communicator;
	ChangeScenes scener;
	Provider provider;
	
	Vector<String> textFieldData;
	Map<String, List<String>> dropDownData;
	User user;
	Service service;
	
	public ProviderFormController() {
		scener = new ChangeScenes();
		communicator = DataCommunicator.getCommunicator();
		provider = communicator.getProvider();
		user = (User) communicator.getUser();
		service = communicator.getService();
		textFieldData = provider.getTextFieldData();
		dropDownData = provider.getDropDownData();
		
	}
	
	@FXML
	public void initialize() {
		for(int i = 0; i < textFieldData.size(); i++) {
			System.out.println(textFieldData.get(i));
		}
	}
	
	@FXML
	public void submitForm(ActionEvent event) {
		
		//provider.handleUserData(inputFields, user, serviceName, paymentMethod, cardNumber, pin)
		
	}

}
