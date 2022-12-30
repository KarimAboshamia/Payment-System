package managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import application.User;
import db.DBConnection;
import models.RefundTransactionModel;
import models.TransactionModel;
import models.UserModel;

public abstract class BalanceManagerInterface {
	private DBConnection connection = DBConnection.getDB();

    UserModel userObject = new UserModel(connection.getDBConnection());
    TransactionModel balanceObject = new TransactionModel(connection.getDBConnection());
    
    public int setBalance(int balance, String username) {

        balance += Integer.parseInt(userObject.getBalance(username));
        userObject.setBalance(balance, username);
        
        //TODO Add "Add to wallet transaction"
        balanceObject.addWalletTransaction(username, balance);
        return balance;
        
    }
    
    public int getBalance(String username) {
        return Integer.parseInt(userObject.getBalance(username));
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
    public Map<String, Map<String, String>> getSystemTransactions(String username) throws SQLException {
    	Map<String, Map<String, String>> systemData = new HashMap<>();
    	ResultSet transactions = balanceObject.getTransactions(username);
    	int counter = 1;
    	if(transactions != null) {
	    	while(transactions.next()) {
	    		Map<String, String> data = new HashMap<>();
	    		data.put("Transaction ID: " , transactions.getString("TransactionID"));
	    		data.put("Service: " , transactions.getString("Service"));
	    		data.put("Amount: " , transactions.getString("Amount"));
	
	    		systemData.put("Payment transaction: " + counter, data);
	    		counter++;
	    		
	    	}
    	}

		ResultSet refundTrans = balanceObject.getRefunds(username);
    	if(refundTrans != null) {
			while(refundTrans.next()) {
				Map<String, String> data = new HashMap<>();
				data.put("Refund ID: " , refundTrans.getString("RefundID"));
				data.put("State: " , refundTrans.getString("State"));
				data.put("Transaction ID: " , refundTrans.getString("TransactionID"));
			
				systemData.put("Refund transaction: " + counter, data);
				counter++;
				
			}
    	}
    	
    	
    	ResultSet walletTrans = balanceObject.getWalletTransactions(username);
    	if(walletTrans != null) {
	    	while(walletTrans.next()) {
	    		Map<String, String> data = new HashMap<>();
	    		data.put("Credit ID: " , walletTrans.getString("ID"));
	    		data.put("Amount: " , refundTrans.getString("Amount"));
	
	    		systemData.put("Add to Wallet transaction: " + counter, data);
	    		counter++;
	    		
	    	}
    	}
        return systemData;

    }    
    /*************************/
}