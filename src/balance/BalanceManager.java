package balance;

import db.DBConnection;

public class BalanceManager {
	
	DBConnection balanceObject = DBConnection.getDB();
	public int setBalance(int balance, String username) {
		System.out.println("Hereeeeeee");
		balance += Integer.parseInt(balanceObject.getBalance(username));
		balanceObject.setBalance(balance, username);
		return balance;
		
	}
	
	public int getBalance(String username) {
		return Integer.parseInt(balanceObject.getBalance(username));
	}

}
