package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.Admin;
import application.User;
import managers.BalanceManagerInterface;
import managers.WalletBalanceManager;

@RestController
public class TransactionsEndPoint {
	UserCreator creator = new UserCreator();
	BalanceManagerInterface manager = new WalletBalanceManager();

	@GetMapping(value="/userTransactions")
	@ResponseBody
	public Map<String, Map<String, String>> getTransactions(@RequestParam String token) throws SQLException{
		Map<String, Map<String, String>> systemData = new HashMap<>();
		User user = (User) creator.createUser(token);
		
		if(user == null)  {
			systemData.put("Wrong Token", null);
			return systemData;
		}
			
		
		if(user.getPermission().equals("1")) {
			systemData.put("Admin Doesn't have access to this end point", null);
			return systemData;
			
		} else {
			ResultSet res = manager.getTransactions(user.getUsername());
			int counter = 1;
			while(res.next()) {
				Map<String, String> data = new HashMap<>();
				data.put("Transaction ID: ", res.getString("TransactionID"));
				data.put("Service Name: " , res.getString("Service"));
				data.put("Amount: ", res.getString("Amount"));
				systemData.put("Transaction " + counter, data);
				counter++;
				
			}
			return systemData;
			
		}
	}

	@GetMapping(value="/systemUsers")
	@ResponseBody
	public Map<String, Map<String, String>> getUsers(@RequestParam String token) throws SQLException {
		Map<String, Map<String, String>> systemData = new HashMap<>();
		Admin user = (Admin) creator.createUser(token);
		if(user == null)  {
			systemData.put("Wrong Token", null);
			return systemData;
		}
		
		
		if(user.getPermission().equals("0")) {
			systemData.put("Normal Users can't list system users", null);
			return systemData;
		} else {
			return user.systemUsers();
		}
			
	}
	
	
	
	@GetMapping(value="/systemTransactions")
	@ResponseBody
	public Map<String, Map<String, String>> getSystemTransactions(@RequestParam String token, @RequestParam String username) throws SQLException{
		Map<String, Map<String, String>> systemData = new HashMap<>();
		Admin user = (Admin) creator.createUser(token);
		if(user == null)  {
			systemData.put("Wrong Token", null);
			return systemData;
		}
		
		
		if(user.getPermission().equals("0")) {
			systemData.put("Normal Users can't list system transactions", null);
			return systemData;
		} else {
			return manager.getSystemTransactions(username);
		}
			
	}
}

