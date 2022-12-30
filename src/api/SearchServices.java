package api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import managers.ServiceManager;
import services.Service;

import java.util.*;

@RestController
public class SearchServices{

	ServiceManager serviceManager = new ServiceManager();
	Vector<Service> systemServices = serviceManager.CreateSystemServices();

	@GetMapping(value="/searchServices")
	@ResponseBody
	public Vector<Service> search(@RequestParam String name){
		Vector<Service> foundservices = serviceManager.Search(systemServices,name);
		return foundservices;
	}

}
