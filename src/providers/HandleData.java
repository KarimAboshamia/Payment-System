package providers;

import java.sql.SQLException;
import java.util.*;

import application.User;
import discount.Discount;
import discount.DiscountScenario;
import discount.OverDiscount;

public abstract class HandleData {
	public abstract String handleUserData(Map<String,String> inputFields, Map<String, String> dropFields, User user, String serviceName, int paymentMethod);
	public float calcDiscount(User user, String serviceName) throws SQLException {
		Discount manager = new OverDiscount();
		DiscountScenario discountCalcObj = new DiscountScenario();
		discountCalcObj.calcOverallDiscount(manager, user, serviceName);
		float overAllDiscount = manager.getDiscount();
		return overAllDiscount;
	}
	
}
