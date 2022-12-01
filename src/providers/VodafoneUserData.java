package providers;

public class VodafoneUserData implements UserData {
	//Takes Data 
	int textFields = 2;

//	@Override
//	public void getUserData() {
//		//enter first name
//		//enter amount
//		
//	}

	@Override
	public int numberOfTextFields() {
		return textFields;
	}
}
