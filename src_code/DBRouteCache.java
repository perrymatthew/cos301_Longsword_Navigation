import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;
import org.json.*;

public class DBRouteCache {

    private void manageRoutes () {
    	try {
	    	JSONParser parser = new JSONParser();
	    	org.json.simple.JSONArray simpleArr = (org.json.simple.JSONArray) parser.parse(new FileReader("DBRouteCache.json"));
	    	org.json.JSONArray arr =  new org.json.JSONArray(simpleArr.toJSONString());
	    	arr = sortArrayByPopularity(arr);
	    	while(arr.length() > 20) {
		    	arr.remove(11);
	    	} 
	    	try (FileWriter file = new FileWriter("DBRouteCache.json")) {
				file.write(arr.toString());
			} catch (Exception e) {
    			e.printStackTrace();	
			}
    	} catch (Exception e) {
	    	e.printStackTrace();
    	}
    }
}