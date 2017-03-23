import java.io.*;
import java.util.ArrayList;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.*;
//import org.json.*;

/* Compile with: javac -cp ./*: *.java */
/* Run with: java -cp ./*: DBRouteCache */

public class DBRouteCache {

	/* FOR TESTING PURPOSES */
	// public static void main(String[] args) {
	// 	manageRoutes();
	// }
	public DBRouteCache(){}
	
	public boolean addRoute(ArrayList<Waypoint> nodes) {
		return true;
	}
	
	public boolean deleteRoute(Waypoint startPoint, Waypoint endPoint) {
		return true;
	}
		
	
    private void manageRoutes () {
    	/*try {
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
    	}*/
    }

    /*private static org.json.JSONArray sortArrayByPopularity (org.json.JSONArray arr) {
    	org.json.JSONObject max = arr.getJSONObject(0);
    	for (int i = 0; i<arr.length()-1; i++) {
    		max = arr.getJSONObject(i);
    		int lowestIndex = i;
	    	for(int e = i+1; e<arr.length(); e++) {
	    		if( arr.getJSONObject(e).getInt("popularity") > max.getInt("popularity") ) {
	    			lowestIndex = e;
	    			max = arr.getJSONObject(e);
	    		}
	    	}
	    	if(lowestIndex != i){
	    		org.json.JSONObject temp = new org.json.JSONObject(arr.getJSONObject(i).toString());
	    		arr.put(i,max);
	    		arr.put(lowestIndex, temp);
	    	}
	    }
	    return arr;
	}*/
	
	private boolean isRoute(Waypoint startPoint,Waypoint endPoint)
	{
	  return true;
	}
        
        public Waypoint[] getRoute(Waypoint startPoint, Waypoint endPoint)
        {
            Waypoint[] routeWaypoints = null;
            /*%TODO
            fetch operation for getting way points
            */
            if(isRoute(startPoint, endPoint))
            {
                if(calculateDistance(startPoint, endPoint))
                {
                    return routeWaypoints;
                }
                
                return routeWaypoints;
            }


            return routeWaypoints;
        }
        
        private boolean calculateDistance(Waypoint startPoint, Waypoint endPoint)
        {
            boolean success = true;
            
            
            return success;
        }
}
