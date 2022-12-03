package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import auth.AuthenticationManager;
import refund.NotificationsObservable;
import refund.Observable;

public class Test {
	public static void main(String []args) {
		/*
		//Test Casting and adding a refund request.
		AppUser user = new User("Ahmed@gmail.com", "Ahmed", "1234", "0");
		User mainuser = (User) user;
		mainuser.requestRefund("Ahmed", 1);
		
		//Test listing all refund requests
		Admin admin = new Admin("Mohamed@gmail.com", "Mohamed", "1234","1");
		String id = "-1";
		try {
			id = admin.listRefunds().getString("RefundID");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Test accepting/rejecting a request
		admin.changeState("-1", "11");
		*/
		
		//Test services creation and search
		/*ServiceManager serviceManager = new ServiceManager();
		Vector<Service> services = serviceManager.CreateSystemServices();
		for(int i = 0; i < services.size(); i++) {
			System.out.println(services.get(i).getName());
		}*/
		//Search
		//AppUser user = new User("Ahmed2@gmail.com", "Ahmed2", "1234", "0");
		//User mainuser = (User) user;
		//System.out.println(mainuser.search(services, "S").get(2).getName());
		
		
		//Test notifications
		AuthenticationManager auth = new AuthenticationManager();
		AppUser user = 	auth.login("Ahmed@gmail.com", "sasuke");
		User mainuser = (User) user;
		Observable notificationsObservable = new NotificationsObservable();
		notificationsObservable.subscribe(mainuser);
		ResultSet res = mainuser.readNotification();
		try {
			System.out.println(res.getString("RefundID"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}

