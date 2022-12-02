package providers;

public class ReceiptUserData implements UserData {
	//Takes Data 
	int textFields = 3;

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
