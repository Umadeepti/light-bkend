	CREATE TABLE IF NOT EXISTS `opportunity_timelines` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `opportunity` mediumint UNSIGNED NOT NULL,
      `response_type` varchar(30) NOT NULL,
      `milestone` varchar(30) NOT NULL,
      `date` datetime DEFAULT NULL,
      `note` varchar(1000) DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_opportunity_timelines_opportunity` (`opportunity`),
      CONSTRAINT `fk_opportunity_timelines_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;