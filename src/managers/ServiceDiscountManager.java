package managers;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import db.*;
import discount.Discount;
import discount.ServiceDiscount;

public class ServiceDiscountManager {
	DBConnection dbobj = DBConnection.getDB();
	public Discount calcDiscount(Discount discount, String name) throws SQLException
	{
		ResultSet overall= dbobj.calcServiceDiscount();
		while(overall.next())
		{
			System.out.println(overall.getString("Service"));
			if(name.toLowerCase().contains(overall.getString("Service").toLowerCase())) {
				System.out.println("Hereeee");
				discount = new ServiceDiscount(discount, Float.parseFloat(overall.getString("DiscountRatio")));
			}
		}
		return discount;
	}
	
	public void setTransDiscount(float ratio) {
		dbobj.setTransactionDiscount(ratio);
	}
	
	public void setServiceDiscount(float ratio, String name) {
		dbobj.setServiceDiscount(ratio, name);
	}
}
