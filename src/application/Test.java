package application;

public class Test {
	public static void main(String []args) {
		AppUser user = new User("Ahmed@gmail.com", "Ahmed", "1234", "0");
		User mainuser = (User) user;
		mainuser.requestRefund("Ahmed", 0);
	
	}

}

