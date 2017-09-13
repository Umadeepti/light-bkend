CREATE TABLE IF NOT EXISTS `clients` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `name` varchar(60) NOT NULL,
      `description` varchar(1000) DEFAULT NULL,
      `account` mediumint UNSIGNED DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_clients_account` (`account`),
      CONSTRAINT `unique_client` UNIQUE (`name`),
      CONSTRAINT `fk_clients_account` FOREIGN KEY (`account`) REFERENCES `accounts` (`ID`) ON DELETE SET NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;