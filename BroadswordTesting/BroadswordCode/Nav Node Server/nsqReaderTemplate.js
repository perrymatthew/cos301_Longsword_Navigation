var nsq = require('nsqjs');

var reader = new nsq.Reader('navigation', 'navup', { lookupdHTTPAddresses : '127.0.0.1:4161', nsqdTCPAddresses : 'localhost:4150' });

reader.connect();

reader.on('message', function(msg) {
  console.log('Received message [%s] : %s', msg.id, msg.body.toString());
  msg.finish();
});