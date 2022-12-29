package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransDiscountModel {

	private Connection connection;
	private static TransDiscountModel db = new TransDiscountModel();
	private Statement statement;
	

	private TransDiscountModel() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db.db");
			statement = connection.createStatement();

		}catch(SQLException e) {
	        System.out.println(e);
	
				
		}
	}
	
	public static TransDiscountModel getDB() {
		return db;
	}

	public ResultSet calcTransactionDiscount() throws SQLException
	{
		String nquery = "select * from TransactionDiscount;";
		ResultSet res = statement.executeQuery(nquery);
		return res;
	}

	public void setTransactionDiscount(float ratio) {
		String newQuery= "insert into TransactionDiscount (DiscountRatio) values ('" + ratio+ "')";
		try {
			statement.executeUpdate(newQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
