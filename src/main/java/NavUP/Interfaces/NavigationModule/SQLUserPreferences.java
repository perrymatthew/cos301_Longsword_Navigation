//Package for Navigation Module
 user preferences and favourite routes
public class SQLUserPreferences {
    //Variables to connect to the DB
    private final static String DB_URL = "";
    private final static String USERNAME = "admin";
    pr
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
