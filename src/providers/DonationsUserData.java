package providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DonationsUserData implements UserData {

	@Override
	public Vector<String> getTextFieldData() {
		Vector<String> textFields = new Vector<String>();
		textFields.add("To");
		textFields.add("Amount");
		textFields.add("Duration");
		return textFields;
	}

	@Override
	public Map<String, List<String>> getDropDownData() {
		Map<String, List<String>> dropDownFields = new HashMap<>();
		dropDownFields.put("Identity", Arrays.asList("Known", "Unknown"));
		return dropDownFields;
	}

}
