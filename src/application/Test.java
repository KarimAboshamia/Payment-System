package application;

import java.sql.SQLException;

public class Test {
	public static void main(String []args) {
		
		//Test Casting and adding a refund request.
		AppUser user = new User("Ahmed@gmail.com", "Ahmed", "1234", "0");
		User mainuser = (User) user;
		mainuser.requestRefund("Ahmed", 1);
		
		//Test listing all refund requests
		Admin admin = new Admin("Mohamed@gmail.com", "Mohamed", "1234","1");
		String id;
		try {
			id = admin.listRefunds().getString("RefundID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Test accepting/rejecting a request
		admin.changeState(1, id);
	
	}

}

