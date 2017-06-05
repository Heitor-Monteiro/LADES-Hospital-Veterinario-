-- MySQL Script generated by MySQL Workbench
-- Seg 05 Jun 2017 00:44:22 BRT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bd_sihv
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bd_sihv
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_sihv` ;
USE `bd_sihv` ;

-- -----------------------------------------------------
-- Table `bd_sihv`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`pessoa` (
  `PK_pessoa` INT NOT NULL AUTO_INCREMENT,
  `cpf-cnpj` VARCHAR(18) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `logra` VARCHAR(100) NOT NULL,
  `casaNumero` VARCHAR(7) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,
  `cidade` VARCHAR(100) NOT NULL,
  `cep` VARCHAR(9) NOT NULL,
  `complemento` VARCHAR(100) NULL,
  `uf` SET('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RN', 'RS', 'RJ', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO') NOT NULL,
  `cadDataHora` TIMESTAMP(6) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `exclusaoLogica` TINYINT(1) NULL,
  PRIMARY KEY (`PK_pessoa`),
  UNIQUE INDEX `CPF_UNIQUE` (`cpf-cnpj` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bd_sihv`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`cliente` (
  `PK_cliente` INT NOT NULL AUTO_INCREMENT,
  `FK_pessoa` INT NOT NULL,
  PRIMARY KEY (`PK_cliente`, `FK_pessoa`),
  INDEX `fk_cliente_pessoa1_idx` (`FK_pessoa` ASC),
  CONSTRAINT `fk_cliente_pessoa1`
    FOREIGN KEY (`FK_pessoa`)
    REFERENCES `bd_sihv`.`pessoa` (`PK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`animais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`animais` (
  `PK_animal` INT NOT NULL AUTO_INCREMENT,
  `especie` VARCHAR(100) NOT NULL,
  `nomeAnimal` VARCHAR(70) NOT NULL,
  `raca` VARCHAR(100) NOT NULL,
  `pelagem` VARCHAR(100) NOT NULL,
  `sexoAnimal` SET('M', 'F') NOT NULL,
  `dataNac` DATE NULL,
  `escalaPeso` SET('g', 'kg', 'T') NOT NULL,
  `peso` DOUBLE NOT NULL,
  `rghv` VARCHAR(9) NOT NULL,
  `rghvNum` DECIMAL(4,0) NOT NULL,
  `categoriaAnimal` SET('P', 'G', 'S') NOT NULL,
  `cadDataHora` TIMESTAMP(6) NOT NULL,
  `cliente_FK_cliente` INT NOT NULL,
  `cliente_FK_pessoa` INT NOT NULL,
  PRIMARY KEY (`PK_animal`, `cliente_FK_cliente`, `cliente_FK_pessoa`),
  INDEX `fk_animais_cliente1_idx` (`cliente_FK_cliente` ASC, `cliente_FK_pessoa` ASC),
  CONSTRAINT `fk_animais_cliente1`
    FOREIGN KEY (`cliente_FK_cliente` , `cliente_FK_pessoa`)
    REFERENCES `bd_sihv`.`cliente` (`PK_cliente` , `FK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bd_sihv`.`telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`telefone` (
  `PK_telefone` INT NOT NULL AUTO_INCREMENT,
  `FK_pessoa` INT NOT NULL,
  `numero` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`PK_telefone`),
  INDEX `fk_telefone_pessoa1_idx` (`FK_pessoa` ASC),
  CONSTRAINT `fk_telefone_pessoa1`
    FOREIGN KEY (`FK_pessoa`)
    REFERENCES `bd_sihv`.`pessoa` (`PK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`user` (
  `PK_user` INT NOT NULL AUTO_INCREMENT,
  `FK_pessoa` INT NOT NULL,
  `userNick` VARCHAR(100) NOT NULL,
  `userSenha` VARCHAR(100) NOT NULL,
  `userTipo` SET('ALUNO', 'MEDICO', 'TÉCNICO') NOT NULL,
  `crmvMatricula` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`PK_user`, `FK_pessoa`),
  UNIQUE INDEX `CRMV_MATRICULA_UNIQUE` (`crmvMatricula` ASC),
  INDEX `fk_user_pessoa1_idx` (`FK_pessoa` ASC),
  CONSTRAINT `fk_user_pessoa1`
    FOREIGN KEY (`FK_pessoa`)
    REFERENCES `bd_sihv`.`pessoa` (`PK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`pelagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`pelagem` (
  `PK_pelagem` INT NOT NULL AUTO_INCREMENT,
  `nomePelagem` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`PK_pelagem`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`consulta` (
  `PK_consulta` INT NOT NULL AUTO_INCREMENT,
  `dataConsulta` TIMESTAMP(6) NOT NULL,
  `sistemasAfetados` VARCHAR(500) NULL,
  `laudo` VARCHAR(500) NULL,
  `laudoConfirme` SET('Sim', 'Não') NULL,
  `valorConsulta` DECIMAL(6,2) NOT NULL,
  `animais_FK_animal` INT NOT NULL,
  `animais_cliente_FK_cliente` INT NOT NULL,
  `animais_cliente_FK_pessoa` INT NOT NULL,
  `user_PK_user` INT NOT NULL,
  `user_FK_pessoa` INT NOT NULL,
  PRIMARY KEY (`PK_consulta`),
  INDEX `fk_consulta_animais1_idx` (`animais_FK_animal` ASC, `animais_cliente_FK_cliente` ASC, `animais_cliente_FK_pessoa` ASC),
  INDEX `fk_consulta_user1_idx` (`user_PK_user` ASC, `user_FK_pessoa` ASC),
  CONSTRAINT `fk_consulta_animais1`
    FOREIGN KEY (`animais_FK_animal` , `animais_cliente_FK_cliente` , `animais_cliente_FK_pessoa`)
    REFERENCES `bd_sihv`.`animais` (`PK_animal` , `cliente_FK_cliente` , `cliente_FK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_consulta_user1`
    FOREIGN KEY (`user_PK_user` , `user_FK_pessoa`)
    REFERENCES `bd_sihv`.`user` (`PK_user` , `FK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`anamnese`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`anamnese` (
  `PK_anamnese` INT NOT NULL AUTO_INCREMENT,
  `queixaPrincipal` VARCHAR(300) NOT NULL,
  `jaFoiTratado` SET('Sim', 'Não') NOT NULL,
  `medicacaoDose` VARCHAR(200) NULL,
  `antecMorbido` VARCHAR(200) NOT NULL,
  `histoFamiliar` VARCHAR(200) NOT NULL,
  `alimentacaoCaseira` SET('Sim', 'Não') NOT NULL,
  `descriCaseira` VARCHAR(200) NULL,
  `alimentacaoRacao` SET('Sim', 'Não') NOT NULL,
  `descriRacao` VARCHAR(200) NULL,
  `vacinacao` VARCHAR(22) NOT NULL,
  `sobreVacina` VARCHAR(100) NULL,
  `origemVacina` SET('Nacional', 'Importada') NULL,
  `vermifugacao` SET('Sim', 'Não') NOT NULL,
  `doseVermifugacao` VARCHAR(50) NULL,
  `dataVermifugacao` DATE NULL,
  `ectoparasitas` SET('Sim', 'Não') NOT NULL,
  `qualEctoparasitas` VARCHAR(34) NULL,
  `controEctoparasitas` SET('Sim', 'Não') NULL,
  `qualProdutoUtiliza` VARCHAR(50) NULL,
  `acessoRua` VARCHAR(24) NOT NULL,
  `descriHabitat` VARCHAR(200) NULL,
  `contactantes` SET('Sim', 'Não') NULL,
  `descriContactantes` VARCHAR(200) NULL,
  `mesmoProbleContacta` SET('Sim', 'Não') NULL,
  `contatoRoedor` SET('Sim', 'Não') NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_anamnese`, `consulta_FK_consulta`),
  INDEX `fk_anamnese_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_anamnese_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`sisDigestorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`sisDigestorio` (
  `PK_sisDigestorio` INT NOT NULL AUTO_INCREMENT,
  `estaSeAlimentando` SET('Sim', 'Não') NOT NULL,
  `descriNaoSeAlimeta` VARCHAR(200) NULL,
  `vomito` SET('Sim', 'Não') NOT NULL,
  `aspectoVomito` VARCHAR(100) NULL,
  `evoluVomito` VARCHAR(200) NULL,
  `regurgitacao` SET('Sim', 'Não') NOT NULL,
  `evoluRegurgitacao` VARCHAR(200) NULL,
  `diarreia` SET('Sim', 'Não') NOT NULL,
  `aspectDiarreia` VARCHAR(100) NULL,
  `evoluDiarreia` VARCHAR(100) NULL,
  `disquesiaTenesmo` SET('Sim', 'Não') NOT NULL,
  `evoluDisquesiaTenesmo` VARCHAR(100) NULL,
  `sistemaAfetado` SET('Sim', 'Não') NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_sisDigestorio`, `consulta_FK_consulta`),
  INDEX `fk_sisDigestorio_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_sisDigestorio_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`sisRespCardio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`sisRespCardio` (
  `PK_sisRespCardio` INT NOT NULL AUTO_INCREMENT,
  `tosse` SET('Sim', 'Não') NOT NULL,
  `tosseProdutiva` SET('Produtiva', 'Não produtiva') NULL,
  `tosseEvolu` VARCHAR(200) NULL,
  `espirro` SET('Sim', 'Não') NOT NULL,
  `espirroEvolu` VARCHAR(200) NULL,
  `secrecaoNasal` SET('Sim', 'Não') NOT NULL,
  `secreNasalUniBilateral` SET('Unilateral(esquerdo)', 'Unilateral(direito)', 'Bilateral') NULL,
  `secreNasalTipo` VARCHAR(50) NULL,
  `secreNasalEvolu` VARCHAR(200) NULL,
  `dispneiaTaquipneia` SET('Sim', 'Não') NOT NULL,
  `dispnTaquipEvolu` VARCHAR(200) NULL,
  `cianose` SET('Sim', 'Não') NOT NULL,
  `cianoseEvolu` VARCHAR(200) NULL,
  `cansacoFacil` SET('Sim', 'Não') NOT NULL,
  `cansaFacilEvolu` VARCHAR(200) NULL,
  `sincope` SET('Sim', 'Não') NOT NULL,
  `sincopeEvolu` VARCHAR(200) NULL,
  `emagrecimento` SET('Sim', 'Não') NOT NULL,
  `emagrecEvolu` VARCHAR(200) NULL,
  `sistemaAfetado` SET('Sim', 'Não') NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_sisRespCardio`, `consulta_FK_consulta`),
  INDEX `fk_sisRespCardio_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_sisRespCardio_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`sisUrinarioMamaria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`sisUrinarioMamaria` (
  `PK_sisUrinarioMamaria` INT NOT NULL AUTO_INCREMENT,
  `ingestHidrica` SET('Normal', 'Hipodipsia', 'Polidipsia') NOT NULL,
  `ingestHidricaEvolu` VARCHAR(200) NULL,
  `estadoUrina` SET('Normal', 'Alterado') NOT NULL,
  `urina` SET('Anúria', 'Oligúria', 'Poliúria', 'Incontinência', 'Iscúria') NOT NULL,
  `urinaAspecto` VARCHAR(200) NULL,
  `urinaEvolu` VARCHAR(200) NULL,
  `ultimoCio` DATE NULL,
  `prenhe` SET('Sim', 'Não') NOT NULL,
  `ultimoParto` DATE NULL,
  `secreVagiPeni` SET('Sim', 'Não') NOT NULL,
  `secreVagiPeniTipo` VARCHAR(50) NULL,
  `secreVagiPeniEvolu` VARCHAR(200) NULL,
  `castrado` SET('Sim', 'Não') NOT NULL,
  `sistemaAfetado` SET('Sim', 'Não') NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_sisUrinarioMamaria`, `consulta_FK_consulta`),
  INDEX `fk_sisUrinarioMamaria_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_sisUrinarioMamaria_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`sisTegumentar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`sisTegumentar` (
  `PK_sisTegumentar` INT NOT NULL AUTO_INCREMENT,
  `evoluLesao` VARCHAR(200) NOT NULL,
  `localiLesao` VARCHAR(200) NOT NULL,
  `pruridoCutaneo` SET('Sim', 'Não') NOT NULL,
  `pruridoCutaneoEvolu` VARCHAR(50) NULL,
  `pruridoOtolog` SET('Sim', 'Não') NOT NULL,
  `pruridoOtologEvolu` VARCHAR(50) NULL,
  `secreOtolog` SET('Sim', 'Não') NOT NULL,
  `secreOtologEvolu` VARCHAR(50) NULL,
  `frequeBanhos` VARCHAR(50) NOT NULL,
  `produUtilBanho` VARCHAR(50) NULL,
  `sistemaAfetado` SET('Sim', 'Não') NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_sisTegumentar`, `consulta_FK_consulta`),
  INDEX `fk_sisTegumentar_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_sisTegumentar_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`sisNeurologico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`sisNeurologico` (
  `PK_sisNeurologico` INT NOT NULL AUTO_INCREMENT,
  `consciencia` SET('Alerta', 'Depressão', 'Esturpor', 'Coma') NOT NULL,
  `comportamento` SET('Normal', 'Demência', 'Agressividade') NOT NULL,
  `ataxia` SET('Sim', 'Não') NOT NULL,
  `ataxiaTipo` VARCHAR(50) NULL,
  `ataxiaEvolu` VARCHAR(200) NULL,
  `paralisia` SET('Sim', 'Não') NOT NULL,
  `paralisiaEspFla` SET('Espástica', 'Flácida') NULL,
  `paralisiaTipo` VARCHAR(50) NULL,
  `paralisiaEvolu` VARCHAR(200) NULL,
  `convulsao` SET('Sim', 'Não') NOT NULL,
  `convulsaoTipo` VARCHAR(50) NULL,
  `convulsaoEvolu` VARCHAR(200) NULL,
  `audicao` SET('Normal', 'Perda auditiva') NOT NULL,
  `sistemaAfetado` SET('Sim', 'Não') NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_sisNeurologico`, `consulta_FK_consulta`),
  INDEX `fk_sisNeurologico_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_sisNeurologico_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`sisOftalmico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`sisOftalmico` (
  `PK_sisOftalmico` INT NOT NULL AUTO_INCREMENT,
  `olhosPupila` SET('Normal', 'Amaurose', 'Anisocoria', 'Estrabismo', 'Midríase', 'Miose') NOT NULL,
  `secreOcular` SET('Sim', 'Não') NOT NULL,
  `secreOcularUniBi` SET('Unilateral(esquerdo)', 'Unilateral(direito)', 'Bilateral') NULL,
  `secreOculaTipo` VARCHAR(50) NULL,
  `secreOculaEvolu` VARCHAR(200) NULL,
  `blefaroespasmo` SET('Sim', 'Não') NOT NULL,
  `blefaroComenta` VARCHAR(50) NULL,
  `exoftalmia` SET('Sim', 'Não') NOT NULL,
  `exoftalComenta` VARCHAR(50) NULL,
  `sistemaAfetado` SET('Sim', 'Não') NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_sisOftalmico`, `consulta_FK_consulta`),
  INDEX `fk_sisOftalmico_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_sisOftalmico_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`sisMuscEsque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`sisMuscEsque` (
  `PK_sisMuscEsque` INT NOT NULL AUTO_INCREMENT,
  `claudicacao` SET('Sim', 'Não') NOT NULL,
  `claudicacaoEvolu` VARCHAR(50) NULL,
  `fraturas` SET('Sim', 'Não') NOT NULL,
  `fraturasEvolu` VARCHAR(50) NULL,
  `atrofMusc` SET('Sim', 'Não') NOT NULL,
  `atrofMuscEvolu` VARCHAR(50) NULL,
  `sistemaAfetado` SET('Sim', 'Não') NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_sisMuscEsque`, `consulta_FK_consulta`),
  INDEX `fk_sisMuscEsque_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_sisMuscEsque_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`exameFisico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`exameFisico` (
  `PK_exameFisico` INT NOT NULL AUTO_INCREMENT,
  `fcBpm` DECIMAL(4,0) NOT NULL,
  `frMpm` DECIMAL(4,0) NOT NULL,
  `termpeReta` DECIMAL(5,1) NOT NULL,
  `tpc` DECIMAL(4,0) NOT NULL,
  `estadoNutric` SET('Bom', 'Caquético', 'Magro', 'Gordo', 'Obeso') NOT NULL,
  `mucosaOral` SET('Róseas', 'Pálidas', 'Perláceas', 'Cianóticas', 'Congestas', 'Ictéricas') NOT NULL,
  `mucosVagPeni` SET('Róseas', 'Pálidas', 'Perláceas', 'Cianóticas', 'Congestas', 'Ictéricas') NOT NULL,
  `mucosaOcular` SET('Róseas', 'Pálidas', 'Perláceas', 'Cianóticas', 'Congestas', 'Ictéricas') NOT NULL,
  `pulso` SET('Fraco', 'Forte', 'Ausente') NOT NULL,
  `estadoPulso` SET('Regular', 'Irregular') NOT NULL,
  `hidratacao` SET('Hidratado', '4-6% de desidratação', '6-8% de desidratação', '8-10% de desidratação', '10-12% de desidratação') NOT NULL,
  `palpaAbdom` VARCHAR(200) NOT NULL,
  `auscuCardiaca` VARCHAR(200) NOT NULL,
  `auscuPulmona` VARCHAR(200) NOT NULL,
  `linfonodos` VARCHAR(200) NOT NULL,
  `pelePelos` VARCHAR(200) NOT NULL,
  `inforAdiciona` VARCHAR(900) NULL,
  `diagDifer` VARCHAR(500) NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_exameFisico`, `consulta_FK_consulta`),
  INDEX `fk_exameFisico_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_exameFisico_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`exameImage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`exameImage` (
  `PK_exameImage` INT NOT NULL AUTO_INCREMENT,
  `regiExami` VARCHAR(200) NOT NULL,
  `posicao` VARCHAR(50) NULL,
  `qtdChapas` INT NULL,
  `laudo` VARCHAR(200) NULL,
  `tipo` SET('RAIOX', 'ULTRASSOM') NOT NULL,
  `dataExaImage` TIMESTAMP(6) NOT NULL,
  `status` SET('Atendido', 'Pendente') NOT NULL,
  `codExameImage` VARCHAR(8) NOT NULL,
  `consulta_FK_consulta` INT NOT NULL,
  PRIMARY KEY (`PK_exameImage`, `consulta_FK_consulta`),
  INDEX `fk_exameImage_consulta1_idx` (`consulta_FK_consulta` ASC),
  CONSTRAINT `fk_exameImage_consulta1`
    FOREIGN KEY (`consulta_FK_consulta`)
    REFERENCES `bd_sihv`.`consulta` (`PK_consulta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`fisica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`fisica` (
  `PK_fisica` INT NOT NULL AUTO_INCREMENT,
  `rg` VARCHAR(20) NOT NULL,
  `sexo` SET('M', 'F') NOT NULL,
  `FK_pessoa` INT NOT NULL,
  PRIMARY KEY (`PK_fisica`, `FK_pessoa`),
  INDEX `fk_fisica_pessoa1_idx` (`FK_pessoa` ASC),
  CONSTRAINT `fk_fisica_pessoa1`
    FOREIGN KEY (`FK_pessoa`)
    REFERENCES `bd_sihv`.`pessoa` (`PK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`juridica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`juridica` (
  `PK_juridica` INT NOT NULL AUTO_INCREMENT,
  `tipoPessoaJuridica` VARCHAR(100) NOT NULL,
  `FK_pessoa` INT NOT NULL,
  PRIMARY KEY (`PK_juridica`, `FK_pessoa`),
  INDEX `fk_juridica_pessoa1_idx` (`FK_pessoa` ASC),
  CONSTRAINT `fk_juridica_pessoa1`
    FOREIGN KEY (`FK_pessoa`)
    REFERENCES `bd_sihv`.`pessoa` (`PK_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`especie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`especie` (
  `PK_especie` INT NOT NULL AUTO_INCREMENT,
  `nomeEspecie` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`PK_especie`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_sihv`.`raca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_sihv`.`raca` (
  `PK_raca` INT NOT NULL AUTO_INCREMENT,
  `nomeRaca` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`PK_raca`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `bd_sihv`.`pessoa`
-- -----------------------------------------------------
START TRANSACTION;
USE `bd_sihv`;
INSERT INTO `bd_sihv`.`pessoa` (`PK_pessoa`, `cpf-cnpj`, `nome`, `logra`, `casaNumero`, `bairro`, `cidade`, `cep`, `complemento`, `uf`, `cadDataHora`, `email`, `exclusaoLogica`) VALUES (1, '0@Q.4$H.8*L-(1)', 'Senhor x', 'xxx', 'xxx000', 'xxx', 'xxx', '00000-000', 'xxx', 'PA', DEFAULT, 'senhorx@email.com', 0);
INSERT INTO `bd_sihv`.`pessoa` (`PK_pessoa`, `cpf-cnpj`, `nome`, `logra`, `casaNumero`, `bairro`, `cidade`, `cep`, `complemento`, `uf`, `cadDataHora`, `email`, `exclusaoLogica`) VALUES (2, '00.000.000/0000-01', 'Hospital Veterinário', 'Hospital veterinário', '001', 'Hospital Veterinário', 'Hospital Veterinário', '00000-001', 'Hospital veterinário', 'PA', DEFAULT, 'naoInformado@naoInformado.com', 0);
INSERT INTO `bd_sihv`.`pessoa` (`PK_pessoa`, `cpf-cnpj`, `nome`, `logra`, `casaNumero`, `bairro`, `cidade`, `cep`, `complemento`, `uf`, `cadDataHora`, `email`, `exclusaoLogica`) VALUES (3, '00.000.000/0000-02', 'Bombeiro', 'Bombeiro', '002', 'Bombeiro', 'Bombeiro', '00000-002', 'Bombeiro', 'PA', DEFAULT, 'naoInformado@naoInformado.com', 0);
INSERT INTO `bd_sihv`.`pessoa` (`PK_pessoa`, `cpf-cnpj`, `nome`, `logra`, `casaNumero`, `bairro`, `cidade`, `cep`, `complemento`, `uf`, `cadDataHora`, `email`, `exclusaoLogica`) VALUES (4, '00.000.000/0000-03', 'Policia Militar', 'Policia militar', '003', 'Policia Militar', 'Policia Militar', '00000-003', 'Policia militar', 'PA', DEFAULT, 'naoInformado@naoInformado.com', 0);
INSERT INTO `bd_sihv`.`pessoa` (`PK_pessoa`, `cpf-cnpj`, `nome`, `logra`, `casaNumero`, `bairro`, `cidade`, `cep`, `complemento`, `uf`, `cadDataHora`, `email`, `exclusaoLogica`) VALUES (5, '00.000.000/0000-04', 'Ibama', 'Ibama', '004', 'Ibama', 'Ibama', '00000-004', 'Ibama', 'PA', DEFAULT, 'naoInformado@naoInformado.com', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bd_sihv`.`cliente`
-- -----------------------------------------------------
START TRANSACTION;
USE `bd_sihv`;
INSERT INTO `bd_sihv`.`cliente` (`PK_cliente`, `FK_pessoa`) VALUES (1, 2);
INSERT INTO `bd_sihv`.`cliente` (`PK_cliente`, `FK_pessoa`) VALUES (2, 3);
INSERT INTO `bd_sihv`.`cliente` (`PK_cliente`, `FK_pessoa`) VALUES (3, 4);
INSERT INTO `bd_sihv`.`cliente` (`PK_cliente`, `FK_pessoa`) VALUES (4, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bd_sihv`.`telefone`
-- -----------------------------------------------------
START TRANSACTION;
USE `bd_sihv`;
INSERT INTO `bd_sihv`.`telefone` (`PK_telefone`, `FK_pessoa`, `numero`) VALUES (1, 1, '(0$) F4%s-U3#c');
INSERT INTO `bd_sihv`.`telefone` (`PK_telefone`, `FK_pessoa`, `numero`) VALUES (2, 2, '(00) 00000-0001');
INSERT INTO `bd_sihv`.`telefone` (`PK_telefone`, `FK_pessoa`, `numero`) VALUES (3, 3, '(00) 00000-0002');
INSERT INTO `bd_sihv`.`telefone` (`PK_telefone`, `FK_pessoa`, `numero`) VALUES (4, 4, '(00) 00000-0003');
INSERT INTO `bd_sihv`.`telefone` (`PK_telefone`, `FK_pessoa`, `numero`) VALUES (5, 5, '(00) 00000-0004');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bd_sihv`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `bd_sihv`;
INSERT INTO `bd_sihv`.`user` (`PK_user`, `FK_pessoa`, `userNick`, `userSenha`, `userTipo`, `crmvMatricula`) VALUES (1, 1, 'senhor x', '6d28e787a2ac472b3670e5a009d28b97', 'MEDICO', 'CRMV-PA )Dx5');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bd_sihv`.`fisica`
-- -----------------------------------------------------
START TRANSACTION;
USE `bd_sihv`;
INSERT INTO `bd_sihv`.`fisica` (`PK_fisica`, `rg`, `sexo`, `FK_pessoa`) VALUES (1, 'n#Dr&X', 'M', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bd_sihv`.`juridica`
-- -----------------------------------------------------
START TRANSACTION;
USE `bd_sihv`;
INSERT INTO `bd_sihv`.`juridica` (`PK_juridica`, `tipoPessoaJuridica`, `FK_pessoa`) VALUES (1, 'Hospital Veterinário', 2);
INSERT INTO `bd_sihv`.`juridica` (`PK_juridica`, `tipoPessoaJuridica`, `FK_pessoa`) VALUES (2, 'Bombeiro', 3);
INSERT INTO `bd_sihv`.`juridica` (`PK_juridica`, `tipoPessoaJuridica`, `FK_pessoa`) VALUES (3, 'Policia Militar', 4);
INSERT INTO `bd_sihv`.`juridica` (`PK_juridica`, `tipoPessoaJuridica`, `FK_pessoa`) VALUES (4, 'Ibama', 5);

COMMIT;

