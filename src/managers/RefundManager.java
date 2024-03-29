package managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import models.RefundTransactionModel;
import models.RefundModel;
import models.TransactionModel;
import models.UserModel;

public class RefundManager {
	private DBConnection connection = DBConnection.getDB();

	private RefundModel refundObject = new RefundModel(connection.getDBConnection());
	TransactionModel transObject = new TransactionModel(connection.getDBConnection());
	UserModel userObject = new UserModel(connection.getDBConnection());
	RefundTransactionModel db = new RefundTransactionModel(connection.getDBConnection());

	public void handleRefund (String usrname, String transId ) throws SQLException
	{ 
		refundObject.insertRefund(usrname, transId);
	}
	
	public ResultSet getRef()
	{
		ResultSet res= db.getRefunds();
		return res;

	}
	
	public void setNewRefundState(String newState, String refundID) throws SQLException {
		
		//if new state is 1
		if(newState.equals("1")) {
			ResultSet res = transObject.getRelatedTransaction(refundID);
			String amount = res.getString("Amount");
			String name = res.getString("Username");
			String transID = res.getString("TransactionID");
			String wallet = userObject.getBalance(name);
			
			float newBalance = Float.parseFloat(wallet) + Float.parseFloat(amount);
			userObject.setBalance((int) newBalance, name);
			
			transObject.removeTransaction(transID);
			
		}
		refundObject.updateState(newState, refundID);
		
	}
	
}
