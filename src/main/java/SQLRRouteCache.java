import java.sql.Connection;
import java.sql.DriverManager;

public class SQLRRouteCache {
    private final String DB_PATH = "";
    private final String USERNAME = "";
    private final String PASSWORD = "";

    public SQLRRouteCache()
    {
        try {
            Connection connection = DriverManager.getConnection(DB_PATH, USERNAME, PASSWORD);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Connected to Database");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}