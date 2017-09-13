CREATE TABLE IF NOT EXISTS `opportunity_events` (
  `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  `opportunity` mediumint UNSIGNED NOT NULL,
  `event` varchar(30) NOT NULL,
  `status` varchar(30) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_opportunity_events_opportunity` (`opportunity`),
  CONSTRAINT `fk_opportunity_events_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;