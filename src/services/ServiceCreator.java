package services;

public class ServiceCreator {

	public ServiceCreator() {
		// TODO Auto-generated constructor stub
	}
	public Service createService(String name ) {
		// TODO Auto-generated method stub
		if(name=="Mobile-Service") {
			Service mobileservice=new MobileService(name);
			return mobileservice;
		}
		else if(name=="LandLine-Service") {
			Service landlineservice=new LandLineService(name);
			return landlineservice;
		}
		else if(name=="InternetPayment-Service") {
			Service internetpaymentservice=new InternetPaymentService(name);
			return internetpaymentservice;
		}
		else if(name=="Donation-Service") {
			Service donationservice=new DonationService(name);
			return donationservice;
		}
		return null;
		
	}

}