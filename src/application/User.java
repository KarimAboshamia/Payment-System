package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import balance.BalanceManager;
import balance.CreditCardManager;
import refund.NotificationManager;
import refund.RefundManager;
import services.Service;
import services.ServiceManager;

public class User extends AppUser{
	int walletBalance;
	RefundManager refm = new RefundManager();
	ServiceManager serviceManager  = new ServiceManager();
	NotificationManager notificationManager = new NotificationManager();
	BalanceManager balanceManager = new BalanceManager();
	CreditCardManager creditManager = new CreditCardManager();
	ResultSet result;
	
	public User(String email, String username, String password, String permission, int Balance) {
		super(email, username, password, permission);
		walletBalance = Balance;
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
	
	public int getBalance() {
		return walletBalance;
	}
	
	public boolean setBalance(String cardNumber, int pin, int balance) {
		//call balance manager if credit card manager returned true
		if(creditManager.checkCreditDetails(cardNumber, pin)) {
			System.out.println("Inside user");
			walletBalance = balanceManager.setBalance(balance, this.getUsername());
			return true;
		}
		else {
			return false;
		}
	}

}

