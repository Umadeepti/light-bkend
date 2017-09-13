CREATE TABLE IF NOT EXISTS `person_project_roles` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `person` mediumint UNSIGNED NOT NULL,
      `role` mediumint UNSIGNED NOT NULL,
      `start_date` datetime DEFAULT NULL,
      `end_date` datetime DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_person_project_roles_person` (`person`),
      KEY `fk_person_project_roles_role` (`role`),
      CONSTRAINT `fk_person_project_roles_person` FOREIGN KEY (`person`) REFERENCES `people` (`ID`),
      CONSTRAINT `fk_person_project_roles_role` FOREIGN KEY (`role`) REFERENCES `project_roles` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;