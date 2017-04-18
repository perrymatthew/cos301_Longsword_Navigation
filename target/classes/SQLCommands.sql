CREATE TABLE `Navigation`.`routecache` (
  `idrouteCache` INT NOT NULL AUTO_INCREMENT COMMENT 'private key for table',
  `routeString` VARCHAR(2048) NOT NULL COMMENT 'the raw string of the route',
  `startPoint` VARCHAR(45) NOT NULL COMMENT 'string for the route\'s starting node',
  `endPoint` VARCHAR(45) NOT NULL COMMENT 'string for the route\'s ending node',
  `popularity` INT NOT NULL COMMENT 'the popularity of the route',
  PRIMARY KEY (`idrouteCache`),
  UNIQUE INDEX `idrouteCache_UNIQUE` (`idrouteCache` ASC))
COMMENT = 'This is the table used by Navigation to manage the caching of popular routes';

CREATE TABLE `Navigation`.`userpins` (

  `pinID` INT NOT NULL,

  `userID` VARCHAR(45) NOT NULL,

  `lat` INT NOT NULL,

  `lon` INT NOT NULL,

  `pinName` VARCHAR(45) NOT NULL,

  PRIMARY KEY (`pinID`))

COMMENT = 'this table will store the details for a users pins';

CREATE TABLE `Navigation`.`preferences` (
  `userID` VARCHAR(45) NOT NULL COMMENT 'unique id for the user',

  `preferences` DOUBLE NOT NULL COMMENT 'maximum length a user wants path to be',

  `restrictions` BIT NOT NULL COMMENT 'indication of availability of preferred route',

  PRIMARY KEY (`userID`)
  )
