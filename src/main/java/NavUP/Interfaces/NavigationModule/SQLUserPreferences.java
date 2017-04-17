//Package for Navigation Module
package NavUP.Interfaces.NavigationModule;

//Import libraries for SQL interaction
import org.json.JSONArray;
import org.json.JSONObject;

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

            JSONObject json = new JSONObject(user);

            String userIdVar = json.getString("userID");
            Double userPref = json.getDouble("preferences");
            Boolean userRestrictions = json.getBoolean("restrictions");
//            Integer userRestrictions = boolReceived.compareTo(true);
            String query = "INSERT INTO `preferences`(userID, preferences, restrictions) VALUE (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, userIdVar);
            insert.setDouble(2, userPref);
            insert.setBoolean(3, userRestrictions);
            insert.executeUpdate();
//            JSONArray pinsArray = new JSONArray();
//            String query = "";
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery(query);
        }
        catch (Exception e){
            //Throw exception if connection failed
            e.printStackTrace();
        }
    }

    //Update preference function to update the preference of a user to the SQL DB
    public void updatePreference(String pref) throws SQLException {
        try {
            //Adding the route to the DB

            JSONObject json = new JSONObject(pref);

            String userIdVar = json.getString("userID");
            Double userPref = json.getDouble("preferences");
//            Boolean boolReceived = json.getBoolean("restrictions");
//            Integer userRestrictions = boolReceived.compareTo(true);
//            UPDATE Customers
