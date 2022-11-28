package application;
import java.util.*;
import services.Service;
import java.sql.SQLException;
import java.sql.ResultSet;
import services.ServiceCreator;
import services.Search;

public class ServiceManager {
	private DBConnection createObj = new DBConnection();
	Vector<Service> allServices = new Vector<>();
	private ServiceCreator factory=new ServiceCreator();
	private Search s=new Search();
	
	public void CreateSystemServices() {
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
	}
	//search for service function
	public Vector<Service> Search(String serviceName){
		
		return s.search(allServices, serviceName);
		
	}
}
