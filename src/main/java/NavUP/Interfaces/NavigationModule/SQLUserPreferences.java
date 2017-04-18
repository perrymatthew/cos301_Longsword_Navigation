/**
 * @author Neo , Nathan
 * @version 1
 *
 *
 */


/**
 * Package for Navigation Module
 *
 */
package NavUP.Interfaces.NavigationModule;

/**
 * Import libraries for SQL interaction and necessary data structures
 *
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

/**
 * Class to manage SQL DB for user preferences and favourite routes
 *
 */
public class SQLUserPreferences {
    /**
     * Variables to connect to the DB
     */
    private final static String DB_URL = "";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "root";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    Connection connection;

    /**
     * Default constructor of SQLUserPreferences
     */
    public SQLUserPreferences()
    {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Class.forName(myDriver);
            System.out.println("Connected to Database");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Add user function to add the user to the SQL DB
    /**
     * addUser function adds new user to SQL DB
     * @param user JSON string of new user details
     *
     */
    public void addUser(String user) throws SQLException {
        try {

            JSONObject json = new JSONObject(user);

            String userIdVar = json.getString("userID");
            Double userPref = json.getDouble("preferences");
            Boolean userRestrictions = json.getBoolean("restrictions");
            String query = "INSERT INTO `preferences`(userID, preferences, restrictions) VALUE (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, userIdVar);
            insert.setDouble(2, userPref);
            insert.setBoolean(3, userRestrictions);
            insert.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * updatePreference function updates preferences attribute of user in SQL DB
     * @param pref JSON string of the user details that are to be updated
     *
     */
    public void updatePreference(String pref) throws SQLException {
        try {
            JSONObject json = new JSONObject(pref);
            String userIdVar = json.getString("userID");
            Double userPref = json.getDouble("preferences");
            String query = "UPDATE preferences SET preferences = ? WHERE userID = ?";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setDouble(1, userPref);
            insert.setString(2, userIdVar);
            insert.executeUpdate();
        }
        catch (Exception e){
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
            select.setString(1, user_ID);
            ResultSet rs = select.executeQuery(query);
            resValue = rs.getBoolean("preferences");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return resValue;
    }
}
