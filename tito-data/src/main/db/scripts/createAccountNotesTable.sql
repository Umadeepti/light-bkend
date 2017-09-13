	CREATE TABLE IF NOT EXISTS `account_notes` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `account` mediumint UNSIGNED NOT NULL,
      `creator` mediumint UNSIGNED NOT NULL,
      `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `note` varchar(1000) NOT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_account_notes_account` (`account`),
      KEY `fk_account_notes_creator` (`creator`),
      CONSTRAINT `fk_account_notes_account` FOREIGN KEY (`account`) REFERENCES `accounts` (`ID`),
      CONSTRAINT `fk_account_notes_creator` FOREIGN KEY (`creator`) REFERENCES `people` (`ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;