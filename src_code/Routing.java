import java.util.ArrayList;

public class Routing {

    private DBRouteCache routeCache = new DBRouteCache();

    public Routing(){}
  
    public void getFromGIS (ArrayList<Waypoint> gisList) {
        listOfNodes = gisList;
    }

    public ArrayList<Waypoint> calculateRoute(Waypoint start, Waypoint end, ArrayList<Waypoint> nodes, DBPreferences userPref){
        return nodes;
    }

    public RouteObj getCache() {
        RouteObj cachedRoute = new RouteObj();

        return cachedRoute;
    }
}
