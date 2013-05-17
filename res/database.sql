CREATE  TABLE `fyle`.`user` (
	`USER_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`USERNAME` VARCHAR(16) UNIQUE NOT NULL,
        `IS_CONNECTED` TINYINT(1) UNSIGNED DEFAULT NULL,
	`EMAIL` VARCHAR(254) UNIQUE NOT NULL, 
	`REG_DATE` DATETIME NOT NULL,
        `PASSWORD` VARCHAR(128) NOT NULL,
	`PASSWORDHEX` VARCHAR(32) NOT NULL,
    `FAILED_LOGIN_NUM` BIGINT UNSIGNED DEFAULT NULL,
    `LOCKOUT` BIGINT UNSIGNED DEFAULT NULL,
    `LOGIN_TIME` BIGINT UNSIGNED DEFAULT NULL,
    `LAST_LOGIN_TIME` BIGINT UNSIGNED DEFAULT NULL, 
	 PRIMARY KEY (`USER_ID`)
);

CREATE TABLE `fyle`.`friend` (
    `FRIEND_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `FRIEND_USERNAME` VARCHAR(16) UNIQUE NOT NULL,
    `IS_FRIEND_ACCEPTED` TINYINT(1) UNSIGNED DEFAULT NULL,
    `USER_ID` INT(10) UNSIGNED NOT NULL,
     PRIMARY KEY (`FRIEND_ID`),
     KEY `FK_USER_TRANSACTION_USER_ID` (`USER_ID`),
     CONSTRAINT `FK_USER_TRANSACTION_USER_ID` FOREIGN KEY (`USER_ID`) 
     REFERENCES `user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
);