package application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
//import services.Service;

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
	
	public ResultSet checkLogin(String email) {
		try {
			String query = "select * from Users WHERE Username = '" + email + "'";
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
		int executionCondition = 0;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] result = md.digest(password.getBytes());
			
			String pass = new String(result);
			System.out.println(pass);
			
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
	
	public void signUpExecute(String email, String username, String pass, int permission) {
		String query3 = "INSERT INTO Users VALUES(" +"'"+email+"'"+","+ "'" +username + "'" + "," + "'" +pass + "'" +"," + permission + ")";
		try {
			statement.executeUpdate(query3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//search for service function
		public ResultSet GetServices(){
			String nameQuery="select * from Services"; 
			ResultSet rs=null;
			try {
				 rs=statement.executeQuery(nameQuery);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
			
		}

		
		public void insertRefund(String usrname, int transId )
		{
			String newQuery= "insert into RefundReq (Username, State, TransactionID, Changed) values (usrname, 0, transId, 0);";
		}
		
		public ResultSet getRefunds()
		{
			String nquery = "SELECT RefundReq.Username, Transactions.Service, Transactions.SeriveProv, Transactions.amount"
					+ "FROM Transactions"
					+ "INNER JOIN RefundReq"
					+ "ON Transactions.TransactionID=RefundReq.TransactionID"
					+ "WHERE State=0;";
			
			ResultSet res=null;
			try {
				res = statement.executeQuery(nquery);			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
		
		public void updateState(int newState, int refundID)
		{
			String nquery = "UPDATE RefundReq"
					+ "SET State = '"+newState+"', Changed = 1"
					+ "WHERE State=0 AND RefundID=refundID;";
			try {
				statement.executeUpdate(nquery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
