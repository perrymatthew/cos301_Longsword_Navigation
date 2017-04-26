/**
 * Class Overview:
 * The SQLUserPins class is where a users pins will be stored in an SQL database.
 * These pins can be returned to access to be displayed as custom locations
 */

/**
 * @author Duart Breedt
 * @version 1
 */

package NavUP.Interfaces.NavigationModule;

import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;

public class SQLUserPins {

    /**
     * Variables to connect to the DB
     */
    private final static String DB_URL = "jdbc:mysql://localhost:3306/Navigation";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    private Connection connection;

    /**
     * The SQLUserPins constructor which connects to the SQL database
     */
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

    /**
     * Adds a pin to the SQL users pin table
     * @param pin The JSON string specifying the pin and user information
     */
    public void dropPin(String pin) {
        try {

            JSONObject json = new JSONObject(pin);

            String userIdVar = json.getString("userID");
            JSONObject pinVar = json.getJSONObject("pin");
            Double latVar = pinVar.getDouble("lat");
            Double lonVar = pinVar.getDouble("long");
            String pinNameVar = pinVar.getString("customName");

            String query = "INSERT INTO `userpins`(userID, lat, lon, pinName) VALUE (?, ?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(query);
            insert.setString(1, userIdVar);
            insert.setDouble(2, latVar);
            insert.setDouble(3, lonVar);
            insert.setString(4, pinNameVar);
            insert.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a specific pin from SQL user pins table
     * @param pin The pin that should be found and removed
     */
    public void removePin(String pin) {
        try {

            JSONObject json = new JSONObject(pin);

            String userIdVar = json.getString("userID");
            JSONObject pinVar = json.getJSONObject("pin");
            Double latVar = pinVar.getDouble("lat");
            Double lonVar = pinVar.getDouble("long");
            String pinNameVar = pinVar.getString("customName");

            String query = "DELETE FROM `userpins` WHERE userID=? AND lat=? AND lon=? AND pinName=?";
            PreparedStatement delete = connection.prepareStatement(query);
            delete.setString(1, userIdVar);
            delete.setDouble(2, latVar);
            delete.setDouble(3, lonVar);
            delete.setString(4, pinNameVar);
            delete.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets all pins belonging to a specific user
     * @param userId The userId of the user whose pins should be fetched
     * @return A JSON string of the pins belonging to the user
     */
    public String getUserPins(String userId) {
        try {
            String query = "SELECT * FROM `userpins` WHERE userID='" + userId + "'";
            PreparedStatement select = connection.prepareStatement(query);
            ResultSet rs = select.executeQuery(query);

            JSONArray pinsArray = new JSONArray();

            while(rs.next()) {

                JSONObject pinObj = new JSONObject();

                pinObj.put("lat", rs.getDouble("lat"));
                pinObj.put("lon", rs.getDouble("lon"));
                pinObj.put("pinName", rs.getString("pinName"));

                pinsArray.put(pinObj);
            }
            JSONObject returnObj = new JSONObject();
            returnObj.put("pins", pinsArray);

            return returnObj.toString();

        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}