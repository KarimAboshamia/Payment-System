package managers;
import discount.Discount;
import discount.ServiceDiscount;
import models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import application.User;
import db.DBConnection;
public class TrasnactionDiscountManager {
	private DBConnection connection = DBConnection.getDB();

	RefundTransactionModel obj = new RefundTransactionModel(connection.getDBConnection());
	TransDiscountModel transDiscObject = new TransDiscountModel(connection.getDBConnection());
	TransactionModel transObject = new TransactionModel(connection.getDBConnection());
	
	public Discount calcDiscount(Discount discount ,User user) throws SQLException
	{
		ResultSet userTrans = transObject.getTransactions(user.getUsername());
		int counter = 0;
		while(userTrans.next()) {
			counter++;
			System.out.println("Inside");
		}
		userTrans.close();
		ResultSet overall= transDiscObject.calcTransactionDiscount();
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
