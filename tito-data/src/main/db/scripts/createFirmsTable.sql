CREATE TABLE IF NOT EXISTS `firms` (
  			`ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  			`name` varchar(60) NOT NULL,
  			`description` varchar(1000) DEFAULT NULL,
  			PRIMARY KEY (`ID`),
  			CONSTRAINT `unique_firm` UNIQUE (`name`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;