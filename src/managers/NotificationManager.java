package managers;

import java.sql.ResultSet;

import models.RefundTransactionModel;
import models.RefundModel;

public class NotificationManager {
	RefundModel refundObject =  RefundModel.getDB();
	//Set changed to 0 if user marked as read functionRead()
	public void markRead(String refundID) {
		refundObject.setChangedRefundColumn(refundID);
	}
	
	//Get all refund requests that match username with changed = 1 and notify user using observable
	public ResultSet getNotifications(String callerName) {
		return refundObject.searchForChangedStates(callerName);
	}
	
}
