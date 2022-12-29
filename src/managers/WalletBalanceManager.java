package managers;

import application.User;
import models.UserModel;

public class WalletBalanceManager extends BalanceManagerInterface {
	
    UserModel userObject = UserModel.getDB();

    @Override
    public String consumeMoney(User  user, float amount, String cardNumber, int pin, String serviceName) {
        int balance = Integer.parseInt(userObject.getBalance(user.getUsername()));
        if(balance >= amount) {
            balance -= amount;
            user.setWalletBalance(balance);
            userObject.setBalance(balance, user.getUsername());
            this.transaction(user.getUsername(), amount, serviceName);
            return "Successfully Charged";
        }
        return "No Sufficient Balance";
    }

}