var express = require('express');
var bodyParser = require('body-parser');
var request = require('request');
var http=require('http');

var MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/NavgationDatabase';
var assert = require('assert');
var port = 5000;

const routes = require('./routes/api');
const mongoose = require('mongoose');

var app = express();
app.use(bodyParser.json());

var nsq = require('nsqjs');
var reader = new nsq.Reader('navigation', 'navup', { lookupdHTTPAddresses : '127.0.0.1:4161', nsqdTCPAddresses : 'localhost:4150' });
var w1 = new nsq.Writer('127.0.0.1', 4150);
var w2 = new nsq.Writer('127.0.0.1', 4150);

var flagForCache = false;

/*
	This function gets a requests from Access and GIS
*/

reader.connect();

reader.on('message', function(msg) {

	try
	{
		var inJSON = JSON.parse(msg.body.toString());
	}
	catch (e)
	{
		msg.finish();
		return;
	}

	console.log('Received message [%s] : %s', msg.id, inJSON);

	var qType = inJSON.queryType;
	console.log('Query Type : %s', qType);

	if (qType == "getRoutes")
	{
		console.log('Start location : %s', inJSON.content.begin);
		console.log('End location : %s', inJSON.content.end);

		//call GIS OR cache function here
		retrieveRoute(inJSON.content.begin, inJSON.content.end);
	}
	else if (qType == "gisReceiveRoutes")
	{
		if (flagForCache)
		{
			//This response was for cache validation purposes
			flagForCache = false;
			validationContinued(inJSON.content.data);
		}
		else
		{
			console.log('\nReceived route from GIS!\n');
			console.log('Start location of route: %s', inJSON.content.start);
			console.log('End location of route: %s', inJSON.content.end);

			console.log('\nNow sending GIS route back to Access...\n');

			var request = '{"src": "Navigation", "dest": "Access", "msgType": "request", "queryType": "navRoutes", "content": {"begin": "' + inJSON.content.start.toString() + '", "end": "' + inJSON.content.end.toString() + '", "data": ' + JSON.stringify(inJSON.content.data) + '}}';

			w2.connect();

			w2.on('ready', function () {
			  //# Simple publish method call. Publishes to users topic
			  
				console.log('\nNav Writer opened');
				w2.publish('access', request);
				console.log('Route request sent to ACCESS...');

			});

			w2.on('closed', function () {
				console.log('Nav Writer closed\n');

				//Important to note: at the moment GIS isn't returning any valid routes, so this system is commented out:

				//validateCachedRouteFor(inJSON.content.start, inJSON.content.end);
			});
		}
	}

	msg.finish();
});

//this function is called from by Access. Here we get route either from cache or from GIS
function retrieveRoute(inStart, inEnd) {
    /*
    	Here the client will be communicating with either:
    	1) The DB to get the route if it already exists
    	2) The path finding algorithm to create a new path AND then store it to DB
	*/

	sendStartAndEndToGIS(inStart, inEnd);
}

/*
	@Todo: sendStartAndEndToGIS sends a request to GIS with the json object containing end and start point

	!!!IMPORTANT!!!

		"queryType" MUST BE "gisGetRoutes"

	!!!!!!!!!!!!!!!
*/

function sendStartAndEndToGIS(start_, end_) {
	console.log('\nSending route request to GIS...');

	var request = '{"src": "Navigation", "dest": "Gis", "msgType": "request", "queryType": "gisGetRoutes", "content": {"begin": "' + start_ +'", "end": "' + end_ + '"}}';

	w1.connect();

	w1.on('ready', function () {
	  //# Simple publish method call. Publishes to users topic
	  
		console.log('Nav Writer opened');
		w1.publish('gis', request);
		console.log('Route request sent to GIS...');

		w1.close();
	});

	w1.on('closed', function () {
		console.log('Nav Writer closed\n');
	});
}

//===========================================================================
//	CASHING CODE:
//===========================================================================

//-----------------------------------------------------------
//         Variables Needed For Cached Paths Searching
//-----------------------------------------------------------
var pathResolve;
var cachedRouteAvalilable = false; // Default to no route available in cache.
var pathReturned = [];
// ----------------------------------------------------------

// Function to allow promise variable to be created exturnally with the resolution specified below.
var localPatths = new Promise((resolve, reject) => {
    pathResolve = resolve;
});

function checkForCahcedRoute(beginPoint, endPoint) {
    MongoClient.connect(url, function(err, db) {
        db.collection('routes').find({ "start": beginPoint }).toArray(function(err, results) {
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

            var beginPoint = path[0]['start'];
            var endPoint = path[0]['end'];
            if (beginPoint == bp && endPoint == ep)
            {
                pathReturned.push(path);
                cachedRouteAvalilable = true;
            }
        })
        localPatths.then(pathStatus()); // This will print out if the route was found or not based on the cachedRouteAvalilable status
    })
}

