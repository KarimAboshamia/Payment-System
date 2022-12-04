package providers;

import java.util.*;

import application.User;

public class HandleSchoolDonationData implements HandleData {

	float amount;
	String city;
	String schoolName;
	String identity;
	String cardNumber = "";
	int pin = 0;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod) {
		
		schoolName = inputFields.get("To");
		amount = (float) (Float.parseFloat((inputFields.get("Amount"))) * 1.5);
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
