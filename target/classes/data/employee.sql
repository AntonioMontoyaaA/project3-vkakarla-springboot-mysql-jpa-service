CREATE  TABLE IF NOT EXISTS `vkakarlapoc`.`employee` (
  `id` INT,
  `first_name` VARCHAR(150) NOT NULL ,
  `last_name` VARCHAR(150) NOT NULL ,
  `department` VARCHAR(20) ,
  `email` VARCHAR(255) ,
  `address` VARCHAR(255) ,
  PRIMARY KEY (`id`) );