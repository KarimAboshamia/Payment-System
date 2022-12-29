package api;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.User;
import discount.Discount;
import discount.DiscountScenario;
import discount.OverDiscount;

@RestController
public class ServiceDiscount {

	Discount s = new OverDiscount();
	DiscountScenario discountCalcObj = new DiscountScenario();
	UserCreator creation = new UserCreator();

	@PostMapping(value="/serviceDiscount")
	@ResponseBody
	public Discount serviceDiscount(@RequestParam String name,@RequestParam String token){
		User user = (User) creation.createUser(token);
		try {
			s = discountCalcObj.calcOverallDiscount(new OverDiscount(), user, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
