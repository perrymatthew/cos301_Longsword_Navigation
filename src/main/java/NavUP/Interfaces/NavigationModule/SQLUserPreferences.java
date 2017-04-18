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
