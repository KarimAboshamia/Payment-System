package dicount;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import db.*;

public class ServiceDisountManager {
	DBConnection dbobj;
	public Discount calcDiscount(Discount discount) throws SQLException
	{
		ResultSet overall= dbobj.calcTransactionDiscount();
		
			while(overall.next())
			{
				discount=new ServiceDiscount(discount);
				discount.getDiscount(Float.parseFloat(overall.getString("DiscountRatio")));
			}
		return discount;
	}
}
