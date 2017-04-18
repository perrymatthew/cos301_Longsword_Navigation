//Package for Navigation Module
package NavUP.Interfaces.NavigationModule;

//Import libraries for SQL interaction
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
        //Variable for the user's ID
        String user_ID = "";
        //Variable for the user's preference
        String user_Pref = "";

        //Try Catch block for error handling
        try{
            boolean resStat = isRestricted(pref);
            String query = "UPDATE preferences SET preferences = ? WHERE userID = ?";
            PreparedStatement preparedsmt = connection.prepareStatement(query);
            preparedsmt.setString(1, pref);
            preparedsmt.setString(2, user_ID);
            preparedsmt.executeUpdate();


//            PreparedStatement insert = connection.prepareStatement(aquery);
//            insert.setString(1, user_ID);
//            insert.setBoolean(2, resStat);
//            insert.setString(3, pref);
//            insert.executeUpdate();

        }
        catch (Exception e){
            //Throw exception if connection failed
            e.printStackTrace();
        }
    }

    //Get user function to get the user from the SQL DB
    public String getUser(String user) throws SQLException {//convert to JSON?
        String cache = "";

        try {
            String query = "SELECT * FROM `preferences` WHERE userID=?";
            PreparedStatement select = connection.prepareStatement(query);
            select.setString(1, user);
            ResultSet rs = select.executeQuery(query);
            cache = rs.getString("userString");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cache;
    }

    //Get preference function to get the user's preference from the SQL DB
    public String getPreference(String pref) throws SQLException {//convert to JSON?
         String cache = "";

        try {
            String query = "SELECT * FROM `preferences` WHERE preferences=?";
            PreparedStatement select = connection.prepareStatement(query);
            select.setString(1, pref);
            ResultSet rs = select.executeQuery(query);
            cache = rs.getString("prefString");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return cache;
    }

    public boolean isRestricted(String pref)
    {
        //get restriction
        boolean resValue = false;

        try {
            String query = "SELECT * FROM `preferences` WHERE userID=?";
            PreparedStatement select = connection.prepareStatement(query);
            //select.setString(1, userID);
            ResultSet rs = select.executeQuery(query);
            resValue = rs.getBoolean("preferences");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return resValue;
    }

    //private  currentUser;

}
