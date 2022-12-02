package providers;

import java.util.Vector;

import application.User;

public class HandleDonationsData implements HandleData{
	String DonationName;
	int amount;
	String city;
	String toWhom;
	public String handleUserData(Vector<String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling Donations data");
		
		DonationName= inputFields.get(0);
		amount = (int) (Integer.parseInt(inputFields.get(1)) * 1.2);
		city = inputFields.get(2);
		toWhom = inputFields.get(3);
		
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		
		//Send these data to the right charity "Not a requirement"
	}

}
