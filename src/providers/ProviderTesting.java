package providers;

import java.util.Vector;

import application.User;
import auth.AuthenticationManager;
import services.MobileService;
import services.Service;

public class ProviderTesting {
	
	public static void main(String [] args) {
		
		//Create service object
		Service mobileService = new MobileService("Mobile", false);
		
		//Create provider for Vodafone
		Provider vodafoneObject = new MobileServiceProvider("Vodafone", null);
		
		//Create HandleVodafoneData
		HandleData vodafoneHandler = new HandleVodafoneData();
		
		//Set VodafoneProvider with handler
		vodafoneObject.setHandleData(vodafoneHandler);

		
		//Login with user for testing purpose
		AuthenticationManager auth = new AuthenticationManager();
		User user = (User)auth.login("test22@gmail.com", "sasuke");
		
		Vector<String> data = new Vector<String>();
		data.add("01147232121");
		data.add( "400");
		//Call handleData from Vodafone with payment methods 0 - 1 - 2
		System.out.println(vodafoneObject.handleUserData(null, user, mobileService.getName(), 0, "234567891234567", 1234 ));
	}
	
	
	
	
	
	
	
	
	
}
