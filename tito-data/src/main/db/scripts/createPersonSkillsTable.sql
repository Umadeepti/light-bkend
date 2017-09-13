CREATE TABLE IF NOT EXISTS `person_skills` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `person` mediumint UNSIGNED NOT NULL,
      `skill` mediumint UNSIGNED NOT NULL,
      `years` tinyint UNSIGNED DEFAULT NULL,
      `level` varchar(30) DEFAULT NULL,
      `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `approver` mediumint UNSIGNED DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_person_skills_person` (`person`),
      KEY `fk_person_skills_skill` (`skill`),
      KEY `fk_person_skills_approver` (`approver`),
      CONSTRAINT `fk_person_skills_person` FOREIGN KEY (`person`) REFERENCES `people` (`ID`),
      CONSTRAINT `fk_person_skills_skill` FOREIGN KEY (`skill`) REFERENCES `skills` (`ID`),
      CONSTRAINT `fk_person_skills_approver` FOREIGN KEY (`approver`) REFERENCES `people` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;