package providers;

import java.util.*;

import application.User;

public class HandleNPOData implements HandleData {

	String DonationName;
	int amount;
	String city;
	String OrganizationName;
	public String handleUserData(Map<String,String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling Donations data");
		
		DonationName= inputFields.get("donationName");
		amount = (int) (Integer.parseInt(inputFields.get("amount")) * 1.2);
		city = inputFields.get("city");
		OrganizationName = inputFields.get("organizationName");
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		
		//Send these data to the right charity "Not a requirement"
	}

}
