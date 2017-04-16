//Package for Navigation Module
package NavUP.Interfaces.NavigationModule;
//Importing the Interface from NavUP.Interfaces
import NavUP.Interfaces.NavigationInterface;

//Navigation class that implements the interface from NavigationInterface
public class Navigation implements NavigationInterface {
    //Default constructor for the Navigation class
    public Navigation () {

    }

    //Get the route from the GIS interface and return it in a JSON string format
    public String getRoute (String pointLocations) {
        return "";
    }

    //Use the custom name (pin) created by a user for future route references
    public void dropPin(String pin) {

    }

    //Removal of the custom location name (pin) which was created by the user
    public void removePin(String pin) {

    }
}