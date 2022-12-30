package managers;

import java.sql.ResultSet;

import db.DBConnection;
import models.RefundTransactionModel;
import models.RefundModel;

public class NotificationManager {
	private DBConnection connection = DBConnection.getDB();

	RefundModel refundObject =  new RefundModel(connection.getDBConnection());
	//Set changed to 0 if user marked as read functionRead()
	public void markRead(String refundID) {
		refundObject.setChangedRefundColumn(refundID);
	}
	
	//Get all refund requests that match username with changed = 1 and notify user using observable
	public ResultSet getNotifications(String callerName) {
		return refundObject.searchForChangedStates(callerName);
	}
	
}
