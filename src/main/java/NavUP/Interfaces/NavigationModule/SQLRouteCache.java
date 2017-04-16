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

    private boolean isRoute(String startPoint,String endPoint) {
        
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
     * Get route function to get the route to the SQL DB
     * @param route
     * @return
     */
    public String getCachedRoute(String route) {
        String cache = "";
        String start = "";
        String end = "";

        try {
            String query = "";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
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
