package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundManager {
	private DBConnection newdb = new DBConnection();
	public void handleRefund (String usrname, int transId )
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
