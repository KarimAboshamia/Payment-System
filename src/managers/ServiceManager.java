package managers;
import java.util.*;

import services.Service;
import services.ServiceCreator;



public class ServiceManager {
	Vector<Service> allServices = new Vector<>();
	private ServiceCreator factory=new ServiceCreator();
	
	public Vector<Service> CreateSystemServices() {
		
		Service temp=factory.createService("Mobile-Service");
		Service temp2=factory.createService("LandLine-Service");
		Service temp3=factory.createService("InternetPayment-Service");
		Service temp4=factory.createService("Donation-Service");
	
		allServices.add(temp);
		allServices.add(temp2);
		allServices.add(temp3);
		allServices.add(temp4);
			
	
		return allServices;
	}
	//search for service function
	public Vector<Service> Search(Vector<Service> allServices, String serviceName){
		Vector<Service> wantedServices=new Vector<>();
		for (int i = 0; i < allServices.size(); i++)
        {
					
			if(allServices.get(i).getName().toLowerCase().contains(serviceName.toLowerCase())) {
				wantedServices.add(allServices.get(i));
			}
        }
		
		return wantedServices;
		
	}
}
