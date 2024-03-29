package managers;

import application.User;

public class CreditCardManager extends BalanceManagerInterface{
    @Override
    public String consumeMoney(User user, float amount, String cardNumber, int pin, String serviceName) {
        if(checkCreditCardDetails(cardNumber, pin)) {
        	//Payment Transaction
            this.transaction(user.getUsername(), amount, serviceName);
            return "Charged Successfully from Credit Card";
        }
        return "Please enter 16 numbers";
    }

}