package providers;

import java.util.*;

import application.User;

public class HandleInternetData implements HandleData {

	String phoneNumber;
	float amount;
	String cardNumber = "";
	int pin = 0;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod) {
		
		phoneNumber = inputFields.get("PhoneNumber");
		amount = Float.parseFloat((inputFields.get("Amount")));
		if(paymentMethod == 2) {
			cardNumber = inputFields.get("Credit Details");
			pin = Integer.parseInt(inputFields.get("PIN"));
		}
		if(phoneNumber.length() == 8) {
			return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		} else if (phoneNumber.length() < 8){
			return "Wrong Land-Line number";
		}
		return null;
	}

}
