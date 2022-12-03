package providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ReceiptUserData implements UserData {

	@Override
	public Vector<String> getTextFieldData() {
		Vector<String> textFields = new Vector<String>();
		textFields.add("LandLine Number");
		textFields.add("Amount");
		textFields.add("City");
		return textFields;
	}

	@Override
	public Map<String, List<String>> getDropDownData() {
		return null;
	}


}
