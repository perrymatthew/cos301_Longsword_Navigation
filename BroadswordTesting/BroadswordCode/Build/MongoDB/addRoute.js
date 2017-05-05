//"C:\Program Files\MongoDB\Server\3.4\bin\mongod.exe"

var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/NavgationDatabase';


//Mock data
// var data = {
//   "data": [
//     {
//       "type": "locations",
//       "id": "1",
//       "attributes": {
//         "location_type": "Venue",
//         "room": "2-27",
//         "building": "IT",
//         "lng": -25.75599,
//         "lat": 28.233137,
//         "level": 2,
//         "ground": 2
//       }
//     },
//     {
//       "type": "locations",
//       "id": "2",
//       "attributes": {
//         "location_type": "Entrance",
//         "room": "N/A",
//         "building": "IT",
//         "lng": -25.755869,
//         "lat": 28.233144,
//         "level": 2,
//         "ground": 2
//       }
//     },
//     {
//       "type": "locations",
//       "id": "3",
//       "attributes": {
//         "location_type": "Point",
//         "room": "N/A",
//         "building": "N/A",
//         "lng": -25.755836,
//         "lat": 28.233162,
//         "level": 0,
//         "ground": 0
//       }
//     },
//     {
//       "type": "locations",
//       "id": "4",
//       "attributes": {
//         "location_type": "Point",
//         "room": "N/A",
//         "building": "N/A",
//         "lng": -25.755811,
//         "lat": 28.233266,
//         "level": 0,
//         "ground": 0
//       }
//     },
//     {
//       "type": "locations",
//       "id": "5",
//       "attributes": {
//         "location_type": "Point",
//         "room": "N/A",
//         "building": "N/A",
//         "lng": -25.755712,
//         "lat": 28.233275,
//         "level": 0,
//         "ground": 0
//       }
//     },
//     {
//       "type": "locations",
//       "id": "6",
//       "attributes": {
//         "location_type": "Point",
//         "room": "N/A",
//         "building": "N/A",
//         "lng": -25.755623,
//         "lat": 28.233404,
//         "level": 0,
//         "ground": 0
//       }
//     },
//     {
//       "type": "locations",
//       "id": "7",
//       "attributes": {
//         "location_type": "Point",
//         "room": "N/A",
//         "building": "N/A",
//         "lng": -25.755567,
//         "lat": 28.233193,
//         "level": 0,
//         "ground": 0
//       }
//     },
//     {
//       "type": "locations",
//       "id": "8",
//       "attributes": {
//         "location_type": "Point",
//         "room": "N/A",
//         "building": "N/A",
//         "lng": -25.755528,
//         "lat": 28.233166,
//         "level": 0,
//         "ground": 0
//       }
//     },
//     {
//       "type": "locations",
//       "id": "9",
//       "attributes": {
//         "location_type": "Point",
//         "room": "N/A",
//         "building": "N/A",
//         "lng": -25.755358,
//         "lat": 28.233218,
//         "level": 0,
//         "ground": 0
//       }
//     },
//     {
//       "type": "locations",
//       "id": "10",
//       "attributes": {
//         "location_type": "Entrance",
//         "room": "N/A",
//         "building": "EMB",
//         "lng": -25.755391,
//         "lat": 28.233297,
//         "level": 2,
//         "ground": 2
//       }
//     }
//   ]
// }


// data = JSON.stringify(data);

// console.log(data);


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
    // console.log(jsonData);
    jsonData = JSON.parse(jsonData);
    var lat1 = jsonData.data[0].attributes.lat;
    var lng1 = jsonData.data[0].attributes.lng;
    // console.log(lat1 + "\n" + lng1)
    var lat2 = jsonData.data[jsonData.data.length-1].attributes.lat;
    var lng2 = jsonData.data[jsonData.data.length-1].attributes.lng;
    // console.log(lat2 + "\n" + lng2)
    var dist = distance(lat1, lng1, lat2, lng2);
    jsonData.start = startPoint;
    jsonData.end = endPoint;
    jsonData.distance = dist;

    console.log(jsonData);
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
var addRoute = function(startPoint, endPoint, jsonData, db, callback)
{
    jsonData = formatData(jsonData, startPoint, endPoint);

    db.collection('routes').insertOne(jsonData,
        function(err, results) {
            console.log(results);
            callback();
        }
    );
}

// formatData(data, "IT 2-27", "EMB");

MongoClient.connect(url, function(err, db) {
    assert.equal(null, err);
    addRoute("IT2-27", "EMB", data, db, function() {});

    db.close();
});