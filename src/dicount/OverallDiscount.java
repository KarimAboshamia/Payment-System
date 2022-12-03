package dicount;
import application.User;
public class OverallDiscount extends Discount{
	float dicountRatio;
	public OverallDiscount ()
	{
		this.dicountRatio=0;
	}
	public float getDiscount (float discountratio)
	{
		return this.dicountRatio;
	}
	
}
