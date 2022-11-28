package application;

public class RefundManager {
	private DBConnection newdb = new DBConnection();
	public static void handleRefund (String usrname, int transId )
	{ 
		newdb.insertRefund(usrname, transId);
	}
}
