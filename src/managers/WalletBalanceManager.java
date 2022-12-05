package managers;

import application.User;

public class WalletBalanceManager extends BalanceManagerInterface {

    @Override
    public String consumeMoney(User  user, float amount, String cardNumber, int pin, String serviceName) {
        int balance = Integer.parseInt(balanceObject.getBalance(user.getUsername()));
        if(balance >= amount) {
            balance -= amount;
            user.setWalletBalance(balance);
            balanceObject.setBalance(balance, user.getUsername());
            this.transaction(user.getUsername(), amount, serviceName);
            return "Successfully Charged";
        }
        return "No Sufficient Balance";
    }

}