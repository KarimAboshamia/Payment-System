package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDiscountModel {

	private Connection connection;
	private static ServiceDiscountModel db = new ServiceDiscountModel();
	private Statement statement;
	

	private ServiceDiscountModel() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db.db");
			statement = connection.createStatement();

		}catch(SQLException e) {
	        System.out.println(e);
	
				
		}
	}
	
	public static ServiceDiscountModel getDB() {
		return db;
	}

	public ResultSet calcServiceDiscount() throws SQLException
	{
		String nquery = "select * from ServiceDiscount;";
		ResultSet res = statement.executeQuery(nquery);
		return res;
	}
	
	public void setServiceDiscount(float ratio, String name) {
		
		String newQuery= "insert into ServiceDiscount (DiscountRatio, Service) values ('" + ratio+ "'," + "'" + name + "')";
		try {
			statement.executeUpdate(newQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
		


}
