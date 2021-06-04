drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) DEFAULT NULL,
    `cost` DOUBLE, 
    PRIMARY KEY (`id`)
    ); 
    
CREATE TABLE IF NOT EXISTS `ims`.`orders`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`fk_customer_id` INT(11) NOT NULL,
PRIMARY KEY(`id`),
FOREIGN KEY(`fk_customer_id`) REFERENCES `customers`(`id`)

); 





