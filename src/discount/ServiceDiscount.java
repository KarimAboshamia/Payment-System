package discount;

import application.User;

public class ServiceDiscount extends DiscountDec {
	Discount obj;
	float discountRatio;
	ServiceDiscount(Discount obj, float discountRatio)
	{
		this.obj=obj;
		this.discountRatio = obj.getDiscount() + discountRatio;
	}
	
	@Override
	public float getDiscount() {
		// TODO Auto-generated method stub
		return discountRatio;
	}
}
