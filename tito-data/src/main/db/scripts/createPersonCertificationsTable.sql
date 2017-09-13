CREATE TABLE IF NOT EXISTS `person_certifications` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `person` mediumint UNSIGNED NOT NULL,
      `certification` mediumint UNSIGNED NOT NULL,
      `earn_date` datetime NOT NULL,
      `expire_date` datetime DEFAULT NULL,
      `approver` mediumint UNSIGNED DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_person_certifications_person` (`person`),
      KEY `fk_person_certifications_certification` (`certification`),
      KEY `fk_person_certifications_approver` (`approver`),
      CONSTRAINT `fk_person_certifications_person` FOREIGN KEY (`person`) REFERENCES `people` (`ID`),
      CONSTRAINT `fk_person_certifications_certification` FOREIGN KEY (`certification`) REFERENCES `certifications` (`ID`),
      CONSTRAINT `fk_person_certifications_approver` FOREIGN KEY (`approver`) REFERENCES `people` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;