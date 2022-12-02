package providers;

public class SharedPhonesUserData implements UserData {
	//Takes Data 
	int textFields = 2;

	@Override
	public int numberOfTextFields() {
		return textFields;
	}

}
