package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import managers.BalanceManagerInterface;
import managers.CreditCardManager;
import managers.DeliveryAtHomeManager;
import managers.NotificationManager;
import managers.RefundManager;
import managers.ServiceManager;
import managers.WalletBalanceManager;
import services.Service;

public class User extends AppUser{
	int walletBalance;
	RefundManager refm = new RefundManager();
	ServiceManager serviceManager  = new ServiceManager();
	NotificationManager notificationManager = new NotificationManager();
	BalanceManagerInterface balanceManagerInterface = new WalletBalanceManager();
	CreditCardManager creditManager = new CreditCardManager();
	ResultSet result;
	
	public User(String email, String username, String password, String permission, int Balance) {
		super(email, username, password, permission);
		walletBalance = Balance;
	}
	
	public void requestRefund(String transId) throws SQLException
	{
		refm.handleRefund(this.getUsername(), transId);
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
	
	public void markRead(String refundID) {
		notificationManager.markRead(refundID);
		
	}
	
	public void setWalletBalance(int wallet) {
		walletBalance = wallet;
		
	}
	public int getBalance() {
		return walletBalance;
	}
	
	public String setBalance(String cardNumber, int pin, int balance) {
		//call balance manager if credit card manager returned true
		if(creditManager.checkCreditCardDetails(cardNumber, pin)) {
			walletBalance = creditManager.setBalance(balance, this.getUsername());
			return "Successfully Charged";
		}
		return "Wrong card Number";
	}
	
	public String consumeBalance(float amount, String serviceName, int paymentMethod, String cardNumber, int pin) {
		
		//Based on payment method call one of the three options
		BalanceManagerInterface balanceManagerInterface = null;
		if(paymentMethod == 0) {
			balanceManagerInterface = new WalletBalanceManager();
		}
		
		if(paymentMethod == 1) {
			//Call Second Type
			balanceManagerInterface = new DeliveryAtHomeManager();
		}
		
		if(paymentMethod == 2) {
			//Call Third type
			balanceManagerInterface = new CreditCardManager();
		}
		System.out.println("Inside user" + amount);
		return balanceManagerInterface.consumeBalance(amount, this, serviceName, cardNumber, pin);

		
	}
	
}

