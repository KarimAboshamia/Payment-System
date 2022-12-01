package providers;

import java.util.Vector;

import application.User;

public interface HandleData {
	public String handleUserData(Vector<String> inputFields, User user, String serviceName, int paymentMethod, String cardNumber, int pin);
}
