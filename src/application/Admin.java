package application;

import java.sql.ResultSet;

import db.DBConnection;
import refund.RefundManager;

public class Admin extends AppUser{
	RefundManager rman = new RefundManager();

	public Admin(String email, String username, String password, String permission) {
		super(email, username, password, permission);
		// TODO Auto-generated constructor stub
	}
	
	public ResultSet listRefunds(){
		ResultSet res = rman.getRef();
		return res;
	}
	
	public void changeState(String newState, String refundID)
	{
		rman.setNewRefundState(newState, refundID);
	}
	
	public void addDiscountOverAll (/*dicount ratio*/)
	{
		
	}
	public void addDiscountSpecific ()
	{
		
	}

}

