/*
	This file is sample code FOR ACCESS to get routes from navigation

	!!!IMPORTANT!!!

		"queryType" MUST BE "getRoutes"

	!!!!!!!!!!!!!!!
*/

var nsq = require('nsqjs');

var reader = new nsq.Reader('access', 'navup', { lookupdHTTPAddresses : '127.0.0.1:4161', nsqdTCPAddresses : 'localhost:4150' });
var w = new nsq.Writer('127.0.0.1', 4150);

w.connect();

var request = '{"src": "Access", "dest": "Navigation", "msgType": "request", "queryType": "getRoutes", "content": {"begin": "IT2-27", "end": "EMB"}}';

w.on('ready', function () {
	//# Simple publish method call. Publishes to users topic

	console.log('Writer opened');
	w.publish('navigation', request);
	console.log('Route request sent to navigation...');
	
    w.close();
});

w.on('closed', function () {
	console.log('Writer closed\n');
});

reader.connect();

reader.on('message', function(msg) {

	try {
		var inJSON = JSON.parse(msg.body.toString());
	}
	catch (e)
	{
		console.log('There was a problem: ' + e);
		msg.finish();
		return;
	}

	console.log('Received message in ACCESS [%s] : %s', msg.id, msg.body.toString());

	var qType = inJSON.queryType;
	console.log('\nQuery Type : %s', qType);

	if (qType == "navRoutes")
	{
		console.log('Start location FROM NAVIGATION: %s', inJSON.content.start);
		console.log('End FROM NAVIGATION: %s', inJSON.content.end);
		console.log('Middle / path of route FROM NAVIGATION: %s', inJSON.content.data);
	}

	msg.finish();
});