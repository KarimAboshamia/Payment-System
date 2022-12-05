package providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public interface UserData {	
	public Vector<String> getTextFieldData();
	public Map<String, List<String>> getDropDownData();
}
