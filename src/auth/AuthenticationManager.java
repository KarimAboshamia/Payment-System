package auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Admin;
import application.AppUser;
import application.User;
import db.DBConnection;

public class AuthenticationManager {//Manager
	private DBConnection authObj = new DBConnection();
	
	public AppUser login(String email, String password) {
		//Query database with name and hashed password
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(password.getBytes());
			
			String pass = new String(result); //Convert Byte Array to String
			
			ResultSet res = authObj.checkLogin(email);
			
			System.out.println(res.getString("Password"));			

			
			if(pass.equals(res.getString("Password"))) {
				if(res.getInt("Permission") == 1) {
					System.out.println("Admin Logged In Sucessfully");	
					return new Admin(res.getString("Email"), res.getString("Username"),res.getString("Password"),res.getString("Permission"));
				}else {
					System.out.println("You have Logged In Successfully");
					return new User(res.getString("Email"), res.getString("Username"),res.getString("Password"),res.getString("Permission"));

				}
			}else {
				System.out.println("Wrong Email or Password");
			}
				
			

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	public int signUp(String email, String username, String password, int permission) {
		
		ArrayList<String> signUpRes = authObj.signUp(email, username, password, permission);
		if(signUpRes.get(0) != null) {
			return 0;
		}
		else if(signUpRes.get(1) != null) {
			return -1;
		}
		
		authObj.signUpExecute(email, username, password, permission);			
		
		return 1;
	}

}
