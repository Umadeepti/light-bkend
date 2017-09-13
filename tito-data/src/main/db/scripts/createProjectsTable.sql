CREATE TABLE IF NOT EXISTS `projects` (
  `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `contract` mediumint UNSIGNED NOT NULL,
  `manager` mediumint UNSIGNED DEFAULT NULL,
  `summary` varchar(2000) DEFAULT NULL,
  `keywords` varchar(1000) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `award_amount` int UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_projects_contract` (`contract`),
  KEY `fk_projects_manager` (`manager`),
  CONSTRAINT `unique_opportunity` UNIQUE (`name`),
  CONSTRAINT `fk_projects_contract` FOREIGN KEY (`contract`) REFERENCES `contracts` (`ID`),
  CONSTRAINT `fk_projects_manager` FOREIGN KEY (`manager`) REFERENCES `people` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;