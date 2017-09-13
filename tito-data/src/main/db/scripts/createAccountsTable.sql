CREATE TABLE IF NOT EXISTS `accounts` (
  			`ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  			`name` varchar(60) NOT NULL,
  			`description` varchar(1000) DEFAULT NULL,
  			`owner` mediumint UNSIGNED DEFAULT NULL,
  			PRIMARY KEY (`ID`),
  			KEY `fk_accounts_owner` (`owner`),
  			CONSTRAINT `unique_account` UNIQUE (`name`),
  			CONSTRAINT `fk_accounts_owner` FOREIGN KEY (`owner`) REFERENCES `people` (`ID`) ON DELETE SET NULL
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;