CREATE TABLE IF NOT EXISTS `project_roles` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `project` mediumint UNSIGNED NOT NULL,
      `labor_category` varchar(30) NOT NULL,
      `rate` decimal(5,2) DEFAULT NULL,
      `years` tinyint UNSIGNED DEFAULT NULL,
      `level` varchar(30) DEFAULT NULL,
      `start_date` datetime DEFAULT NULL,
      `end_date` datetime DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_project_roles_project` (`project`),
      CONSTRAINT `fk_project_roles_project` FOREIGN KEY (`project`) REFERENCES `projects` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;