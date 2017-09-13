CREATE TABLE IF NOT EXISTS `opportunity_steps` (
  `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  `opportunity` mediumint UNSIGNED NOT NULL,
  `name` varchar(30) NOT NULL,
  `decision` varchar(30) NOT NULL,
  `review_date` datetime DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `approver` mediumint UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_opportunity_steps_opportunity` (`opportunity`),
  KEY `fk_opportunity_steps_people_approver` (`approver`),
  CONSTRAINT `fk_opportunity_steps_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`),
  CONSTRAINT `fk_opportunity_steps_people_approver` FOREIGN KEY (`approver`) REFERENCES `people` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;