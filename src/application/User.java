package application;

import java.util.Iterator;
import java.util.Vector;

import services.Service;

public class User extends AppUser{
	RefundManager refm = new RefundManager();
	ServiceManager serviceManager  = new ServiceManager();
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

}

