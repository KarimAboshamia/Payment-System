package providers;

import java.util.*;

import application.User;

public class HandleQuarterReceiptData  implements HandleData{
	String landLine;
	int amount;
	String city;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod, String cardNumber, int pin) {
		System.out.println("Handling Quarter Receipt data");
		
		landLine = inputFields.get("landLine");
		amount = (int) (Integer.parseInt(inputFields.get("amount")) * 1.18);
		city = inputFields.get("city");
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
	}

}
