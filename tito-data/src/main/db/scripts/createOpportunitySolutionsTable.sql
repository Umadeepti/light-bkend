CREATE TABLE IF NOT EXISTS `opportunity_solutions` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `opportunity` mediumint UNSIGNED NOT NULL,
      `solution` mediumint UNSIGNED NOT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_opportunity_solutions_opportunity` (`opportunity`),
      KEY `fk_opportunity_solutions_solution` (`solution`),
      CONSTRAINT `fk_opportunity_solutions_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`),
      CONSTRAINT `fk_opportunity_solutions_solution` FOREIGN KEY (`solution`) REFERENCES `solutions` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;