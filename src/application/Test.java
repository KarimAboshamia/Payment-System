package application;

public class Test {
	public static void main(String []args) {
		AuthenticationManager obj = new AuthenticationManager();
		DBConnection obj2 = new DBConnection();
		//obj2.signUp("user6", "password5", 0);
		System.out.println(obj.signUp("user45@gmail.com", "user21", "password4", 0));
		//obj.login("user12", "password4");
	}

}

