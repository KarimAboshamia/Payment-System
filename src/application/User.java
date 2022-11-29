package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import services.Service;

public class User extends AppUser{
	RefundManager refm = new RefundManager();
	ServiceManager serviceManager  = new ServiceManager();
	NotificationManager notificationManager = new NotificationManager();
	ResultSet result;
	
	public User(String email, String username, String password, String permission) {
		super(email, username, password, permission);
		// TODO Auto-generated constructor stub
	}
	
	public void requestRefund(String username, int transId)
	{
		refm.handleRefund(username,transId);
	}
	
	public Vector<Service> search(Vector<Service> allServices,String name){
		return serviceManager.Search(allServices, name);
		
	}
	
	public void notifications() {
		result = notificationManager.getNotifications(this.getUsername());
				
	}
	
	public ResultSet readNotification() {
		return result;
	}

}

