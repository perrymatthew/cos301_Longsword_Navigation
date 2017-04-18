/**
 * Class Overview:
 * The SQLUsersPreferences class stores all of a users preferences and restrictions.
 * These restrictions are used in route choices before they are sent back to access
 */

/**
 * @author Neo Thokoa, Nathan Ngobale, Azhar Patel
 * @version 1
 */

package NavUP.Interfaces.NavigationModule;

import org.json.JSONObject;
import java.sql.*;

public class SQLUserPreferences {
    /**
     * Variables to connect to the DB
     */
    private final static String DB_URL = "jdbc:mysql://localhost:3306/Navigation";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
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
     * @param userID JSON string of new user details
     */
    public void addUser(String userID, boolean userRestriction, double userPreference) throws SQLException {
        try {
            String query = "INSERT INTO `preferences`(userID, preferences, restrictions) VALUE (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, userID);
            insert.setDouble(2, userPreference);
            insert.setBoolean(3, userRestriction);
            insert.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * updatePreference function updates preferences attribute of user in SQL DB
     * @param pref JSON string of the user details that are to be updated
     */
    public void updatePreference(String userID, double userPreference) throws SQLException {
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

    /**
     * updatePreference function updates preferences attribute of user in SQL DB
     * @param pref JSON string of the user details that are to be updated
     */
    public void updateRestrictions(String userID, boolean userRestriction) throws SQLException {
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

    /**
     * Get user function to get the user from the SQL DB.
     * @param user
     * @return This is a string representing the user .
     * @throws SQLException
     */
    public String getUser(String userID) throws SQLException {
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

    /**
     * This method will return the preferences for a user.
     * @param userID This is the Unique ID for the user.
     * @return A string representing the user's preferences.
     */
    public String getPreference(String userID) {
        String pref = "";

        try {
            String query = "SELECT * FROM `preferences` WHERE userID=userID";
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);
            pref = rs.getString("preferences");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return pref;
    }

    /**
     * This method will return the restrictions for a User.
     * @param userID This is the Unique ID for the user.
     * @return A string representing the user's preferences.
     */
    public String getRestrictions(String userID) {
        String restrictions = "";
        try {
            String query = "SELECT * FROM preferences WHERE userID=userID";
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);
            restrictions = rs.getString("restrictions");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return restrictions;
    }
}