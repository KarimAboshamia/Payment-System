package api;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.Admin;
import application.AppUser;
import application.User;
import discount.Discount;
import discount.DiscountScenario;
import discount.OverDiscount;
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
	
		Service service = searchForService(serviceName);
		if(service != null) {
			for(int j = 0; j < service.getProviders().size(); j++) {
				map.put(service.getProviders().get(j).getProviderName(), service.getProviders().get(j));					
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
		Service service = searchForService(serviceName);
		if(service != null) {
			for(int j = 0; j < service.getProviders().size(); j++) {
				if(service.getProviders().get(j).getProviderName().toLowerCase().equals(providerName.toLowerCase())) {
					service.getProviders().get(j).getTextFieldData();
					HashMap<String, List<String>> tmp = new HashMap<>();
					List tmpList = Collections.list(service.getProviders().get(j).getTextFieldData().elements());
					tmp.put("TextFields", tmpList);
					map.put("DropDown", service.getProviders().get(j).getDropDownData());
					map.put("TextFields", tmp);
					return map;

				}
			}
				
		}
		map.put("Wrong input", null);
		return map;
	}

	
	@PostMapping(value="/payForProvider")
	@ResponseBody
	public String payForProvider(@RequestParam String token, @RequestParam String providerName, @RequestParam String serviceName, @RequestParam String paymentMethod, @RequestBody Map<String,String> inputFields, @RequestParam String activateDiscount) throws SQLException{
		HashMap<String, String> map = new HashMap<>();
		AppUser user = creation.createUser(token);
		
		User normalUser = null;
		boolean addDiscount = false;
		if(user == null) {
			String error = "Error: Wrong token";
			return error;
		}
		if(user.getPermission().equals("0")) {
			normalUser = (User)user;
		} else {
			
			String error = "State Admin can't pay for a provider.";
			return error;
		}
		
		if(activateDiscount.toLowerCase().equals("yes")) addDiscount = true;

		Service service = searchForService(serviceName);
		if(service != null) {
			for(int j = 0; j < service.getProviders().size(); j++) {
				if(service.getProviders().get(j).getProviderName().toLowerCase().equals(providerName.toLowerCase())) {
					int textSize = service.getProviders().get(j).getTextFieldData().size();
					if(service.getProviders().get(j).getDropDownData() != null) {
						int dropSize = service.getProviders().get(j).getDropDownData().size();
					}
					HashMap<String,String> textFieldsInput = new HashMap<>();
					HashMap<String,String> dropDownInput = new HashMap<>();
					for(int i = 0; i < inputFields.size(); i++) {
						String key = (String) inputFields.keySet().toArray()[i];
						String value = (String) inputFields.values().toArray()[i];
						
						if(i < service.getProviders().get(j).getTextFieldData().size()) textFieldsInput.put(key, value);
						else dropDownInput.put(key, value);
						
					}
					
					int paymentMapping = 0;
					if(paymentMethod.toLowerCase().equals("credit")) {
						textFieldsInput.put("Credit Details", inputFields.get("Credit Details"));
						textFieldsInput.put("PIN", inputFields.get("PIN"));
						paymentMapping = 2;
					} else if (paymentMethod.toLowerCase().equals("wallet")){
						paymentMapping = 0;
						
					} else if (paymentMethod.toLowerCase().equals("delivery")) {
						if(!service.getCachOnDelivery()) {
							String error = "You can't Pay for this service on Delivery";
							return error;
							
						}
						paymentMapping = 1;
						
					} else {
						String error = "Wrong Payment Method, Check Documentation for types";
						return error;
					}
					return service.getProviders().get(j).handleUserData(textFieldsInput, dropDownInput, normalUser, serviceName, paymentMapping, addDiscount);
				}
			}
		}
		return null;
		
	}

	public Service searchForService(String serviceName) {
		Vector<Service> systemServices = serviceManager.CreateSystemServices();
		for(int i = 0; i < systemServices.size(); i++) {
			if(systemServices.get(i).getName().toLowerCase().equals(serviceName.toLowerCase())) 
				return systemServices.get(i);
		}
		return null;
	}
}
