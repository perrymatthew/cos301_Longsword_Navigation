import java.util.Random;

public class Routing
{
    private DBRouteCache routeCache = new DBRouteCache();

    private String start_Point = "";
    private String end_Point = "";
    private Double distance = 0.0;
    private Routing route = new Routing();

    public Routing(){}

    public Routing getRoute(String start, String end){
        start_Point = start;
        end_Point = end;

        distance = Math.random();

        return route;
    }

    public String display_Route(){
        String dis = "Start Point: " + start_Point + " End Point: " + end_Point + " Distance Traveled: " + Double.toString(distance);
        return dis;
    }
}
