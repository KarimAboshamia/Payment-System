package balance;

public class CreditCardManager extends BalanceManagerInterface{
    @Override
    public String consumeMoney(String username, int amount, String cardNumber, int pin, String serviceName) {
        if(checkCredit(cardNumber, pin)) {
            return "Charged Successfully from Credit Card";
        }
        return "Please enter 16 numbers";
    }

}