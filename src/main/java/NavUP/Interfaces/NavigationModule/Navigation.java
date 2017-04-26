/**
 * Class Overview:
 * The Navigation class is where the Navigation Interface's functionality will be implemented.
 * It allows other modules to make calls with the interface class and have those calls perform certain functions based on what is implemented in this class
 */

/**
 * @author Marin Peroski
 * @version 1
 */

package NavUP.Interfaces.NavigationModule;
import NavUP.Interfaces.NavigationInterface;
import org.gis.DB_Functions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class Navigation implements NavigationInterface {
    private SQLRouteCache routeCache;
    private SQLUserPreferences userPreferences;
    private SQLUserPins userPins;
    private DB_Functions GIS;

    /**
     * Default constructor for the Navigation class
     */
    public Navigation () {
        routeCache = new SQLRouteCache();
        userPreferences = new SQLUserPreferences();
        userPins = new SQLUserPins();
        GIS = new DB_Functions();
    }

    /**
     * Get the route from the GIS interface and return it in a JSON string format
     * @param pointLocations will contain a start and end point along with a user ID
     * @return a JSON formatted string with a route for the Access module
     */
    public String getRoute (String pointLocations) {
        String cache = "";
        String fromGIS = "";
        String route = "";
        String st = "";
        String en = "";
        boolean checkRoute = false;

        try {
            JSONObject jObject = new JSONObject(pointLocations);
            JSONObject jSource = jObject.getJSONObject("source");
            JSONObject jDestination = jObject.getJSONObject("destination");
            JSONObject jRestrictions = jObject.getJSONObject("restrictions");
            JSONObject jPreferences = jObject.getJSONObject("preferences");

            String user = jObject.getString("userID");

            System.out.println("Current User: " + user);
            System.out.println("");

            userPreferences.addUser(user, jRestrictions.getBoolean("isDisabled"), jPreferences.getDouble("maximumRouteLength"));

            st = Double.toString(jSource.getDouble("lat")) + "_" + Double.toString(jSource.getDouble("long"));
            en = Double.toString(jDestination.getDouble("lat")) + "_" + Double.toString(jDestination.getDouble("long"));

            checkRoute = routeCache.isRoute(st, en);

            if (checkRoute) {
                cache = routeCache.getCachedRoute(st, en);
                return cache;
            }
            else {
                fromGIS = GIS.getRoutes(jSource.getDouble("lat"), jSource.getDouble("long"), jDestination.getDouble("lat"), jDestination.getDouble("long"));

                jObject = new JSONObject(fromGIS);
                JSONArray jRoutes = jObject.getJSONArray("routes");

                int arrSize = jRoutes.length();
                int whichOne = 0;
                double size = 0.0;
                double userRestrict = Double.parseDouble(userPreferences.getPreference(user));

                JSONObject jWaypoints = jRoutes.getJSONObject(0);
                size = jWaypoints.getDouble("length");

                for (int i = 1; i < arrSize; i++) {
                    jWaypoints = jRoutes.getJSONObject(i);

                    if (size > jWaypoints.getDouble("length") && size >= userRestrict) {
                        size = jWaypoints.getDouble("length");
                        whichOne = i;
                    }
                }
                route = jRoutes.getString(whichOne);
                routeCache.addRoute(route);
                return route;
            }

        }
        catch (JSONException e) {
            System.out.println("Can not parse string");
        }
        catch (SQLException e) {
            System.out.println("Error in DB");
        }
        return "";
    }

    /**
     * Add a custom name (pin) created by a user for future route references
     * @param pin JSON formatted string passed from Access module containing co-ordinates and a user ID (addition)
     */
    public void dropPin(String pin) {
        userPins.dropPin(pin);
    }

    /**
     * Removal of the custom location name (pin) which was created by the user
     * @param pin JSON formatted string passed from Access module containing co-ordinates and a user ID (removal)
     */
    public void removePin(String pin) {
        userPins.removePin(pin);
    }

    /**
     * @param userID which is just a strin providing the userID to retrieve pins created by that user
     * @return a JSON formatted string of pin array containing the latitude, longitude and name associated to those points
     */
    public String getUserPins (String userID) {
        String pins = "";
        try {
            JSONObject jObject = new JSONObject(userID);
            pins = userPins.getUserPins(jObject.getString("userID"));
            return pins;
        }
        catch (JSONException e) {
            System.out.println("Can not parse string");
        }
        return "";
    }
}