/**
 * @author Duart Breedt
 * @version 1
 *
 */

package NavUP.Interfaces.NavigationModule;

import java.sql.*;

/**
 * Class to manage user pins in the SQL database
 */
public class SQLUserPins {

    /**
     * Variables to connect to the DB
     */
    private final static String DB_URL = "jdbc:mysql://localhost/";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "root";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    private Connection connection;

    public SQLUserPins() {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Class.forName(myDriver);
            System.out.println("Connected to Database");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
