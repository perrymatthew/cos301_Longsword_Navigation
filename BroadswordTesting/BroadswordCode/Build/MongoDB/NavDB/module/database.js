var mongodb = require('mongodb');
var MongoClient = mongodb.MongoClient;
var URL = 'mongodb://localhost:27017/NavgationDatabase';


var db;
var error;
var waiting = []; // Callbacks waiting for the connection to be made

MongoClient.connect(URL, function(err, database) {
    error = err;
    db = database;

    waiting.forEach(function(callback) {
        callback(err, database);
    });
});

module.exports = function(callback) {
    if (db || error) {
        callback(error, db);
    } else {
        waiting.push(callback);
    }
}