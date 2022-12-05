package managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class RefundManager {
	private DBConnection newdb = DBConnection.getDB();
	public void handleRefund (String usrname, String transId ) throws SQLException
	{ 
		newdb.insertRefund(usrname, transId);
	}
	
	public ResultSet getRef()
	{
		ResultSet res= newdb.getRefunds();
		return res;

	}
	
	public void setNewRefundState(String newState, String refundID) throws SQLException {
		
		//if new state is 1
		if(newState.equals("1")) {
			ResultSet res = newdb.getRelatedTransaction(refundID);
			String amount = res.getString("Amount");
			String name = res.getString("Username");
			String transID = res.getString("TransactionID");
			String wallet = newdb.getBalance(name);
			
			float newBalance = Float.parseFloat(wallet) + Float.parseFloat(amount);
			newdb.setBalance((int) newBalance, name);
			
			newdb.removeTransaction(transID);
			
		}
		newdb.updateState(newState, refundID);
		
	}
	
}
