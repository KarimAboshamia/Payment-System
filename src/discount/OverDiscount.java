package discount;
import application.User;
public class OverDiscount extends Discount{
	float dicountRatio;
	public OverDiscount ()
	{
		this.dicountRatio=0;
	}
	public float getDiscount ()
	{
		return this.dicountRatio;
	}
	
}
