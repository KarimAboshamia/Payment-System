package services;
import providers.*;

public class ServiceCreator {

	public ServiceCreator() {
		// TODO Auto-generated constructor stub
	}
	public Service createService(String name) {
		// TODO Auto-generated method stub

		if(name.equals("Mobile-Service")) {
			Service mobileservice=new MobileService(name,false);
			
			//add provider with its wanted handler
			Provider Vodafone=new MobileServiceProvider("Vodafone", new HandleVodafoneData());
			Provider Etisalat=new MobileServiceProvider("Etisalat", new HandleEtisalatData());
			Provider Orange=new MobileServiceProvider("Orange", new HandleOrangeData());
			Provider We=new MobileServiceProvider("We", new HandleWeData());
			
			mobileservice.addProvider(Vodafone);
			mobileservice.addProvider(Etisalat);
			mobileservice.addProvider(Orange);
			mobileservice.addProvider(We);
			
			return mobileservice;
		}
		else if(name.equals("LandLine-Service")) {
			Service landlineservice=new LandLineService(name,false);
			Provider MonthlyLandLine=new LandLineProvider("Monthly", new HandleMonthlyReceiptData());
			Provider QuarterLandLine=new LandLineProvider("Monthly", new HandleQuarterReceiptData());
			
			landlineservice.addProvider(QuarterLandLine);
			landlineservice.addProvider(MonthlyLandLine);
			
			return landlineservice;
		}
		else if(name.equals("InternetPayment-Service")) {
			Service internetpaymentservice=new InternetPaymentService(name,false);
			Provider Vodafone=new LandLineServiceProvidor("Vodafone", new HandleInternetData());
			Provider Etisalat=new LandLineServiceProvidor("Etisalat", new HandleInternetData());
			Provider Orange=new LandLineServiceProvidor("Orange", new HandleInternetData());
			Provider We=new LandLineServiceProvidor("We", new HandleInternetData());
			
			internetpaymentservice.addProvider(Vodafone);
			internetpaymentservice.addProvider(Etisalat);
			internetpaymentservice.addProvider(Orange);
			internetpaymentservice.addProvider(We);
			return internetpaymentservice;
		}
		else if(name.equals("Donation-Service")) {
			Service donationservice=new DonationService(name,false);
			Provider cancerHospital=new DonationsProvider("Hospital",new HandleCancerDonationData());
			Provider schools=new DonationsProvider("Hospital",new HandleSchoolDonationData());
			Provider NPO=new DonationsProvider("Hospital",new HandleNPOData());

			return donationservice;
		}
		return null;
		
	}

}
