package application;

public class Admin extends AppUser{
	RefundManager rman = new RefundManager();
	public Admin(String username, String password, String permission) {
		super(username, password, permission);
		// TODO Auto-generated constructor stub
	}
	
	public void listRefunds(){
		rman.getRef();
	}

}

