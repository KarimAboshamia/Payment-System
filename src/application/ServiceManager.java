package application;
import java.util.*;
import services.Service;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ServiceManager {
	private DBConnection createObj = new DBConnection();
	Vector<Service> allServices = new Vector<>();
	public void CreateSystemServices() {
		ResultSet rs=createObj.GetServices();
		try {
			while(rs.next()) {
				Service temp=new Service(rs.getString("Name"));
				allServices.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
