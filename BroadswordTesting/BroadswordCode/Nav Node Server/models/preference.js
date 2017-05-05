//initial setup
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

//create preference Schema & model
const PreferenceSchema = new Schema({
    userID: {
      type: String,
      required: [true, 'Name field is required']
    },
    stairs: {
      type: Boolean,
      default: false
    },
    elevator: {
      type: Boolean,
      default: false
    },
    genderMale: {
      type: Boolean,
      default: false
    },
    mostPopular: {
      type: Boolean,
      default: false
    },
    leastTraffic: {
      type: Boolean,
      default: false
    },
    shortestPath: {
      type: Boolean,
      default: false
    }

});

//create model after the schema
const Preference = mongoose.model('preference', PreferenceSchema);

//this is so that it can be included elsewhere
module.exports = Preference;



























//
