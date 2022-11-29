package application;

import java.sql.ResultSet;

public class NotificationManager {
	DBConnection notificationObject = new DBConnection();
	//Set changed to 0 if user marked as read functionRead()
	public void markRead(String refundID) {
		notificationObject.setChangedRefundColumn(refundID);
	}
	
	//Get all refund requests that match username with changed = 1 and notify user using observable
	public ResultSet getNotifications(String callerName) {
		return notificationObject.searchForChangedStates(callerName);
	}
	
}
