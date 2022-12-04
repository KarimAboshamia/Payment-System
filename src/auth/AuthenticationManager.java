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
	private DBConnection authObj = DBConnection.getDB();
	
	public AppUser login(String email, String password) {
		//Query database with name and hashed password
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(password.getBytes());
			
			String pass = new String(result); //Convert Byte Array to String
			String editedPass = "u" + pass + "u";
			ResultSet res = authObj.checkLogin(email);
			

			
			if(editedPass.equals(res.getString("Password"))) {
				if(res.getInt("Permission") == 1) {
					return new Admin(res.getString("Email"), res.getString("Username"),res.getString("Password"),res.getString("Permission"));
				}else {
					return new User(res.getString("Email"), res.getString("Username"),res.getString("Password"),res.getString("Permission"), Integer.parseInt(res.getString("Wallet")));

				}
			}
					

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
