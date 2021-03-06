-- MySQL Script generated by MySQL Workbench
-- Wed May 31 23:32:53 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `unidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `unidade` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `unidade` (
  `id_unidade` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `razao_social` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  `endereco` VARCHAR(45) NULL,
  `cnpj` INT NULL,
  `blocos` VARCHAR(45) NULL,
  PRIMARY KEY (`id_unidade`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `id_unidade_index` ON `unidade` (`id_unidade` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `permissao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `permissao` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `permissao` (
  `id_permissao` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  PRIMARY KEY (`id_permissao`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `id_permissao_index` ON `permissao` (`id_permissao` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  `celular` VARCHAR(45) NULL,
  `matricula` INT NULL,
  `email` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  `fk_id_unidade` INT NOT NULL,
  `fk_id_permissao` INT NOT NULL,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `id_usuario_index` ON `usuario` (`id_usuario` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sala` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `sala` (
  `id_sala` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NULL,
  `bloco` VARCHAR(45) NULL,
  PRIMARY KEY (`id_sala`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `material` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `material` (
  `id_material` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `descricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id_material`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `kit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kit` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `kit` (
  `id_kit` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `quantidade` VARCHAR(45) NULL,
  `material_id_material` INT NOT NULL,
  PRIMARY KEY (`id_kit`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reserva` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id_reserva` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL,
  `data_ini` DATE NULL,
  `data_fin` DATE NULL,
  `horario` TIME(3) NULL,
  `fk_id_unidade` INT NOT NULL,
  `fk_id_usuario` INT NOT NULL,
  `fk_id_sala` INT NOT NULL,
  `fk_id_kit` INT NOT NULL,
  PRIMARY KEY (`id_reserva`))
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
