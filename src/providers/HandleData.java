package providers;

import java.util.*;

import application.User;

public interface HandleData {
	public String handleUserData(Map<String,String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin);
	//public bool checkDiscounts(User user);
}
