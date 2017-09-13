CREATE TABLE IF NOT EXISTS `solutions` (
  			`ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  			`name` varchar(30) NOT NULL,
  			`description` varchar(1000) DEFAULT NULL,
  			PRIMARY KEY (`ID`),
  			CONSTRAINT `unique_solution` UNIQUE (`name`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;