package application;

public class Test {
	public static void main(String []args) {
		Authentication obj = new Authentication();
		DBConnection obj2 = new DBConnection();
		//obj2.signUp("user6", "password5", 0);
		//obj.signUp("user12", "password4", 0);
		obj.login("user12", "password4");
	}

}

