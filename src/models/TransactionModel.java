package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionModel {
	private Statement statement;
	

	public TransactionModel(Statement statement) {
		this.statement = statement;
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
	
	public ResultSet getTransactions(String user) throws SQLException {
		String query = "select * from Transactions Where Username = '" + user + "'";
		ResultSet result = statement.executeQuery(query);
		return result;
	}
	
	public void addWalletTransaction(String username, int balance) {
		String query = "insert into WalletTransaction (Username, Amount) values ('"+username+"','"+balance+")";
		 
	}
	
	public ResultSet getRefunds(String username)
	{
		String nquery = "SELECT * from RefundReq where Username=" + "'" + username + "'";
		
		ResultSet res = null;
		try {
			res = statement.executeQuery(nquery);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public  ResultSet getWalletTransactions(String username) {
		String query = "Select * from WalletTransaction where Username=" + "'"+username+"'";
		ResultSet res = null;
		try {
			res = statement.executeQuery(query);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
}
