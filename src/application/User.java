package application;


public class User extends AppUser{
	RefundManager refm = new RefundManager();
	public User(String username, String password, String permission) {
		super(username, password, permission);
		// TODO Auto-generated constructor stub
	}
	
	public void requestRefund(String username, int transId)
	{
		refm.handleRefund(username,transId);
	}

}

