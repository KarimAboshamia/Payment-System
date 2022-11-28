package services;
import java.util.*;

public class Search {

	public Search() {
		// TODO Auto-generated constructor stub
	}
	public Vector<Service> search(Vector<Service> allServices,String name){
		Vector<Service> wantedServices=new Vector<>();

		Iterator it=allServices.iterator();
		for (int i = 0; i < allServices.size(); i++)
        {
			if(allServices.get(i).getName().contains(name)) {
				wantedServices.add(allServices.get(i));
			}
        }
		return wantedServices;
		
	}

}
