package providers;

import java.util.*;

import application.User;

public class HandleNPOData extends HandleData {

	float amount;
	String city;
	String organizationName;
	String identity;
	String cardNumber = "";
	int pin = 0;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod) {
		
		organizationName= inputFields.get("To");
		amount = (float) (Float.parseFloat((inputFields.get("Amount"))) * 1.4);
		city = inputFields.get("Duration");
		identity = dropFields.get("Identity");
		if(paymentMethod == 2) {
			cardNumber = inputFields.get("Credit Details");
			pin = Integer.parseInt(inputFields.get("PIN"));
		}
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		
		//Send these data to the right charity "Not a requirement"
	}

}
