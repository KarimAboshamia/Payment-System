package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
	private DBConnection obj = new DBConnection();
	
	public AppUser login(String username, String password) {
		//Query database with name and hashed password
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(password.getBytes());
			
			String pass = new String(result); //Convert Byte Array to String
			
			ResultSet res = obj.checkLogin(username);
			
			System.out.println(res.getString("Password"));			

			if(res.next()) {
				if(pass.equals(res.getString("Password"))) {
					if(res.getInt("Permission") == 1) {
						System.out.println("Admin Logged In Sucessfully");			
					}else {
						System.out.println("You have Logged In Successfully");
					}
					return new Admin(res.getString("Username"), res.getString("Password"), Integer.toString(res.getInt("Permission")));
				}else {
					System.out.println("Wrong Email or Password");
				}
				
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
	
	public void signUp(String username, String password, int permission) {
		
		int signUpRes = obj.signUp(username, password, permission);
		if(signUpRes == 1) {
			System.out.println("Signed UP");
		}else {
			System.out.println("This username is already used");
		}
		
		
	}

}
