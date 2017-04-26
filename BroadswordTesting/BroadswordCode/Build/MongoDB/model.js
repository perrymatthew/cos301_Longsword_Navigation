const mongoose = require('Mongoose');
const schema = mongoose.Schema;

const locationSchema = new Schema({
    name: String,
    x: String,
    y: String,
    z: String
});

const location = mongoose.model('location', locationSchema);

module.exports = location;