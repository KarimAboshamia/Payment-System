package providers;

import java.util.*;

import application.User;

public class HandleWeData implements HandleData{
	String phoneNumber;
	int amount;
	public String handleUserData(Map<String,String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling vodafone data");
		
		phoneNumber = inputFields.get("phoneNumber");
		amount = Integer.parseInt(inputFields.get("amount"));
		if(phoneNumber.substring(0,3).equals(015) && phoneNumber.length() == 11) {
			return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		} else if (phoneNumber.length() < 11){
			return "Wrong phone number";
		} else if (!(phoneNumber.substring(0,3).equals(015))) {
			return "Not We Number";
		}
		return null;
	}

}
