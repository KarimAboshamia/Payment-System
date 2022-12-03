package refund;

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
	
	public void setNewRefundState(String newState, String refundID) {
		newdb.updateState(newState, refundID);
		
	}
	
}
