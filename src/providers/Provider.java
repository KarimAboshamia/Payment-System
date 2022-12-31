package providers;

import java.sql.SQLException;
import java.util.*;
import application.User;

public abstract class Provider {
	private String providerName;
	Map<String,String> textField =new HashMap<>();
	Map<String, ArrayList<String>> dropDownFields = new HashMap<>();
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
	
	
	public Vector<String> getTextFieldData() {
		//To create User Interface
		return userData.getTextFieldData();
	}
	
	public Map<String, List<String>> getDropDownData(){
		return userData.getDropDownData();
	}
	

	public String handleUserData(Map<String,String> textFieldsInput, Map<String, String>dropDownInput, User user, String serviceName, int paymentMethod, boolean addDiscount) throws SQLException {
		
		//Create transaction with the username and service details 
		//Takes users replies on the form and handle it
		if(addDiscount) {
			Float overAllDiscount = handleData.calcDiscount(user, serviceName);
			
			Float  amount = Float.parseFloat((textFieldsInput.get("Amount")));
			amount = amount * (1 - (overAllDiscount/100)) ;
			textFieldsInput.put("Amount", Float.toString(amount));
		}
		
		return handleData.handleUserData(textFieldsInput, dropDownInput, user, serviceName, paymentMethod);
	}
}
