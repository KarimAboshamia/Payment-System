package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RefundModel {
	private Statement statement;
	

	public RefundModel(Statement statement) {
		this.statement = statement;
		
	}
	


	public void insertRefund(String usrname, String transId)
	{
		String newQuery= "insert into RefundReq (Username, State, TransactionID, Changed) values ('"+usrname+"', 0, '"+transId+"', 0);";
		try {
			statement.executeUpdate(newQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateState(String newState, String refundID)
	{
		String nquery = "UPDATE RefundReq "
				+ "SET State = '"+newState+"', Changed = 1 "
				+ "WHERE State=0 AND RefundID = '" + refundID + "';";
		
		try {
			statement.executeUpdate(nquery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public ResultSet searchForChangedStates(String callerName) {
		String query = "select * from RefundReq WHERE Username = '" +  callerName + "' AND Changed = 1";
		try {
			ResultSet result = statement.executeQuery(query);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	public void setChangedRefundColumn(String id) {
		String nquery = "UPDATE RefundReq "
				+ "SET Changed = 0 "
				+ "WHERE RefundID = '" + id + "';";
		
		try {
			statement.executeUpdate(nquery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
