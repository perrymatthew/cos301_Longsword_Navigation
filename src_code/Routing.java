public class Routing implements Navigation
{
    private DBRouteCache routeCache = new DBRouteCache();
    
    public void getFromGIS (ArrayList<Waypoint> gisList) {
        listOfNodes = gisList;
    }
}
