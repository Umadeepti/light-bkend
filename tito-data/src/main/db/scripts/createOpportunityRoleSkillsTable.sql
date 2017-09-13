CREATE TABLE IF NOT EXISTS `opportunity_role_skills` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `role` mediumint UNSIGNED NOT NULL,
      `skill` mediumint UNSIGNED NOT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_opportunity_role_skills_role` (`role`),
      KEY `fk_opportunity_role_skills_skill` (`skill`),
      CONSTRAINT `fk_opportunity_role_skills_role` FOREIGN KEY (`role`) REFERENCES `opportunity_roles` (`ID`),
      CONSTRAINT `fk_opportunity_role_skills_skill` FOREIGN KEY (`skill`) REFERENCES `skills` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;