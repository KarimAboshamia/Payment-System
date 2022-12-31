package providers;

import java.util.*;

import application.User;

public class HandleQuarterReceiptData  extends HandleData{
	String landLine;
	float amount;
	String city;
	String cardNumber = "";
	int pin = 0;
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields,User user, String serviceName, int paymentMethod) {
		
		landLine = inputFields.get("landLine");
		amount = (float) (Float.parseFloat((inputFields.get("Amount"))) * 1.18);
		city = inputFields.get("city");
		if(paymentMethod == 2) {
			cardNumber = inputFields.get("Credit Details");
			pin = Integer.parseInt(inputFields.get("PIN"));
		}
		return user.consumeBalance(amount, serviceName, paymentMethod, cardNumber, pin);
	}

}
