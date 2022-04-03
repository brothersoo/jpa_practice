-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema jpa_test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jpa_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jpa_test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `jpa_test` ;

-- -----------------------------------------------------
-- Table `jpa_test`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa_test`.`User` (
  `id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa_test`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa_test`.`Country` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa_test`.`City`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa_test`.`City` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`, `country_id`),
  INDEX `fk_City_Country1_idx` (`country_id` ASC) VISIBLE,
  CONSTRAINT `fk_City_Country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `jpa_test`.`Country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa_test`.`Article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa_test`.`Article` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `city_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Article_City1_idx` (`city_id` ASC) VISIBLE,
  INDEX `fk_Article_User1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Article_City1`
    FOREIGN KEY (`city_id`)
    REFERENCES `jpa_test`.`City` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Article_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `jpa_test`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa_test`.`Vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa_test`.`Vendor` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa_test`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa_test`.`Product` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `type` TINYINT NOT NULL,
  `vendor_id` INT NOT NULL,
  PRIMARY KEY (`id`, `vendor_id`),
  INDEX `fk_Product_Company1_idx` (`vendor_id` ASC) VISIBLE,
  CONSTRAINT `fk_Product_Company1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `jpa_test`.`Vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa_test`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa_test`.`Order` (
  `id` INT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Purchase_User1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Purchase_Product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_Purchase_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `jpa_test`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Purchase_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `jpa_test`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
