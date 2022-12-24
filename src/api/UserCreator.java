package api;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import application.Admin;
import application.AppUser;
import application.User;

public class UserCreator {
	
	public AppUser createUser(String token) {

		String[] splitArray = token.split(",");
		AppUser user;
		if(splitArray[2].equals("1")) {
			 user = new Admin(splitArray[0], splitArray[3], splitArray[1], splitArray[2]);
		} else {
			 user = new User(splitArray[0], splitArray[3], splitArray[1], splitArray[2], Integer.parseInt(splitArray[4]));	
		}
		return user;
	}

}
