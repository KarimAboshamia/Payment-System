package application;

public class RefundManager {
	private DBConnection newdb = new DBConnection();
	public void handleRefund (String usrname, int transId )
	{ 
		newdb.insertRefund(usrname, transId);
	}
	
	public void getRef()
	{
		ResultSet res= newdb.getRefunds();
		while (res.next()) {
		      String name = rs.getString("name");
		      System.out.println(name);
		}

	}
	
}
