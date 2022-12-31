package providers;

import java.util.*;

import application.User;

public class HandleCancerDonationData extends HandleData {

	String donationName;
	float amount;
	String city;
	String identity;
	String cardNumber;
	int pin;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod) {
		
		donationName= inputFields.get("To");
		amount = (float) (Float.parseFloat((inputFields.get("Amount"))) * 1.2);
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
