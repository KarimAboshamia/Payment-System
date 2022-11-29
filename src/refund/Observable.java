package refund;

import application.User;

public interface Observable {
	
	
	public void subscribe(User user);
	public void notifyUser();

}
