package managers;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.User;
import discount.Discount;
import discount.ServiceDiscount;
import models.*;

public class ServiceDiscountManager {
	TransDiscountModel transDiscObject = TransDiscountModel.getDB();
	ServiceDiscountModel serviceDiscObj = ServiceDiscountModel.getDB();
	public Discount calcDiscount(Discount discount, String name) throws SQLException
	{
		ResultSet overall= serviceDiscObj.calcServiceDiscount();
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
		transDiscObject.setTransactionDiscount(ratio);
	}
	
	public void setServiceDiscount(float ratio, String name) {
		serviceDiscObj.setServiceDiscount(ratio, name);
	}
}
