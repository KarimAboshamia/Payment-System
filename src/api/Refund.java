package api;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.Admin;
import application.User;
import communication.DataCommunicator;
import db.DBConnection;
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
	@PostMapping(value="/requestRefund")
	@ResponseBody
	public String setRefundReq(@RequestParam String transactionID,@RequestParam String Token)
	{
		String msg = null;
		Set<String> set = new HashSet<String> (); 
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
	
	@GetMapping(value="/viewRefundsList")
	@ResponseBody
	public  HashMap<String, Map<String,String>> reviewRefunds(@RequestParam String Token) throws SQLException
	{
		HashMap<String, Map<String,String>> map= new HashMap<>();
		Admin user = (Admin) creator.createUser(Token);
		if(user.getPermission().equals("1"))
		{
			RefundManager obj=new RefundManager();
			ResultSet res = obj.getRef();
			int cnt=0;
			while(res.next()) 
			{
				Map<String,String>m=new HashMap<>();
				cnt++;
				m.put("ID", res.getString("RefundID"));
				m.put("Service", res.getString("Service"));
				m.put("Amount", res.getString("Amount"));
				map.put("service"+cnt, m);
			}
			if(cnt>0)
			{
				return map;
			}
			else
			{
				map.put("No refund requests found", null);
				return map;
			}
		}
		else
		{
			map.put("User cannot view the refund list", null);
			return map;
		}
		
	}
	
	@PostMapping(value="/changeRefundState")
	@ResponseBody
	public String changestate(@RequestParam String refundID,@RequestParam  String Token, String state)
	{
		Admin user = (Admin) creator.createUser(Token);
		String r;
		if(user.getPermission().equals("1"))
		{
			if(state=="accept")
			{
				try {
							user.changeState("1", refundID);
						} catch (SQLException e) {
							e.printStackTrace();
						}
				r="Request Accepted";
			}
			else
			{
								
						try {
							user.changeState("-1", refundID);
						} catch (SQLException e) {
							e.printStackTrace();
						}				
				r="Request Rejected";		
			}	
			return r;
		}
		else
		{
			return "User Cannot modify the refund state";
		}
	}

	}

