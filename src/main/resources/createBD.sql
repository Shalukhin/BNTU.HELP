-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema studshop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema studshop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `studshop` DEFAULT CHARACTER SET utf8 ;
USE `studshop` ;

-- -----------------------------------------------------
-- Table `studshop`.`kurs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`kurs` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `numberKurs` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nameStatus` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `ratioPay` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`personalData`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`personalData` (
  `idUser` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `idInvitingUser` INT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_personalData_user_idx` (`idInvitingUser` ASC),
  CONSTRAINT `fk_personalData_user`
    FOREIGN KEY (`idInvitingUser`)
    REFERENCES `studshop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `studshop`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `password` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `idRole` INT(11) NOT NULL,
  `idStatus` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_idx` (`idRole` ASC),
  INDEX `fk_user_status1_idx` (`idStatus` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`idRole`)
    REFERENCES `studshop`.`role` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_status`
    FOREIGN KEY (`idStatus`)
    REFERENCES `studshop`.`status` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_personalData`
    FOREIGN KEY (`id`)
    REFERENCES `studshop`.`personalData` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`subject` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nameSubject` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`work`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`work` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nameWork` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `priceWork` DECIMAL NULL DEFAULT NULL,
  `id_kurs` INT(11) NOT NULL,
  `id_subject` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_work_subject1_idx` (`id_subject` ASC),
  INDEX `fk_work_kurs1_idx` (`id_kurs` ASC),
  CONSTRAINT `fk_work_kurs1`
    FOREIGN KEY (`id_kurs`)
    REFERENCES `studshop`.`kurs` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_work_subject1`
    FOREIGN KEY (`id_subject`)
    REFERENCES `studshop`.`subject` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `priceOrder` DECIMAL NULL,
  `iduser` INT(11) NOT NULL,
  `idwork` INT(11) NOT NULL,
  `isPaid` INT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`iduser` ASC),
  INDEX `fk_order_work1_idx` (`idwork` ASC),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `studshop`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_work1`
    FOREIGN KEY (`idwork`)
    REFERENCES `studshop`.`work` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
