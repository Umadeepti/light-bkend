CREATE TABLE IF NOT EXISTS `opportunity_past_performances` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `opportunity` mediumint UNSIGNED NOT NULL,
      `past_performance` mediumint UNSIGNED NOT NULL,
      `note` varchar(1000) DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_opportunity_past_performances_opportunity` (`opportunity`),
      KEY `fk_opportunity_past_performances_past_performance` (`past_performance`),
      CONSTRAINT `fk_opportunity_past_performances_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`),
      CONSTRAINT `fk_opportunity_past_performances_past_performance` FOREIGN KEY (`past_performance`) REFERENCES `past_performances` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;