//initial setup
const express = require('express');
const router = express.Router();
const Preference = require('../models/preference')

//get a list of users preferences from the database
router.get('/preferences/:id', function(req, res, next){
    Preference.find({userID: req.params.id}).then(function(preferences){
      res.send(preferences);
    });
});

//add new user preference to the db
router.post('/preferences', function(req, res, next){
    Preference.create(req.body).then(function(preference){
      res.send(preference);
    }).catch(next);
});

//update a users preference in the db
router.put('/preferences/:id', function(req, res, next){
  Preference.findByIdAndUpdate({_id: req.params.id}, req.body).then(function(){
    Preference.findOne({_id: req.params.id}).then(function(preference){
      res.send(preference);
    });
  });
});

//delete a users preference from the db
router.delete('/preferences/:id', function(req, res, next){
    Preference.findByIdAndRemove({_id: req.params.id}).then(function(preference){
      res.send(preference);
    });
});

  // attempt to make template for nsq
  // if (qType == "getPreferences")
  // {
  //   console.log('userID : %s', inJSON.content.userID);
  //   getUserPreferences(inJSON.content.userID);
  // }
  // else if(qType == "createUserPreference")
  // {
  //   console.log('userID : %s', inJSON.content.preferenceObject);
  //   createUserPreference(inJSON.content.preferenceObject);
  // }
  // else if(qType == "updateUserPreference")
  // {
  //   console.log('userID : %s', inJSON.content.preferenceID);
  //   console.log('userID : %s', inJSON.content.preferenceObject);
  //   updateUserPreference(inJSON.content.preferenceID, inJSON.content.preferenceObject);
  // }
  // else if(qType == "deleteUserPreference")
  // {
  //   console.log('userID : %s', inJSON.content.preferenceID);
  //   deleteUserPreference(inJSON.content.preferenceID);
  // }
  //
  // function getUserPreferences(uid)
  // {
  //   Preference.find({userID: uid}).then(function(preferences){
  //     res.send(preferences);
  //   });
  // }
  //
  // function createUserPreference(pObj)
  // {
  //   Preference.create(pObj).then(function(preference){
  //     res.send(preference);
  //   }).catch(next);
  // }
  //
  // function updateUserPreference(pid, pObj)
  // {
  //   Preference.findByIdAndUpdate({_id: pid}, pObj).then(function(){
  //     Preference.findOne({_id: pid}).then(function(preference){
  //       res.send(preference);
  //     });
  //   });
  // }
  //
  // function deleteUserPreference(pid)
  // {
  //   Preference.findByIdAndRemove({_id: pid}).then(function(preference){
  //     res.send(preference);
  //   });
  // }


//Allows ability to import in index
module.exports = router;


































//
