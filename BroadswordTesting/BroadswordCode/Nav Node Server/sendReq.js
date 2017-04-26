//example of sending start and end location to Navigation
var request = require('request');

request.post(
    'http://127.0.0.1:5000/accept',
    { json: { start: 'Humanities', end: 'Law' } },
    function (error, response, body) {
        if (!error && response.statusCode == 100) {
			console.log("\Route received (from start to end):\n");
			console.log("Start: "  + response.body.start);
			console.log("Middle: " + response.body.middle);
			console.log("End: " + response.body.end);
        }
		else
		{
			console.log("\Response undefined.\n");
		}
    }
);