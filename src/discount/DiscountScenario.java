package discount;

import java.sql.SQLException;

import application.User;

public class DiscountScenario {
	Discount obj;
	TrasnactionDiscountManager objT =new TrasnactionDiscountManager();
	ServiceDisountManager objS = new ServiceDisountManager();
	public Discount calcOverallDiscount(Discount obj, User user) throws SQLException
	{
		obj=objT.calcDiscount(obj, user);
		obj=objS.calcDiscount(obj);
		return obj;
		
	}
}
