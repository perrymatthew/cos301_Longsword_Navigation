/**
 * Class Overview:
 * The SQLRouteCahce class is where the most common routes will be stored in an SQL DB.
 * These routes will be first checked from cache before any calculations are done from GIS
 */

/**
 * @author Matthew Perry
 * @version 1
 */

package NavUP.Interfaces.NavigationModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public class SQLRouteCache {
    /**
     * Variables to connect to the DB
     */
    private final static String DB_URL = "jdbc:mysql://localhost:3306/Navigation";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    private Connection connection;

    /**
     * Default constructor
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
     * isRoute function to check if route exists in the SQL DB.
     * @param startPoint The start waypoint.
     * @param endPoint The end waypoint.
     * @return true if route already exists, false otherwise.
     */
    public boolean isRoute(String startPoint,String endPoint) {
        String check = getCachedRoute(startPoint, endPoint);
        if(check.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * Add route function to add the route to the SQL DB.
     * @param route this is a JSON String that serves as the route.
     */
    public void addRoute(String route) {
        String start;
        String end;
        int pop = 0;

        try {
            JSONObject json = new JSONObject(route);
            JSONArray routeObj = json.getJSONArray("waypoints");
            int length = routeObj.length();
            start = routeObj.getJSONObject(0).getDouble("lat") + "_" +
                    routeObj.getJSONObject(0).getDouble("long");
            end = routeObj.getJSONObject(length - 1).getDouble("lat") + "_" +
                    routeObj.getJSONObject(length - 1).getDouble("long");

            try {
                String query;
                query = "INSERT INTO routecache(routeString, startPoint, endPoint, popularity) VALUE (?, ?, ?, ?)";
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

        this.manageRoutes();
    }

    /**
     * Get route function to get the route from the SQL DB
     * @param startpoint The start waypoint
     * @param endpoint The end waypoint
     * @return The JSON string of the found route or an empty string if it is not found
     */
    public String getCachedRoute(String startpoint, String endpoint) {
        String cache = "";
        int id = 0;

        try {
            String query = "SELECT * FROM `routecache` WHERE startPoint='" + startpoint + "'" + " AND endPoint='" + endpoint +"'";
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);

            if(rs.next()){
                cache = rs.getString("routeString");
                id = rs.getInt("idrouteCache");
            }

            /**
             * If the route is found in the database, increase its popularity.
             */
            if (!cache.equals(""))
            {
                query = "UPDATE `routecache` SET popularity = routecache.popularity + 1 WHERE idrouteCache = ?";
                PreparedStatement update = connection.prepareStatement(query);
                update.setInt(1, id);
                update.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cache;
    }

    /**
     * This method is dedicated to managing the number of routes that are stored in the cache. After a route is added,
     * this method is called and will decide which route to then remove. It then calls removeRoute() for the route that
     * has been decided to be removed.
     */
    public void manageRoutes()
    {
        String queryStr = "SELECT * FROM routecache;";
        try {
            PreparedStatement query = connection.prepareStatement(queryStr);
            ResultSet rs = query.executeQuery();
            if (rs.getRow() > 20)
            {
                String sort = "SELECT idrouteCache, popularity FROM routecache ORDER BY popularity LIMIT 12;";
                query = connection.prepareStatement(sort);
                rs = query.executeQuery();
                rs.last();
                int id = rs.getInt(1);
                this.removeRoute(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
            JSONArray routeObj = json.getJSONArray("waypoints");
            int length = routeObj.length();
            start = routeObj.getJSONObject(0).getDouble("lat") + "_" +
                    routeObj.getJSONObject(0).getDouble("long");
            end = routeObj.getJSONObject(length - 1).getDouble("lat") + "_" +
                    routeObj.getJSONObject(length - 1).getDouble("long");

            try {
                String query;
                query = "DELETE FROM routecache WHERE startPoint=? AND endPoint=?;";
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
     * Remove route function to remove the route to the SQL DB.
     * @param routeID This int is the unique ID that is the Primary key for a route.
     */
    public void removeRoute(int routeID)
    {
        try {
            String query;
            query = "DELETE FROM routecache WHERE idrouteCache = ?;";
            PreparedStatement remove = connection.prepareStatement(query);
            remove.setInt(1, routeID);
            remove.executeUpdate();

        }
        catch (SQLException e){
            System.out.println("Remove route SQL failure: " + e.toString());
        }
    }
}