package discount;

import application.User;

public class ServiceDiscount extends DiscountDec {
	float discountRatio;
	public ServiceDiscount(Discount obj, float discountRatio)
	{
		this.obj=obj;
		this.discountRatio = obj.getDiscount() + discountRatio;
	}
	
	@Override
	public float getDiscount() {
		return discountRatio;
	}
}
