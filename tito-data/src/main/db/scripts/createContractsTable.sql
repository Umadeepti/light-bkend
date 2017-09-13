#Contracts Table
CREATE TABLE IF NOT EXISTS `contracts` (
  `ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `account` mediumint UNSIGNED NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `prime` boolean DEFAULT NULL,
  `award_date` datetime DEFAULT NULL,
  `close_date` datetime DEFAULT NULL,
  `award_amount` int UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_contracts_account` (`account`),
  CONSTRAINT `fk_contracts_account` FOREIGN KEY (`account`) REFERENCES `accounts` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;