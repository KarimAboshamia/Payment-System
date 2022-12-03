package providers;

import java.util.*;

import application.User;

public class HandleWeData implements HandleData{
	String phoneNumber;
	float amount;
	String cardNumber = "";
	int pin = 0;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod) {
		System.out.println("Handling vodafone data");
		
		phoneNumber = inputFields.get("phoneNumber");
		amount = Float.parseFloat((inputFields.get("Amount")));
		if(paymentMethod == 2) {
			cardNumber = inputFields.get("Credit Details");
			pin = Integer.parseInt(inputFields.get("PIN"));
		}
		if(phoneNumber.substring(0,3).equals("015") && phoneNumber.length() == 11) {
			return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		} else if (phoneNumber.length() < 11){
			return "Wrong phone number";
		} else if (!(phoneNumber.substring(0,3).equals("015"))) {
			return "Not We Number";
		}
		return null;
	}

}
