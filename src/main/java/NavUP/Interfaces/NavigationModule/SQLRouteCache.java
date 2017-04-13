//Package for Navigation Module
package NavUP.Interfaces.NavigationModule;

//Import libraries for SQL interaction
import java.sql.*;

//Class to manage SQL DB for most common routes
public class SQLRouteCache {
    //Variables to connect to the DB
    private final static String DB_URL = "jdbc:mysql://localhost/";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "root";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    Connection connection;

    //Default constructor
    public SQLRouteCache()
    {
        //Try Catch block for error handling
        try {
            //Establish connection to teh DB
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Class.forName(myDriver);
            System.out.println("Connected to Database");
        }
        catch (Exception e){
            //Throw exception if connection failed
            e.printStackTrace();
        }
    }

    //Add route function to add the route to the SQL DB
    public void addRoute(String route) throws SQLException {
        //Variable for the start point
        String start = "";
        //Variable for the end point
        String end = "";
        //Variable for the roues popularity
        int pop = 0;

        //Try Catch block for error handling
        try {
            //Adding the route to the DB
            String query = "insert into routecache (routeString, startPoint, endPoint, popularity)\n" + "values (\"" + route + "\",\"" + start + "\",\"" + end + "\"," + pop + ");";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException e){
            //Throw exception if connection failed
            e.printStackTrace();
        }
    }

    //Remove route function to remove the route to the SQL DB
    public void removeRoute(String route) throws SQLException {
        //Variable for the start point
        String start = "";
        //Variable for the end point
        String end = "";
        //Variable for the roues popularity
        int pop = 0;

        //Try Catch block for error handling
        try {
            //Removing the route from the DB
            String query = "";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException e){
            //Throw exception if connection failed
            e.printStackTrace();
        }
    }

    //Get route function to get the route to the SQL DB
    public String getCachedRoute(String route) throws SQLException {
        //Variable to store JSON route once found
        String cahce = "";
        //Variable for the start point
        String start = "";
        //Variable for the end point
        String end = "";

        //Try Catch block for error handling
        try {
            //Retrieve the route from the DB
            String query = "";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException e){
            //Throw exception if connection failed
            e.printStackTrace();
        }
        //Return the JSON formatted route
        return cahce;
    }
}