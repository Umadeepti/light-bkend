CREATE TABLE IF NOT EXISTS `opportunity_incumbents` (
  	`ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  	`firm` mediumint UNSIGNED NOT NULL,
  	`opportunity` mediumint UNSIGNED NOT NULL,
  	PRIMARY KEY (`ID`),
 	KEY `fk_opportunity_incumbents_firm` (`firm`),
  	KEY `fk_opportunity_incumbents_opportunity` (`opportunity`),
  	CONSTRAINT `fk_opportunity_incumbents_firm` FOREIGN KEY (`firm`) REFERENCES `firms` (`ID`),
  	CONSTRAINT `fk_opportunity_incumbents_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;