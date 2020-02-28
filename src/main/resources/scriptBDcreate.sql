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
-- Table `studshop`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `numberCourse` INT(11) NULL DEFAULT NULL,
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
-- Table `studshop`.`personaldata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`personaldata` (
  `idUser` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `bonusMoney` DECIMAL(5,2) NULL DEFAULT 0,
  `idInvitingUser` INT(11) NOT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `fk_personaldata_user1_idx` (`idInvitingUser` ASC),
  CONSTRAINT `fk_personaldata_user1`
    FOREIGN KEY (`idInvitingUser`)
    REFERENCES `studshop`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


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
  INDEX `fk_user_personaldata1_idx` (`id` ASC),
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`idRole`)
    REFERENCES `studshop`.`role` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_status`
    FOREIGN KEY (`idStatus`)
    REFERENCES `studshop`.`status` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_personaldata`
    FOREIGN KEY (`id`)
    REFERENCES `studshop`.`personaldata` (`idUser`)
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
-- Table `studshop`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`task` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nameTask` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `priceTask` DECIMAL(5,2) NULL DEFAULT NULL,
  `idCourse` INT(11) NOT NULL,
  `idSubject` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_work_subject1_idx` (`idSubject` ASC),
  INDEX `fk_work_kurs1_idx` (`idCourse` ASC),
  CONSTRAINT `fk_work_kurs1`
    FOREIGN KEY (`idCourse`)
    REFERENCES `studshop`.`course` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_work_subject1`
    FOREIGN KEY (`idSubject`)
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
  `priceOrder` DECIMAL(5,2) NULL DEFAULT NULL,
  `idUser` INT(11) NOT NULL,
  `idTask` INT(11) NOT NULL,
  `isPaid` INT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`idUser` ASC),
  INDEX `fk_order_work1_idx` (`idTask` ASC),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`idUser`)
    REFERENCES `studshop`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_work1`
    FOREIGN KEY (`idTask`)
    REFERENCES `studshop`.`task` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
