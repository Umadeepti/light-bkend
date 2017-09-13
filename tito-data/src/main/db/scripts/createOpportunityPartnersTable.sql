CREATE TABLE IF NOT EXISTS `opportunity_partners` (
  `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  `firm` mediumint UNSIGNED NOT NULL,
  `opportunity` mediumint UNSIGNED NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `exclusive` boolean DEFAULT NULL,
  `signed_nda` boolean DEFAULT NULL,
  `signed_ta` boolean DEFAULT NULL,
  `workshare` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_opportunity_partners_firm` (`firm`),
  KEY `fk_opportunity_partners_opportunity` (`opportunity`),
  CONSTRAINT `fk_opportunity_partners_firm` FOREIGN KEY (`firm`) REFERENCES `firms` (`ID`),
  CONSTRAINT `fk_opportunity_partners_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;