/*
	This file is sample code to get routes from mavigation FROM ACCESS
*/

var nsq = require('nsqjs');

var w = new nsq.Writer('127.0.0.1', 4150);

w.connect();

var request = '{"src"  : "Access", "dest" : "Navigation", "msgType" : "request","queryType" : "validateUser","content" : {"fname" : "John","sname" : "Doe","stud_num" : "15043143"}}';

w.on('ready', function () {
  //# Simple publish method call. Publishes to users topic
  
  w.publish('navigation', request);
  console.log('Writer opened');
});



w.on('closed', function () {
  console.log('Writer closed');
});