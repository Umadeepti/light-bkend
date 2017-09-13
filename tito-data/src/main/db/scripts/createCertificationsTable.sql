CREATE TABLE IF NOT EXISTS `certifications` (
  `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `solution` mediumint UNSIGNED DEFAULT NULL,
  `skill` mediumint UNSIGNED DEFAULT NULL,
  `certification_authority` varchar(30) NOT NULL,
  `years_valid` tinyint UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_certifications_solution` (`solution`),
  KEY `fk_certifications_skill` (`skill`),
  CONSTRAINT `unique_certification` UNIQUE (`name`),
  CONSTRAINT `fk_certifications_solution` FOREIGN KEY (`solution`) REFERENCES `solutions` (`ID`) ON DELETE SET NULL,
  CONSTRAINT `fk_certifications_skill` FOREIGN KEY (`skill`) REFERENCES `skills` (`ID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

