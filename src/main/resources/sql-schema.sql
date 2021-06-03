

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

CREATE TABLE IF NOT EXISTS `ims`.`order-items`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`quantity` INT(11),
`fk_order_id` INT(11) NOT NULL,
`fk_item_id` INT(11) NOT NULL,
PRIMARY KEY(`id`), 
FOREIGN KEY(`fk_order_id`) REFERENCES `orders`(`id`),
FOREIGN KEY(`fk_item_id`) REFERENCES `items`(`id`)
); 

use ims;
SELECT * FROM `order-items`; 
INSERT INTO orders(fk_customer_id) VALUES (1);
SELECT * FROM customers; 
INSERT INTO items(name, cost) VALUES ("Dress", 24.99);
INSERT INTO `order-items`(`quantity`, `fk_order_id`, `fk_item_id`) VALUES (?, ?, ?);
SELECT * FROM `order-items` oi JOIN `items` i ON oi.fk_item_id = i.id; 




