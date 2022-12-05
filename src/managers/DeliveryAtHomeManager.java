package managers;

import application.User;

public class DeliveryAtHomeManager extends BalanceManagerInterface{

    @Override
    public String consumeMoney(User user, float amount, String cardNumber, int pin, String serviceName) {
        return "Object is being sent to home";
    }
    

}