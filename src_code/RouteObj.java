import java.util.ArrayList;

public class RouteObj {

    public ArrayList<Waypoint> route;

    public RouteObj() {this.route = new ArrayList<Waypoint>();}

    public RouteObj(ArrayList<Waypoint> _route)
    {
        this.route = _route;
    }
    
    public ArrayList<Waypoint> getRoute()
    {
        return this.route;
    }

    public boolean clearRoute() {
        route.clear();
        return true;
    }

    public boolean isEmpty() {
        if (route.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}