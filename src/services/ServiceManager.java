package services;
import java.util.*;

import db.DBConnection;

import java.sql.SQLException;
import java.sql.ResultSet;

public class ServiceManager {
	private DBConnection createObj = DBConnection.getDB();
	Vector<Service> allServices = new Vector<>();
	private ServiceCreator factory=new ServiceCreator();
	
	public Vector<Service> CreateSystemServices() {
		ResultSet rs=createObj.GetServices();
		try {
			while(rs.next()) {
				Service temp=factory.createService(rs.getString("Name"));
				allServices.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
