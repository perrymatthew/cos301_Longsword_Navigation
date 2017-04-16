/**
 * @author Matthew Perry
 * @version 1
 *
 */

/**
 * Package for Navigation Module
 *
 */
package NavUP.Interfaces.NavigationModule;

/**
 * Import libraries for SQL interaction
 *
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

/**
 * Class to manage SQL DB for most common routes
 */
public class SQLRouteCache {
    /**
     * Variables to connect to the DB
     */
    private final static String DB_URL = "jdbc:mysql://localhost/";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "root";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    private Connection connection;

    /**
     * Default constructor
     *
     */
    public SQLRouteCache()
    {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Class.forName(myDriver);
            System.out.println("Connected to Database");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Add route function to add the route to the SQL DB
     * @param route this is a JSON String that serves as the route.
     */
    public void addRoute(String route) {
        String start;
        String end;
        int pop = 0;

        try {
            JSONObject json = new JSONObject(route);
            JSONArray routeObj = json.getJSONArray("route");
            int length = json.getInt("length");
            start = routeObj.getJSONObject(0).getDouble("lat") + "," +
                    routeObj.getJSONObject(0).getDouble("long");
            end = routeObj.getJSONObject(length).getDouble("lat") + "," +
                    routeObj.getJSONObject(length).getDouble("long");

            try {
                String query;
                query = "INSERT INTO `routecache`(routeString, startPoint, endPoint, popularity) VALUE (?, ?, ?, ?)";
                PreparedStatement insert = connection.prepareStatement(query);
                insert.setString(1, route);
                insert.setString(2, start);
                insert.setString(3, end);
                insert.setInt(4, pop);
                insert.executeUpdate();
            }
            catch (SQLException e){
                System.out.println("Add route SQL failure: " + e.toString());
            }
        } catch (JSONException e) {
            System.out.println("Add route JSON failure: " + e.toString());
        }
    }

    /**
     * Remove route function to remove the route to the SQL DB.
     * @param route this is a JSON String that serves as the route.
     */
    public void removeRoute(String route) {
        String start;
        String end;

        try {
            JSONObject json = new JSONObject(route);
            JSONArray routeObj = json.getJSONArray("route");
            int length = json.getInt("length");
            start = routeObj.getJSONObject(0).getDouble("lat") + "," +
                    routeObj.getJSONObject(0).getDouble("long");
            end = routeObj.getJSONObject(length).getDouble("lat") + "," +
                    routeObj.getJSONObject(length).getDouble("long");

            try {
                String query;
                query = "DELETE FROM `routecache` WHERE startPoint=? AND endPoint=?;";
                PreparedStatement remove = connection.prepareStatement(query);
                remove.setString(1, start);
                remove.setString(2, end);
                remove.executeUpdate();

            }
            catch (SQLException e){
                System.out.println("Remove route SQL failure: " + e.toString());
            }
        }
        catch (JSONException e) {
            System.out.println("Remove route JSON failure: " + e.toString());
        }
    }

    /**
     * Get route function to get the route from the SQL DB
     * @param start The start waypoint
     * @param end The end waypoint
     * @return The JSON string of the found route or an empty string if it is not found
     */
    public String getCachedRoute(String start, String end) {
        String cache = "";

        try {
            String query = "SELECT * FROM `routecache` WHERE startPoint=? AND endPoint=?";
            PreparedStatement select = connection.prepareStatement(query);
            select.setString(1, start);
            select.setString(2, end);
            ResultSet rs = select.executeQuery(query);
            cache = rs.getString("routeString");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cache;
    }

    private void manageRoutes()
    {

    }
}