package providers;

import java.util.*;

import application.User;

public class HandleNPOData implements HandleData {

	int amount;
	String city;
	String organizationName;
	String identity;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling Donations data");
		
		organizationName= inputFields.get("To");
		amount = (int) (Integer.parseInt(inputFields.get("Amount")) * 1.4);
		city = inputFields.get("Duration");
		identity = dropFields.get("Identity");
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		
		//Send these data to the right charity "Not a requirement"
	}

}
