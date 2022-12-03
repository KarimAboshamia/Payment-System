package discount;

public class TestDiscount {
	public static void main(String[]args) {
		Discount s = new OverDiscount();
		s = new ServiceDiscount(s, 10);
		s = new TransactionDiscount(s, 5);
		s = new TransactionDiscount(s, 5);

		s = new TransactionDiscount(s, 5);
		s = new ServiceDiscount(s, 10);

		System.out.println(s.getDiscount());
		
		
	}

}
