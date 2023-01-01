package api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Set;
import java.util.regex.Pattern;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import application.Admin;
import application.AppUser;
import application.User;
import managers.AuthenticationManager;
import managers.UsersManager;

public class UserCreator {
	UsersManager validateToken = new UsersManager();
	AuthenticationManager auth = new AuthenticationManager();
	
	public AppUser createUser(String token) throws SQLException {
		
		token = decrypt(token);
		if(token == null) return null;
		String[] splitArray = token.split(",");
		AppUser user = null;

		
		if(splitArray[2].equals("1")) {
			user = auth.login(splitArray[0], splitArray[1]);
			//user = new Admin(splitArray[0], splitArray[3], splitArray[1], splitArray[2]);
		} else {
			user = auth.login(splitArray[0], splitArray[1]);
			//user = new User(splitArray[0], splitArray[3], splitArray[1], splitArray[2], Integer.parseInt(splitArray[4]));	
		}
		return user;
	}
	
	public String decrypt(String data) {
		String token = null;
		data = data.replaceAll("_", "\\+");
		System.out.println("Decrypt");

		System.out.println(data);

		try {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("run");
		token = encryptor.decrypt(data);
		} catch (Exception ex) {
			return null;
		}
		return token;
	}
}
