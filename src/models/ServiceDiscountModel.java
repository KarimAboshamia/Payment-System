package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceDiscountModel {
	private Statement statement;
	

	public ServiceDiscountModel(Statement statement) {
		this.statement = statement;
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
