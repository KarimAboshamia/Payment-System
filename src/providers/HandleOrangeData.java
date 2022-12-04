package providers;

import java.util.*;

import application.User;

public class HandleOrangeData implements HandleData{
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
		if(phoneNumber.substring(0,3).equals("012") && phoneNumber.length() == 11) {
			return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		} else if (phoneNumber.length() < 11){
			return "Wrong phone number";
		} else if (!(phoneNumber.substring(0,3).equals("012"))) {
			return "Not Orange Number";
		}
		return null;
	}
}
