package discount;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import db.*;

public class ServiceDisountManager {
	DBConnection dbobj = DBConnection.getDB();
	public Discount calcDiscount(Discount discount) throws SQLException
	{
		ResultSet overall= dbobj.calcServiceDiscount();
		
		while(overall.next())
		{
			discount = new ServiceDiscount(discount, Float.parseFloat(overall.getString("DiscountRatio")));
		}
		return discount;
	}
}
