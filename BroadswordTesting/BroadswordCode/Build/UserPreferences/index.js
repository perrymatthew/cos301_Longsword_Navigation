//importing needed files
const express = require('express');
const bodyParser = require('body-parser');
const routes = require('./routes/api');
const mongoose = require('mongoose');

//set up express app
const app = express();

//connect to mongodb
mongoose.connect('mongodb://localhost/UserPreference');
//Because its been deprecated change it to global promise
mongoose.Promise = global.Promise;

//middleware used for reading request body
app.use(bodyParser.json());

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


































//
