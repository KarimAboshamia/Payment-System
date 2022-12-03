package dicount;

public class DiscountScenario {
	Discount obj;
	TrasnactionDiscountManager objT =new TrasnactionDiscountManager();
	ServiceDisountManager objS = new ServiceDisountManager();
	public float calcOverallDiscount(Discount obj, User user)
	{
		obj=objT.calcDiscount(obj, user);
		obj=objS.calcDiscount(obj);
		return obj.getDiscount(0);
		
	}
}
