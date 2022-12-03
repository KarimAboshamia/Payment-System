package providers;

import java.util.*;

import application.User;

public interface HandleData {
	public String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields, User user, String serviceName, int paymentMethod);
}
