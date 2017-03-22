import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Navigation nav = new Navigation();

        ArrayList<Waypoint> entry = new ArrayList<Waypoint>();

        Waypoint w0 = new Waypoint();
        Waypoint w1 = new Waypoint();
        Waypoint w2 = new Waypoint();
        Waypoint w3 = new Waypoint();
        Waypoint w4 = new Waypoint();

        w0.setNode("Node0", 1.0, false, true);
        w1.setNode("Node1", 2.0, false, true);
        w2.setNode("Node2", 3.0, false, true);
        w3.setNode("Node3", 4.0, false, true);
        w4.setNode("Node4", 5.0, false, true);

        entry.add(0,w0);
        entry.add(1,w1);
        entry.add(2,w2);
        entry.add(3,w3);
        entry.add(4,w4);

        nav.getFromGIS(entry);
        nav.getFromAccess(w0, w4);
        nav.getRoute();

        System.out.println("Testing Route \n");

        System.out.print(nav.displayRoute());

        System.out.println("");

        System.out.println("Testing Cache \n");

        if (nav.addCache(entry)) {
            System.out.println("Cache Added\n");
        }
        else {
            System.out.println("Unsuccessful\n");
        }

        boolean[] prefs = new boolean[0];

        System.out.println("Testing Preferences \n");

        if (nav.addPref(prefs)) {
            System.out.println("");
        }
        else {
            System.out.println("Unsuccessful\n");
        }
    }
}