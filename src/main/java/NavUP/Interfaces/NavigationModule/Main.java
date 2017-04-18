//Package for Navigation Module
package NavUP.Interfaces.NavigationModule;

//Will not be included in the Master Branch
public class Main {
    //Main to test the functionality of everything
    public static void main(String[] args) {
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
        String pointLocations =
                "{"
                        + " \"userID\": \"user01\","
                        + " \"source\":"
                        + " {"
                        + "     \"lat\": \"10\","
                        + "     \"long\": \"20\""
                        + " },"
                        + " \"destination\":"
                        + " {"
                        + "     \"lat\": \"30\","
                        + "     \"long\": \"40\""
                        + " },"
                        + " \"restrictions\":"
                        + " {"
                        + "     \"isDisabled\": \"false\""
                        + " },"
                        + " \"preferences\":"
                        + " {"
                        + "     \"maximumRouteLength\": \"50\""
                        + " }"
                        +
                "}";
        //Strings to test pin functionality
        String pin1 =
                "{"
                        + " \"userID\": \"user01\","
                        + " \"pin\":"
                        + " {"
                        + "     \"lat\": \"10\","
                        + "     \"long\": \"20\","
                        + "     \"customName\": \"DatPoint\""
                        + " }"
                        +
                "}";
        String pin2 =
                "{"
                        + " \"userID\": \"user02\","
                        + " \"pin\":"
                        + " {"
                        + "     \"lat\": \"10\","
                        + "     \"long\": \"20\","
                        + "     \"customName\": \"DatPoint\""
                        + " }"
                        +
                "}";
        Navigation navModule = new Navigation();

        //navModule.dropPin(pin1);
    }
}