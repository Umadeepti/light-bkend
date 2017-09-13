CREATE TABLE IF NOT EXISTS `sub_clients` (
      `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
      `name` varchar(60) NOT NULL,
      `description` varchar(1000) DEFAULT NULL,
      `client` mediumint UNSIGNED DEFAULT NULL,
      PRIMARY KEY (`ID`),
      KEY `fk_sub_clients_client` (`client`),
      CONSTRAINT `unique_sub_client` UNIQUE (`name`),
      CONSTRAINT `fk_sub_clients_client` FOREIGN KEY (`client`) REFERENCES `clients` (`ID`) ON DELETE SET NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;