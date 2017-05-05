/*
	This file is sample code FOR GIS to
	- get route request from navigation and
	- send route to navigation

	!!!IMPORTANT!!!

		"queryType" MUST BE "gisReceiveRoutes"

	!!!!!!!!!!!!!!!
*/

var nsq = require('nsqjs');

var reader = new nsq.Reader('gis', 'navup', { lookupdHTTPAddresses : '127.0.0.1:4161', nsqdTCPAddresses : 'localhost:4150' });
var w = new nsq.Writer('127.0.0.1', 4150);

reader.connect();

reader.on('message', function(msg) {

	try {
		var inJSON = JSON.parse(msg.body.toString());
	}
	catch (e)
	{
		msg.finish();
		return;
	}

	console.log('Received message in GIS [%s] : %s', msg.id, msg.body.toString());

	var qType = inJSON.queryType;
	console.log('\nQuery Type : %s', qType);

	if (qType == "gisGetRoutes")
	{
		console.log('Start location : %s', inJSON.content.begin);
		console.log('End location : %s', inJSON.content.end);

		//call GIS OR cache function here
		calculateRoute(inJSON.content.begin, inJSON.content.end);
	}

	msg.finish();
});


/*
	@Todo: send routes to nav
*/

function calculateRoute(inStart, inEnd) {
	
	//{"_id":"58e3e097bea9a52e44fd20e6","routeID":"1","beginPoint":"Humanities","endPoint":"Law","waypoints":[{"name":"Piazza"},{"name":"TuksFm"},{"name":"Centenary"}]}

	//Do all the GIS calculation stuff...
	//calculating...

    console.log("\nRoute successfully calculated HERE IN GIS.");

    var request = '{"src": "Gis", "dest": "Navigation", "msgType": "request", "queryType": "gisReceiveRoutes", "content": {"_id":"58f695afa6498028bcd69832","data":[{"type":"locations","id":"1","attributes":{"location_type":"Venue","room":"2-27","building":"IT","lng":-25.75599,"lat":28.233137,"level":2,"ground":2}},{"type":"locations","id":"2","attributes":{"location_type":"Entrance","room":"N/A","building":"IT","lng":-25.755869,"lat":28.233144,"level":2,"ground":2}},{"type":"locations","id":"3","attributes":{"location_type":"Point","room":"N/A","building":"N/A","lng":-25.755836,"lat":28.233162,"level":0,"ground":0}},{"type":"locations","id":"4","attributes":{"location_type":"Point","room":"N/A","building":"N/A","lng":-25.755811,"lat":28.233266,"level":0,"ground":0}},{"type":"locations","id":"5","attributes":{"location_type":"Point","room":"N/A","building":"N/A","lng":-25.755712,"lat":28.233275,"level":0,"ground":0}},{"type":"locations","id":"6","attributes":{"location_type":"Point","room":"N/A","building":"N/A","lng":-25.755623,"lat":28.233404,"level":0,"ground":0}},{"type":"locations","id":"7","attributes":{"location_type":"Point","room":"N/A","building":"N/A","lng":-25.755567,"lat":28.233193,"level":0,"ground":0}},{"type":"locations","id":"8","attributes":{"location_type":"Point","room":"N/A","building":"N/A","lng":-25.755528,"lat":28.233166,"level":0,"ground":0}},{"type":"locations","id":"9","attributes":{"location_type":"Point","room":"N/A","building":"N/A","lng":-25.755358,"lat":28.233218,"level":0,"ground":0}},{"type":"locations","id":"10","attributes":{"location_type":"Entrance","room":"N/A","building":"EMB","lng":-25.755391,"lat":28.233297,"level":2,"ground":3}}],"start":"IT2-27","end":"EMB","distance":"61.32"}}';

	w.connect();

	w.on('ready', function () {
		//# Simple publish method call. Publishes to users topic

		console.log('Writer opened');
		w.publish('navigation', request);
		console.log('Route request sent to navigation...');

		w.close();
	});

	w.on('closed', function () {
		console.log('Writer closed');
	});
}