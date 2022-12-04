package services;
import providers.*;

public class ServiceCreator {

	public ServiceCreator() {
	}
	public Service createService(String name) {

		if(name.equals("Mobile-Service")) {
			Service mobileservice=new MobileService(name,false);
			
			//add provider with its wanted handler
			Provider Vodafone=new MobileServiceProvider("Vodafone", new HandleVodafoneData());
			Provider Etisalat=new MobileServiceProvider("Etisalat", new HandleEtisalatData());
			Provider Orange=new MobileServiceProvider("Orange", new HandleOrangeData());
			Provider We=new MobileServiceProvider("We", new HandleWeData());
			
			UserData data = new ServiceUserData();
			
			Vodafone.setUserData(data);
			Etisalat.setUserData(data);
			Orange.setUserData(data);
			We.setUserData(data);
			
			mobileservice.addProvider(Vodafone);
			mobileservice.addProvider(Etisalat);
			mobileservice.addProvider(Orange);
			mobileservice.addProvider(We);
			
			return mobileservice;
		}
		else if(name.equals("LandLine-Service")) {
			Service landlineservice=new LandLineService(name,false);
			Provider MonthlyLandLine=new LandLineProvider("Monthly", new HandleMonthlyReceiptData());
			Provider QuarterLandLine=new LandLineProvider("Quarterly", new HandleQuarterReceiptData());
			
			landlineservice.addProvider(QuarterLandLine);
			landlineservice.addProvider(MonthlyLandLine);
			
			UserData data = new ReceiptUserData();
			
			MonthlyLandLine.setUserData(data);
			QuarterLandLine.setUserData(data);
			
			
			return landlineservice;
		}
		else if(name.equals("InternetPayment-Service")) {
			Service internetpaymentservice=new InternetPaymentService(name,false);
			Provider Vodafone=new LandLineServiceProvidor("Vodafone", new HandleInternetData());
			Provider Etisalat=new LandLineServiceProvidor("Etisalat", new HandleInternetData());
			Provider Orange=new LandLineServiceProvidor("Orange", new HandleInternetData());
			Provider We=new LandLineServiceProvidor("We", new HandleInternetData());
			
			UserData data = new ServiceUserData();
			
			Vodafone.setUserData(data);
			Etisalat.setUserData(data);
			Orange.setUserData(data);
			We.setUserData(data);
			
			internetpaymentservice.addProvider(Vodafone);
			internetpaymentservice.addProvider(Etisalat);
			internetpaymentservice.addProvider(Orange);
			internetpaymentservice.addProvider(We);
			return internetpaymentservice;
		}
		else if(name.equals("Donation-Service")) {
			Service donationservice=new DonationService(name,false);
			Provider cancerHospital=new DonationsProvider("Cancer",new HandleCancerDonationData());
			Provider schools=new DonationsProvider("School",new HandleSchoolDonationData());
			Provider NPO=new DonationsProvider("NPO",new HandleNPOData());
			donationservice.addProvider(NPO);
			donationservice.addProvider(cancerHospital);
			donationservice.addProvider(schools);
			
			UserData data = new DonationsUserData();
			
			cancerHospital.setUserData(data);
			schools.setUserData(data);
			NPO.setUserData(data);


			return donationservice;
		}
		return null;
		
	}

}
