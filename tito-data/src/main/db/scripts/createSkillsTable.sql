CREATE TABLE IF NOT EXISTS `skills` (
  			`ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  			`name` varchar(30) NOT NULL,
  			`description` varchar(1000) DEFAULT NULL,
  			`solution` mediumint UNSIGNED NOT NULL,
  			PRIMARY KEY (`ID`),
  			KEY `fk_skills_solution` (`solution`),
  			CONSTRAINT `unique_skill` UNIQUE (`name`),
  			CONSTRAINT `fk_skills_solution` FOREIGN KEY (`solution`) REFERENCES `solutions` (`ID`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;