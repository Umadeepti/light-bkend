CREATE TABLE IF NOT EXISTS `opportunity_notes` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `opportunity` mediumint UNSIGNED NOT NULL,
      `creator` mediumint UNSIGNED NOT NULL,
      `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `note` varchar(1000) NOT NULL,
      `topic` varchar(30) NOT NULL DEFAULT "General",
      PRIMARY KEY (`ID`),
      KEY `fk_opportunity_notes_opportunity` (`opportunity`),
      KEY `fk_opportunity_notes_creator` (`creator`),
      CONSTRAINT `fk_opportunity_notes_opportunity` FOREIGN KEY (`opportunity`) REFERENCES `opportunities` (`ID`),
      CONSTRAINT `fk_opportunity_notes_creator` FOREIGN KEY (`creator`) REFERENCES `people` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;