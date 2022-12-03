package dicount;

import application.User;

public class TransactionDiscount extends DiscountDec {
	Discount obj;
	
	TransactionDiscount(Discount obj)
	{
		this.obj=obj;
	}
	@Override
	public float getDiscount(float discountratio) {
		// TODO Auto-generated method stub
		return obj.getDiscount(discountratio)+discountratio;
	}
	
}
