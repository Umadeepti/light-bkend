CREATE TABLE IF NOT EXISTS `person_clearances` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `person` mediumint UNSIGNED NOT NULL,
      `clearance` mediumint UNSIGNED NOT NULL,
      `issue_date` datetime NOT NULL,
      `expire_date` datetime DEFAULT NULL,
      `approver` mediumint UNSIGNED DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_person_clearances_person` (`person`),
      KEY `fk_person_clearances_clearance` (`clearance`),
      KEY `fk_person_clearances_approver` (`approver`),
      CONSTRAINT `fk_person_clearances_person` FOREIGN KEY (`person`) REFERENCES `people` (`ID`),
      CONSTRAINT `fk_person_clearances_clearance` FOREIGN KEY (`clearance`) REFERENCES `clearances` (`ID`),
      CONSTRAINT `fk_person_clearances_approver` FOREIGN KEY (`approver`) REFERENCES `people` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;