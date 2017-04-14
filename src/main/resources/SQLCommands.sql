CREATE TABLE `cos301`.`routecache` (
  `idrouteCache` INT NOT NULL AUTO_INCREMENT COMMENT 'private key for table',
  `routeString` VARCHAR(2048) NOT NULL COMMENT 'the raw string of the route',
  `startPoint` VARCHAR(45) NOT NULL COMMENT 'string for the route\'s starting node',
  `endPoint` VARCHAR(45) NOT NULL COMMENT 'string for the route\'s ending node',
  `popularity` INT NOT NULL COMMENT 'the popularity of the route',
  PRIMARY KEY (`idrouteCache`),
  UNIQUE INDEX `idrouteCache_UNIQUE` (`idrouteCache` ASC))
COMMENT = 'This is the table used by Navigation to manage the caching of popular routes';

