package api;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.Admin;
import application.User;
import communication.DataCommunicator;
//import db.DBConnection;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import managers.RefundManager;
import scenes.ChangeScenes;

@RestController

public class Refund {
	RefundManager manager = new RefundManager(); 
	UserCreator creator = new UserCreator();
	Set<String> set = new HashSet<String> (); 
	@PostMapping(value="/requestRefund")
	@ResponseBody
	public String setRefundReq(@RequestParam String transactionID,@RequestParam String Token)
	{
		String msg = null;
		
		User user = (User) creator.createUser(Token);
		try {
			if(set.contains(transactionID)) {
				msg= "Request Already Sent";							
			} else {
				user.requestRefund(transactionID);
				set.add(transactionID);
				msg= "Request sent successfully";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	/*public String reviewRefunds (@RequestParam String Token) throws IOException, SQLException
	{
		Admin admin;
		DataCommunicator communicator = DataCommunicator.getCommunicator();
		admin = (Admin) communicator.getUser();
		DBConnection db = null;
		User user = (User) creator.createUser(Token);
		ResultSet res = db.getTransactions(user.getUsername());
		int counter = 0;
		String r = null;
		while(res.next()) {
			if(state=="accept")
			{
				try {
							admin.changeState("1", refundId);
						} catch (SQLException e) {
							e.printStackTrace();
						}
				r="Request Accepted";
			}
			else
			{
								
						try {
							admin.changeState("-1", refundId);
						} catch (SQLException e) {
							e.printStackTrace();
						}				
				r="Request Rejected";		
			}		
				counter++;
		}
		
		if(counter == 0) {
			return "No refund requests";
		}
		else
		{
			return r;
		}
	}*/
	}