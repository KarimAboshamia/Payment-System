package db;
//import discount.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//import services.Service;


public class DBConnection {
	private Connection connection;
	private static DBConnection db = new DBConnection();
	private Statement statement;
	

	private DBConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db.db");
			statement = connection.createStatement();

		}catch(SQLException e) {
	        System.out.println(e);
	
				
		}
	}
	
	public static DBConnection getDB() {
		return db;
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
	
	//search for service function
		public ResultSet GetServices(){
			String nameQuery="select * from Services"; 
			ResultSet rs=null;
			try {
				 rs=statement.executeQuery(nameQuery);
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
			
		}

		
		public void insertRefund(String usrname, String transId)
		{
			String newQuery= "insert into RefundReq (Username, State, TransactionID, Changed) values ('"+usrname+"', 0, '"+transId+"', 0);";
			try {
				statement.executeUpdate(newQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public ResultSet getRefunds()
		{
			String nquery = "SELECT RefundReq.Username, RefundReq.RefundID, Transactions.Service, Transactions.Amount FROM Transactions INNER JOIN RefundReq ON Transactions.TransactionID=RefundReq.TransactionID WHERE State=0;";
			
			ResultSet res = null;
			try {
				res = statement.executeQuery(nquery);			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return res;
		}
		
		public void updateState(String newState, String refundID)
		{
			String nquery = "UPDATE RefundReq "
					+ "SET State = '"+newState+"', Changed = 1 "
					+ "WHERE State=0 AND RefundID = '" + refundID + "';";
			
			try {
				statement.executeUpdate(nquery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public ResultSet searchForChangedStates(String callerName) {
			String query = "select * from RefundReq WHERE Username = '" +  callerName + "' AND Changed = 1";
			try {
				ResultSet result = statement.executeQuery(query);
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		public void setChangedRefundColumn(String id) {
			String nquery = "UPDATE RefundReq "
					+ "SET Changed = 0 "
					+ "WHERE RefundID = '" + id + "';";
			
			try {
				statement.executeUpdate(nquery);
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
		
		public void insertTransaction(String userName, float amount, String serviceName) {
			String newQuery= "insert into Transactions (Username, Amount, Service) values ('"+userName+"', '" + amount + "', '" + serviceName + "')";
			try {
				statement.executeUpdate(newQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public ResultSet getRelatedTransaction(String refundID) {
			String query = "SELECT * FROM Transactions INNER JOIN RefundReq ON Transactions.TransactionID=RefundReq.TransactionID WHERE RefundID = '" + refundID + "'"; 
			//String query = "select * from RefundReq WHERE RefundID = '" +  refundID + "'";
			
			try {
				//ResultSet result = statement.executeQuery(query);
				//String transID = result.getString("TransactionID");
				//result.close();
				//query = "select * from Transactions WHERE TransactionID = '" +  transID + "'";
				ResultSet result2 = statement.executeQuery(query);
				return result2;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		
		public void removeTransaction(String transactionID) {
			String query = "delete from Transactions WHERE TransactionID = '" +  transactionID + "'";
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public ResultSet calcServiceDiscount() throws SQLException
		{
			String nquery = "select * from ServiceDiscount;";
			ResultSet res = statement.executeQuery(nquery);
			return res;
		}
		
		public void setServiceDiscount(float ratio, String name) {
			
			String newQuery= "insert into ServiceDiscount (DiscountRatio, Service) values ('" + ratio+ "'," + "'" + name + "')";
			try {
				statement.executeUpdate(newQuery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
		
		public ResultSet calcTransactionDiscount() throws SQLException
		{
			String nquery = "select * from TransactionDiscount;";
			ResultSet res = statement.executeQuery(nquery);
			return res;
		}

		public void setTransactionDiscount(float ratio) {
			String newQuery= "insert into TransactionDiscount (DiscountRatio) values ('" + ratio+ "')";
			try {
				statement.executeUpdate(newQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public ResultSet getTransactions(String user) throws SQLException {
			String query = "select * from Transactions Where Username = '" + user + "'";
			ResultSet result = statement.executeQuery(query);
			return result;
		}

}
