package providers;

import java.util.*;

import application.User;

public class HandleInternetData implements HandleData {

	String phoneNumber;
	int amount;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling Etisalat Ground phone internet data");
		
		phoneNumber = inputFields.get("phoneNumber");
		amount = Integer.parseInt(inputFields.get("amount"));
		if(phoneNumber.length() == 8) {
			return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		} else if (phoneNumber.length() < 8){
			return "Wrong Land-Line number";
		}
		return null;
	}

}
