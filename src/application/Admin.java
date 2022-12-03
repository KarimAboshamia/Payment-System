package application;

import java.sql.ResultSet;

import discount.ServiceDiscountManager;
import refund.RefundManager;

public class Admin extends AppUser{
	RefundManager refundManager = new RefundManager();
	ServiceDiscountManager discountManager = new ServiceDiscountManager();
	
	public Admin(String email, String username, String password, String permission) {
		super(email, username, password, permission);
		// TODO Auto-generated constructor stub
	}
	
	public ResultSet listRefunds(){
		ResultSet res = refundManager.getRef();
		return res;
	}
	
	public void changeState(String newState, String refundID)
	{
		refundManager.setNewRefundState(newState, refundID);
	}
	
	public void addTransactionDiscount (float discountRatio)
	{
		discountManager.setTransDiscount(discountRatio);
		
	}
	public void addOverallDiscount (float discountRatio)
	{
		discountManager.setServiceDiscount(discountRatio);
	}

}

