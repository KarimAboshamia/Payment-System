package models;
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


public class RefundTransactionModel {

	private Statement statement;
	

	public RefundTransactionModel(Statement statement) {
		this.statement = statement;

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

}
