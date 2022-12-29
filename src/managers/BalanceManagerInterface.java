package managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import models.RefundTransactionModel;
import models.TransactionModel;
import models.UserModel;

public abstract class BalanceManagerInterface {
    
    UserModel userObject = UserModel.getDB();
    TransactionModel balanceObject = TransactionModel.getDB();
    
    public int setBalance(int balance, String username) {
        balance += Integer.parseInt(userObject.getBalance(username));
        userObject.setBalance(balance, username);
        return balance;
        
    }
    
    public int getBalance(String username) {
        return Integer.parseInt(userObject.getBalance(username));
    }
    

    public boolean checkCredit(String cardNumber, int pin) {
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
}