package balance;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public abstract class BalanceManagerInterface {
    
    DBConnection balanceObject = DBConnection.getDB();
    public int setBalance(int balance, String username) {
        balance += Integer.parseInt(balanceObject.getBalance(username));
        balanceObject.setBalance(balance, username);
        return balance;
        
    }
    
    public int getBalance(String username) {
        return Integer.parseInt(balanceObject.getBalance(username));
    }
    

    public boolean checkCredit(String cardNumber, int pin) {
        if(cardNumber.length() == 16) {
            return true;
        }
        return false;
    }
    public String consumeBalance(int amount, String username, String serviceName, String cardNumber, int pin) {
            //Template steps
            //1.Check payment method - if balance consume from balance - if credit card check credit details - if at home add delivery at home
            //2. transaction shared functionality
            return consumeMoney(username, amount, cardNumber, pin, serviceName);
            //Consume money calls transaction if succeeded 
        
    }
    
    public abstract String consumeMoney(String username, int amount, String cardNumber, int pin, String serviceName);
    public void transaction(String userName, int amount, String serviceName) {
        
        balanceObject.insertTransaction(userName, amount, serviceName);
        
      
    }
    
    public ResultSet getTransactions(String username) throws SQLException {
    	return balanceObject.getTransactions(username);
    }
}