CREATE TABLE IF NOT EXISTS `opportunity_roles` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `opportunity` mediumint UNSIGNED NOT NULL,
      `labor_category` varchar(30) NOT NULL,
      `rate` decimal(5,2) DEFAULT NULL,
      `years` tinyint UNSIGNED DEFAULT NULL,
      `level` varchar(30) DEFAULT NULL,
      `start_date` datetime DEFAULT NULL,
      `end_date` datetime DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_opportunity_roles_opportunity` (`opportunity`),
      CONSTRAINT `fk_opportunity_roles_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;