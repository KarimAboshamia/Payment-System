package discount;

import application.User;

public class TransactionDiscount extends DiscountDec {
	float discountRatio;
	TransactionDiscount(Discount obj, float discountRatio)
	{
		this.obj=obj;
		this.discountRatio = obj.getDiscount() + discountRatio;
		
	}
	@Override
	public float getDiscount() {
		return discountRatio;
	}
	
}
