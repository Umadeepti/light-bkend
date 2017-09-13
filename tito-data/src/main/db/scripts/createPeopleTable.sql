CREATE TABLE IF NOT EXISTS `people` (
  			`ID` mediumint UNSIGNED NOT NULL AUTO_INCREMENT,
  			`first_name` varchar(20) NOT NULL,
  			`last_name` varchar(20) NOT NULL,
  			`status` varchar(20) DEFAULT NULL,
  			`manager` mediumint UNSIGNED DEFAULT NULL,
  			PRIMARY KEY (`ID`),
  			KEY `fk_people_manager` (`manager`),
  			CONSTRAINT `fk_people_manager` FOREIGN KEY (`manager`) REFERENCES `people` (`ID`) ON DELETE SET NULL
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;