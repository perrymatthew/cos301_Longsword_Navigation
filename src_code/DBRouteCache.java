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
            br = new BufferedReader(new FileReader(FILENAME));

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

    private void sortArrayByPopularity () {
    	try {
            JSONObject max;

            for (int i = 0; i<parsedJSON.length()-1; i++) {
                max = parsedJSON.getJSONObject(i);
                int lowestIndex = i;
                for(int e = i+1; e<parsedJSON.length(); e++) {
                    if( parsedJSON.getJSONObject(e).getInt("popularity") > max.getInt("popularity") ) {
                        lowestIndex = e;
                        max = parsedJSON.getJSONObject(e);
                    }
                }
                if(lowestIndex != i){
                    org.json.JSONObject temp = new JSONObject(parsedJSON.getJSONObject(i).toString());
                    parsedJSON.put(i, max);
                    parsedJSON.put(lowestIndex, temp);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}
	
	private boolean isRoute(String startPoint,String endPoint) {
	  return true;
	}
	private boolean isRoute(JSONObject route) { return false; }
        
    public String getRoute(String startPoint, String endPoint) {
        System.out.println("Retrieving route from cache...");
        String route = "";
        try {

            for (int i = 0; i < parsedJSON.length(); i++) {                                 // Iterate through all routes
                JSONObject aRoute = parsedJSON.getJSONObject(i);               // A route
                JSONArray routesWps = aRoute.getJSONArray("waypoints");    // Array of waypoints

                // First waypoint's name
                String startWpName = routesWps.getJSONObject(0).getString("name");

                // Last waypoint's name
                String endWpName = routesWps.getJSONObject(routesWps.length() - 1).getString("name");

                if (startPoint.equals(startWpName) && endPoint.equals(endWpName)) {
                    increasePopularity(aRoute);
                    route = aRoute.toString();
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return route;
    }
        
    /*private boolean calculateDistance(Waypoint startPoint, Waypoint endPoint) {
        boolean success = true;
        if(startPoint.getOperational() && endPoint.getOperational())
        {
            
        }
        
        return success;
    }*/

    private void increasePopularity(JSONObject route) {
        System.out.println("Increasing route popularity...");
        try {
            Integer popularity = route.getInt("popularity")+1;
            route.put("popularity", popularity);
            writeToFile();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        System.out.println("Writing routes to local cache...");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(FILENAME)));
            bw.write(parsedJSON.toString());
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
