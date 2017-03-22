import java.util.ArrayList;

public class Navigation {

    private DBPreferences user_Pref = new DBPreferences();
    private DBRouteCache cache = new DBRouteCache();
    private RouteObj result = new RouteObj();
    private Waypoint start = new Waypoint();
    private Waypoint end = new Waypoint();
    private Routing calculate = new Routing();
    private ArrayList<Waypoint> listOfNodes = new ArrayList<Waypoint>();

    public ArrayList<Waypoint> getRoute() {
        buildRoute();
        return result.getRoute();
    }

    public boolean addPref (boolean[] pref) {
        user_Pref.addPref("", pref);

        return true;
    }

    public boolean addCache (ArrayList<Waypoint> list) {
        cache.addRoute(list);

        return true;
    }

    public void getFromAccess (Waypoint s, Waypoint e) {
        start = s;
        end = e;

        System.out.println("Start Node: " + s.getName());
        System.out.println("End Node: " + e.getName() + "\n");
    }

    public void getFromGIS (ArrayList<Waypoint> gisList) {
        listOfNodes = gisList;
    }

    public void buildRoute () {
        if (!result.isEmpty()) {

            result.clearRoute();
            result = new RouteObj(calculate.calculateRoute(start, end, listOfNodes, user_Pref));
        }
        else {
            result = new RouteObj(calculate.calculateRoute(start, end, listOfNodes, user_Pref));
        }
    }

    public String displayRoute(){
        String dis = "Route: \n";

        for (int i = 0; i < result.route.size(); i++) {
            dis += result.route.get(i).getName() + " ";
        }

        dis += "\n";

        Double val = (Math.random() * 10);

        if (result.route.size() != 0) {
            dis += "Total Distance: " + val.toString() + "\n";
        }
        else {
            dis = "Empty Route \n";
        }

        return dis;
    }
}