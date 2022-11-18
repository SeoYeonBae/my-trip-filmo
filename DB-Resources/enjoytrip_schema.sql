-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb3 ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`do`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`do` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`do` (
  `area_code` INT NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`area_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- insert do
INSERT INTO `do` (`area_code`,`name`) VALUES (1,'서울');
INSERT INTO `do` (`area_code`,`name`) VALUES (2,'인천');
INSERT INTO `do` (`area_code`,`name`) VALUES (3,'대전');
INSERT INTO `do` (`area_code`,`name`) VALUES (4,'대구');
INSERT INTO `do` (`area_code`,`name`) VALUES (5,'광주');
INSERT INTO `do` (`area_code`,`name`) VALUES (6,'부산');
INSERT INTO `do` (`area_code`,`name`) VALUES (7,'울산');
INSERT INTO `do` (`area_code`,`name`) VALUES (8,'세종특별자치시');
INSERT INTO `do` (`area_code`,`name`) VALUES (31,'경기도');
INSERT INTO `do` (`area_code`,`name`) VALUES (32,'강원도');
INSERT INTO `do` (`area_code`,`name`) VALUES (33,'충청북도');
INSERT INTO `do` (`area_code`,`name`) VALUES (34,'충청남도');
INSERT INTO `do` (`area_code`,`name`) VALUES (35,'경상북도');
INSERT INTO `do` (`area_code`,`name`) VALUES (36,'경상남도');
INSERT INTO `do` (`area_code`,`name`) VALUES (37,'전라북도');
INSERT INTO `do` (`area_code`,`name`) VALUES (38,'전라남도');
INSERT INTO `do` (`area_code`,`name`) VALUES (39,'제주도');

-- -----------------------------------------------------
-- Table `enjoytrip`.`sigungu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`sigungu` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`sigungu` (
  `area_code` INT NOT NULL,
  `sigungu_code` INT NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`area_code`, `sigungu_code`),
  CONSTRAINT `sigungu_do_area_code_fk`
    FOREIGN KEY (`area_code`)
    REFERENCES `enjoytrip`.`do` (`area_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- insert `sigungu`
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,1,'강남구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,2,'강동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,3,'강북구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,4,'강서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,5,'관악구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,6,'광진구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,7,'구로구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,8,'금천구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,9,'노원구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,10,'도봉구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,11,'동대문구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,12,'동작구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,13,'마포구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,14,'서대문구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,15,'서초구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,16,'성동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,17,'성북구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,18,'송파구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,19,'양천구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,20,'영등포구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,21,'용산구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,22,'은평구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,23,'종로구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,24,'중구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (1,25,'중랑구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,1,'강화군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,2,'계양구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,3,'미추홀구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,4,'남동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,5,'동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,6,'부평구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,7,'서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,8,'연수구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,9,'옹진군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (2,10,'중구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (3,1,'대덕구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (3,2,'동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (3,3,'서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (3,4,'유성구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (3,5,'중구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,1,'남구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,2,'달서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,3,'달성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,4,'동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,5,'북구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,6,'서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,7,'수성구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (4,8,'중구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (5,1,'광산구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (5,2,'남구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (5,3,'동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (5,4,'북구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (5,5,'서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,1,'강서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,2,'금정구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,3,'기장군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,4,'남구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,5,'동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,6,'동래구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,7,'부산진구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,8,'북구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,9,'사상구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,10,'사하구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,11,'서구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,12,'수영구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,13,'연제구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,14,'영도구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,15,'중구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (6,16,'해운대구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (7,1,'중구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (7,2,'남구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (7,3,'동구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (7,4,'북구');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (7,5,'울주군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (8,1,'세종특별자치시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,1,'가평군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,2,'고양시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,3,'과천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,4,'광명시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,5,'광주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,6,'구리시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,7,'군포시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,8,'김포시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,9,'남양주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,10,'동두천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,11,'부천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,12,'성남시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,13,'수원시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,14,'시흥시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,15,'안산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,16,'안성시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,17,'안양시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,18,'양주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,19,'양평군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,20,'여주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,21,'연천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,22,'오산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,23,'용인시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,24,'의왕시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,25,'의정부시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,26,'이천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,27,'파주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,28,'평택시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,29,'포천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,30,'하남시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (31,31,'화성시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,1,'강릉시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,2,'고성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,3,'동해시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,4,'삼척시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,5,'속초시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,6,'양구군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,7,'양양군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,8,'영월군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,9,'원주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,10,'인제군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,11,'정선군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,12,'철원군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,13,'춘천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,14,'태백시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,15,'평창군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,16,'홍천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,17,'화천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (32,18,'횡성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,1,'괴산군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,2,'단양군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,3,'보은군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,4,'영동군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,5,'옥천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,6,'음성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,7,'제천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,8,'진천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,9,'청원군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,10,'청주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,11,'충주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (33,12,'증평군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,1,'공주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,2,'금산군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,3,'논산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,4,'당진시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,5,'보령시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,6,'부여군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,7,'서산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,8,'서천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,9,'아산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,11,'예산군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,12,'천안시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,13,'청양군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,14,'태안군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,15,'홍성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (34,16,'계룡시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,1,'경산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,2,'경주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,3,'고령군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,4,'구미시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,5,'군위군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,6,'김천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,7,'문경시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,8,'봉화군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,9,'상주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,10,'성주군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,11,'안동시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,12,'영덕군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,13,'영양군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,14,'영주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,15,'영천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,16,'예천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,17,'울릉군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,18,'울진군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,19,'의성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,20,'청도군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,21,'청송군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,22,'칠곡군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (35,23,'포항시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,1,'거제시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,2,'거창군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,3,'고성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,4,'김해시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,5,'남해군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,6,'마산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,7,'밀양시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,8,'사천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,9,'산청군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,10,'양산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,12,'의령군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,13,'진주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,14,'진해시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,15,'창녕군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,16,'창원시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,17,'통영시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,18,'하동군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,19,'함안군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,20,'함양군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (36,21,'합천군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,1,'고창군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,2,'군산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,3,'김제시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,4,'남원시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,5,'무주군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,6,'부안군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,7,'순창군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,8,'완주군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,9,'익산시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,10,'임실군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,11,'장수군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,12,'전주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,13,'정읍시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (37,14,'진안군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,1,'강진군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,2,'고흥군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,3,'곡성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,4,'광양시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,5,'구례군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,6,'나주시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,7,'담양군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,8,'목포시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,9,'무안군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,10,'보성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,11,'순천시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,12,'신안군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,13,'여수시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,16,'영광군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,17,'영암군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,18,'완도군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,19,'장성군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,20,'장흥군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,21,'진도군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,22,'함평군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,23,'해남군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (38,24,'화순군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (39,1,'남제주군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (39,2,'북제주군');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (39,3,'서귀포시');
INSERT INTO `sigungu` (`area_code`,`sigungu_code`,`name`) VALUES (39,4,'제주시');

-- -----------------------------------------------------
-- Table `enjoytrip`.`tour_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`tour_list` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`tour_list` (
  `idx` INT NOT NULL,
  `area_code` INT NULL DEFAULT NULL,
  `sigungu_code` INT NULL DEFAULT NULL,
  `title` TEXT NULL DEFAULT NULL,
  `addr1` TEXT NULL DEFAULT NULL,
  `addr2` TEXT NULL DEFAULT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `content_id` INT NULL DEFAULT NULL,
  `mapx` DOUBLE NULL DEFAULT NULL,
  `mapy` DOUBLE NULL DEFAULT NULL,
  `image` TEXT NULL DEFAULT NULL,
  `tel` TEXT NULL DEFAULT NULL,
  `zipcode` varchar(30) NULL DEFAULT NULL,
  PRIMARY KEY (`idx`),
  INDEX `tour_list_sigungu_area_code_fk_idx` (`area_code` ASC, `sigungu_code` ASC) VISIBLE,
  CONSTRAINT `tour_list_sigungu_area_code_sigungu_code_fk`
    FOREIGN KEY (`area_code` , `sigungu_code`)
    REFERENCES `enjoytrip`.`sigungu` (`area_code` , `sigungu_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- insert tourlist

-- -----------------------------------------------------
-- Table `enjoytrip`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`users` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`users` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_password` VARCHAR(20) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_tel` VARCHAR(20) NOT NULL,
  `manager` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`board` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
  `article_no` INT NOT NULL auto_increment,
  `user_id` VARCHAR(16) NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `hit` INT NOT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  INDEX `board_users_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`article_no`),
  CONSTRAINT `board_users_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`notice` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`notice` (
  `article_no` INT NOT NULL auto_increment,
  `user_id` VARCHAR(16) NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `hit` INT NOT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  INDEX `notice_users_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`article_no`),
  CONSTRAINT `notice_users_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- insert users
insert into enjoytrip.users (user_id, user_name, user_password, user_email, user_tel, manager)
values     ('ssafy', '김싸피', '1234', 'ssafy@ssafy.com', '010-1234-5678', 0), 
        ('admin', '관리자', '1234', 'admin@google.com', '010-5678-1234', 1);
select * from users;

-- insert boarddo
insert into enjoytrip.board (user_id, subject, content, hit, register_time)
values
('ssafy', '강아지 동반 펜션 로하랑 추천합니다.', '강원도에 있어요 너무 좋았습니다!', 0, now()),
('ssafy', '강릉 서핑 같이 가실 분 계신가요?', '2023년 8월에 같이 가실 분', 0, now());
select *
from board;

-- insert notice
insert into enjoytrip.notice (user_id, subject, content, hit, register_time)
values
('admin', '[공지사항] 개인정보 처리 방침 변경 안내', '참고하세요!', 0, now()),
('admin', '7월 이달의 리뷰 이벤트 당첨자', '장예주 강윤주 축하합니다!', 0, now());

