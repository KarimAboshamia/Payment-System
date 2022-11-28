package application;


public class User extends AppUser{
	public User(String username, String password, String permission) {
		super(username, password, permission);
		// TODO Auto-generated constructor stub
	}
	
	public void requestRefund(String username, int transId)
	{
		RefundManager.handleRefund(username,transId);
	}

}

