package managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.DBConnection;

public class UsersManager {
	private DBConnection usersObj = DBConnection.getDB();
	
	public Map<String, Map<String, String>> getSystemUsers() throws SQLException {
		Map<String, Map<String, String>> systemData = new HashMap<>();
		ResultSet users = usersObj.getSystemUsers();
    	int counter = 1;
    	if(users != null) {
	    	while(users.next()) {
	    		Map<String, String> data = new HashMap<>();
	    		data.put("username: " , users.getString("Username"));
	
	    		systemData.put("User: " + counter, data);
	    		counter++;
	    		
	    	}
    	}
    	return systemData;
		
	}

}
