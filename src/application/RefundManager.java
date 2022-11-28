package application;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundManager {
	private DBConnection newdb = new DBConnection();
	public void handleRefund (String usrname, int transId )
	{ 
		newdb.insertRefund(usrname, transId);
	}
	
	public void getRef()
	{
		ResultSet res= newdb.getRefunds();
		try {
			while (res.next()) {
			      String name = res.getString("name");
			      System.out.println(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
