CREATE TABLE IF NOT EXISTS `project_solutions` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `project` mediumint UNSIGNED NOT NULL,
      `solution` mediumint UNSIGNED NOT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_project_solutions_opportunity` (`project`),
      KEY `fk_project_solutions_solution` (`solution`),
      CONSTRAINT `fk_project_solutions_opportunity` FOREIGN KEY (`project`) REFERENCES `projects` (`ID`),
      CONSTRAINT `fk_project_solutions_solution` FOREIGN KEY (`solution`) REFERENCES `solutions` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;