function pathStatus() {
	localPatths.then(function() {
		var fs = require('fs');

	    if (cachedRouteAvalilable)
	    {
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
	        
	        fs.close();

	    	validateCachedRouteFor("IT2-27", "EMB");
	    }
	    else
	    {
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
    })
}

/*
    @function validateCachedRouteFor:: validates specific cached route
*/

function validateCachedRouteFor(beginPoint, endPoint)
{
	flagForCache = true;

	sendStartAndEndToGIS(beginPoint, endPoint);
}

function validationContinued(routeFromGIS)
{
	var inFromGIS = JSON.stringify(routeFromGIS);
	checkForCahcedRoute("IT2-27", "EMB");

	localPatths.then( function () {
			//Now read waypoints from text and store in memory
			console.log("Here");
		    fs = require('fs');

			fs.readFile('cachedRoute.txt', 'utf8', function (err,data)
		    {
		        if (err)
		        {
		            console.log("There was an error reading from routes: " + err);
		            return;
		        }
		        if (data != "")
		        {
					console.log("Full data read: " + data);

			        //read waypoints here

			        var t = data.split('The route is available in local storage. Path is being returned.')[1];
			        var cacheJSON = JSON.parse(t.toString());

			        if (inFromGIS == JSON.stringify(cacheJSON.data))
			        {
			        	console.log("Cahed route is correct - don't change.");
			        }
			        else
			        {
			        	console.log("Cahed route is INcorrect - change needed.");

			        	MongoClient.connect(url, function(err, db) {
						    assert.equal(null, err);

						    addRoute(
					    		cacheJSON.start, 
					    		cacheJSON.end, 
					    		'{"data": ' + inFromGIS + "}", 
					    		db, 
					    		removeCachedRoute(
							    	JSON.stringify(cacheJSON.start), 
							    	db, 
						    		function() 
						    		{
						    			console.log("Old cashed route deleted.");
						    		}
							    )
					    	);					    

						    db.close();
						});
			        }

			        return;
		        }
		        else
		        {
		        	console.log("Adding new cached route");

		        	MongoClient.connect(url, function(err, db) {
					    assert.equal(null, err);

					    addRoute(
				    		cacheJSON.start, 
				    		cacheJSON.end, 
				    		'{"data": ' + inFromGIS + "}", 
				    		db, 
				    		function() {}
				    	);					    

					    db.close();
					});
		        }
		    });
		}
	);
}

/************************************************************************************
*   This function formats the route data received from GIS
*   This function takes one parameter jsonData being the JSON string received
*   from GIS and the names of the start and end points
*   This function extracts the latitude and longitude and calls the helper
*   function to calculate the distance. The start point, end point and distance
*   is then added to the JSON object which is then returned
************************************************************************************/

function formatData(jsonData, startPoint, endPoint)
{
    jsonData = JSON.parse(jsonData);
    var lat1 = jsonData.data[0].attributes.lat;
    var lng1 = jsonData.data[0].attributes.lng;
	
    var lat2 = jsonData.data[jsonData.data.length-1].attributes.lat;
    var lng2 = jsonData.data[jsonData.data.length-1].attributes.lng;
	
    var dist = distance(lat1, lng1, lat2, lng2);
	
    jsonData.start = startPoint;
    jsonData.end = endPoint;
    jsonData.distance = dist;
	
    return jsonData;
}

/*************************************************************************************
*   This function calculates the distance between 2 coordinates.
*   lat1, lat2 = the decimal latitudinal value
*   lon1, lon2 = the decumal longitudinal value
*   This function returns the distance in metres fixed at 2 decimal places
*************************************************************************************/

function distance(lat1, lon1, lat2, lon2) 
{
    var radlat1 = Math.PI * lat1/180
    var radlat2 = Math.PI * lat2/180
    var theta = lon1-lon2
    var radtheta = Math.PI * theta/180
    var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
    dist = Math.acos(dist)
    dist = dist * 180/Math.PI
    dist = dist * 60 * 1.1515
    dist = dist * 1.609344
    dist = dist * 1000;
    return dist.toFixed(2);
}

/*************************************************************************************
*   This function calls the helper function to format the JSON data and inserts
*   it into the mongo db database.
*   This funciton takes the starting point, end point, the JSON route string,
*   the database and a callback function
*************************************************************************************/

var addRoute = function (startPoint, endPoint, jsonData, db, callback)
{
    jsonData = formatData(jsonData, startPoint, endPoint);

    console.log("Adding: " + JSON.stringify(jsonData));

    db.collection('routes').insertOne(jsonData,
        function(err, results) {
            //console.log(results);
            //callback();
            console.log("Cashed route updated.");
        }
    );
}

function removeCachedRoute (startPoint, db, callback) {

    console.log("Deleting: " + startPoint);
    db.collection('routes').remove({"start": startPoint},
        function(err, results) {
            //console.log(results);
            callback();
        }
    );
};

//===========================================================================
//	USER PREFERENCE CODE:
//===========================================================================

//initialize routes
app.use('/api', routes);

//error handling middleware
app.use(function(err, req, res, next){
  console.log(err);
  res.status(422).send({error: err.message});
});

//listen for requests
app.listen(process.env.port || 4000, function(){
  console.log("Listening for requests");
});

var server = app.listen(port, function() {
    var host = server.address().address;
    var port = server.address().port;

    console.log("App listening", host, port)
})


//***************************************************TEMP FOR NOW***************************************************
//Important to note: at the moment GIS isn't returning any valid routes, so this system is commented out:

//validateCachedRouteFor("IT2-27", "EMB");

//***************************************************TEMP FOR NOW***************************************************
