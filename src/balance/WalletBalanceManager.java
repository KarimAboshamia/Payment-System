package balance;

public class WalletBalanceManager extends BalanceManagerInterface {

    @Override
    public String consumeMoney(String username, int amount, String cardNumber, int pin, String serviceName) {
        int balance = Integer.parseInt(balanceObject.getBalance(username));
        if(balance >= amount) {
            System.out.println("User money charge " + amount);
            balance -= amount;
            balanceObject.setBalance(balance, username);
            this.transaction(username, amount, serviceName);
            return "Successfully Charged";
        }
        return "No Sufficient Balance";
    }

}