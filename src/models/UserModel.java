package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserModel {
	private Statement statement;
	

	public UserModel(Statement statement) {
		this.statement = statement;
	}

	public ResultSet checkLogin(String email) {
		try {
			String query = "select * from Users WHERE Email = '" + email + "'";
			ResultSet rs = statement.executeQuery(query);
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public ArrayList<String> signUp(String email, String username, String password, int permission) {
		MessageDigest md;
		ArrayList<String> results = new ArrayList<String>();
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(password.getBytes());
			
			String pass = new String(result);
			
			String query1 = "select * from Users WHERE Username = '" + username + "'";
			String query2 = "select * from Users WHERE Email = '" + email + "'";
			ResultSet userRes = statement.executeQuery(query1);
			results.add(userRes.getString("Username"));
			userRes.close();
			
			ResultSet emailRes = statement.executeQuery(query2);
			results.add(emailRes.getString("email"));
			emailRes.close();
		
		}catch (SQLException e) {
			//DB Exception
			e.printStackTrace();
		}catch (NoSuchAlgorithmException e) {
			//Hashing Exception
			e.printStackTrace();
		}
		return results;
	}
	
	public void signUpExecute(String email, String username, String password, int permission) {
		MessageDigest md = null;
	
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		byte[] result = md.digest(password.getBytes());
		String pass = new String(result);
		String editedPass = "u" + pass + "u";


		String query3 = "INSERT INTO Users (Email, Username, Password, Permission) VALUES(" +"'"+email+"'"+","+ "'" +username + "'" + "," + "'" +editedPass + "'" +"," + permission + ")";
		try {
			statement.executeUpdate(query3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void setBalance(int balance, String username) {
		String query = "UPDATE Users SET Wallet ='" + balance +"' WHERE Username = '" +  username + "'";
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getBalance(String username) {
		String query = "select * from Users WHERE Username = '" +  username + "'";
		try {
			ResultSet result = statement.executeQuery(query);
			return result.getString("Wallet");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getSystemUsers() {
		String query = "Select * from Users where Permission=" + "'" + 0 + "'";
		ResultSet res = null;
		try {
			res = statement.executeQuery(query);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
