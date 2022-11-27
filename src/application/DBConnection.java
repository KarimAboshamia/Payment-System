package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private Connection connection;
	private Statement statement;
	

	public DBConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db.db");
			statement = connection.createStatement();

		}catch(SQLException e) {
	        System.out.println(e);
	
				
		}
	}
	
	public ResultSet checkLogin(String username) {
		try {
			String query = "select * from Users WHERE Username = '" + username + "'";
			ResultSet rs = statement.executeQuery(query);
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public int signUp(String username, String password, int permission) {
		MessageDigest md;
		int executionCondition = 0;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(password.getBytes());
			
			String pass = new String(result);
			System.out.println(pass);
			
			String query1 = "select * from Users WHERE Username = '" + username + "'";
			String query2 = "INSERT INTO Users VALUES(" + "'" +username + "'" + "," + "'" +pass + "'" +"," + permission + ")";
			ResultSet rs = statement.executeQuery(query1);
			if(rs.getString("Username") == null) {
				executionCondition = statement.executeUpdate(query2);
		
			} else {
				executionCondition = 0;
			}
			
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executionCondition;
	}
	
}
