package db;
//import discount.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//import services.Service;


public class DBConnection {
	private Connection connection;
	private static DBConnection db = new DBConnection();
	private Statement statement;
	

	private DBConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db.db");
			statement = connection.createStatement();

		}catch(SQLException e) {
	        System.out.println(e);
	
				
		}
	}
	
	public static DBConnection getDB() {
		return db;
	}
	
	public Statement getDBConnection() {
		return statement;
	}
}
