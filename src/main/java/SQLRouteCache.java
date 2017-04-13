import java.sql.*;

public class SQLRouteCache {
    private final static String DB_URL = "jdbc:mysql://localhost/";
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "root";
    private final static String myDriver = "org.gjt.mm.mysql.Driver";
    Connection connection;

    public SQLRouteCache()
    {
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Class.forName(myDriver);
            System.out.println("Connected to Database");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    
}