CREATE TABLE IF NOT EXISTS `clearances` (
  			`ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  			`name` varchar(30) NOT NULL,
  			`description` varchar(1000) DEFAULT NULL,
  			`issuing_body` varchar(30) DEFAULT NULL, 
  			`agency` varchar(20) NOT NULL,
  			PRIMARY KEY (`ID`),
  			CONSTRAINT `unique_clearance` UNIQUE (`name`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;