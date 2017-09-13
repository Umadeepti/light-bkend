	CREATE TABLE IF NOT EXISTS `partner_skills` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `firm` mediumint UNSIGNED NOT NULL,
      `skill` mediumint UNSIGNED NOT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_partner_skills_firm` (`firm`),
      KEY `fk_partner_skills_skill` (`skill`),
      CONSTRAINT `fk_partner_skills_firm` FOREIGN KEY (`firm`) REFERENCES `firms` (`ID`),
      CONSTRAINT `fk_partner_skills_skill` FOREIGN KEY (`skill`) REFERENCES `skills` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;