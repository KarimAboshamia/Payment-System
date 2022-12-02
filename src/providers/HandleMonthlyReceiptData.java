package providers;

import java.util.Vector;

import application.User;

public class HandleMonthlyReceiptData implements HandleData{
	String landLine;
	int amount;
	String city;
	public String handleUserData(Vector<String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling Monthly Receipt data");
		
		landLine = inputFields.get(0);
		amount = (int) (Integer.parseInt(inputFields.get(1)) * 1.12);
		city = inputFields.get(2);
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
	}
}
