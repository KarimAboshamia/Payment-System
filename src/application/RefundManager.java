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
	
}
