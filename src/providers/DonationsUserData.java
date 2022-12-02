package providers;

public class DonationsUserData implements UserData {
	//Takes Data 
	int textFields = 4;

	@Override
	public int numberOfTextFields() {
		return textFields;
	}

}
