package providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public interface UserData {	
	Vector<String> textField =new Vector<>();
	Map<String, List<String>> dropDownFields = new HashMap<>();
	public Vector<String> getTextFieldData();
	public Map<String, List<String>> getDropDownData();
}
