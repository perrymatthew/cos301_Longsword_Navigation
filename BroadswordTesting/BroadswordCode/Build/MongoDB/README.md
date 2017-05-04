The mongo DB is used to store the preferences of the users as well as the routes that are calculated.

---------------------------------
The user preferences data
---------------------------------
  - userID
  - preferences
      -- Stairs (Include them in route or not)
    -- Shortest route
    -- Minimal Traffic Encountered 

---------------------------------
The route data
---------------------------------
  - All the waypoints between the start and then end will be stored in a JSON string
  - The Start and End will be stored separately as to make it easier to do calculations. 
      For example find all the routes that have the same starting point.
-------------------------------------------------------------------------------------------

Things to work on:
  - Make non-hardcoded functions 
  - Allow for parameters in query calls 