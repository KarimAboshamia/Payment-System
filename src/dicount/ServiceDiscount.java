package dicount;

import application.User;

public class ServiceDiscount extends DiscountDec {
	Discount obj;
	ServiceDiscount(Discount obj)
	{
		this.obj=obj;
	}
	
	@Override
	public float getDiscount(float discountratio) {
		// TODO Auto-generated method stub
		return obj.getDiscount(discountratio)+discountratio;
	}
}
