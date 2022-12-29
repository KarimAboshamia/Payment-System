package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceModel {

	private Connection connection;
	private static ServiceModel db = new ServiceModel();
	private Statement statement;
	

	private ServiceModel() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db.db");
			statement = connection.createStatement();

		}catch(SQLException e) {
	        System.out.println(e);
	
				
		}
	}
	
	public static ServiceModel getDB() {
		return db;
	}
	public ResultSet GetServices(){
		String nameQuery="select * from Services"; 
		ResultSet rs=null;
		try {
			 rs=statement.executeQuery(nameQuery);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}

}
