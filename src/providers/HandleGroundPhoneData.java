package providers;

import java.util.Vector;

import application.User;

public class HandleGroundPhoneData implements HandleData {
	String phoneNumber;
	int amount;
	public String handleUserData(Vector<String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling Ground phone data");
		
		phoneNumber = inputFields.get(0);
		amount = Integer.parseInt(inputFields.get(1));
		if(phoneNumber.length() == 8) {
			return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
		} else if (phoneNumber.length() < 8){
			return "Wrong Land-Line number";
		}
		return null;
	}

}
