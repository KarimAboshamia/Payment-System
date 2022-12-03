package providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ServiceUserData implements UserData{

	@Override
	public Vector<String> getTextFieldData() {
		Vector<String> textFields = new Vector<String>();
		textFields.add("PhoneNumber");
		textFields.add("Amount");
		return textFields;
	}

	@Override
	public Map<String, List<String>> getDropDownData() {
		return null;
	}

}
