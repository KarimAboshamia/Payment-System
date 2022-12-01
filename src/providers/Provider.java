package providers;

import java.util.Vector;

import application.User;

public abstract class Provider {
	private String providerName;
	Vector<String> textField = new Vector<String>();
	//Vector<String> dropField = new Vector<String>();
	UserData userData;
	HandleData handleData;
	
	public Provider(String name) {
		providerName = name;
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

	public String handleUserData(Vector<String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		
		//Create transaction with the username and service details 
		//Takes users replies on the form and handle it 
		return handleData.handleUserData(inputFields, user, serviceName, paymentMethod, cardNumber, pin);
	}
}
