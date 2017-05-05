package NavUP.Interfaces;

//NavigationInterface which will be used by other modules and will be implemented in the Navigation class
public interface NavigationInterface {
    public String getRoute(String pointLocations);
    public void dropPin(String pin);
    public void removePin(String pin);
}

//getRoute() sample input:
//pointLocations will be a JSON string with two Locations:
// There should be a way to differentiate between a static and dynamic preferences and restrictions.
// The static ones are in the navigation database which it will use to make decisions.
// The dynamic ones should possibly be specified by access according to user selections dynamically - customised to user.
// This function should also include a preferences and restrictions objects.
///{
//    userID: String,
//    source:
//    {
//        lat: double,
//        long: double
//    },
//
//    destination:
//    {
//        lat: double,
//        long: double
//    },
//
//    restrictions:
//    {
//        isDisabled: boolean
//    },
//
//    preferences:
//    {
//        maximumRouteLength: double
//    }
//}
//
//getRoute() sample output:
//We expect a JSON string with a single rout as an array of locations to be returned..of the form:
//{
//    length: double,
//    waypoints:
//    [
//        {
//            lat: double,
//            long: double
//        }
//        ,...
//    ]
//}
//

//getRoute() will make use of the GIS.getRoutes(String pointLocations) function:
//this function takes the same string you get just without the user ID
//this function returns an array of routes which you have to sort through and pick the best one.
//the function returns:
//{
//    routes:
//    [
//        {
//            length: double,
//            waypoints:
//            [
//                {
//                    lat: double,
//                    long: double
//                }
//                ,...
//            ]
//        }
//        ,...
//    ]
//}

//dropPin() input:
//this is a JSON string with the user identification token and a location being the pin:
// When dropping a pin a custom name can be specified by the user - Navigation should map all future references to this name to the
// original name of the location where the pin was dropped.
///{
//    userID: String,
//    pin:
//    {
//        lat: double,
//        long: double,
//        customName: String
//    }
//}

//removePin() input:
//this is a JSON string with the user identification token and a location being the pin:
// Access will provide functionality to remove a pin.
// There can only be one pin at a lat and long coordinate for a specific user.
// So on removal of a pin only the userID and the coordinates need to be specified.
///{
//    userID: String,
//    pin:
//    {
//        lat: double,
//        long: double
//    }
//}

//public String getUserPins (String userID)
//Json Format:
//
//Input Paramater: {userID:String}
//Output Return Value:
//{
//  pins:
//  [
//      {
//          lat: double,
//          long: double,
//          customName: String
//      },
//  ]