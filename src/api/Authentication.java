package api;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import application.AppUser;
import application.User;
import managers.AuthenticationManager;

@RestController
public class Authentication {
	AuthenticationManager auth = new AuthenticationManager();
	

	@GetMapping(value="/login")
	@ResponseBody
	public Map<String, String> authenticate(@RequestParam String email, @RequestParam String password) {
		AppUser user = auth.login(email,password);
	    HashMap<String, String> map = new HashMap<>();	
		if(user != null) {
		    if(user.getPermission().equals("1")) {
				String res = email + "," + password + "," + user.getPermission() + "," + user.getUsername();
				res = encrypt(res);
				map.put("Token", res);
		    	
		    } else {
		    	User normalUser  = (User)user;
		    	String res = email + "," + password + "," + user.getPermission() + "," + user.getUsername() + "," + normalUser.getBalance();
				res = encrypt(res);
				map.put("Token", res);
		    }   
		} else {
			map.put("Error", "Wrong Email or Password");
		}
		return map;
	}
	
	@PostMapping(value="/signup")
	@ResponseBody
	public Map<String, String> signUp(@RequestParam String email,@RequestParam String username,@RequestParam String password,@RequestParam int permission) {
		int res = auth.signUp(email, username, password, permission);
		HashMap<String, String> map = new HashMap<>();
		if(res == 1) {
			map.put("State", "Signed Up Successfully");
		} else if (res == 0) {
			map.put("State", "Username already used...");
			
		} else if (res == -1) {
			map.put("State", "Email already used...");			
			
		}
		return map;
	}
	
	public String encrypt(String data) {


		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("run");
		String token = encryptor.encrypt(data);
		System.out.println("Encrypt");

		System.out.println(token);
		token = token.replaceAll(Pattern.quote("+"), "_");
		return token;
	}

}
