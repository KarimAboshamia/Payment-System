package discount;

import java.sql.SQLException;

import application.User;
import managers.ServiceDiscountManager;
import managers.TrasnactionDiscountManager;

public class DiscountScenario {
	Discount obj;
	TrasnactionDiscountManager objT =new TrasnactionDiscountManager();
	ServiceDiscountManager objS = new ServiceDiscountManager();
	public Discount calcOverallDiscount(Discount obj, User user, String name) throws SQLException
	{
		obj=objT.calcDiscount(obj, user);
		obj=objS.calcDiscount(obj, name);
		return obj;
		
	}
}
