package communication;

import application.AppUser;
import providers.Provider;
import services.Service;

public class DataCommunicator {
	private static DataCommunicator communicator = new DataCommunicator();
	AppUser user;
	Service service;
	Provider provider;
	
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
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public Service getService() {
		return service;
	}
	
	public void setProvider(Provider provider) {
		 this.provider = provider;
	}
	
	public Provider getProvider() {
		return provider;
	}

	
}
