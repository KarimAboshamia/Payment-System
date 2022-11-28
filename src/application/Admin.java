package application;


public class Admin extends AppUser{
	RefundManager rman = new RefundManager();
	private DBConnection authObj = new DBConnection();

	public Admin(String username, String password, String permission) {
		super(username, password, permission);
		// TODO Auto-generated constructor stub
	}
	
	public void listRefunds(){
		rman.getRef();
	}
	
	public void changeState(int newState, int refundID)
	{
		authObj.updateState(newState, refundID);
	}

}

