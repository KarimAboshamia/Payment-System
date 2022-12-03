package dicount;
import db.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.User;
public class TrasnactionDiscountManager {
	DBConnection obj;
	public Discount calcDiscount(Discount discount ,User user) throws SQLException
	{
		ResultSet overall= obj.calcTransactionDiscount();
		ResultSet userTrans = obj.getTransactions();
		if(userTrans==null)
		{
			while(overall.next())
			{
				discount=new TransactionDiscount(discount);
				discount.getDiscount(Float.parseFloat(overall.getString("DiscountRatio")));
			}
		}
		return discount;
	}
}
