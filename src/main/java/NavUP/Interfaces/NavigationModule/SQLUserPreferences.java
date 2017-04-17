//Package for Navigation Module
package NavUP.Interfaces.NavigationModule;

//Import libraries for SQL interaction
import java.sql.*;

//Class to manage SQL DB for user preferences and favourite routes
public class SQLUserPreferences {
    //Variables to connect to the DB
    private final static String DB_URL = "";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "root";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    Connection connection;

    //Default constructor
    public SQLUserPreferences()
    {
        //Try Catch block for error handling
        try {
            //Establish connection to teh DB
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Class.forName(myDriver);
            System.out.println("Connected to Database");
        }
        catch (Exception e) {
            //Throw exception if connection failed
            e.printStackTrace();
        }
    }

    //Add user function to add the user to the SQL DB
    public void addUser(String user) throws SQLException {
        //Variable for the user's ID
        String user_ID = "";
        //Variable for the user's preference
        String user_Pref = "";

        //Try Catch block for error handling
        try {
            //Adding the route to the DB
            String query = "";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
        }
        catch (SQLException e){
            //Throw exception if connection failed
            e.printStackTrace();
        }
    }

    //Update preference function to update the preference of a user to the SQL DB
    public void updatePreference(String pref) throws SQLException {
        //Variable for the user's ID
        String user_ID = "";
        //Variable for the user's preference
        String user_Pref = "";

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

    //Get user function to get the user from the SQL DB
    public String getUser(String user) throws SQLException {
        //Variable for the user's ID
        String user_ID = "";
        //Variable for the user's preference
        String user_Pref = "";
        //Variable to return user in JSON format
        String client = "";

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
        return client;
    }

    //Get preference function to get the user's preference from the SQL DB
    public String getPreference(String pref) throws SQLException {
        //Variable for the user's ID
        String user_ID = "";
        //Variable for the user's preference
        String user_Pref = "";
        //Variable to return preference in JSON format
        String preference = "";

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
        return preference;
    }
}
