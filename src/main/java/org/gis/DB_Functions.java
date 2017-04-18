package org.gis;

//For Integration to replace with the proper class
public class DB_Functions {
    public DB_Functions () {}

    public String getRoutes(double sLat, double sLong, double dLat, double dLong)
    {
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
        return route;
    }
}
