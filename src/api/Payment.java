package api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.AppUser;
import managers.ServiceManager;
import providers.Provider;
import services.Service;

@RestController
public class Payment {
	UserCreator creation = new UserCreator();
	ServiceManager serviceManager = new ServiceManager();
	
	
	@GetMapping(value="/getProviders")
	@ResponseBody
	public Map<String, Provider> getProviders(@RequestParam String serviceName){
		HashMap<String, Provider> map = new HashMap<>();
	
		//Create all system services, return all providers of the provided service
		
		
		Vector<Service> systemServices = serviceManager.CreateSystemServices();
		for(int i = 0; i < systemServices.size(); i++) {
			System.out.println(serviceName);
			if(systemServices.get(i).getName().toLowerCase().equals(serviceName.toLowerCase())) {
				for(int j = 0; j < systemServices.get(i).getProviders().size(); j++) {
					map.put(systemServices.get(i).getProviders().get(j).getProviderName(), systemServices.get(i).getProviders().get(j));					
				}
			}
			return map;

		}
		map.put("Provider", null);
		return map;
		
	}
	
	@GetMapping(value="/getProviderFormData")
	@ResponseBody
	public  Map<String, Map<String, List<String>>> getProviderFormData(@RequestParam String providerName, @RequestParam String serviceName){
		HashMap<String, Map<String, List<String>>> map = new HashMap<>();
		Vector<Service> systemServices = serviceManager.CreateSystemServices();
		for(int i = 0; i < systemServices.size(); i++) {
			if(systemServices.get(i).getName().toLowerCase().equals(serviceName.toLowerCase())) {
				for(int j = 0; j < systemServices.get(i).getProviders().size(); j++) {
					if(systemServices.get(i).getProviders().get(j).getProviderName().toLowerCase().equals(providerName.toLowerCase())) {
						systemServices.get(i).getProviders().get(j).getTextFieldData();
						HashMap<String, List<String>> tmp = new HashMap<>();
						List tmpList = Collections.list(systemServices.get(i).getProviders().get(j).getTextFieldData().elements());
						tmp.put("TextFields", tmpList);
						map.put("DropDown", systemServices.get(i).getProviders().get(j).getDropDownData());
						map.put("TextFields", tmp);
						return map;

					}
				}
				
			}
		}

		map.put("Wrong input", null);
		return map;
	}
	
	@PostMapping(value="/payForProvider")
	@ResponseBody
	public  Map<String, String> payForProvider(@RequestParam String providerName, @RequestParam String serviceName){
		HashMap<String, String> map = new HashMap<>();

		return null;
	}

}
