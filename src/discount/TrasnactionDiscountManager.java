package discount;
import db.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.User;
public class TrasnactionDiscountManager {
	DBConnection obj = DBConnection.getDB();
	public Discount calcDiscount(Discount discount ,User user) throws SQLException
	{
		ResultSet userTrans = obj.getTransactions(user.getUsername());
		int counter = 0;
		while(userTrans.next()) {
			counter++;
			System.out.println("Inside");
		}
		userTrans.close();
		ResultSet overall= obj.calcTransactionDiscount();
		if(counter == 0)
		{
			while(overall.next())
			{
				discount = new ServiceDiscount(discount, Float.parseFloat(overall.getString("DiscountRatio")));
			}
		}
		return discount;
	}
}
