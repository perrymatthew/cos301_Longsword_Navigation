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

    /**
     * addUser function adds a user to the DB
     * @param userId User id passed to function to identify the user
     * @param userRestriction The user's restriction (disabled or not)
     * @param userPreference The user's prefered route (with regards to the length)
     * @throws SQLException Thrown exception in case the user can not be added 
     */
    public void addUser(String userId, boolean userRestriction, double userPreference) throws SQLException {
        try {
            String query = "INSERT INTO `preferences`(userID, preferences, restrictions) VALUE (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, userId);
            insert.setDouble(2, userPreference);
            insert.setBoolean(3, userRestriction);
            insert.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * updatePreference function to update the already existing user's preference
     * @param userId The user's ID to update
     * @param userPreference The preference associated to the user's ID
     * @throws SQLException Thrown exception in case no update can be made
     */
    public void updatePreference(String userId, double userPreference) throws SQLException {
        try {
            String query = "UPDATE preferences SET preferences=userID WHERE userID='" + userId + "'";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * updateRestrictions function to update the already existing user's restriction
     * @param userId The user's ID to update
     * @param userRestriction The restriction associated to the user's ID
     * @throws SQLException Thrown exception in case no update can be made
     */
    public void updateRestrictions(String userId, boolean userRestriction) throws SQLException {
        try {
            String query = "UPDATE preferences SET restrictions = userRestriction WHERE userID='" + userId + "'";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * getUser function to get the user from the SQL DB.
     * @param userId The passed user ID to search from the DB
     * @return This is a string representing the user .
     * @throws SQLException Thrown exception in case no user can be obtained
     */
    public String getUser(String userId) throws SQLException {
        String user = "";
        try {
            String query = "SELECT userID FROM preferences WHERE userID='" + userId + "'";
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);
            user = rs.getString("userID");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * This method will return the preferences for a user.
     * @param userId This is the Unique ID for the user.
     * @return A string representing the user's preferences.
     */
    public String getPreference(String userId) {
        String pref = "";

        try {
            String query = "SELECT preferences FROM preferences WHERE userID='" + userId + "'";
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);

            if(rs.next()){
                pref = rs.getString(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return pref;
    }

    /**
     * This method will return the restrictions for a User.
     * @param userId This is the Unique ID for the user.
     * @return A string representing the user's preferences.
     */
    public String getRestrictions(String userId) {
        String restrictions = "";
        try {
            String query = "SELECT restrictions FROM preferences WHERE userID='" + userId + "'";
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);

            if(rs.next()){
                restrictions = rs.getString(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return restrictions;
    }
}