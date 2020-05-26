-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
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
-- Table `studshop`.`finishfile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`finishfile` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nameFinishFile` VARCHAR(50) NULL DEFAULT NULL,
  `dataFinishFile` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


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
  `nameTask` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `priceTask` DECIMAL(5,2) NULL DEFAULT NULL,
  `idCourse` INT(11) NOT NULL,
  `idSubject` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_work_subject1_idx` (`idSubject` ASC),
  INDEX `fk_work_kurs1_idx` (`idCourse` ASC),
  CONSTRAINT `fk_work_kurs1`
    FOREIGN KEY (`idCourse`)
    REFERENCES `studshop`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_work_subject1`
    FOREIGN KEY (`idSubject`)
    REFERENCES `studshop`.`subject` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`personaldata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`personaldata` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `bonusMoney` DECIMAL(5,2) NULL DEFAULT 0.00,
  `idInvitingUser` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_personaldata_user1_idx` (`idInvitingUser` ASC),
  CONSTRAINT `fk_personaldata_user1`
    FOREIGN KEY (`idInvitingUser`)
    REFERENCES `studshop`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `studshop`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nameRole` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `idSubject` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_role_subject1` (`idSubject` ASC),
  CONSTRAINT `fk_role_subject1`
    FOREIGN KEY (`idSubject`)
    REFERENCES `studshop`.`subject` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
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
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `password` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
  `idRole` INT(11) NOT NULL,
  `idStatus` INT(11) NOT NULL,
  `idPersonalData` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_status1_idx` (`idStatus` ASC),
  INDEX `fk_user_role1_idx` (`idRole` ASC),
  INDEX `fk_user_personaldata1` (`idPersonalData` ASC),
  CONSTRAINT `fk_user_personaldata1`
    FOREIGN KEY (`idPersonalData`)
    REFERENCES `studshop`.`personaldata` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`idRole`)
    REFERENCES `studshop`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_status1`
    FOREIGN KEY (`idStatus`)
    REFERENCES `studshop`.`status` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `studshop`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `studshop`.`order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idUser` INT(11) NOT NULL,
  `idTask` INT(11) NOT NULL,
  `idRealizer` INT(11) NULL DEFAULT NULL,
  `note` VARCHAR(1024) NOT NULL,
  `adjustedPriceTask` DECIMAL(5,2) NOT NULL,
  `priceOrder` DECIMAL(5,2) NOT NULL,
  `isProcessed` TINYINT(1) NOT NULL DEFAULT 0,
  `isConfirmed` TINYINT(1) NOT NULL DEFAULT 0,
  `isPaid` TINYINT(1) NOT NULL DEFAULT 0,
  `isCompleted` TINYINT(1) NOT NULL DEFAULT 0,
  `dateCreate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `dateProcess` DATETIME NULL DEFAULT NULL,
  `dateConfirm` DATETIME NULL DEFAULT NULL,
  `datePay` DATETIME NULL DEFAULT NULL,
  `dateComplete` DATETIME NULL DEFAULT NULL,
  `idFinishFile` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`idUser` ASC),
  INDEX `fk_order_work1_idx` (`idTask` ASC),
  INDEX `fk_order_file1_idx` (`idFinishFile` ASC),
  INDEX `fk_order_user3` (`idRealizer` ASC),
  CONSTRAINT `fk_order_finishfile1`
    FOREIGN KEY (`idFinishFile`)
    REFERENCES `studshop`.`finishfile` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `studshop`.`task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`idUser`)
    REFERENCES `studshop`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_user3`
    FOREIGN KEY (`idRealizer`)
    REFERENCES `studshop`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 70
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `studshop`.`course`
-- -----------------------------------------------------
START TRANSACTION;
USE `studshop`;
INSERT INTO `studshop`.`course` (`id`, `numberCourse`) VALUES (1, 1);
INSERT INTO `studshop`.`course` (`id`, `numberCourse`) VALUES (2, 2);
INSERT INTO `studshop`.`course` (`id`, `numberCourse`) VALUES (3, 3);
INSERT INTO `studshop`.`course` (`id`, `numberCourse`) VALUES (4, 4);
INSERT INTO `studshop`.`course` (`id`, `numberCourse`) VALUES (5, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `studshop`.`subject`
-- -----------------------------------------------------
START TRANSACTION;
USE `studshop`;
INSERT INTO `studshop`.`subject` (`id`, `nameSubject`) VALUES (1, 'Химия');
INSERT INTO `studshop`.`subject` (`id`, `nameSubject`) VALUES (2, 'Математика');
INSERT INTO `studshop`.`subject` (`id`, `nameSubject`) VALUES (3, 'Инженерная графика');
INSERT INTO `studshop`.`subject` (`id`, `nameSubject`) VALUES (4, 'Информатика');

COMMIT;


-- -----------------------------------------------------
-- Data for table `studshop`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `studshop`;
INSERT INTO `studshop`.`role` (`id`, `nameRole`, `idSubject`) VALUES (1, 'admin', NULL);
INSERT INTO `studshop`.`role` (`id`, `nameRole`, `idSubject`) VALUES (2, 'user', NULL);
INSERT INTO `studshop`.`role` (`id`, `nameRole`, `idSubject`) VALUES (3, 'realizer', 1);
INSERT INTO `studshop`.`role` (`id`, `nameRole`, `idSubject`) VALUES (4, 'realizer', 2);
INSERT INTO `studshop`.`role` (`id`, `nameRole`, `idSubject`) VALUES (5, 'realizer', 3);
INSERT INTO `studshop`.`role` (`id`, `nameRole`, `idSubject`) VALUES (6, 'realizer', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `studshop`.`status`
-- -----------------------------------------------------
START TRANSACTION;
USE `studshop`;
INSERT INTO `studshop`.`status` (`id`, `nameStatus`, `ratioPay`) VALUES (1, 'vip', 0.5);
INSERT INTO `studshop`.`status` (`id`, `nameStatus`, `ratioPay`) VALUES (2, 'standart', 1);
INSERT INTO `studshop`.`status` (`id`, `nameStatus`, `ratioPay`) VALUES (3, 'blocked', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `studshop`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `studshop`;
INSERT INTO `studshop`.`user` (`id`, `login`, `password`, `idRole`, `idStatus`, `idPersonalData`) VALUES (1, 'Vasil', '112358', 1, 1, NULL);
UPDATE `user` SET `id` = '0' WHERE `user`.`id` = 1;
COMMIT;

