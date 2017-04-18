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
