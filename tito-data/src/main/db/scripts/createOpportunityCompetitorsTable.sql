CREATE TABLE IF NOT EXISTS `opportunity_competitors` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `firm` mediumint UNSIGNED NOT NULL,
      `opportunity` mediumint UNSIGNED NOT NULL,
      `likely_prime` boolean DEFAULT NULL,
	  `note` varchar(1000) DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_opportunity_competitors_firm` (`firm`),
      KEY `fk_opportunity_competitors_opportunity` (`opportunity`),
      CONSTRAINT `fk_opportunity_competitors_firm` FOREIGN KEY (`firm`) REFERENCES `firms` (`ID`),
      CONSTRAINT `fk_opportunity_competitors_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;