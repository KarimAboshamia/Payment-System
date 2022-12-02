package providers;

import java.util.Vector;
import java.util.*;
import application.User;

public abstract class Provider {
	private String providerName;
	//Vector<String> textField = new Vector<String>();
	Map<String,String> textField =new HashMap<>();
	//Vector<String> dropField = new Vector<String>();
	UserData userData;
	HandleData handleData;
	
	public Provider(String name , HandleData datahandler) {
		providerName = name;
		this.handleData=datahandler;
	}
	
	public String getProviderName() {
		return providerName;
	}
	
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	public void setHandleData(HandleData handleData) {
		this.handleData = handleData;
	}
	
	
	public int getTextFields() {
		//To create User Interface
		return userData.numberOfTextFields();
	}

	public String handleUserData(Map<String,String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		
		//Create transaction with the username and service details 
		//Takes users replies on the form and handle it 
		return handleData.handleUserData(inputFields, user, serviceName, paymentMethod, cardNumber, pin);
	}
}
