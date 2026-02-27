-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema schooldb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema schooldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `schooldb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `schooldb` ;

-- -----------------------------------------------------
-- Table `schooldb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schooldb`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `schooldb`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schooldb`.`log` (
  `orderId` INT NOT NULL,
  `clientName` VARCHAR(100) NOT NULL,
  `productName` VARCHAR(100) NOT NULL,
  `quantity` INT NOT NULL,
  `totalPrice` DOUBLE NOT NULL,
  `billDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `schooldb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schooldb`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `stock` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `schooldb`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schooldb`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `clientId` INT NOT NULL,
  `productId` INT NOT NULL,
  `quantity` INT NOT NULL,
  `orderDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `clientId` (`clientId` ASC) VISIBLE,
  INDEX `productId` (`productId` ASC) VISIBLE,
  CONSTRAINT `orders_ibfk_1`
    FOREIGN KEY (`clientId`)
    REFERENCES `schooldb`.`client` (`id`),
  CONSTRAINT `orders_ibfk_2`
    FOREIGN KEY (`productId`)
    REFERENCES `schooldb`.`product` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
