var MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/NavgationDatabase';


//-----------------------------------------------------------
//         Variables Needed For Cached Paths Searching
//-----------------------------------------------------------
var pathResolve;
var cachedRouteAvalilable = false; // Default to no route available in cache.
var pathReturned = [];

// Function to allow promise variable to be created exturnally with the resolution specified below.
var localPatths = new Promise((resolve, reject) => {
    pathResolve = resolve;
});


//-----------------------------------------------------------

checkForCahcedRoute("Humanities", "Law");

function checkForCahcedRoute(beginPoint, endPoint) {

    MongoClient.connect(url, function(err, db) {
        db.collection('routes').find({ "beginPoint": beginPoint }).toArray(function(err, results) {
            try {
                var paths = [];
                paths.push(results);
                pathResolve(paths);
            } catch (error) {
                console.log("========================================================");
                console.log("Catch error occured when connecting to MongoClient to get query results");
                console.log("========================================================");
                console.log(error);
                console.log("========================================================");
            }
        });
        db.close(); // Close the DB connection
    });



    return localPatths.then(pathConsumer(beginPoint, endPoint));

}

/*
    The path consumer function is responsible for receiving the promise Object
    called localPaths and enforces the .then() function on it to ensure that the
    aysnc call has been completed to the database to get the path specified.
    @params
        bp - The begin point coordinate (in this case the string name)
        ep - The end point coordinate (in this case the string name)
*/
function pathConsumer(bp, ep) {

    localPatths.then(pathsArray => {

        pathsArray.forEach(path => {

            var beginPoint = path[0]['beginPoint'];
            var endPoint = path[0]['endPoint'];
            if (beginPoint == bp && endPoint == ep) {
                pathReturned.push(path);
                cachedRouteAvalilable = true;

            }
        })
        pathStatus(); // This will print out if the route was found or not based on the cachedRouteAvalilable status

    })
}


function pathStatus() {
    var fs = require('fs');
    if (cachedRouteAvalilable) {
        console.log("The route is available in local storage. Path is being returned.");
        console.log("----------------------------------------------------------------");
        console.log(pathReturned[0][0]);
        console.log("----------------------------------------------------------------");



        var stream = fs.createWriteStream("cachedRoute.txt");
        stream.once('open', function(fd) {
            stream.write("cachedRouteAvalilable : True\n");
            stream.write("The route is available in local storage. Path is being returned.\n");
            stream.write(JSON.stringify(pathReturned[0][0]));
            stream.end();
        });



    } else {
        console.log("We do not have the path cached. Request the path from GIS.");
        var stream = fs.createWriteStream("cachedRoute.txt");
        stream.once('open', function(fd) {
            stream.write("cachedRouteAvalilable : False\n");
            stream.write("We do not have the path cached. Request the path from GIS.\n");
            //stream.write(JSON.stringify(pathReturned[0][0]) + "\ n ");
            stream.end();
        });
        fs.close();
    }

}