package services;

import java.util.Vector;

import providers.Provider;

public abstract class Service {
	private String name;
	Vector<Provider> providers = new Vector<Provider>();
	private boolean cachOnDelivery;
	
	public Service(String n, boolean cachOnDelivery) {
		this.name=n;
		this.cachOnDelivery = cachOnDelivery;
	}
	
	public void addProvider(Provider provider) {
		providers.add(provider);
	}
	
	public String getName() {
		return this.name;
	}

	public boolean getCachOnDelivery() {
		return cachOnDelivery;
	}
}
