package balance;

public class CreditCardManager {
	
	//Thin class that can have extra credit card logic if needed later
	public boolean checkCreditDetails(String number, int pin) {
		System.out.println("Number "+ number.length());
		if(number.length() > 16) { return true;};
		return false;
	}

}
