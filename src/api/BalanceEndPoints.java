package api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.AppUser;
import application.User;

@RestController
public class BalanceEndPoints {
	
	UserCreator creation = new UserCreator();
	
	@PostMapping(value="/updateBalance")
	@ResponseBody
	public Map<String, String> updateBalance(@RequestParam String token,@RequestParam String card, @RequestParam int PIN, @RequestParam int newBalance){
		System.out.println(token);
		AppUser user = creation.createUser(token);
		HashMap<String, String> map = new HashMap<>();
		if(user.getPermission().equals("1")) {
			map.put("State", "Admin Doesn't have a balance");
		} else {
			User normalUser = (User)user;
			String res = normalUser.setBalance(card, PIN, newBalance);
			map.put("State", res);
		}
		 
		 
		return map;
	}

}
