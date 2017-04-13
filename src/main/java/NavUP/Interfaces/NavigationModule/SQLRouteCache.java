/**
 * @author Matthew Perry
 */

/**
 * Package for Navigation Module
 */
package NavUP.Interfaces.NavigationModule;

/**
 * Import libraries for SQL interaction
 */

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
    Connection connection;

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
     * @param route
     */
    public void addRoute(String route) {
        String start = "";
        String end = "";
        int pop = 0;

        try {
            String query = "insert into routecache (routeString, startPoint, endPoint, popularity)\n" + "values (\"" + route + "\",\"" + start + "\",\"" + end + "\"," + pop + ");";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Remove route function to remove the route to the SQL DB
     * @param route
     */
    public void removeRoute(String route) {
        String start = "";
        String end = "";
        int pop = 0;

        try {
            String query = "";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException e){
            e.printStackTrace();
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
}