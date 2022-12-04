package providers;

import java.util.*;

import application.User;

public class HandleCancerDonationData implements HandleData {

	String donationName;
	float amount;
	String city;
	String identity;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod) {
		
		donationName= inputFields.get("To");
		amount = (float) (Float.parseFloat((inputFields.get("Amount"))) * 1.2);
		city = inputFields.get("Duration");
		identity = dropFields.get("Identity");
		String cardNumber = inputFields.get("Credit Details");
		int pin = Integer.parseInt(inputFields.get("pin"));
		
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		
		//Send these data to the right charity "Not a requirement"
	}

}
