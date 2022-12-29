package managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import db.DBConnection;

public abstract class BalanceManagerInterface {
    
    DBConnection balanceObject = DBConnection.getDB();
    public int setBalance(int balance, String username) {
        balance += Integer.parseInt(balanceObject.getBalance(username));
        balanceObject.setBalance(balance, username);
        
        //TODO Add "Add to wallet transaction"
        balanceObject.addWalletTransaction(username, balance);
        return balance;
        
    }
    
    public int getBalance(String username) {
        return Integer.parseInt(balanceObject.getBalance(username));
    }
    

    public boolean checkCreditCardDetails(String cardNumber, int pin) {
        if(cardNumber.length() == 16) {
            return true;
        }
        return false;
    }
    
    //Start of transaction using form
    public String consumeBalance(float amount,  User user, String serviceName, String cardNumber, int pin) {
            return consumeMoney(user, amount, cardNumber, pin, serviceName);        
    }
    
    public abstract String consumeMoney(User user, float amount, String cardNumber, int pin, String serviceName);
    public void transaction(String userName, float amount, String serviceName) {
        
        balanceObject.insertTransaction(userName, amount, serviceName);
        
      
    }
    //End of transaction using form
    
    public ResultSet getTransactions(String username) throws SQLException {
    	return balanceObject.getTransactions(username);
    }
    
    /***************************/
    public void getSystemTransactions(String username) throws SQLException {
    	balanceObject.getTransactions(username);
    	balanceObject.getRefunds(username);
    	balanceObject.getWalletTransactions(username);
    }
    /*************************/
}