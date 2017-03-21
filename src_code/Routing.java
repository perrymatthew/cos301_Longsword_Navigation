import java.util.Random;

public class Routing implements Navigation
{
    private DBRouteCache routeCache = new DBRouteCache();

    private String start_Point = "";
    private String end_Point = "";
    private Double distance = 0.0;
    private Routing route = new Routing();

    public Routing(){}

    public Routing calculateRoute(String start, String end){
        start_Point = start;
        end_Point = end;

        distance = Math.random();

        return route;
    }

    public String display_Route(){
        String dis = start_Point + end_Point + Double.toString(distance);
        return dis;
    }
}