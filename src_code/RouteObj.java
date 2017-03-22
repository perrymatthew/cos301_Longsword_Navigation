
import java.util.ArrayList;

public class RouteObj
{
    ArrayList<Waypoint> route;
    
    public RouteObj(ArrayList<Waypoint> _route)
    {
        this.route = _route;
    }
    
    public ArrayList getRoute()
    {
        return this.route;
    }
}
