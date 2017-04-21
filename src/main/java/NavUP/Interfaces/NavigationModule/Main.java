//Package for Navigation Module
package NavUP.Interfaces.NavigationModule;

//Will not be included in the Master Branch
public class Main {
    //Main to test the functionality of everything
    public static void main(String[] args) {
        //All strings below follow a JSON formatting
        //String to simulate GIS getRoute return type
        String route =
                "{"
                        + " \"routes\":"
                        + " ["
                        + "     {"
                        + "         \"length\": \"10\","
                        + "         \"waypoints\":"
                        + "         ["
                        + "             {"
                        + "                 \"lat\": \"1\","
                        + "                 \"long\": \"2\""
                        + "             },"
                        + "             {"
                        + "                 \"lat\": \"3\","
                        + "                 \"long\": \"4\""
                        + "             },"
                        + "             {"
                        + "                 \"lat\": \"5\","
                        + "                 \"long\": \"6\""
                        + "             }"
                        + "         ],"
                        + "     },"
                        + "     {"
                        + "         \"length\": \"30\","
                        + "         \"waypoints\":"
                        + "         ["
                        + "             {"
                        + "                 \"lat\": \"7\","
                        + "                 \"long\": \"8\""
                        + "             },"
                        + "             {"
                        + "                 \"lat\": \"9\","
                        + "                 \"long\": \"10\""
                        + "             },"
                        + "             {"
                        + "                 \"lat\": \"11\","
                        + "                 \"long\": \"12\""
                        + "             },"
                        + "         ],"
                        + "     },"
                        + "     {"
                        + "         \"length\": \"50\","
                        + "         \"waypoints\":"
                        + "         ["
                        + "             {"
                        + "                 \"lat\": \"13\","
                        + "                 \"long\": \"14\""
                        + "             },"
                        + "             {"
                        + "                 \"lat\": \"15\","
                        + "                 \"long\": \"16\""
                        + "             },"
                        + "             {"
                        + "                 \"lat\": \"17\","
                        + "                 \"long\": \"18\""
                        + "             }"
                        + "         ]"
                        + "     }"
                        + " ]"
                        +
                "}";
        //String to test getRoute functionality
        String pointLocations1 =
                "{"
                        + " \"userID\": \"user01\","
                        + " \"source\":"
                        + " {"
                        + "     \"lat\": \"1\","
                        + "     \"long\": \"2\""
                        + " },"
                        + " \"destination\":"
                        + " {"
                        + "     \"lat\": \"5\","
                        + "     \"long\": \"6\""
                        + " },"
                        + " \"restrictions\":"
                        + " {"
                        + "     \"isDisabled\": \"false\""
                        + " },"
                        + " \"preferences\":"
                        + " {"
                        + "     \"maximumRouteLength\": \"20\""
                        + " }"
                        +
                "}";
        String pointLocations2 =
                "{"
                        + " \"userID\": \"user02\","
                        + " \"source\":"
                        + " {"
                        + "     \"lat\": \"1\","
                        + "     \"long\": \"2\""
                        + " },"
                        + " \"destination\":"
                        + " {"
                        + "     \"lat\": \"5\","
                        + "     \"long\": \"6\""
                        + " },"
                        + " \"restrictions\":"
                        + " {"
                        + "     \"isDisabled\": \"true\""
                        + " },"
                        + " \"preferences\":"
                        + " {"
                        + "     \"maximumRouteLength\": \"15\""
                        + " }"
                        +
                        "}";
        //Strings to test pin functionality
        String pin1 =
                "{"
                        + " \"userID\": \"user01\","
                        + " \"pin\":"
                        + " {"
                        + "     \"lat\": \"1\","
                        + "     \"long\": \"2\","
                        + "     \"customName\": \"DatPoint\""
                        + " }"
                        +
                "}";
        String pin2 =
                "{"
                        + " \"userID\": \"user02\","
                        + " \"pin\":"
                        + " {"
                        + "     \"lat\": \"5\","
                        + "     \"long\": \"6\","
                        + "     \"customName\": \"DatPoint\""
                        + " }"
                        +
                "}";
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(">><><><><><><><><><><><><><><< Navigation Module >><><><><><><><><><><><><><<");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Responsibility:");
        System.out.println("1) Take a start, end, userID, user preference and restrictions to getRoute");
        System.out.println("2) First check if route is in the cache");
        System.out.println("3.1) If it is then simply return it from the DB");
        System.out.println("3.2) If it is not then call getRoute using the GIS interface to return a route");
        System.out.println("4) Return that route to the Access module");
        System.out.println("5) Allow users to add a pin and remove a pin");
        System.out.println("6) Return all of a users pins");
        System.out.println("");

        //Navigation object instance
        Navigation navModule = new Navigation();
        System.out.println("");

        //Add a route to the cache
        System.out.println(navModule.getRoute(pointLocations1));
        System.out.println("");
        System.out.println(navModule.getRoute(pointLocations2));
        System.out.println("");

        //Add a pin, display all of the pins and then remove the pins
        navModule.dropPin(pin1);
        navModule.dropPin(pin1);
        navModule.dropPin(pin2);
        navModule.dropPin(pin2);
        navModule.removePin(pin2);
        System.out.println(navModule.getUserPins(pin1));
        System.out.println("");

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(">><><><><><><><><><><><><><><< Navigation Module >><><><><><><><><><><><><><<");
        System.out.println("-----------------------------------------------------------------------------");
    }
}