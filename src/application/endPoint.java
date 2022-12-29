package application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class endPoint {
	
	@PostMapping(value="/print")
	@ResponseBody
	public String test(@RequestBody Human obj) {
		System.out.println(obj.getHumanName());
		return "sent";
	}

}
