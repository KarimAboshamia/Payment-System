package balance;

public class DeliveryAtHomeManager extends BalanceManagerInterface{

    @Override
    public String consumeMoney(String username, int amount, String cardNumber, int pin, String serviceName) {
        return "Object is being sent to home";
    }
    

}