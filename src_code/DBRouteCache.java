package com.navigation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBRouteCache {

	JSONArray parsedJSON;
	private final String FILENAME = "DBRouteCache.json";

	public DBRouteCache(){
		try {
            BufferedReader br = null;
            String tempJSON = "";
            try { br = new BufferedReader(new FileReader(FILENAME)); }
            catch (FileNotFoundException e) { e.printStackTrace(); }

            String line = br.readLine();
            tempJSON = line;
            while (line != null) {
                line = br.readLine();
                tempJSON += line;
            }

            parsedJSON = new JSONArray(tempJSON);
            System.out.println(parsedJSON.length()+" Routes Loaded From Cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public boolean addRoute(String route) {
		System.out.println("Adding a popular route...");
		try {
            JSONObject routeObj = new JSONObject(route);
            if(isRoute(routeObj)) {
                return false;
            } else {
                routeObj.put("popularity", 0);
                parsedJSON.put(routeObj);
                manageRoutes();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
		return true;
	}
	
	public boolean deleteRoute(String startPoint, String endPoint) {
		System.out.println("Deleting popular route...");
	    try {

            // Create list of JSONObjects from the JSONArray parsedJSON
            List<JSONObject> result = new ArrayList<JSONObject>(parsedJSON.length());

            // Populate this list
            for (int i = 0; i < parsedJSON.length(); i++)
                result.add(parsedJSON.getJSONObject(i));

            // Remove specified route
            for(int i = 0; i<result.size(); i++) {
                JSONArray wps = result.get(i).getJSONArray("waypoints");
                if(wps.getJSONObject(0).get("name").equals(startPoint) && wps.getJSONObject(wps.length()-1).get("name").equals(endPoint))
                    result.remove(i);
            }

            // Clean out all routes in parsedJSON
            parsedJSON = new JSONArray();

            // Place the JSONObjects back into the JSONArray parsedJSON
            for (JSONObject obj : result)
                parsedJSON.put(obj);

            writeToFile();
        } catch(Exception e) {
	        e.printStackTrace();
        }
        return true;
	}
		
	
    private void manageRoutes () {
    	System.out.println("Managing popular routes...");
	    try {
            sortArrayByPopularity();

            // Create list of JSONObjects from the JSONArray parsedJSON
            List<JSONObject> result = new ArrayList<JSONObject>(parsedJSON.length());

            // Populate this list
            for (int i = 0; i < parsedJSON.length(); i++)
                result.add(parsedJSON.getJSONObject(i));

            // Remove items at index 11 as long as the list size is greater than 20
            while (result.size() > 20)
                result.remove(11);

            // Clean out all routes in parsedJSON
            parsedJSON = new JSONArray();

            // Place the JSONObjects back into the JSONArray parsedJSON
            for (JSONObject obj : result)
                parsedJSON.put(obj);

            writeToFile();
        } catch (Exception e) {
	        e.printStackTrace();
        }
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
            if(startPoint.getOperational() && endPoint.getOperational())
            {
                
            }
            
            return success;
        }
}
