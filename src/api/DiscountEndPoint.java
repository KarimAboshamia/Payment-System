package api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.Admin;
import application.AppUser;
import application.User;

@RestController
public class DiscountEndPoint {
	
	UserCreator creation = new UserCreator();
	
	@PostMapping(value="/discount")
	@ResponseBody
	public  Map<String, String> discount(@RequestParam String token, @RequestParam String discountType, @RequestParam float discountRatio, @RequestParam String serviceName){
		HashMap<String, String> map = new HashMap<>();
		AppUser user = creation.createUser(token);
		if(user == null) {
			map.put("Error:", "Wrong token");
		}
		
		if(user.getPermission().equals("0")) {
			map.put("State", "Normal User can't add discount.");
		} else {
			Admin adminUser = (Admin)user;
			if(discountType.toLowerCase().equals("service")) {
				adminUser.addServiceDiscount(discountRatio, serviceName);
				map.put("State", "Service discount added successfully");
			} else {
				adminUser.addTransactionDiscount(discountRatio);
				map.put("State", "Transaction discount added successfully");
				
			}
		}
		return map;
	}
	

}
