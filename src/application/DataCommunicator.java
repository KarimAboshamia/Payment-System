package application;

public class DataCommunicator {
	private static DataCommunicator communicator = new DataCommunicator();
	AppUser user;
	
	private DataCommunicator() {};
	
	public static DataCommunicator getCommunicator() {
		return communicator;
	}
	
	public void setUser(AppUser user) {
		this.user = user;
	}
	
	public AppUser getUser() {
		return user;
	}

	
}
