package application;

import java.sql.ResultSet;

public class NotificationsObservable implements Observable {
	User currentUser;
	NotificationManager notification = new NotificationManager();
	
	public void subscribe(User user) {
		currentUser = user;
		ResultSet res = notification.getNotifications(currentUser.getUsername());
		if(res != null) {
			currentUser.notifications();
		}
	}
	
	public void notifyUser() {
		ResultSet res = notification.getNotifications(currentUser.getUsername());
		if(res != null) {
			currentUser.notifications();
		}
	}

}
