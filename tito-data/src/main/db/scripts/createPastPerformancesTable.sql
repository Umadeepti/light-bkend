CREATE TABLE IF NOT EXISTS `past_performances` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `account` mediumint UNSIGNED NOT NULL,
      `project` mediumint UNSIGNED DEFAULT NULL,
      `project_name` varchar(30) NOT NULL,
      `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `note` varchar(1000) DEFAULT NULL,
      `writeup` varchar(2500) DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_past_performances_account` (`account`),
      KEY `fk_past_performances_project` (`project`),
      CONSTRAINT `fk_past_performances_account` FOREIGN KEY (`account`) REFERENCES `accounts` (`ID`),
      CONSTRAINT `fk_past_performances_project` FOREIGN KEY (`project`) REFERENCES `projects` (`ID`) ON DELETE SET NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;