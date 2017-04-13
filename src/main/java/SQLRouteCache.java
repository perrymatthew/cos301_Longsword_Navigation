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

    public boolean addRoute(String route) throws SQLException {
        String start, end;
        int pop = 0;

        String query = "insert into routecache (routeString, startPoint, endPoint, popularity)\n" +
                "values (\"" + route + "\",\"" + start + "\",\"" + end + "\"," + pop + ");";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
    }
}