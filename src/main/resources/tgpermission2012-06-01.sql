/*
SQLyog Enterprise Trial - MySQL GUI v8.14 
MySQL - 5.1.45-community : Database - tgpermission
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tgpermission` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tgpermission`;

/*Table structure for table `c_action` */

DROP TABLE IF EXISTS `c_action`;

CREATE TABLE `c_action` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'è®°å½•Id',
  `name` varchar(30) NOT NULL COMMENT 'æ“ä½œåç§°',
  `enname` varchar(30) NOT NULL COMMENT 'è‹±æ–‡åç§°',
  `handler` varchar(100) DEFAULT NULL COMMENT 'æ“ä½œæ–¹æ³•',
  `icon` varchar(60) DEFAULT NULL COMMENT 'å›¾æ ‡',
  `status` varchar(30) NOT NULL COMMENT 'çŠ¶æ€',
  `orderid` int(10) unsigned DEFAULT NULL COMMENT 'æ’åº',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`),
  UNIQUE KEY `enname` (`enname`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `c_action` */

insert  into `c_action`(`id`,`name`,`enname`,`handler`,`icon`,`status`,`orderid`,`memo`) values (1,'æ·»åŠ ','add','add()','','status100',1,''),(4,'åˆ é™¤','delete','delete()','','status100',2,''),(5,'ä¿®æ”¹','modify','modify()','','status100',3,''),(7,'æŸ¥è¯¢','query','query()','','status100',7,''),(8,'å®¡æ ¸','validate','validate()','','status100',8,''),(10,'åˆ·æ–°','refresh','refresh()','','status100',9,''),(11,'é«˜çº§æŸ¥è¯¢','advanceQuery','','','status100',10,'');

/*Table structure for table `c_arguments` */

DROP TABLE IF EXISTS `c_arguments`;

CREATE TABLE `c_arguments` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'è®°å½•id',
  `name` varchar(30) DEFAULT NULL COMMENT 'å‚æ•°é”®å',
  `value` varchar(100) DEFAULT NULL COMMENT 'å‚æ•°é”®å€¼',
  `memo` varchar(100) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `orderid` int(10) DEFAULT NULL COMMENT 'æ’åº',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `c_arguments` */

insert  into `c_arguments`(`id`,`name`,`value`,`memo`,`orderid`) values (3,'SYSTEM_PASSWORD','123456','',0),(4,'SYSTEM_ICON1','icon','',2),(6,'SYSTEM_VALUE','value','',4);

/*Table structure for table `c_dictionarys` */

DROP TABLE IF EXISTS `c_dictionarys`;

CREATE TABLE `c_dictionarys` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'è®°å½•id',
  `nickname` varchar(30) DEFAULT NULL COMMENT 'æ˜µç§°',
  `name` varchar(30) DEFAULT NULL COMMENT 'å¸¸é‡æ˜¾ç¤ºåç§°',
  `value` varchar(50) DEFAULT NULL COMMENT 'å¸¸é‡ä»£ç å€¼',
  `createtime` varchar(30) DEFAULT NULL COMMENT 'åˆ›å»ºæ—¥æœŸ',
  `type` varchar(30) DEFAULT NULL COMMENT 'å¸¸é‡æ’åºå€¼',
  `updatable` varchar(10) DEFAULT NULL COMMENT 'æ˜¯å¦å¯ç¼–è¾‘',
  `creator` varchar(30) DEFAULT NULL COMMENT 'åˆ›å»ºäºº',
  `status` varchar(30) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `orderid` varchar(10) DEFAULT NULL COMMENT 'æ’åº',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

/*Data for the table `c_dictionarys` */

insert  into `c_dictionarys`(`id`,`nickname`,`name`,`value`,`createtime`,`type`,`updatable`,`creator`,`status`,`orderid`,`memo`) values (27,'status','å¯ç”¨','status100','2011-10-08','0','icon101','admin','status100','7',''),(28,'status','ç¦ç”¨','status101','2011-10-08','0','icon101','admin','status100','',''),(29,'icon','æ˜¯','icon100','2011-10-08','0','icon101','admin','status100','',''),(30,'icon','å¦','icon101','2011-10-08','0','icon101','admin','status100','',''),(31,'leaf','æ˜¯','leaf100','2011-10-08','0','icon101','admin','status100','',''),(32,'leaf','å¦','leaf101','2011-10-08','0','icon101','admin','status100','',''),(33,'open','æ˜¯','open100','2011-10-08','0','icon101','admin','status100','',''),(34,'open','å¦','open101','2011-10-08','0','icon101','admin','status100','',''),(35,'education','é«˜ä¸­','education100','2011-10-08','0','icon101','admin','status100','',''),(36,'education','å¤§ä¸“','education101','2011-10-08','0','icon100','admin','status101','',''),(37,'education','æœ¬ç§‘','education102','2011-10-08','0','icon101','admin','status100','',''),(38,'education','ç ”ç©¶ç”Ÿ','education103','2011-10-08','0','icon101','admin','status100','',''),(39,'marriage','å·²å©š','marriage100','2011-10-08','0','icon101','admin','status100','',''),(40,'marriage','æœªå©š','marriage101','2011-10-08','0','icon101','admin','status100','',''),(41,'sex','ç”·','sex100','2011-10-08','0','icon101','admin','status100','',''),(42,'sex','å¥³','sex101','2011-10-08','0','icon101','admin','status100','',''),(46,'priceType','æ”¶è´­ä»·','priceType100','2011-10-02','1','icon101','ç‹æ˜','status100','','ä»·æ ¼ç±»å‹'),(47,'priceType','æ‰¹å‘ä»·','priceType101','2011-10-02','1','icon101','ç‹æ˜','status100','','ä»·æ ¼ç±»å‹'),(48,'priceType','é›¶å”®ä»·','priceType102','2011-10-02','1','icon101','ç‹æ˜','status100','','ä»·æ ¼ç±»å‹'),(49,'telephoneType','æ‰‹æœº','telephoneType100','2011-10-09','','icon101','','status100','',''),(50,'telephoneType','å›ºå®šç”µè¯','telephoneType101','2011-10-09','','icon101','','status100','',''),(51,'telephoneType','å°çµé€š','telephoneType102','2011-10-09','','icon101','','status100','',''),(52,'farmertype','æ•£å…¼å°æˆ·','farmertype100','2011-10-09','','icon101','','status100','',''),(53,'farmertype','ä¸“ä¸šå¤§æˆ·','farmertype101','2011-10-09','','icon101','','status100','',''),(54,'farmertype','åˆä½œç»„ç»‡','farmertype102','2011-10-09','','icon101','','status100','',''),(55,'farmertype','ä¸­ä»‹ç»çºª','farmertype103','2011-10-09','','icon101','','status100','',''),(56,'farmertype','ä¿¡æ¯ç«™ç‚¹','farmertype104','2011-10-09','','icon101','','status100','',''),(57,'farmertype','åŠ å·¥ä¼ä¸š','farmertype105','2011-10-09','','icon101','','status100','',''),(58,'farmertype','å•†è´¸ç‰©æµ','farmertype106','2011-10-09','','icon101','','status100','',''),(59,'farmertype','æ¶ˆè´¹ç¾¤å›¢','farmertype107','2011-10-09','','icon100','','status100','',''),(60,'farmertype','æ¶‰å†œå…¬èŒ','farmertype108','2011-10-09','','icon101','','status100','',''),(61,'farmertype','å…¶ä»–äººå‘˜','farmertype109','2011-10-09','','icon101','','status100','',''),(62,'examinestate','æœªå®¡','examinestate100','2011-10-03','1','icon101','ç‹æ˜','status100','1','ä»·æ ¼åº“å®¡æ ¸çŠ¶æ€'),(63,'examinestate','å·²å®¡','examinestate101','2011-10-03','1','icon101','ç‹æ˜','status100','1',''),(64,'questionnaireType','å•é€‰é¢˜','questionnaireType100','2011-10-12','1','icon101','ç‹æ˜','status100','1','è°ƒæŸ¥å·¥å•é—®é¢˜ç±»å‹'),(65,'questionnaireType','å¤šé€‰é¢˜','questionnaireType101','2011-10-12','1','icon101','ç‹æ˜','status100','1','1'),(66,'questionnaireType','ç®€ç­”é¢˜','questionnaireType102','2011-10-12','1','icon101','ç‹æ˜','status100','1','1'),(67,'supplyProducttype','æ™®é€š','supplyProducttype100','2011-10-12','','icon100','','status100','',''),(68,'supplyProducttype','æœ‰æœº','supplyProductname100','2011-10-12','','icon100','','status100','',''),(69,'supplyProducttype','æ— å…¬å®³','supplyProductname101','2011-10-12','','icon100','','status100','',''),(70,'supplyProducttype','ç»¿A','supplyProductname102','2011-10-12','','icon100','','status100','',''),(71,'supplyProducttype','ç»¿AA','supplyProductname103','2011-10-12','','icon100','','status100','',''),(72,'supplyType','é¢„ä¾›','supplyType100','2011-10-12','','icon100','','status100','',''),(73,'supplyType','é¢„æ±‚','supplyType101','2011-10-12','','icon100','','status100','',''),(74,'supplyType','ç°ä¾›','supplyType102','2011-10-12','','icon100','','status100','',''),(75,'supplyType','ç°æ±‚','supplyType103','2011-10-12','','icon100','','status100','',''),(79,'quesEmergency','å¹³æ€¥','quesEmergency100','2011-10-12','1','icon100','admin','status100','1',''),(80,'quesEmergency','åŠ æ€¥','quesEmergency101','2011-10-12','2','icon100','admin','status100','2',''),(81,'quesEmergency','ç‰¹æ€¥','quesEmergency102','2011-10-12','3','icon100','admin','status100','3',''),(82,'quesEmergency','ç‰¹æ','quesEmergency103','2011-10-12','4','icon100','admin','status100','4',''),(83,'quesAlarmlevel','æ— ','quesAlarmlevel100','2011-10-12','1','icon100','admin','','1',''),(84,'quesAlarmlevel','è“','quesAlarmlevel101','2011-10-12','2','icon100','admin','status100','2',''),(85,'quesAlarmlevel','é»„','quesAlarmlevel102','2011-10-12','3','icon100','admin','','3',''),(86,'quesAlarmlevel','æ©™','quesAlarmlevel103','2011-10-12','4','','admin','','4',''),(87,'quesAlarmlevel','çº¢','quesAlarmlevel104','2011-10-12','5','','admin','','5',''),(88,'quesSettingstate','å·²è§£å†³','quesSettingstate103','2011-10-12','1','','admin','status100','1',''),(89,'quesSettingstate','å¾…è§£å†³','quesSettingstate104','2011-10-12','2','','admin','status100','2',''),(90,'quesSettingstate','æ”¾å¼ƒ','quesSettingstate105','2011-10-12','3','','admin','status100','3',''),(91,'quesSettingstyle','åå¸­è§£å†³','quesSettingstyle100','2011-10-12','1','','admin','status100','1',''),(92,'quesSettingstyle','èµ„æºäººå‘˜è§£å†³','quesSettingstyle101','2011-10-12','2','','admin','status100','2',''),(93,'quesSettingstyle','ä¸“å®¶è§£å†³','quesSettingstyle102','2011-10-12','3','','admin','status100','3',''),(94,'quesSettingstyle','å†œå‹æ¨èè§£å†³','quesSettingstyle103','2011-10-12','4','','admin','status100','4',''),(96,'quesReplystyle','è‡ªåŠ¨å‘é€','quesReplystyle100','2011-10-12','1','','admin','status100','1',''),(97,'quesReplystyle','æ‰‹åŠ¨å‘é€','quesReplystyle101','2011-10-12','2','','admin','status100','2',''),(98,'quesAsktype','é—®è¯¢','quesAsktype100','2011-10-12','1','','admin','status100','1',''),(99,'quesAsktype','å‘å¸ƒ','quesAsktype101','2011-10-12','2','','admin','status100','2',''),(100,'quesAsktype','å…¶ä»–','quesAsktype102','2011-10-12','3 ','icon100','admin','status100','3',''),(101,'surveyInfoState','åŸå§‹','surveyInfoState100','2011-10-13','','icon100','','status100','',''),(102,'surveyInfoState','å¾…å®¡','surveyInfoState101','2011-10-13','','icon100','','status100','',''),(103,'surveyInfoState','å·²å®¡','surveyInfoState102','2011-10-13','','icon100','','status100','',''),(104,'surveyInfoState','å‘å¸ƒ','surveyInfoState103','2011-10-13','','icon100','','status100','',''),(105,'priceunit','å…ƒ/å…¬æ–¤','priceunit100','2011-10-02','','icon101','ç‹æ˜','status100','',''),(106,'priceunit','å…ƒ/æ–¤','priceunit101','2011-10-02','','icon101','','status100','',''),(107,'priceunit','å…ƒ/å¨','priceunit102','2011-10-02','','icon101','','status100','',''),(108,'quesTypePart','å“ç§','quesTypePart100','2011-10-18','1','icon100','admin','status100','1',''),(109,'quesTypePart','æ’­ç§è‚²è‹—','quesTypePart101','2011-10-18','2','icon100','haoly','status100','2',''),(110,'quesTypePart','æ ½åŸ¹ç®¡ç†','quesTypePart102','2011-10-18','3','icon100','','status100','3',''),(111,'quesTypePart','é‡‡æ”¶è´®è¿','quesTypePart103','2011-10-18','4','icon100','','status100','4',''),(112,'quesTypePart','åŠ å·¥','quesTypePart104','2011-10-18','5','icon100','','status100','',''),(113,'quesTypePart','ç—…è™«è‰å®³é˜²æ²»','quesTypePart105','2011-10-18','6','icon100','','status100','6',''),(114,'quesTypePart','å¸‚åœºè¡Œæƒ…','quesTypePart106','2011-10-18','7','icon100','','status100','7',''),(115,'businessPeriodic','æŒ‰å¤©','businessPeriodic100','2011-10-20','1','icon101','haoly','status100','1',''),(116,'businessPeriodic','æŒ‰å‘¨','businessPeriodic101','2011-10-20','2','icon101','','status100','2',''),(117,'businessPeriodic','æŒ‰æœˆ','businessPeriodic102','2011-10-20','3','icon101','','status100','3',''),(118,'businessType','çŸ­ä¿¡','businessType100','2011-10-20','1','icon101','haoly','status100','1',''),(119,'businessType','å½©ä¿¡','businessType101','2011-10-20','2','icon100','','status100','2',''),(120,'businessType','æ‰‹æœºæŠ¥','businessType102','2011-10-20','3','icon100','','status100','3',''),(121,'productType','åˆçº§äº§å“','productType100','2011-10-20','','icon100','','status100','',''),(122,'productType','ä¸­çº§äº§å“','productType101','2011-10-20','','icon100','','status100','',''),(123,'productType','é«˜çº§äº§å“','productType102','2011-10-20','','icon100','','status100','',''),(124,'productStatus','å…è´¹','productStatus100','2011-10-20','','icon100','','status100','',''),(125,'productStatus','æ”¶è´¹','productStatus101','2011-10-20','','icon100','','status100','',''),(126,'auditState','åˆå§‹','auditState100','2011-10-27','1','icon100','haoly','status100','1',''),(127,'auditState','å¾…å®¡','auditState101','2011-10-27','2','icon100','','status100','2',''),(128,'auditState','é©³å›','auditState102','2011-10-27','3','icon100','','status100','3',''),(129,'auditState','å·²å®¡','auditState103','2011-10-27','4','icon100','','status100','4',''),(130,'auditState','å‘å¸ƒ','auditState104','2011-10-27','5','icon100','','status100','5',''),(131,'workordertype','é—®è¯¢å·¥å•','workordertype100','2011-11-01','','icon101','','status100','',''),(132,'workordertype','ä»·æ ¼å·¥å•','workordertype101','2011-11-01','','icon101','','status100','',''),(133,'workordertype','è°ƒæŸ¥å·¥å•','workordertype102','2011-11-01','','icon101','','status100','',''),(134,'workordertype','å®šåˆ¶å·¥å•','workordertype103','2011-11-01','','icon101','','status100','',''),(135,'workordertype','ä¾›æ±‚å·¥å•','workordertype104','2011-11-01','','icon101','','status100','',''),(136,'quesReplystyle','ä¸å‘é€','quesReplystyle102','2011-11-03','3','icon100','','status100','3',''),(137,'kiki','fff','kiki100','2011-11-16','','icon101','','status100','',''),(138,'kiki','ddd','kiki101','2011-11-16','','icon100','','status100','',''),(139,'å‘ç»™','é£','å‘ç»™100','2011-11-16','','icon100','','status100','',''),(140,'questionTypeTop','é˜²ç¾','questionTypeTop100','2011-11-23','1','icon100','haoly','status100','1',''),(141,'questionTypeTop','æ”¿ç­–','questionTypeTop101','2011-11-23','2','icon100','','status100','2',''),(142,'questionTypeTop','ç”Ÿäº§','questionTypeTop102','2011-11-23','3','icon100','','status100','3',''),(143,'questionTypeTop','å¸‚åœº','questionTypeTop103','2011-11-23','4','icon100','','status100','4',''),(144,'questionTypeTop','ç¤¾ä¼š','questionTypeTop104','2011-11-23','5','icon100','','status100','5',''),(145,'stage','ç«‹é¡¹','stage100','2011-12-12','','icon100','admin','status100','','é¡¹ç›®çš„ç«‹é¡¹é˜¶æ®µ'),(146,'stage','å¼€å‘','stage101','2011-12-12','','icon100','','status100','','é¡¹ç›®çš„å¼€å‘é˜¶æ®µ'),(147,'stage','æµ‹è¯•','stage102','2011-12-12','','icon100','','status100','','é¡¹ç›®çš„æµ‹è¯•é˜¶æ®µ'),(148,'stage','ç»´æŠ¤','stage103','2011-12-12','','icon100','','status100','','é¡¹ç›®çš„ç»´æŠ¤é˜¶æ®µ'),(149,'state','æ¿€æ´»','state100','2011-12-12','','icon100','','status100','','é¡¹ç›®æ¿€æ´»çŠ¶æ€'),(150,'state','æœªæ¿€æ´»','state101','2011-12-12','','icon100','','status100','','é¡¹ç›®æœªæ¿€æ´»çŠ¶æ€'),(151,'priority','é«˜','priority100','2011-12-12','','icon100','','status100','','ä¼˜å…ˆçº§ä¸ºé«˜'),(152,'priority','ä¸­','priority101','2011-12-12','','icon100','','status100','','ä¼˜å…ˆçº§ä¸ºä¸­'),(153,'priority','ä½','priority102','2011-12-12','','icon100','','status100','','ä¼˜å…ˆçº§ä¸ºä½'),(154,'state','ä¸é€‰','state102','2011-12-13','','icon100','','status100','',''),(155,'demand_State','æœªæ¿€æ´»','demand_State100','2011-12-14','','icon100','jiangqiang','status100','','éœ€æ±‚æœªæ¿€æ´»çŠ¶æ€'),(156,'demand_state','æœªå®Œæˆ','demand_state101','2011-12-14','','icon100','jiangqiang','status100','','éœ€æ±‚æ¿€æ´»ä½†æœªå®Œæˆ'),(157,'demand_state','å·²å®Œæˆ','demand_state102','2011-12-14','','icon100','jiangqiang','status100','','éœ€æ±‚æ¿€æ´»å¹¶å·²å®Œæˆ');

/*Table structure for table `c_flowtype` */

DROP TABLE IF EXISTS `c_flowtype`;

CREATE TABLE `c_flowtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(100) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `list` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `c_flowtype` */

insert  into `c_flowtype`(`id`,`typename`,`parentid`,`sort`,`list`) values (1,'å…¨éƒ¨ç±»å‹',NULL,1,1),(4,'æƒå¨1',1,4,4),(6,'qq',1,NULL,NULL);

/*Table structure for table `c_group` */

DROP TABLE IF EXISTS `c_group`;

CREATE TABLE `c_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ç»„è®°å½•id',
  `name` varchar(30) NOT NULL COMMENT 'ç»„åç§°',
  `parentid` int(10) unsigned DEFAULT NULL COMMENT 'ä¸Šçº§ç»„Id',
  `enname` varchar(30) DEFAULT NULL COMMENT 'è‹±æ–‡åç§°',
  `grouptype` varchar(20) DEFAULT NULL COMMENT 'ç»„ç±»åˆ«',
  `status` varchar(30) DEFAULT NULL COMMENT 'çŠ¶æ€',
  `orderid` varchar(30) DEFAULT NULL COMMENT 'æ’åº',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`),
  KEY `FK_group_group` (`parentid`),
  CONSTRAINT `FK_group_group` FOREIGN KEY (`parentid`) REFERENCES `c_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `c_group` */

insert  into `c_group`(`id`,`name`,`parentid`,`enname`,`grouptype`,`status`,`orderid`,`memo`) values (41,'åå¸­',NULL,'','','status100','2',''),(44,'åå¸­ç­é•¿',41,'as','asa','status101','','as'),(47,'æ™®é€šåå¸­',41,'ptzx','','status100','',''),(48,'èµ„æº',NULL,'zy','','status100','',''),(49,'èµ„æºç­é•¿',48,'zybz','','status100','',''),(50,'æ™®é€šèµ„æº',48,'ptzy','','status100','',''),(51,'å®¡æ ¸',NULL,'toexamine','','status100','2',''),(68,'ç­é•¿ç»„',NULL,'','','status100','',''),(71,'å†œæˆ·ç»„',NULL,'custom','å†œæˆ·','status100','3','');

/*Table structure for table `c_grouprole` */

DROP TABLE IF EXISTS `c_grouprole`;

CREATE TABLE `c_grouprole` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'è®°å½•',
  `groupid` int(10) unsigned NOT NULL COMMENT 'ç»„id',
  `roleid` int(10) unsigned NOT NULL COMMENT 'è§’è‰²id',
  PRIMARY KEY (`id`),
  KEY `FK_grouprole_group` (`groupid`),
  KEY `FK_grouprole_role` (`roleid`),
  CONSTRAINT `FK_grouprole_group` FOREIGN KEY (`groupid`) REFERENCES `c_group` (`id`),
  CONSTRAINT `FK_grouprole_role` FOREIGN KEY (`roleid`) REFERENCES `c_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

/*Data for the table `c_grouprole` */

insert  into `c_grouprole`(`id`,`groupid`,`roleid`) values (17,47,21),(19,51,20),(37,49,15),(44,41,20),(46,41,21),(47,41,15),(48,41,16),(76,48,15),(77,48,16),(78,49,16);

/*Table structure for table `c_operlog` */

DROP TABLE IF EXISTS `c_operlog`;

CREATE TABLE `c_operlog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'æ“ä½œæ—¥å¿—è®°å½•id',
  `opertype` varchar(20) DEFAULT NULL COMMENT 'æ“ä½œç±»å‹',
  `content` varchar(200) DEFAULT NULL COMMENT 'æ“ä½œå†…å®¹',
  `cost` int(100) unsigned DEFAULT NULL COMMENT 'è€—æ—¶',
  `createip` varchar(20) DEFAULT NULL COMMENT 'ipåœ°å€',
  `createuser` varchar(30) DEFAULT NULL COMMENT 'æ“ä½œç”¨æˆ·',
  `createdate` varchar(20) DEFAULT NULL COMMENT 'æ—¥æœŸ',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=813 DEFAULT CHARSET=utf8;

/*Data for the table `c_operlog` */

insert  into `c_operlog`(`id`,`opertype`,`content`,`cost`,`createip`,`createuser`,`createdate`,`memo`) values (5,'delteæ“ä½œ','è¶…çº§ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',51,'192.168.1.105','è¶…çº§ç”¨æˆ·','2011-31-29','delæ–¹æ³•'),(6,'saveæ“ä½œ','è¶…çº§ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.105','è¶…çº§ç”¨æˆ·','2011-09-29','saveæ–¹æ³•'),(7,'delteæ“ä½œ','è¶…çº§ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',51,'192.168.1.105','è¶…çº§ç”¨æˆ·','2011-09-29','delæ–¹æ³•'),(8,'saveæ“ä½œ','æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.105','admin','2011-09-29','saveæ–¹æ³•'),(9,'delteæ“ä½œ','æ‰§è¡Œdelæ–¹æ³•',56,'192.168.1.105','admin','2011-09-29','delæ–¹æ³•'),(10,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.105','admin','2011-09-29','saveæ–¹æ³•'),(11,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',76,'192.168.1.105','admin','2011-09-29','delæ–¹æ³•'),(12,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.105','admin','2011-09-29','saveæ–¹æ³•'),(13,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',56,'192.168.1.105','admin','2011-09-29','updateæ–¹æ³•'),(14,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',61,'192.168.1.105','admin','2011-09-29','delUserFromGroupæ–¹æ³•'),(15,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',66,'192.168.1.105','admin','2011-09-29','delUserFromGroupæ–¹æ³•'),(16,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',61,'192.168.1.105','admin','2011-09-29','delUserFromGroupæ–¹æ³•'),(17,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',61,'192.168.1.105','admin','2011-09-29','delUserFromGroupæ–¹æ³•'),(18,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',95,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(19,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',79,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(20,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',64,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(21,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(22,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(23,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',64,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(24,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(25,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(26,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(27,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(28,'delteæ“ä½œ','test,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',48,'192.168.1.105','test','2011-09-30','delUserFromGroupæ–¹æ³•'),(29,'delteæ“ä½œ','test,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',48,'192.168.1.105','test','2011-09-30','delUserFromGroupæ–¹æ³•'),(30,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',79,'192.168.1.105','admin','2011-09-30','delæ–¹æ³•'),(31,'delteæ“ä½œ','test,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',48,'192.168.1.105','test','2011-09-30','delUserFromGroupæ–¹æ³•'),(32,'updateæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',16,'192.168.1.105','test','2011-09-30','updateæ–¹æ³•'),(33,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(34,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(35,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','test','2011-09-30','saveæ–¹æ³•'),(36,'updateæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.105','test','2011-09-30','updateæ–¹æ³•'),(37,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(38,'updateæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',48,'192.168.1.105','test','2011-09-30','updateæ–¹æ³•'),(39,'updateæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',31,'192.168.1.105','test','2011-09-30','updateæ–¹æ³•'),(40,'updateæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',48,'192.168.1.105','test','2011-09-30','updateæ–¹æ³•'),(41,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',64,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(42,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-09-30','delæ–¹æ³•'),(43,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(44,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',47,'192.168.1.105','admin','2011-09-30','deleteæ–¹æ³•'),(45,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',16,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(46,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(47,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.105','admin','2011-09-30','delæ–¹æ³•'),(48,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(49,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(50,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','deleteæ–¹æ³•'),(51,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(52,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(53,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',50,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(54,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','delUserFromGroupæ–¹æ³•'),(55,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(56,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(57,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',76,'192.168.1.105','admin','2011-09-30','deleteæ–¹æ³•'),(58,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',61,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(59,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',71,'192.168.1.105','test','2011-09-30','saveæ–¹æ³•'),(60,'delteæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',86,'192.168.1.105','test','2011-09-30','deleteæ–¹æ³•'),(61,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.105','test','2011-09-30','saveæ–¹æ³•'),(62,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',136,'192.168.1.105','admin','2011-09-30','deleteæ–¹æ³•'),(63,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.105','test','2011-09-30','saveæ–¹æ³•'),(64,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',61,'192.168.1.105','test','2011-09-30','saveæ–¹æ³•'),(65,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',61,'192.168.1.105','test','2011-09-30','saveæ–¹æ³•'),(66,'delteæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',81,'192.168.1.105','test','2011-09-30','deleteæ–¹æ³•'),(67,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(68,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',51,'192.168.1.105','admin','2011-09-30','deleteæ–¹æ³•'),(69,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(70,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','delæ–¹æ³•'),(71,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','delæ–¹æ³•'),(72,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','saveæ–¹æ³•'),(73,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',46,'192.168.1.105','admin','2011-09-30','delUserFromGroupæ–¹æ³•'),(74,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'192.168.1.105','admin','2011-09-30','delæ–¹æ³•'),(75,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.105','admin','2011-09-30','updateæ–¹æ³•'),(76,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',73,'192.168.1.105','test','2011-10-05','saveæ–¹æ³•'),(77,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',65,'192.168.1.105','test','2011-10-05','saveæ–¹æ³•'),(78,'delteæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',110,'192.168.1.105','test','2011-10-05','deleteæ–¹æ³•'),(79,'updateæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',44367,'192.168.1.105','test','2011-10-05','updateæ–¹æ³•'),(80,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',59,'192.168.1.101','admin','2011-10-05','delæ–¹æ³•'),(81,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',72,'192.168.1.101','admin','2011-10-05','saveæ–¹æ³•'),(82,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',73,'192.168.1.100','admin','2011-10-05','updateæ–¹æ³•'),(83,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',100,'192.168.1.100','admin','2011-10-05','updateæ–¹æ³•'),(84,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',109,'192.168.1.100','admin','2011-10-05','updateæ–¹æ³•'),(85,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.100','admin','2011-10-05','updateæ–¹æ³•'),(86,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',75,'192.168.1.100','admin','2011-10-05','updateæ–¹æ³•'),(87,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',109,'192.168.1.101','admin','2011-10-05','updateæ–¹æ³•'),(88,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',25,'192.168.1.101','admin','2011-10-05','updateæ–¹æ³•'),(89,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',53,'192.168.1.101','admin','2011-10-05','updateæ–¹æ³•'),(90,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',76,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(91,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',31,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(92,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',32,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(93,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',50,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(94,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(95,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(96,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',32,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(97,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-10-06','saveæ–¹æ³•'),(98,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',37,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(99,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.105','admin','2011-10-06','saveæ–¹æ³•'),(100,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.105','admin','2011-10-06','saveæ–¹æ³•'),(101,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.105','admin','2011-10-06','saveæ–¹æ³•'),(102,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.101','admin','2011-10-06','updateæ–¹æ³•'),(103,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',108,'192.168.1.101','admin','2011-10-06','updateæ–¹æ³•'),(104,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',51,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(105,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',37,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(106,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',53,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(107,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',50,'192.168.1.105','admin','2011-10-06','updateæ–¹æ³•'),(108,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(109,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(110,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',43,'192.168.1.105','admin','2011-10-06','saveæ–¹æ³•'),(111,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',56,'192.168.1.105','admin','2011-10-06','delUserFromGroupæ–¹æ³•'),(112,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',64,'192.168.1.105','admin','2011-10-06','delUserFromGroupæ–¹æ³•'),(113,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',50,'192.168.1.100','admin','2011-10-06','delæ–¹æ³•'),(114,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.100','admin','2011-10-06','delæ–¹æ³•'),(115,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',56,'192.168.1.105','admin','2011-10-06','delUserFromGroupæ–¹æ³•'),(116,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',51,'192.168.1.105','admin','2011-10-06','delUserFromGroupæ–¹æ³•'),(117,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',63,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(118,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.105','admin','2011-10-06','saveæ–¹æ³•'),(119,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',37,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(120,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',18230,'192.168.1.105','admin','2011-10-06','updateæ–¹æ³•'),(121,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-06','delæ–¹æ³•'),(122,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromGroupæ–¹æ³•',51,'192.168.1.105','admin','2011-10-06','delUserFromGroupæ–¹æ³•'),(123,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',116,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(124,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',69,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(125,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',71,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(126,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',67,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(127,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',64,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(128,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',87,'192.168.1.100','admin','2011-10-06','updateæ–¹æ³•'),(129,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',66,'192.168.1.105','admin','2011-10-06','saveæ–¹æ³•'),(130,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.123','admin','2011-10-02','updateæ–¹æ³•'),(131,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.101','admin','2011-10-07','updateæ–¹æ³•'),(132,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',44,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(133,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',28,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(134,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(135,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',74,'192.168.1.100','admin','2011-10-07','updateæ–¹æ³•'),(136,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',40,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(137,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',53,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(138,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(139,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',4577,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(140,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(141,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',44,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(142,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(143,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',51,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(144,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(145,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(146,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(147,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(148,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(149,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(150,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(151,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(152,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',32,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(153,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',59,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(154,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',34,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(155,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(156,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(157,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',74,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(158,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33042,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(159,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',67193,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(160,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(161,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',40,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(162,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(163,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(164,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',34,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(165,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',157,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(166,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',32,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(167,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',32,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(168,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(169,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(170,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(171,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(172,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-07','saveæ–¹æ³•'),(173,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(174,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-07','delæ–¹æ³•'),(175,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',60,'192.168.1.105','admin','2011-10-08','delæ–¹æ³•'),(176,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',40,'192.168.1.105','admin','2011-10-08','delæ–¹æ³•'),(177,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',38,'192.168.1.105','admin','2011-10-08','delæ–¹æ³•'),(178,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.123','admin','2011-10-03','saveæ–¹æ³•'),(179,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',31,'192.168.1.123','admin','2011-10-03','updateæ–¹æ³•'),(180,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',47,'192.168.1.123','admin','2011-10-03','deleteæ–¹æ³•'),(181,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(182,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',38,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(183,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(184,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(185,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',44,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(186,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',36,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(187,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',45,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(188,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',36,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(189,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(190,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(191,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',37,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(192,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',76,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(193,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',57,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(194,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(195,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',45,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(196,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(197,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',50,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(198,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(199,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',38,'192.168.1.105','admin','2011-10-08','delæ–¹æ³•'),(200,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',51,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(201,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',51,'192.168.1.105','admin','2011-10-08','delUserFromæ–¹æ³•'),(202,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',47,'192.168.1.111','admin','2011-10-08','deleteæ–¹æ³•'),(203,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',64,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(204,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',32,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(205,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',45,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(206,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',55,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(207,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',31,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(208,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',95,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(209,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',81,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(210,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',71,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(211,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',64,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(212,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',91,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(213,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',104,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(214,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',64,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(215,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',65,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(216,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(217,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',64,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(218,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(219,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',51,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(220,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',36,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(221,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',48,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(222,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',27,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(223,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',53,'192.168.1.100','admin','2011-10-08','saveæ–¹æ³•'),(224,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(225,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(226,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(227,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',48,'192.168.1.100','admin','2011-10-08','updateæ–¹æ³•'),(228,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',79,'192.168.1.105','admin','2011-10-08','saveæ–¹æ³•'),(229,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',31,'192.168.1.105','admin','2011-10-08','updateæ–¹æ³•'),(230,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',36,'192.168.1.105','admin','2011-10-08','delæ–¹æ³•'),(231,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',65,'192.168.1.105','admin','2011-10-09','saveæ–¹æ³•'),(232,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',71,'192.168.1.105','admin','2011-10-09','updateæ–¹æ³•'),(233,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',73,'192.168.1.105','admin','2011-10-09','updateæ–¹æ³•'),(234,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(235,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(236,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(237,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.123','admin','2011-10-02','delæ–¹æ³•'),(238,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.123','admin','2011-10-02','delæ–¹æ³•'),(239,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.123','admin','2011-10-02','delæ–¹æ³•'),(240,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(241,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(242,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(243,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',82,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(244,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',57,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(245,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',80,'192.168.1.100','admin','2011-10-09','updateæ–¹æ³•'),(246,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(247,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',52,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(248,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',34,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(249,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',57,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(250,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',40,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(251,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(252,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',52,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(253,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(254,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(255,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(256,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(257,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(258,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',54,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(259,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',67,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(260,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',57,'192.168.1.100','admin','2011-10-09','saveæ–¹æ³•'),(261,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',149,'192.168.1.100','admin','2011-10-09','deleteæ–¹æ³•'),(262,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',76,'192.168.1.105','admin','2011-10-09','updateæ–¹æ³•'),(263,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.123','admin','2011-10-03','saveæ–¹æ³•'),(264,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.123','admin','2011-10-03','saveæ–¹æ³•'),(265,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.123','admin','2011-10-03','saveæ–¹æ³•'),(266,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',78,'192.168.1.123','admin','2011-10-03','updateæ–¹æ³•'),(267,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.123','admin','2011-10-03','updateæ–¹æ³•'),(268,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.123','admin','2011-10-03','updateæ–¹æ³•'),(269,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',52,'127.0.0.1','admin','2011-10-11','saveæ–¹æ³•'),(270,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.100','admin','2011-10-11','saveæ–¹æ³•'),(271,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',37,'192.168.1.100','admin','2011-10-11','saveæ–¹æ³•'),(272,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',45,'127.0.0.1','admin','2011-10-11','saveæ–¹æ³•'),(273,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'127.0.0.1','admin','2011-10-11','saveæ–¹æ³•'),(274,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',89,'127.0.0.1','admin','2011-10-11','deleteæ–¹æ³•'),(275,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',20,'192.168.1.101','admin','2011-10-11','updateæ–¹æ³•'),(276,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',8,'192.168.1.101','admin','2011-10-11','updateæ–¹æ³•'),(277,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',21,'192.168.1.101','admin','2011-10-11','updateæ–¹æ³•'),(278,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',57,'192.168.1.100','admin','2011-10-11','saveæ–¹æ³•'),(279,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',174,'192.168.1.100','admin','2011-10-11','updateæ–¹æ³•'),(280,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',124,'127.0.0.1','admin','2011-10-11','updateæ–¹æ³•'),(281,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',94,'192.168.1.111','admin','2011-10-11','updateæ–¹æ³•'),(282,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',86,'127.0.0.1','admin','2011-10-12','saveæ–¹æ³•'),(283,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',36,'127.0.0.1','admin','2011-10-12','saveæ–¹æ³•'),(284,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',37,'127.0.0.1','admin','2011-10-12','saveæ–¹æ³•'),(285,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',36,'127.0.0.1','admin','2011-10-12','saveæ–¹æ³•'),(286,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',39,'127.0.0.1','admin','2011-10-12','updateæ–¹æ³•'),(287,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',38,'127.0.0.1','admin','2011-10-12','updateæ–¹æ³•'),(288,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',26,'127.0.0.1','admin','2011-10-12','updateæ–¹æ³•'),(289,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',38,'127.0.0.1','admin','2011-10-12','deleteæ–¹æ³•'),(290,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',25,'127.0.0.1','admin','2011-10-12','saveæ–¹æ³•'),(291,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(292,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(293,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(294,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(295,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',40,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(296,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(297,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(298,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(299,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',44,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(300,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(301,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',42,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(302,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',31,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(303,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',33,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(304,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',40,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(305,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',22,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(306,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',38,'192.168.1.100','admin','2011-10-12','saveæ–¹æ³•'),(307,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',77,'192.168.1.105','admin','2011-10-12','updateæ–¹æ³•'),(308,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',66,'192.168.1.105','admin','2011-10-12','updateæ–¹æ³•'),(309,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',79,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(310,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',50,'127.0.0.1','admin','2011-10-12','delæ–¹æ³•'),(311,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',40,'127.0.0.1','admin','2011-10-12','delæ–¹æ³•'),(312,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(313,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',90,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(314,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',91,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(315,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(316,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(317,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(318,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(319,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(320,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(321,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(322,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(323,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(324,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(325,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(326,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(327,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(328,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(329,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(330,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(331,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(332,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(333,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(334,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(335,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','updateæ–¹æ³•'),(336,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.111','admin','2011-10-12','delæ–¹æ³•'),(337,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(338,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(339,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',88,'192.168.1.100','admin','2011-10-12','updateæ–¹æ³•'),(340,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.111','admin','2011-10-12','updateæ–¹æ³•'),(341,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','delæ–¹æ³•'),(342,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','delæ–¹æ³•'),(343,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.111','admin','2011-10-12','delæ–¹æ³•'),(344,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(345,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(346,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-12','saveæ–¹æ³•'),(347,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-10-12','saveæ–¹æ³•'),(348,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.105','admin','2011-10-12','saveæ–¹æ³•'),(349,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',107,'192.168.1.105','admin','2011-10-12','updateæ–¹æ³•'),(350,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',52,'127.0.0.1','admin','2011-10-13','saveæ–¹æ³•'),(351,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',77,'192.168.1.101','admin','2011-10-13','updateæ–¹æ³•'),(352,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.111','admin','2011-10-13','saveæ–¹æ³•'),(353,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.111','admin','2011-10-13','saveæ–¹æ³•'),(354,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',78,'192.168.1.111','admin','2011-10-13','updateæ–¹æ³•'),(355,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',28,'127.0.0.1','admin','2011-10-13','saveæ–¹æ³•'),(356,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',39,'127.0.0.1','admin','2011-10-13','delUserFromæ–¹æ³•'),(357,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',39,'127.0.0.1','admin','2011-10-13','delUserFromæ–¹æ³•'),(358,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',38,'127.0.0.1','admin','2011-10-13','saveæ–¹æ³•'),(359,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',37,'127.0.0.1','admin','2011-10-13','delæ–¹æ³•'),(360,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'127.0.0.1','admin','2011-10-13','delæ–¹æ³•'),(361,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'127.0.0.1','admin','2011-10-13','delæ–¹æ³•'),(362,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',47,'127.0.0.1','admin','2011-10-13','delUserFromæ–¹æ³•'),(363,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',4,'127.0.0.1','admin','2011-10-13','saveæ–¹æ³•'),(364,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',9,'127.0.0.1','admin','2011-10-13','saveæ–¹æ³•'),(365,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',9,'127.0.0.1','admin','2011-10-13','saveæ–¹æ³•'),(366,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',8,'127.0.0.1','test','2011-10-13','saveæ–¹æ³•'),(367,'saveæ“ä½œ','test,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',8,'127.0.0.1','test','2011-10-13','saveæ–¹æ³•'),(368,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',59,'192.168.1.105','admin','2011-10-13','saveæ–¹æ³•'),(369,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',45,'192.168.1.105','admin','2011-10-13','saveæ–¹æ³•'),(370,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',43,'192.168.1.105','admin','2011-10-13','saveæ–¹æ³•'),(371,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',36,'192.168.1.105','admin','2011-10-13','saveæ–¹æ³•'),(372,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.105','admin','2011-10-13','saveæ–¹æ³•'),(373,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',86,'192.168.1.105','admin','2011-10-13','updateæ–¹æ³•'),(374,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',94,'192.168.1.111','admin','2011-10-13','saveæ–¹æ³•'),(375,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'127.0.0.1','admin','2011-10-14','delæ–¹æ³•'),(376,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',20,'127.0.0.1','admin','2011-10-14','saveæ–¹æ³•'),(377,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'127.0.0.1','admin','2011-10-14','saveæ–¹æ³•'),(378,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',56,'192.168.1.101','admin','2011-10-14','updateæ–¹æ³•'),(379,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',51,'192.168.1.101','admin','2011-10-14','updateæ–¹æ³•'),(380,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',70,'192.168.1.101','admin','2011-10-14','updateæ–¹æ³•'),(381,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',26,'192.168.1.108','admin','2011-10-14','delæ–¹æ³•'),(382,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',26,'192.168.1.108','admin','2011-10-14','delæ–¹æ³•'),(383,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',68,'192.168.1.108','admin','2011-10-14','deleteæ–¹æ³•'),(384,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',21,'192.168.1.108','admin','2011-10-14','saveæ–¹æ³•'),(385,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',20,'192.168.1.108','admin','2011-10-14','delæ–¹æ³•'),(386,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.108','admin','2011-10-14','saveæ–¹æ³•'),(387,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-14','saveæ–¹æ³•'),(388,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.111','admin','2011-10-14','updateæ–¹æ³•'),(389,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.108','admin','2011-10-14','saveæ–¹æ³•'),(390,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',44,'192.168.1.108','admin','2011-10-14','delæ–¹æ³•'),(391,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.108','admin','2011-10-14','saveæ–¹æ³•'),(392,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',41,'192.168.1.108','admin','2011-10-14','delæ–¹æ³•'),(393,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',27,'192.168.1.108','admin','2011-10-14','saveæ–¹æ³•'),(394,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',62,'192.168.1.108','admin','2011-10-14','delæ–¹æ³•'),(395,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',26,'192.168.1.108','admin','2011-10-14','saveæ–¹æ³•'),(396,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(397,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',85,'192.168.1.105','admin','2011-10-15','deleteæ–¹æ³•'),(398,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',64,'192.168.1.105','admin','2011-10-15','deleteæ–¹æ³•'),(399,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',34,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(400,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(401,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',17,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(402,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(403,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',22,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(404,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(405,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(406,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(407,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(408,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(409,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(410,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(411,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(412,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(413,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(414,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(415,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',37,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(416,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',51,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(417,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',61,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(418,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',48,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(419,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',48,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(420,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',48,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(421,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',51,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(422,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',71,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(423,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',51,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(424,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',51,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(425,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',81,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(426,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',47,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(427,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',48,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(428,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',86,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(429,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',64,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(430,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',63,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(431,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',16,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(432,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',43,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(433,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',64,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(434,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',51,'192.168.1.105','admin','2011-10-15','delUserFromæ–¹æ³•'),(435,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(436,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(437,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(438,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',78,'192.168.1.123','admin','2011-10-02','deleteæ–¹æ³•'),(439,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.123','admin','2011-10-02','saveæ–¹æ³•'),(440,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',78,'192.168.1.123','admin','2011-10-02','updateæ–¹æ³•'),(441,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',78,'192.168.1.123','admin','2011-10-02','updateæ–¹æ³•'),(442,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',62,'192.168.1.123','admin','2011-10-02','updateæ–¹æ³•'),(443,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',34,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(444,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(445,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',56,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(446,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(447,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(448,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',36,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(449,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',42,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(450,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',23,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(451,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(452,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',41,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(453,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(454,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',60,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(455,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',49,'192.168.1.105','admin','2011-10-15','delæ–¹æ³•'),(456,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',44,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(457,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',37,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(458,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',53,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(459,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',53,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(460,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',37,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(461,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',42,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(462,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.105','admin','2011-10-15','updateæ–¹æ³•'),(463,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.105','admin','2011-10-15','saveæ–¹æ³•'),(464,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',35,'127.0.0.1','admin','2011-10-17','delæ–¹æ³•'),(465,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',34,'127.0.0.1','admin','2011-10-17','updateæ–¹æ³•'),(466,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.105','admin','2011-10-17','updateæ–¹æ³•'),(467,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',35,'192.168.1.105','admin','2011-10-17','updateæ–¹æ³•'),(468,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',39,'127.0.0.1','admin','2011-10-18','saveæ–¹æ³•'),(469,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'127.0.0.1','admin','2011-10-18','delæ–¹æ³•'),(470,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-18','saveæ–¹æ³•'),(471,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.111','admin','2011-10-18','saveæ–¹æ³•'),(472,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.111','admin','2011-10-18','saveæ–¹æ³•'),(473,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-18','saveæ–¹æ³•'),(474,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-18','saveæ–¹æ³•'),(475,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-18','saveæ–¹æ³•'),(476,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-18','saveæ–¹æ³•'),(477,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-20','saveæ–¹æ³•'),(478,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',16,'192.168.1.111','admin','2011-10-20','saveæ–¹æ³•'),(479,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-20','saveæ–¹æ³•'),(480,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.111','admin','2011-10-20','saveæ–¹æ³•'),(481,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-20','saveæ–¹æ³•'),(482,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.111','admin','2011-10-20','saveæ–¹æ³•'),(483,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',55,'192.168.1.108','admin','2011-10-20','saveæ–¹æ³•'),(484,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.108','admin','2011-10-20','saveæ–¹æ³•'),(485,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',39,'192.168.1.108','admin','2011-10-20','saveæ–¹æ³•'),(486,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',45,'192.168.1.108','admin','2011-10-20','saveæ–¹æ³•'),(487,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.108','admin','2011-10-20','saveæ–¹æ³•'),(488,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',108,'192.168.1.101','admin','2011-10-21','deleteæ–¹æ³•'),(489,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',119,'127.0.0.1','admin','2011-10-21','updateæ–¹æ³•'),(490,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',94,'192.168.1.108','admin','2011-10-21','updateæ–¹æ³•'),(491,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',44,'192.168.1.108','admin','2011-10-21','updateæ–¹æ³•'),(492,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.105','admin','2011-10-21','updateæ–¹æ³•'),(493,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',5,'127.0.0.1','admin','2011-10-21','saveæ–¹æ³•'),(494,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',8,'127.0.0.1','admin','2011-10-21','saveæ–¹æ³•'),(495,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',54,'127.0.0.1','admin','2011-10-21','updateæ–¹æ³•'),(496,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',85,'127.0.0.1','admin','2011-10-24','updateæ–¹æ³•'),(497,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',6,'127.0.0.1','admin','2011-10-24','saveæ–¹æ³•'),(498,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',9,'127.0.0.1','admin','2011-10-24','saveæ–¹æ³•'),(499,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',38,'127.0.0.1','admin','2011-10-24','updateæ–¹æ³•'),(500,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',8,'127.0.0.1','admin','2011-10-24','saveæ–¹æ³•'),(501,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',34,'127.0.0.1','admin','2011-10-24','updateæ–¹æ³•'),(502,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',6,'127.0.0.1','admin','2011-10-24','saveæ–¹æ³•'),(503,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',38,'127.0.0.1','admin','2011-10-24','updateæ–¹æ³•'),(504,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',34,'127.0.0.1','admin','2011-10-24','updateæ–¹æ³•'),(505,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',8,'127.0.0.1','admin','2011-10-24','saveæ–¹æ³•'),(506,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'127.0.0.1','admin','2011-10-24','updateæ–¹æ³•'),(507,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',51,'127.0.0.1','admin','2011-10-24','updateæ–¹æ³•'),(508,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',36,'127.0.0.1','admin','2011-10-24','saveæ–¹æ³•'),(509,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',43,'127.0.0.1','admin','2011-10-24','delæ–¹æ³•'),(510,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',82,'127.0.0.1','admin','2011-10-25','delæ–¹æ³•'),(511,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'127.0.0.1','admin','2011-10-25','updateæ–¹æ³•'),(512,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',35,'127.0.0.1','admin','2011-10-25','updateæ–¹æ³•'),(513,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',24,'127.0.0.1','admin','2011-10-25','updateæ–¹æ³•'),(514,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',35,'127.0.0.1','admin','2011-10-25','updateæ–¹æ³•'),(515,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',39,'127.0.0.1','admin','2011-10-25','updateæ–¹æ³•'),(516,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',6,'127.0.0.1','admin','2011-10-25','saveæ–¹æ³•'),(517,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',8,'127.0.0.1','admin','2011-10-25','saveæ–¹æ³•'),(518,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',39,'127.0.0.1','admin','2011-10-25','delæ–¹æ³•'),(519,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',38,'127.0.0.1','admin','2011-10-25','delæ–¹æ³•'),(520,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',36,'127.0.0.1','admin','2011-10-25','saveæ–¹æ³•'),(521,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',39,'127.0.0.1','admin','2011-10-25','saveæ–¹æ³•'),(522,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',37,'127.0.0.1','admin','2011-10-25','saveæ–¹æ³•'),(523,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',66,'192.168.1.101','admin','2011-10-25','saveæ–¹æ³•'),(524,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',71,'192.168.1.101','admin','2011-10-25','updateæ–¹æ³•'),(525,'saveæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',44,'127.0.0.1','8001@163.com','2011-10-25','saveæ–¹æ³•'),(526,'updateæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',40,'127.0.0.1','8001@163.com','2011-10-25','updateæ–¹æ³•'),(527,'updateæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',39,'127.0.0.1','8001@163.com','2011-10-25','updateæ–¹æ³•'),(528,'delteæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',40,'127.0.0.1','8001@163.com','2011-10-25','delæ–¹æ³•'),(529,'saveæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',43,'127.0.0.1','8001@163.com','2011-10-25','saveæ–¹æ³•'),(530,'delteæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',44,'127.0.0.1','8001@163.com','2011-10-25','delæ–¹æ³•'),(531,'updateæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',38,'127.0.0.1','8001@163.com','2011-10-25','updateæ–¹æ³•'),(532,'saveæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',8,'127.0.0.1','8001@163.com','2011-10-25','saveæ–¹æ³•'),(533,'saveæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',34,'127.0.0.1','8001@163.com','2011-10-25','saveæ–¹æ³•'),(534,'delteæ“ä½œ','8001@163.com,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',40,'127.0.0.1','8001@163.com','2011-10-25','delæ–¹æ³•'),(535,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',56,'192.168.1.112','admin','2011-10-25','updateæ–¹æ³•'),(536,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',5,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(537,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(538,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',45,'192.168.1.112','admin','2011-10-25','delæ–¹æ³•'),(539,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',53,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(540,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.112','admin','2011-10-25','updateæ–¹æ³•'),(541,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.112','admin','2011-10-25','updateæ–¹æ³•'),(542,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',20391,'192.168.1.112','admin','2011-10-25','updateæ–¹æ³•'),(543,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',51,'192.168.1.112','admin','2011-10-25','updateæ–¹æ³•'),(544,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.112','admin','2011-10-25','updateæ–¹æ³•'),(545,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',44,'192.168.1.112','admin','2011-10-25','delæ–¹æ³•'),(546,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',0,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(547,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',5,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(548,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',5,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(549,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',171,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(550,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(551,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'192.168.1.112','admin','2011-10-25','delæ–¹æ³•'),(552,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',21,'192.168.1.112','admin','2011-10-25','delæ–¹æ³•'),(553,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',52,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(554,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',50,'192.168.1.112','admin','2011-10-25','updateæ–¹æ³•'),(555,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',21,'192.168.1.112','admin','2011-10-25','delæ–¹æ³•'),(556,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',72,'192.168.1.112','admin','2011-10-25','saveæ–¹æ³•'),(557,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.112','admin','2011-10-26','saveæ–¹æ³•'),(558,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',111,'192.168.1.110','admin','2011-10-26','updateæ–¹æ³•'),(559,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.110','admin','2011-10-26','updateæ–¹æ³•'),(560,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',146,'192.168.1.110','admin','2011-10-26','deleteæ–¹æ³•'),(561,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',61,'192.168.1.110','admin','2011-10-26','updateæ–¹æ³•'),(562,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',146,'192.168.1.112','admin','2011-10-26','updateæ–¹æ³•'),(563,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.112','admin','2011-10-26','saveæ–¹æ³•'),(564,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',61,'192.168.1.112','admin','2011-10-26','updateæ–¹æ³•'),(565,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',5,'127.0.0.1','admin','2011-10-26','saveæ–¹æ³•'),(566,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.112','admin','2011-10-26','saveæ–¹æ³•'),(567,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',176,'192.168.1.112','admin','2011-10-26','updateæ–¹æ³•'),(568,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',36,'192.168.1.112','admin','2011-10-26','updateæ–¹æ³•'),(569,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.112','admin','2011-10-26','saveæ–¹æ³•'),(570,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'192.168.1.112','admin','2011-10-26','delæ–¹æ³•'),(571,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',36,'192.168.1.112','admin','2011-10-26','delæ–¹æ³•'),(572,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.112','admin','2011-10-26','delæ–¹æ³•'),(573,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',36,'192.168.1.112','admin','2011-10-26','delæ–¹æ³•'),(574,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',172,'192.168.1.123','admin','2011-10-01','deleteæ–¹æ³•'),(575,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',41,'192.168.1.112','admin','2011-10-26','updateæ–¹æ³•'),(576,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',44,'192.168.1.112','admin','2011-10-26','saveæ–¹æ³•'),(577,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',56,'192.168.1.112','admin','2011-10-26','saveæ–¹æ³•'),(578,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',94,'192.168.1.111','admin','2011-10-27','saveæ–¹æ³•'),(579,'saveæ“ä½œ','8004@163.com,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',53,'127.0.0.1','8004@163.com','2011-10-27','saveæ–¹æ³•'),(580,'saveæ“ä½œ','8004@163.com,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'127.0.0.1','8004@163.com','2011-10-27','saveæ–¹æ³•'),(581,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-27','saveæ–¹æ³•'),(582,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-27','saveæ–¹æ³•'),(583,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-10-27','saveæ–¹æ³•'),(584,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-10-27','saveæ–¹æ³•'),(585,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.111','admin','2011-10-27','saveæ–¹æ³•'),(586,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'127.0.0.1','admin','2011-10-28','saveæ–¹æ³•'),(587,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',34,'127.0.0.1','admin','2011-10-28','updateæ–¹æ³•'),(588,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',52,'127.0.0.1','admin','2011-10-28','delæ–¹æ³•'),(589,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',34,'127.0.0.1','admin','2011-10-28','saveæ–¹æ³•'),(590,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',43,'127.0.0.1','admin','2011-10-28','saveæ–¹æ³•'),(591,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',34,'127.0.0.1','admin','2011-10-28','deleteæ–¹æ³•'),(592,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',69,'192.168.1.100','admin','2011-10-31','saveæ–¹æ³•'),(593,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',73,'192.168.1.100','admin','2011-10-31','updateæ–¹æ³•'),(594,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',71,'192.168.1.100','admin','2011-11-01','saveæ–¹æ³•'),(595,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',65,'192.168.1.100','admin','2011-11-01','saveæ–¹æ³•'),(596,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.100','admin','2011-11-01','saveæ–¹æ³•'),(597,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',65,'192.168.1.100','admin','2011-11-01','saveæ–¹æ³•'),(598,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.100','admin','2011-11-01','saveæ–¹æ³•'),(599,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',45,'192.168.1.112','admin','2011-11-01','delUserFromæ–¹æ³•'),(600,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',46,'192.168.1.112','admin','2011-11-01','delUserFromæ–¹æ³•'),(601,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',36,'192.168.1.112','admin','2011-11-01','delæ–¹æ³•'),(602,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',110,'192.168.1.102','admin','2011-11-03','updateæ–¹æ³•'),(603,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',57,'192.168.1.102','admin','2011-11-03','saveæ–¹æ³•'),(604,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',65,'192.168.1.102','admin','2011-11-03','updateæ–¹æ³•'),(605,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-11-03','saveæ–¹æ³•'),(606,'updateæ“ä½œ','8002@163.com,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',52,'192.168.1.102','8002@163.com','2011-11-03','updateæ–¹æ³•'),(607,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',61,'192.168.1.112','admin','2011-11-03','updateæ–¹æ³•'),(608,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',54,'192.168.1.112','admin','2011-11-03','updateæ–¹æ³•'),(609,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.111','admin','2011-11-04','saveæ–¹æ³•'),(610,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.112','admin','2011-11-04','saveæ–¹æ³•'),(611,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',91,'192.168.1.105','admin','2011-11-04','deleteæ–¹æ³•'),(612,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',64,'192.168.1.100','admin','2011-11-04','saveæ–¹æ³•'),(613,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',127,'192.168.1.100','admin','2011-11-04','deleteæ–¹æ³•'),(614,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',41,'192.168.1.112','admin','2011-11-04','saveæ–¹æ³•'),(615,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',56,'192.168.1.112','admin','2011-11-04','delUserFromæ–¹æ³•'),(616,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',52,'192.168.1.112','admin','2011-11-04','delUserFromæ–¹æ³•'),(617,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',45,'192.168.1.112','admin','2011-11-04','saveæ–¹æ³•'),(618,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.112','admin','2011-11-04','updateæ–¹æ³•'),(619,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',46,'192.168.1.112','admin','2011-11-04','deleteæ–¹æ³•'),(620,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',37,'192.168.1.112','admin','2011-11-04','deleteæ–¹æ³•'),(621,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',51,'192.168.1.112','admin','2011-11-04','saveæ–¹æ³•'),(622,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',53,'192.168.1.112','admin','2011-11-04','updateæ–¹æ³•'),(623,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',56,'192.168.1.112','admin','2011-11-04','deleteæ–¹æ³•'),(624,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',44,'192.168.1.112','admin','2011-11-04','updateæ–¹æ³•'),(625,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',46,'192.168.1.112','admin','2011-11-04','updateæ–¹æ³•'),(626,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',56,'192.168.1.112','admin','2011-11-04','delUserFromæ–¹æ³•'),(627,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',53,'192.168.1.112','admin','2011-11-04','delUserFromæ–¹æ³•'),(628,'saveæ“ä½œ','8002@163.com,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',35,'192.168.1.112','8002@163.com','2011-11-04','saveæ–¹æ³•'),(629,'delteæ“ä½œ','8002@163.com,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'192.168.1.112','8002@163.com','2011-11-04','delæ–¹æ³•'),(630,'delteæ“ä½œ','8002@163.com,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',26,'192.168.1.112','8002@163.com','2011-11-04','delæ–¹æ³•'),(631,'updateæ“ä½œ','8002@163.com,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',33,'192.168.1.112','8002@163.com','2011-11-04','updateæ–¹æ³•'),(632,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.108','admin','2011-11-15','saveæ–¹æ³•'),(633,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',103,'192.168.1.108','admin','2011-11-15','deleteæ–¹æ³•'),(634,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',91,'192.168.1.108','admin','2011-11-15','updateæ–¹æ³•'),(635,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',64,'192.168.1.108','admin','2011-11-15','saveæ–¹æ³•'),(636,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',141,'192.168.1.108','admin','2011-11-15','deleteæ–¹æ³•'),(637,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',81,'192.168.1.108','admin','2011-11-15','deleteæ–¹æ³•'),(638,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',88,'127.0.0.1','admin','2011-11-17','updateæ–¹æ³•'),(639,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',104,'127.0.0.1','admin','2011-11-17','updateæ–¹æ³•'),(640,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',83,'127.0.0.1','admin','2011-11-17','updateæ–¹æ³•'),(641,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',99,'127.0.0.1','admin','2011-11-17','updateæ–¹æ³•'),(642,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',95,'127.0.0.1','admin','2011-11-17','updateæ–¹æ³•'),(643,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',30,'127.0.1.1','admin','2011-11-18','saveæ–¹æ³•'),(644,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',35,'127.0.1.1','admin','2011-11-18','saveæ–¹æ³•'),(645,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',235,'192.168.1.115','admin','2011-11-23','saveæ–¹æ³•'),(646,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',94,'192.168.1.115','admin','2011-11-23','updateæ–¹æ³•'),(647,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',78,'192.168.1.115','admin','2011-11-23','updateæ–¹æ³•'),(648,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',44,'192.168.1.112','admin','2011-11-23','delæ–¹æ³•'),(649,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.115','admin','2011-11-23','saveæ–¹æ³•'),(650,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.115','admin','2011-11-23','saveæ–¹æ³•'),(651,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.115','admin','2011-11-23','saveæ–¹æ³•'),(652,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',16,'192.168.1.115','admin','2011-11-23','saveæ–¹æ³•'),(653,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.115','admin','2011-11-23','saveæ–¹æ³•'),(654,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',156,'192.168.1.115','admin','2011-11-24','deleteæ–¹æ³•'),(655,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',92,'192.168.1.103','admin','2011-11-26','saveæ–¹æ³•'),(656,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',90,'192.168.1.103','admin','2011-11-26','updateæ–¹æ³•'),(657,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',62,'192.168.1.111','admin','2011-11-28','saveæ–¹æ³•'),(658,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.111','admin','2011-11-28','delæ–¹æ³•'),(659,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',46,'192.168.1.111','admin','2011-11-28','saveæ–¹æ³•'),(660,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',47,'192.168.1.111','admin','2011-11-28','delæ–¹æ³•'),(661,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.106','admin','2011-11-29','updateæ–¹æ³•'),(662,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',32,'192.168.1.106','admin','2011-11-29','delæ–¹æ³•'),(663,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.106','admin','2011-11-29','saveæ–¹æ³•'),(664,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',31,'192.168.1.106','admin','2011-11-29','saveæ–¹æ³•'),(665,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',46,'192.168.1.106','admin','2011-11-29','delæ–¹æ³•'),(666,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',80,'127.0.0.1','admin','2011-12-01','saveæ–¹æ³•'),(667,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',98,'127.0.0.1','admin','2011-12-01','updateæ–¹æ³•'),(668,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',103,'192.168.1.158','admin','2011-12-01','updateæ–¹æ³•'),(669,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',85,'192.168.1.158','admin','2011-12-01','updateæ–¹æ³•'),(670,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',57,'192.168.1.158','admin','2011-12-01','updateæ–¹æ³•'),(671,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',78,'192.168.1.158','admin','2011-12-01','updateæ–¹æ³•'),(672,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',78,'192.168.1.158','admin','2011-12-01','updateæ–¹æ³•'),(673,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',81,'192.168.1.106','admin','2011-12-06','delæ–¹æ³•'),(674,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',128,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(675,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',184,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(676,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',101,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(677,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',93,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(678,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',95,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(679,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',84,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(680,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',72,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(681,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',76,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(682,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',81,'192.168.1.105','admin','2011-12-06','deleteæ–¹æ³•'),(683,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',63,'192.168.1.102','admin','2011-12-06','deleteæ–¹æ³•'),(684,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.102','admin','2011-12-06','saveæ–¹æ³•'),(685,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',47,'192.168.1.102','admin','2011-12-06','deleteæ–¹æ³•'),(686,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',48,'192.168.1.107','admin','2011-12-07','delæ–¹æ³•'),(687,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-12-08','saveæ–¹æ³•'),(688,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.105','admin','2011-12-08','saveæ–¹æ³•'),(689,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-12-08','saveæ–¹æ³•'),(690,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',79,'192.168.1.105','admin','2011-12-08','updateæ–¹æ³•'),(691,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',80,'192.168.1.105','admin','2011-12-08','updateæ–¹æ³•'),(692,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.105','admin','2011-12-08','updateæ–¹æ³•'),(693,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',173,'192.168.1.105','admin','2011-12-09','updateæ–¹æ³•'),(694,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.105','admin','2011-12-09','updateæ–¹æ³•'),(695,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.105','admin','2011-12-09','updateæ–¹æ³•'),(696,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',64,'192.168.1.105','admin','2011-12-12','delæ–¹æ³•'),(697,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',32,'192.168.1.105','admin','2011-12-12','delæ–¹æ³•'),(698,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(699,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',64,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(700,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(701,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',80,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(702,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(703,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',63,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(704,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(705,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(706,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(707,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',47,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(708,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',32,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(709,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',48,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(710,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',49,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(711,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',49,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(712,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',50,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(713,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',49,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(714,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',32,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(715,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(716,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(717,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.105','admin','2011-12-12','saveæ–¹æ³•'),(718,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',48,'192.168.1.105','admin','2011-12-12','updateæ–¹æ³•'),(719,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.103','admin','2011-12-13','saveæ–¹æ³•'),(720,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',95,'192.168.1.103','admin','2011-12-13','updateæ–¹æ³•'),(721,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',63,'192.168.1.103','admin','2011-12-13','updateæ–¹æ³•'),(722,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.103','admin','2011-12-13','saveæ–¹æ³•'),(723,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.100','admin','2011-12-14','saveæ–¹æ³•'),(724,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',33,'192.168.1.100','admin','2011-12-14','saveæ–¹æ³•'),(725,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',17,'192.168.1.100','admin','2011-12-14','updateæ–¹æ³•'),(726,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',33,'192.168.1.100','admin','2011-12-14','updateæ–¹æ³•'),(727,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',32,'192.168.1.100','admin','2011-12-14','saveæ–¹æ³•'),(728,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',18,'192.168.1.100','admin','2011-12-14','updateæ–¹æ³•'),(729,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.1.105','admin','2011-12-19','saveæ–¹æ³•'),(730,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',33,'192.168.1.105','admin','2011-12-19','delæ–¹æ³•'),(731,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(732,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(733,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',21,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(734,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(735,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(736,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',62,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(737,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',21,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(738,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',62,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(739,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',52,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(740,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',52,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(741,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',59,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(742,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',42,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(743,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',62,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(744,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(745,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',41,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(746,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',21,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(747,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',21,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(748,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(749,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',36,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(750,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','delæ–¹æ³•'),(751,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',51,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(752,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',21,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(753,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',56,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(754,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(755,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(756,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(757,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',41,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(758,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',33,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(759,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(760,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(761,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(762,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',41,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(763,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(764,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(765,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',36,'192.168.1.158','admin','2012-02-02','deleteæ–¹æ³•'),(766,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',61,'192.168.1.199','admin','2012-04-12','saveæ–¹æ³•'),(767,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',70,'192.168.1.158','admin','2012-04-16','saveæ–¹æ³•'),(768,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',56,'192.168.1.158','admin','2012-04-16','updateæ–¹æ³•'),(769,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',40,'192.168.1.102','admin','2012-04-23','saveæ–¹æ³•'),(770,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdelæ–¹æ³•',38,'192.168.1.102','admin','2012-04-23','delæ–¹æ³•'),(771,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',40,'192.168.1.199','admin','2012-04-23','updateæ–¹æ³•'),(772,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',29,'192.168.1.199','admin','2012-04-23','updateæ–¹æ³•'),(773,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',77,'192.168.1.199','admin','2012-04-24','delUserFromæ–¹æ³•'),(774,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',328,'192.168.2.109','admin','2012-05-03','saveæ–¹æ³•'),(775,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',250,'192.168.2.109','admin','2012-05-03','updateæ–¹æ³•'),(776,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',125,'192.168.2.109','admin','2012-05-04','saveæ–¹æ³•'),(777,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',282,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(778,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',266,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(779,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',234,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(780,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',62,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(781,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',125,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(782,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',0,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(783,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',16,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(784,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',0,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(785,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',0,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(786,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',172,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(787,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',156,'192.168.2.109','admin','2012-05-04','saveæ–¹æ³•'),(788,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',31,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(789,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',47,'192.168.2.109','admin','2012-05-04','saveæ–¹æ³•'),(790,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',32,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(791,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',172,'192.168.2.109','admin','2012-05-04','saveæ–¹æ³•'),(792,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',141,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(793,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',187,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(794,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',889,'192.168.2.102','admin','2012-05-04','saveæ–¹æ³•'),(795,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',421,'192.168.2.109','admin','2012-05-04','saveæ–¹æ³•'),(796,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',125,'192.168.2.109','admin','2012-05-04','saveæ–¹æ³•'),(797,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',610,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(798,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',297,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(799,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',500,'192.168.2.109','admin','2012-05-04','deleteæ–¹æ³•'),(800,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',140,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(801,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',328,'192.168.2.109','admin','2012-05-04','updateæ–¹æ³•'),(802,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',156,'192.168.2.109','admin','2012-05-05','saveæ–¹æ³•'),(803,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',78,'192.168.2.109','admin','2012-05-05','deleteæ–¹æ³•'),(804,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',48,'192.168.1.158','admin','2012-05-30','deleteæ–¹æ³•'),(805,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',32,'192.168.1.158','admin','2012-05-30','updateæ–¹æ³•'),(806,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',48,'192.168.1.158','admin','2012-05-30','saveæ–¹æ³•'),(807,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',15,'192.168.1.158','admin','2012-05-30','saveæ–¹æ³•'),(808,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',96,'192.168.1.158','admin','2012-05-30','deleteæ–¹æ³•'),(809,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡ŒdelUserFromæ–¹æ³•',40,'192.168.1.158','admin','2012-05-30','delUserFromæ–¹æ³•'),(810,'updateæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œupdateæ–¹æ³•',40,'192.168.1.158','admin','2012-05-30','updateæ–¹æ³•'),(811,'saveæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œsaveæ–¹æ³•',105,'192.168.1.105','admin','2012-05-31','saveæ–¹æ³•'),(812,'delteæ“ä½œ','admin,ç”¨æˆ·æ‰§è¡Œdeleteæ–¹æ³•',37,'192.168.1.105','admin','2012-05-31','deleteæ–¹æ³•');

/*Table structure for table `c_resource` */

DROP TABLE IF EXISTS `c_resource`;

CREATE TABLE `c_resource` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'èµ„æºè®°å½•id',
  `name` varchar(30) NOT NULL COMMENT 'èµ„æºåç§°',
  `enname` varchar(30) NOT NULL COMMENT 'è‹±æ–‡åç§°',
  `systemid` int(10) unsigned NOT NULL COMMENT 'æ‰€å±ç³»ç»Ÿid',
  `parent_id` int(10) unsigned DEFAULT NULL COMMENT 'ä¸Šçº§èµ„æºè®°å½•id',
  `resourcetype` varchar(2) DEFAULT NULL COMMENT 'èµ„æºç±»å‹',
  `link` varchar(100) DEFAULT NULL COMMENT 'é“¾æ¥',
  `icon` varchar(30) DEFAULT NULL COMMENT 'å›¾æ ‡',
  `iconopen` varchar(30) DEFAULT NULL COMMENT 'æ‰“å¼€å›¾æ ‡',
  `isopen` varchar(30) DEFAULT NULL COMMENT 'æ˜¯å¦æ‰“å¼€',
  `isleaf` varchar(30) NOT NULL COMMENT 'æ˜¯å¦èŠ‚ç‚¹',
  `status` varchar(30) NOT NULL COMMENT 'çŠ¶æ€',
  `orderid` int(10) unsigned DEFAULT NULL COMMENT 'æ’åº',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`),
  KEY `FK_resource_system` (`systemid`),
  KEY `FK_resource_resource` (`parent_id`),
  CONSTRAINT `FK_resource_resource` FOREIGN KEY (`parent_id`) REFERENCES `c_resource` (`id`),
  CONSTRAINT `FK_resource_system` FOREIGN KEY (`systemid`) REFERENCES `c_system` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `c_resource` */

insert  into `c_resource`(`id`,`name`,`enname`,`systemid`,`parent_id`,`resourcetype`,`link`,`icon`,`iconopen`,`isopen`,`isleaf`,`status`,`orderid`,`memo`) values (5,'ç³»ç»Ÿä¸­å¿ƒ','systemCenter',22,NULL,'','','icon_xtzx.png','open100','open100','leaf100','status100',4,''),(8,'äººå‘˜ç®¡ç†','users',22,5,'','user/indexUser.tg','icon-nav','open100','open100','leaf100','status100',7,''),(10,'ç»„ç®¡ç†','group',22,5,'','group/indexGroup.tg','icon-nav','open100','open100','leaf100','status100',9,'é¥¿'),(11,'è§’è‰²ç®¡ç†','role',22,5,'','permissions/role/indexRole.tg','icon-nav','open100','open100','leaf100','status100',8,''),(17,'æ—¥å¿—ç®¡ç†','log',22,5,'æ—¥å¿—','log/indexLog.tg','','open100','open100','leaf100','status100',1,''),(18,'ç³»ç»Ÿå­—å…¸','dictionary',22,5,'','dictionary/indexDictionary.tg','','open100','open100','leaf100','status100',10,''),(19,'å…¨å±€å‚æ•°','arguments',22,5,'','permissions/arguments/indexArguments.tg','','open100','open100','leaf100','status100',11,''),(20,'æ“ä½œä¿¡æ¯','action',22,5,'','permissions/actions/indexActions.tg','','open100','open100','leaf100','status100',12,''),(21,'èµ„æºä¿¡æ¯','resource',22,5,'','permissions/resources/indexResources.tg','','open100','open100','leaf100','status100',13,''),(22,'ç³»ç»Ÿä¿¡æ¯','system',22,5,'','sys/indexMessage.tg','','open100','open100','leaf100','status100',19,''),(24,'æµç¨‹å®šä¹‰ç±»å‹','flowtype',22,5,'','views/workflow/flowtype/flowtype.jsp','','open100','open100','leaf100','status100',21,''),(25,'å†œå‹ä¿¡æ¯','callCustInfo',22,5,'','callcustinfo/indexCallCustInfo.tg','','open100','open100','leaf100','status100',22,'TEST'),(26,'å†œå‹','agriculture',22,5,'','agricultureTg/findAllAgriculture.tg','','open101','open101','leaf100','status100',23,'');

/*Table structure for table `c_resource_action` */

DROP TABLE IF EXISTS `c_resource_action`;

CREATE TABLE `c_resource_action` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `resource_id` int(10) unsigned NOT NULL,
  `action_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c_resource_action_1` (`action_id`),
  KEY `FK_c_resource_action_2` (`resource_id`),
  CONSTRAINT `FK_c_resource_action_2` FOREIGN KEY (`resource_id`) REFERENCES `c_resource` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_resource_action_1` FOREIGN KEY (`action_id`) REFERENCES `c_action` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `c_resource_action` */

insert  into `c_resource_action`(`id`,`resource_id`,`action_id`) values (12,18,10),(14,17,10),(15,21,1),(16,21,4),(52,11,11),(53,21,11),(55,20,11),(56,18,11),(57,8,11),(58,22,11),(59,26,11),(60,26,8),(61,26,1),(62,10,10),(63,10,1),(64,10,4),(65,10,5),(66,10,7),(67,10,8);

/*Table structure for table `c_role` */

DROP TABLE IF EXISTS `c_role`;

CREATE TABLE `c_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'è§’è‰²è®°å½•id',
  `name` varchar(30) NOT NULL COMMENT 'è§’è‰²åç§°',
  `enname` varchar(30) DEFAULT NULL COMMENT 'è‹±æ–‡åç§°',
  `status` varchar(30) NOT NULL COMMENT 'çŠ¶æ€ï¼ŒåŒ…æ‹¬ï¼šå¯ç”¨ï¼Œéšè—ï¼Œå†»ç»“ï¼Œå¼ƒç”¨',
  `orderid` int(10) unsigned DEFAULT NULL COMMENT 'æ’åº',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `c_role` */

insert  into `c_role`(`id`,`name`,`enname`,`status`,`orderid`,`memo`) values (15,'administrator','','status100',1,'æµ‹è¯•ä¿¡æ¯'),(16,'test','','status100',2,'å·¥ä½œ'),(20,'å®¡æ ¸','toexamine','status100',5,''),(21,'æ™®é€šåå¸­','at','status100',2,''),(22,'æ™®é€šèµ„æº','resource','status100',1,'TEST');

/*Table structure for table `c_roleauth` */

DROP TABLE IF EXISTS `c_roleauth`;

CREATE TABLE `c_roleauth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleid` int(11) NOT NULL COMMENT 'è§’è‰²id',
  `resourceid` int(11) NOT NULL COMMENT 'èµ„æºid',
  `actions` varchar(500) DEFAULT NULL COMMENT 'æ“ä½œid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=507 DEFAULT CHARSET=utf8;

/*Data for the table `c_roleauth` */

insert  into `c_roleauth`(`id`,`roleid`,`resourceid`,`actions`) values (238,21,22,NULL),(239,21,33,NULL),(240,21,49,NULL),(241,21,50,NULL),(242,21,5,NULL),(243,21,26,NULL),(244,21,18,NULL),(245,21,10,NULL),(246,21,42,NULL),(247,21,25,NULL),(248,21,48,NULL),(249,21,20,NULL),(250,21,17,NULL),(251,21,40,NULL),(252,21,41,NULL),(253,21,11,NULL),(254,21,29,NULL),(255,21,53,NULL),(256,21,52,NULL),(257,21,8,NULL),(258,21,30,NULL),(259,21,37,NULL),(260,21,51,NULL),(261,21,31,NULL),(262,21,39,NULL),(263,21,21,NULL),(264,21,34,NULL),(265,21,19,NULL),(266,21,47,NULL),(267,21,36,NULL),(268,21,38,NULL),(269,21,45,NULL),(365,20,10,''),(366,20,40,''),(367,20,53,''),(368,20,8,''),(369,20,36,''),(370,20,18,''),(371,20,41,''),(372,20,19,''),(373,20,21,''),(374,20,20,'action:add,action:delete,action:modify,action:query,action:validate,action:refresh,action:higherQuery'),(375,20,42,''),(376,20,50,''),(377,20,31,''),(378,20,39,''),(379,20,37,''),(380,20,45,''),(381,20,34,''),(382,20,52,''),(383,20,25,''),(384,20,11,''),(385,20,49,''),(386,20,17,''),(387,20,29,''),(388,20,51,''),(389,20,38,''),(390,20,47,''),(391,20,48,''),(392,20,26,''),(393,20,5,''),(394,20,30,''),(395,20,22,''),(443,20,65,''),(444,20,67,''),(445,15,18,NULL),(446,15,49,NULL),(447,15,38,NULL),(449,15,37,NULL),(450,15,40,NULL),(451,15,11,NULL),(452,15,52,NULL),(453,15,8,NULL),(454,15,36,NULL),(455,15,65,NULL),(456,15,31,NULL),(457,15,30,NULL),(458,15,53,NULL),(459,15,45,NULL),(460,15,34,NULL),(461,15,17,NULL),(462,15,21,NULL),(463,15,29,NULL),(464,15,19,NULL),(465,15,41,NULL),(466,15,51,NULL),(467,15,48,NULL),(468,15,68,NULL),(470,15,26,NULL),(471,15,42,NULL),(472,15,47,NULL),(473,15,22,NULL),(474,15,10,NULL),(475,15,50,NULL),(476,15,20,NULL),(477,15,67,NULL),(478,20,68,''),(479,15,69,NULL),(480,20,69,''),(481,21,67,NULL),(482,21,69,NULL),(483,21,65,NULL),(484,21,68,NULL),(489,15,24,NULL),(490,20,24,''),(491,15,27,NULL),(492,15,5,NULL),(493,15,25,NULL),(494,16,8,NULL),(495,16,5,NULL),(496,16,25,NULL),(497,16,21,NULL),(498,16,19,NULL),(499,16,22,NULL),(500,16,26,NULL),(501,16,20,NULL),(502,16,18,NULL),(503,16,11,NULL),(504,16,17,NULL),(505,16,24,NULL),(506,16,10,NULL);

/*Table structure for table `c_system` */

DROP TABLE IF EXISTS `c_system`;

CREATE TABLE `c_system` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'è®°å½•id',
  `name` varchar(30) NOT NULL COMMENT 'ç³»ç»Ÿåç§°',
  `enname` varchar(30) DEFAULT NULL COMMENT 'è‹±æ–‡åç§°',
  `contextpath` varchar(50) NOT NULL COMMENT 'ä¸Šä¸‹æ–‡',
  `tableprefix` varchar(50) NOT NULL COMMENT 'è¡¨åå‰ç¼€',
  `logo` varchar(30) DEFAULT NULL COMMENT 'ç³»ç»Ÿæ ‡å¿—',
  `icon` varchar(30) DEFAULT NULL COMMENT 'ç³»ç»Ÿå›¾æ ‡',
  `version` varchar(10) DEFAULT NULL COMMENT 'ç‰ˆæœ¬',
  `builddate` varchar(10) DEFAULT NULL COMMENT 'æ„å»ºæ—¥æœŸ',
  `status` varchar(10) NOT NULL COMMENT 'çŠ¶æ€',
  `orderid` int(10) unsigned DEFAULT NULL COMMENT 'æ’åº',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `c_system` */

insert  into `c_system`(`id`,`name`,`enname`,`contextpath`,`tableprefix`,`logo`,`icon`,`version`,`builddate`,`status`,`orderid`,`memo`) values (22,'æµ‹è¯•ç³»ç»Ÿ','test','/test','_test','test','','v1.0','','status100',6,'æµ‹è¯•ä¿¡æ¯');

/*Table structure for table `c_user` */

DROP TABLE IF EXISTS `c_user`;

CREATE TABLE `c_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ç”¨æˆ·è®°å½•id',
  `logonid` varchar(30) NOT NULL COMMENT 'ç”¨æˆ·ç™»å½•å',
  `password` varchar(64) NOT NULL COMMENT 'å¯†ç ',
  `name` varchar(30) NOT NULL COMMENT 'ç”¨æˆ·åç§°',
  `enname` varchar(30) DEFAULT NULL COMMENT 'è‹±æ–‡åç§°',
  `usertype` varchar(2) DEFAULT NULL COMMENT 'ç”¨æˆ·ç±»å‹ï¼Œ1è¡¨ç¤ºç³»ç»Ÿå†…å»ºè®°å½•ï¼Œæ— æ³•åˆ é™¤ä¿®æ”¹è¯¥è®°å½•',
  `position` varchar(30) DEFAULT NULL COMMENT 'èŒä½',
  `employeddate` varchar(10) DEFAULT NULL COMMENT 'å…¥èŒæ—¥æœŸ',
  `age` varchar(20) DEFAULT NULL COMMENT 'å¹´é¾„',
  `birthday` varchar(10) DEFAULT NULL COMMENT 'å‡ºç”Ÿæ—¥æœŸ',
  `sex` char(10) DEFAULT NULL COMMENT 'æ€§åˆ«',
  `marriage` char(20) DEFAULT NULL COMMENT 'å©šå§»çŠ¶å†µ',
  `education` varchar(20) DEFAULT NULL COMMENT 'æ•™è‚²æƒ…å†µ',
  `nativeplace` varchar(30) DEFAULT NULL COMMENT 'ç±è´¯',
  `address` varchar(50) DEFAULT NULL COMMENT 'åœ°å€',
  `phone` varchar(30) DEFAULT NULL COMMENT 'ç”µè¯',
  `email` varchar(30) DEFAULT NULL COMMENT 'é‚®ç®±',
  `registerdate` varchar(20) DEFAULT NULL COMMENT 'æ³¨å†Œæ—¶é—´',
  `lastlogondate` varchar(20) DEFAULT NULL COMMENT 'ä¸Šæ¬¡ç™»å½•æ—¶é—´',
  `lastlogonip` varchar(20) DEFAULT NULL COMMENT 'ä¸Šæ¬¡ç™»å½•IP',
  `lastlogoffdate` varchar(20) DEFAULT NULL COMMENT 'ä¸Šæ¬¡é€€å‡ºæ—¶é—´',
  `status` varchar(20) NOT NULL COMMENT 'çŠ¶æ€ï¼ŒåŒ…æ‹¬1å¯ç”¨ï¼Œ2éšè—ï¼Œ3å†»ç»“ï¼Œ4å¼ƒç”¨',
  `orderid` varchar(10) DEFAULT NULL COMMENT 'æ’åº',
  `memo` varchar(50) DEFAULT NULL COMMENT 'å¤‡æ³¨',
  `jobmember` varchar(100) DEFAULT NULL COMMENT 'å‘˜å·¥å·¥å·',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_1_c_user` (`logonid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `c_user` */

insert  into `c_user`(`id`,`logonid`,`password`,`name`,`enname`,`usertype`,`position`,`employeddate`,`age`,`birthday`,`sex`,`marriage`,`education`,`nativeplace`,`address`,`phone`,`email`,`registerdate`,`lastlogondate`,`lastlogonip`,`lastlogoffdate`,`status`,`orderid`,`memo`,`jobmember`) values (2,'test','test','æµ‹è¯•ç”¨æˆ·','test','1','å·¥äºº','','23','','sex100','marriage100','education101','å±±ä¸œé’å²›','å±±ä¸œé’å²›','13436521458','test@163.com','','','','','status100','','2112',''),(4,'admin','admin','admin','admin','1','admin','','','','sex100','marriage100','education100','','','','','','','','','status100','','',NULL);

/*Table structure for table `c_usergroup` */

DROP TABLE IF EXISTS `c_usergroup`;

CREATE TABLE `c_usergroup` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'è®°å½•Id',
  `userid` int(10) unsigned NOT NULL COMMENT 'ç”¨æˆ·id',
  `groupid` int(10) unsigned NOT NULL COMMENT 'ç»„id',
  PRIMARY KEY (`id`),
  KEY `FK_groupuser_group` (`groupid`),
  KEY `FK_groupuser_user` (`userid`),
  CONSTRAINT `FK_groupuser_group` FOREIGN KEY (`groupid`) REFERENCES `c_group` (`id`),
  CONSTRAINT `FK_groupuser_user` FOREIGN KEY (`userid`) REFERENCES `c_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `c_usergroup` */

insert  into `c_usergroup`(`id`,`userid`,`groupid`) values (47,2,48),(48,2,51),(49,4,51),(50,2,49);

/*Table structure for table `call_cust_info` */

DROP TABLE IF EXISTS `call_cust_info`;

CREATE TABLE `call_cust_info` (
  `CUST_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'å†œå‹IDï¼Œè‡ªå¢é•¿',
  `CUST_SEX` varchar(30) DEFAULT NULL COMMENT 'å†œå‹æ€§åˆ«',
  `CUST_BIRTHDAY` varchar(20) DEFAULT NULL COMMENT 'å‡ºç”Ÿæ—¥æœŸ',
  `CUST_PROFESSION` varchar(20) DEFAULT NULL COMMENT 'èŒä¸š',
  `CUST_MEMBER` varchar(10) DEFAULT NULL COMMENT 'æ˜¯å¦ä¼šå‘˜',
  `CUST_QQ` varchar(15) DEFAULT NULL COMMENT 'QQ',
  `CUST_EMAIL` varchar(50) DEFAULT NULL COMMENT 'Email',
  `CUST_INTEGRAL` int(11) DEFAULT NULL COMMENT 'ç§¯åˆ†',
  `CUST_IDNO` varchar(20) DEFAULT NULL COMMENT 'èº«ä»½è¯å·',
  `CUST_FARMERTYPE` varchar(400) DEFAULT NULL COMMENT 'å†œå‹ç±»å‹',
  `CUST_ADDRESSID` varchar(100) DEFAULT NULL COMMENT 'åœ°å€ID',
  `CUST_FARMERNAME` varchar(15) DEFAULT NULL COMMENT 'å§“å',
  `CUST_FID` varchar(16) DEFAULT '' COMMENT 'å¤–éƒ¨Id',
  `CUST_ADDRESSDETAIL` varchar(100) DEFAULT '' COMMENT 'åœ°å€çš„è¯¦ç»†ä¿¡æ¯',
  PRIMARY KEY (`CUST_ID`),
  KEY `FK_call_cust_info` (`CUST_ADDRESSID`),
  CONSTRAINT `FK_call_cust_info` FOREIGN KEY (`CUST_ADDRESSID`) REFERENCES `call_address` (`ADDRESS_ID`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

/*Data for the table `call_cust_info` */

insert  into `call_cust_info`(`CUST_ID`,`CUST_SEX`,`CUST_BIRTHDAY`,`CUST_PROFESSION`,`CUST_MEMBER`,`CUST_QQ`,`CUST_EMAIL`,`CUST_INTEGRAL`,`CUST_IDNO`,`CUST_FARMERTYPE`,`CUST_ADDRESSID`,`CUST_FARMERNAME`,`CUST_FID`,`CUST_ADDRESSDETAIL`) values (40,'sex100',NULL,'',NULL,'','',0,'','farmertype100',NULL,'ç‰›å…ˆç”Ÿ',NULL,NULL),(41,'sex100',NULL,'',NULL,'','',0,'','farmertype109',NULL,'éƒè·¯é‡',NULL,NULL),(42,'sex100',NULL,'',NULL,'','',0,'','farmertype106',NULL,'å¤ªå¤',NULL,NULL),(44,'sex100',NULL,'',NULL,'357454755','sunchaotong18@163.com',0,'','farmertype100',NULL,'å­™æœåˆš',NULL,''),(46,'sex100',NULL,'',NULL,'','',0,'','farmertype100',NULL,'å­™æœ',NULL,'');

/*Table structure for table `jbpm4_deployment` */

DROP TABLE IF EXISTS `jbpm4_deployment`;

CREATE TABLE `jbpm4_deployment` (
  `DBID_` bigint(20) NOT NULL,
  `NAME_` longtext,
  `TIMESTAMP_` bigint(20) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_deployment` */

insert  into `jbpm4_deployment`(`DBID_`,`NAME_`,`TIMESTAMP_`,`STATE_`) values (1,NULL,0,'active');

/*Table structure for table `jbpm4_deployprop` */

DROP TABLE IF EXISTS `jbpm4_deployprop`;

CREATE TABLE `jbpm4_deployprop` (
  `DBID_` bigint(20) NOT NULL,
  `DEPLOYMENT_` bigint(20) DEFAULT NULL,
  `OBJNAME_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `STRINGVAL_` varchar(255) DEFAULT NULL,
  `LONGVAL_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_DEPLPROP_DEPL` (`DEPLOYMENT_`),
  KEY `FK_DEPLPROP_DEPL` (`DEPLOYMENT_`),
  CONSTRAINT `FK_DEPLPROP_DEPL` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_deployprop` */

insert  into `jbpm4_deployprop`(`DBID_`,`DEPLOYMENT_`,`OBJNAME_`,`KEY_`,`STRINGVAL_`,`LONGVAL_`) values (4,1,'æµ‹è¯•æµç¨‹','langid','jpdl-4.4',NULL),(5,1,'æµ‹è¯•æµç¨‹','pdid','æµ‹è¯•æµç¨‹-1',NULL),(6,1,'æµ‹è¯•æµç¨‹','pdkey','æµ‹è¯•æµç¨‹',NULL),(7,1,'æµ‹è¯•æµç¨‹','pdversion',NULL,1);

/*Table structure for table `jbpm4_execution` */

DROP TABLE IF EXISTS `jbpm4_execution`;

CREATE TABLE `jbpm4_execution` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ACTIVITYNAME_` varchar(255) DEFAULT NULL,
  `PROCDEFID_` varchar(255) DEFAULT NULL,
  `HASVARS_` bit(1) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `ID_` varchar(255) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `HISACTINST_` bigint(20) DEFAULT NULL,
  `PARENT_` bigint(20) DEFAULT NULL,
  `INSTANCE_` bigint(20) DEFAULT NULL,
  `SUPEREXEC_` bigint(20) DEFAULT NULL,
  `SUBPROCINST_` bigint(20) DEFAULT NULL,
  `PARENT_IDX_` int(11) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  UNIQUE KEY `ID_` (`ID_`),
  KEY `IDX_EXEC_SUBPI` (`SUBPROCINST_`),
  KEY `IDX_EXEC_PARENT` (`PARENT_`),
  KEY `IDX_EXEC_SUPEREXEC` (`SUPEREXEC_`),
  KEY `IDX_EXEC_INSTANCE` (`INSTANCE_`),
  KEY `FK_EXEC_SUBPI` (`SUBPROCINST_`),
  KEY `FK_EXEC_INSTANCE` (`INSTANCE_`),
  KEY `FK_EXEC_SUPEREXEC` (`SUPEREXEC_`),
  KEY `FK_EXEC_PARENT` (`PARENT_`),
  CONSTRAINT `FK_EXEC_INSTANCE` FOREIGN KEY (`INSTANCE_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUBPI` FOREIGN KEY (`SUBPROCINST_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_EXEC_SUPEREXEC` FOREIGN KEY (`SUPEREXEC_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_execution` */

/*Table structure for table `jbpm4_hist_actinst` */

DROP TABLE IF EXISTS `jbpm4_hist_actinst`;

CREATE TABLE `jbpm4_hist_actinst` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `HPROCI_` bigint(20) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `START_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TRANSITION_` varchar(255) DEFAULT NULL,
  `NEXTIDX_` int(11) DEFAULT NULL,
  `HTASK_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_HTI_HTASK` (`HTASK_`),
  KEY `IDX_HACTI_HPROCI` (`HPROCI_`),
  KEY `FK_HACTI_HPROCI` (`HPROCI_`),
  KEY `FK_HTI_HTASK` (`HTASK_`),
  CONSTRAINT `FK_HACTI_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HTI_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_actinst` */

/*Table structure for table `jbpm4_hist_detail` */

DROP TABLE IF EXISTS `jbpm4_hist_detail`;

CREATE TABLE `jbpm4_hist_detail` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USERID_` varchar(255) DEFAULT NULL,
  `TIME_` datetime DEFAULT NULL,
  `HPROCI_` bigint(20) DEFAULT NULL,
  `HPROCIIDX_` int(11) DEFAULT NULL,
  `HACTI_` bigint(20) DEFAULT NULL,
  `HACTIIDX_` int(11) DEFAULT NULL,
  `HTASK_` bigint(20) DEFAULT NULL,
  `HTASKIDX_` int(11) DEFAULT NULL,
  `HVAR_` bigint(20) DEFAULT NULL,
  `HVARIDX_` int(11) DEFAULT NULL,
  `MESSAGE_` longtext,
  `OLD_STR_` varchar(255) DEFAULT NULL,
  `NEW_STR_` varchar(255) DEFAULT NULL,
  `OLD_INT_` int(11) DEFAULT NULL,
  `NEW_INT_` int(11) DEFAULT NULL,
  `OLD_TIME_` datetime DEFAULT NULL,
  `NEW_TIME_` datetime DEFAULT NULL,
  `PARENT_` bigint(20) DEFAULT NULL,
  `PARENT_IDX_` int(11) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_HDET_HVAR` (`HVAR_`),
  KEY `IDX_HDET_HACTI` (`HACTI_`),
  KEY `IDX_HDET_HTASK` (`HTASK_`),
  KEY `IDX_HDET_HPROCI` (`HPROCI_`),
  KEY `FK_HDETAIL_HVAR` (`HVAR_`),
  KEY `FK_HDETAIL_HPROCI` (`HPROCI_`),
  KEY `FK_HDETAIL_HTASK` (`HTASK_`),
  KEY `FK_HDETAIL_HACTI` (`HACTI_`),
  CONSTRAINT `FK_HDETAIL_HACTI` FOREIGN KEY (`HACTI_`) REFERENCES `jbpm4_hist_actinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`),
  CONSTRAINT `FK_HDETAIL_HVAR` FOREIGN KEY (`HVAR_`) REFERENCES `jbpm4_hist_var` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_detail` */

/*Table structure for table `jbpm4_hist_procinst` */

DROP TABLE IF EXISTS `jbpm4_hist_procinst`;

CREATE TABLE `jbpm4_hist_procinst` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) DEFAULT NULL,
  `PROCDEFID_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `START_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `ENDACTIVITY_` varchar(255) DEFAULT NULL,
  `NEXTIDX_` int(11) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_procinst` */

/*Table structure for table `jbpm4_hist_task` */

DROP TABLE IF EXISTS `jbpm4_hist_task`;

CREATE TABLE `jbpm4_hist_task` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `EXECUTION_` varchar(255) DEFAULT NULL,
  `OUTCOME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `END_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `NEXTIDX_` int(11) DEFAULT NULL,
  `SUPERTASK_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_HSUPERT_SUB` (`SUPERTASK_`),
  KEY `FK_HSUPERT_SUB` (`SUPERTASK_`),
  CONSTRAINT `FK_HSUPERT_SUB` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_task` */

/*Table structure for table `jbpm4_hist_var` */

DROP TABLE IF EXISTS `jbpm4_hist_var`;

CREATE TABLE `jbpm4_hist_var` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `PROCINSTID_` varchar(255) DEFAULT NULL,
  `EXECUTIONID_` varchar(255) DEFAULT NULL,
  `VARNAME_` varchar(255) DEFAULT NULL,
  `VALUE_` varchar(255) DEFAULT NULL,
  `HPROCI_` bigint(20) DEFAULT NULL,
  `HTASK_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_HVAR_HTASK` (`HTASK_`),
  KEY `IDX_HVAR_HPROCI` (`HPROCI_`),
  KEY `FK_HVAR_HPROCI` (`HPROCI_`),
  KEY `FK_HVAR_HTASK` (`HTASK_`),
  CONSTRAINT `FK_HVAR_HPROCI` FOREIGN KEY (`HPROCI_`) REFERENCES `jbpm4_hist_procinst` (`DBID_`),
  CONSTRAINT `FK_HVAR_HTASK` FOREIGN KEY (`HTASK_`) REFERENCES `jbpm4_hist_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_hist_var` */

/*Table structure for table `jbpm4_id_group` */

DROP TABLE IF EXISTS `jbpm4_id_group`;

CREATE TABLE `jbpm4_id_group` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL,
  `PARENT_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_GROUP_PARENT` (`PARENT_`),
  KEY `FK_GROUP_PARENT` (`PARENT_`),
  CONSTRAINT `FK_GROUP_PARENT` FOREIGN KEY (`PARENT_`) REFERENCES `jbpm4_id_group` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_id_group` */

/*Table structure for table `jbpm4_id_membership` */

DROP TABLE IF EXISTS `jbpm4_id_membership`;

CREATE TABLE `jbpm4_id_membership` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `USER_` bigint(20) DEFAULT NULL,
  `GROUP_` bigint(20) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_MEM_GROUP` (`GROUP_`),
  KEY `IDX_MEM_USER` (`USER_`),
  KEY `FK_MEM_GROUP` (`GROUP_`),
  KEY `FK_MEM_USER` (`USER_`),
  CONSTRAINT `FK_MEM_GROUP` FOREIGN KEY (`GROUP_`) REFERENCES `jbpm4_id_group` (`DBID_`),
  CONSTRAINT `FK_MEM_USER` FOREIGN KEY (`USER_`) REFERENCES `jbpm4_id_user` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_id_membership` */

/*Table structure for table `jbpm4_id_user` */

DROP TABLE IF EXISTS `jbpm4_id_user`;

CREATE TABLE `jbpm4_id_user` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `ID_` varchar(255) DEFAULT NULL,
  `PASSWORD_` varchar(255) DEFAULT NULL,
  `GIVENNAME_` varchar(255) DEFAULT NULL,
  `FAMILYNAME_` varchar(255) DEFAULT NULL,
  `BUSINESSEMAIL_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_id_user` */

/*Table structure for table `jbpm4_job` */

DROP TABLE IF EXISTS `jbpm4_job`;

CREATE TABLE `jbpm4_job` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `STATE_` varchar(255) DEFAULT NULL,
  `ISEXCLUSIVE_` bit(1) DEFAULT NULL,
  `LOCKOWNER_` varchar(255) DEFAULT NULL,
  `LOCKEXPTIME_` datetime DEFAULT NULL,
  `EXCEPTION_` longtext,
  `RETRIES_` int(11) DEFAULT NULL,
  `PROCESSINSTANCE_` bigint(20) DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  `CFG_` bigint(20) DEFAULT NULL,
  `SIGNAL_` varchar(255) DEFAULT NULL,
  `EVENT_` varchar(255) DEFAULT NULL,
  `REPEAT_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_JOBRETRIES` (`RETRIES_`),
  KEY `IDX_JOBDUEDATE` (`DUEDATE_`),
  KEY `IDX_JOBLOCKEXP` (`LOCKEXPTIME_`),
  KEY `IDX_JOB_CFG` (`CFG_`),
  KEY `IDX_JOB_EXE` (`EXECUTION_`),
  KEY `IDX_JOB_PRINST` (`PROCESSINSTANCE_`),
  KEY `FK_JOB_CFG` (`CFG_`),
  CONSTRAINT `FK_JOB_CFG` FOREIGN KEY (`CFG_`) REFERENCES `jbpm4_lob` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_job` */

/*Table structure for table `jbpm4_lob` */

DROP TABLE IF EXISTS `jbpm4_lob`;

CREATE TABLE `jbpm4_lob` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `BLOB_VALUE_` longblob,
  `DEPLOYMENT_` bigint(20) DEFAULT NULL,
  `NAME_` longtext,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_LOB_DEPLOYMENT` (`DEPLOYMENT_`),
  KEY `FK_LOB_DEPLOYMENT` (`DEPLOYMENT_`),
  CONSTRAINT `FK_LOB_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_`) REFERENCES `jbpm4_deployment` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_lob` */

insert  into `jbpm4_lob`(`DBID_`,`DBVERSION_`,`BLOB_VALUE_`,`DEPLOYMENT_`,`NAME_`) values (2,0,'<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<process name=\"æµ‹è¯•æµç¨‹\" version=\"1\" xmlns=\"http://jbpm.org/4.4/jpdl\">\n  <description>æµ‹è¯•æµç¨‹</description>\n  <start name=\"startNode1\" g=\"74,25,44,55\">\n    <description>å¼€å§‹</description>\n    <transition g=\"0,0\" name=\"è¿çº¿\" to=\"userTask1\"/>\n  </start>\n  <end name=\"endNode1\" g=\"418,90,44,55\">\n    <description>ç»“æŸ</description>\n  </end>\n  <task candidate-users=\"admin\" name=\"userTask1\" g=\"244,111,44,55\">\n    <description>äººå·¥</description>\n    <transition g=\"0,0\" name=\"è¿çº¿\" to=\"endNode1\"/>\n  </task>\n</process>',1,'æµ‹è¯•æµç¨‹.jpdl.xml'),(3,0,'‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0@\0\0\0\0\0“ ù\0\0.ŠIDATxÚíwt•×™¯µn™Yk–—3ÿdÍ½3wâŒssgâLbrÓ\'NâëÄ&‰anq#˜nŠÕ*T¡Ô»„*ê4õ\nD7U(6ÀØkì™õ»gor”Ãá¨\"TŸßZï2}ÂÏyÏ>ö·¿o{-Y²D3fÌ (Š¢(Š\ZeÜÇËüô>•••@€üà?¿!ÄÏ¸DÃ~ğ#ğƒDh`øÁ~~ğC€\r?øÁÀ~¡á?øøÁâ ğƒüàGà‡\0ñøÁ~ğ#ğC€xüà?øø!@¼\0~ğƒüàG \Z~~ğƒüDÃÀ~ğƒA€h`øøÁ~ğ#\r??øÁ~ğC€h`øÁÀ~ğƒDÃ~~ğƒü \Z~ğ#ğƒü ˆ†üàGà?ˆĞÀğƒüüà‡\0\Z~ğƒü BÃ~ğ#ğƒÄ@Ã~ğƒÄ@à?øÁÀâ ğƒüàGà‡\0ñøÁ~ğƒü \Z~~ğƒü4HsîÜ¹>©‹/jÿş¯J		ÉJIIW||¢ÒÒÒU[S§3gÎèüùówıs\0\0üà?ø!@*@­­­:uê”6mÊÔ¢EoèÕW&éÙg^Ğ3×k“&ëÅ_Ö“OşF£ŸxJÿòIM~mšòòò²t	\"ğƒüàG ¡%@gÿ\\Y™9Z¸p‰æÎ™§Õ«Wkû¶mjn>¥òò2•••+!!AÉIIÊÈØ¤ÔÔTyÏõÖ¯FÑØ±ãµ97ßV[[Dà?øÁ @ƒ[€>¢\r\"m-Yü¦¢¢\"U[[£]»\ZUPX¬°ğ-\\ìÛ^³çÎ×ÄßOÑ3Ï¾   `åææhÁü\ZóÔ8[óæ-ĞÉ“\' ?øÁ~ğC€§\055íSLLœ–/_akíÚµòóĞšà0­\néV½éëç¦hmÜ°ÁÖÏ¿¤Y³¼uêT3Dà?øÁ~Ğà C‡)++Gşš={­Y³ç*Ê!D¦’R”—_¨Êª\ZÕ7ìÒá#o©ag£ıûöeÊp|¯ó¹‘Ñç\'$ÙŠÑôi3ä3o¡?\0øÁ~ğƒ48èäÉSª®®•¿_€¼½}´12ÚVfV®rróT]S§––³zçò]¿qCïŞ¼©+W¯ÚÅÎÕÚv^\'NœÔşµeëvû}Î*((Rxx˜¦N™¡€€å:{ö,Ä\0\0øÁ~ğC€V€ZÎœÑ¡C‡#ÿ\0ååØS¥e:á£+W®ŞŸwoê¦C~>øà]½ú+@—RtáÂE½óÎe]zûm‡(QÃÎ]íÇ°Ç)-ÓÂ…5sÆlåænF€\0€\0?øÁ~„\01\0øÁ~ğC€úU€šOŸVSÓ~Í›ç­ÚÚ:5îŞÓ^\'NœÒïêÚµëjk;¯K—Ş¶¢S´»D™»²µ¹)OUû«u®µUÍÍ§uæŒ9MvÙÛÛ´Ï–9Îî={íâêE‹iÉ_;vb\0 ğƒüà‡\0\rŒ\0vÈÏÛo¿£œìl%%%Ûµ@\'\\ê¸f«ººÆJ‹o¦Ÿ	ÿ©æ”ÌÑ¢‹ôJî+z6óY‰£åé«ìBj3ûsÉqÌÖ¶6[Îc9{NòõõWAA1Ä\0@à?øÁ\Z :sÆÎêÌŸ?ßñ÷V;»cêÚõë¶Ì)­­[·Ù{=²ògZ]³Z§+Ts¶F…Ç\nå_áß^S¦Ê?wµªªªí¬YmÊyLSùùZ¹r¥\"#£ÕÒÒ‚\01\0øÁ~ğC€ú_€ÎŸ¿ #G($$Dï½÷>şøc}üÉ\'ù¹aëm‡\0ÅÇ\'è‰•OÊg›SF‚Ò¦ß&@¦I~Iû‹\Z\Zn×™ºñî»·ë¨?\\»¦eZ±QB€\0üà?ø!@ı+@§[ZìMéíJNNÕgŸ}fëOúÜ®á1µcG©Êê*ôO+Tôîh;ë“u8KË«–ß!?¦~û3ıcğvÆÈ,|6eã<ö\'¹*,,°ëjkë \0?øÁ~Pÿ\nĞ‰“\'í	‹‹Š”‘‘©÷ßßÖG}¤3çZm™;CÏH~]O§>­\r»6ÜqÚË½z@_Zı]­+\\¯úú[gÇqÛÔ¾¦½JNJQyy%Ä\0@à?øÁê_:~â„vïiRAA¾’“Sì¥î¶®^Õ¹Ö6[fÎÑ¿³‹àü.ûwv´»ø˜ÇŒüx-öÒßş½&§,Rss³-çeôÎºxñ¢]t]V\01\0øÁ~ğC€ú[€Ÿ°÷ù)))Ñ†\ríeî¦\\¯3¤_KlÅÆµşnÅßYá1eşì|ÜÌM)òÑÔÔEÙ¹bëÄ©SVzœÇ¿ê¬¢¢\"UÕÔ\"@\0~ğƒü ş sY{vN6oŞ¬U«Vë¤CTœÕ¸g¯­ú†š›7÷ê¨Ì%ñ¿L}^«JÂTU]mëÀ¡Ãj9s¶ıØMMMÚ±c‡½\\b\0 ğƒüà‡\0õ«\0=zL)©›”œœl÷ÿ:èg™ÙSW\Zß-ùùkß¿¶ô„C€¶6lSLL¬­†]»Ûkö\nËÈØ¤½{›tààAˆ€À~ğƒÔ¿d*6>Q		‰\Z;vÜmw€ŞVZf+!1I™YÙú¿ÿÕ¥\0})àKz)çeù.²©64ì²UY]Ó~ÜšÚ:­	\nÒÙ³çtğàaˆ€À~ğƒÔÿ”‘­°µ\Z3fŒ½9a•CVL•WTÚ**Ùª¼ü­JÒñı¶¼–ü÷è‘?ĞëE¯«¼¾Beåå*-¯´UQYÕ~\\sO¡ĞĞP»µÆ¾ı \0?øÁ~PÿPÃ®]š=w¾üü–júô™Ú¶}Çm•³9_ÛKËìf©+Ãô÷Áé¯–}]ÿÕ÷>+=ÿíÍ¿Ò?]Ïd<£É9“•]š«£ÇÙYŸ‚â[Îc9~Æ8q\\gÌ&¬Ü	š€À~ğƒ4tút‹ŞX\Z¨°°µš:uª¢cb•—_x[¥¤g8D(ÏŞ¼ps^æ¥úé‡ÁÏë¾Oè§!¿ÕK±SåŸ¨4Çóì)³œ\\¥¦gŞqœ5Á!Š‹‹µW™…ÑìÆ\0@à?øÁ\Z2U²u›Æ?û;¥§§iÙ²JMÛ¤M™·U\\b²â“R´t©¿¦N›¡×gÍ‘Ÿ ­I“¦hÏù.õ³Ï1Ïuÿşğµë±n½Ì,†67aD€\0üà?ø!@&@fGø…K–jâ¤©jh¨S|B¢£’î¨èØx­ŒÖú¨EÇÅkÅê5¶¢bãìcæk±ñ	w|ßŠ•«ìeöõuuÚ¹k·jë:ı÷0\00\0øÁ~ğC€ \0\0?øÁ~P_)sO1c\'hÏBÕÕÕ*#3ËVtŒCn6FİQk#6ÜV¸ÜÖÄ‰“TQQ¦-[·«dÛv+\\ş\rfWúÇ\\ãÆÓùóç\0\0üà?ø!@÷V€L™+·~1ú)Íš3OE…¶ÊÊ+””’ªàĞ°Õâ%oÊÇg¾­-[Jì\rÍbj³ÿXG??$$D^^^¶şöoÿV\0\0~ğƒü {+@¦Ê+«ôo?ı…F?ù[1±1Y¯È¨h­X¹ZËWvX«‚Öhcd”æÏ_¨eË–)\'7×Vhx„âStøÈ‘N¶™ºÿşûÛ%ÈÔ£>:(gƒ\0à?øÁ @ÃH€LíjÜ­_dë[£¾¯7|ı¬ĞLŸ>]?úñOôÃı›ÆOxN³fÏÕªÕAIŠS\\|‚|}}µpÁÅÆÅjåê5Z¸Ä×VrÚ&»+|w~vCCƒ•W	\ZŒ³A\0ğƒüàG a&@¦Œ°˜JLNÓè_Õş‹^8YKıü5w®·–/¿%D>óæ)$$Ták×jùŠ•zmÊL½âx^À²UíwîéÏ61ÂcÄÇU„ÌÚ ÷ßŸ€~ğƒü roè¶MSSfv®fÌòÖÏmg…üú7õÏrüù{úÎ÷~lP¿2iªÂ#6¨º¶ö®~3æÔ—§Ù ââb\0PøøÁ\"÷V€\\ëTs³vïİ«ú†*1Wu9ÊüıÀÁC}ö3Ücfƒ\\%h0Ì1\0À~ğƒA€F\0õGyŠY =jÔ¨;fƒêêê\0@áGà?ˆOr&00ğÙ ooï~Ÿ\rb\0€üà?‚\0!@ı&@&fƒ¾úÕ¯öël\0üà?øêW213>fæg fƒ\0à?øÁ @P¿3fÖÇÌş¸J™2³D\0 ğ#ğƒ„\0\rK2éh6È¬b\0`\0…ü ¨ÏË)İ}ü^3f6Èıæ‰÷j6ˆ\0~ğƒü4‚È]j:’œŞÊÏİ‰™\r2÷º×³A\0ğƒüàG a$@îâĞQyzn_ÈÏİ\n3ænÑî³A}¹±*\0üà?øh	PG³8]ÍôtôÜŞÈP_ÅÓlP_m¬Ê\0\0?øÁ~B€:ê©õuRSSû|6ˆ€~ğƒA€†‘\0õôX_öº—dÒ×«2\00€Â~ğ#Ğ0 Ì\0yúzG²4ĞäŒ9ıå>Ô›U\0@á?øh§S`w{5Ø½™\rò´±jOfƒ\0@á?øh\nPOD¦3Q\ZŒäŒ§U»;Ä\0À\0\n?øÁ @#X€ºZ=˜È¤·«2\00€Â~ğ#Ğ0 ÎîìÜ›Ç»\09ãi6¨³U\0@á?øh	ĞPÜ¬¯bfƒÜ7Víh6ˆ€~ğƒA€ a!@&m¬jc\0`\0…üü hX\n3fÖÇ}6ÈucU\0PøÁ~B€†\0™t4dÖ1\00€Â~ğ#&@æ T×5Ôh°ñ[¶l™î»ï¾Û$èk_ûšbccé/Š¢(ªßŠ ~@fƒîÕÆªôüà¿Í\0\Z¸¿cîİ×«Ò~ğƒü \ZxĞÇÌ=üğÃÌÑğƒüD<~6V5³A=İX•ş#ğƒüà‡\0ÑÀCŠŸ9õe¤çn6V¥ÿüà?ø!@4ğäçi6¨»«ÂÀ~ğƒDY~6Ve6ˆşƒüàG \ZxDğó´±êÄ‰™\r¢ÿà?øˆŞü<Íu´±*üüà?ø!@4ğ°âçi6Èl¯1Ògƒè?øÁ~ğC€hàaÎÏÓÆªæïÎUáGà7tùõÅö?­­­ºtém55íScc£ÊË+TPP¤††ªª®Ñ;—/«­­mĞìµHÿ!@\0ğëv:ÛX~~#S€Ìm4Ì/Hë×GjæŒÙzñw¯hüoŸÕ¸±ãõ›1ÿ®_?ù´F~JÿòIÍã£üüB:ÕŒ\0!@\r<ôøyš\r2k…FÚlı¿‘*@f¶§±q­  +63gÎRdd¤¶mÛªƒ÷k_Ó^Õ××©¬¬TÑÑQJKK³¿@ıjôuÈQTT¬C.ØB€ B~6Vi³AôüF¢\0µ´´¨  X+WÙ\nTA~>¢»\Z•˜”¢U«ƒå°\\o¼¹T3_Ÿ«—^è/&hÅŠUJII–¿¿¿ÆŒ§iSgÚ2\"…\0!@„Rü<m¬jfƒFÂÆªôüFš\0ùÙ¼9_AAÁ\n\r\r±µn]„Ö‡jõšP­Xµ¦ËZ°p‰BBÃ”œœ¤I“&Ûzáù—´gÏ\"4ğĞâç>´xñbÇ\0¤+W®èÓO?íó¢ÿè?ÒÿdägÇ2‡ğ¬W@@€}î[k#6jcT¬­¤ä4«ªªFµµ\rª¨¬¶eÛ”‘İş<SQÑ±ŠO°µ`şM|õ556îF€ B=~®³A7oŞ¼£:z¼³¯;sı\ZıGÿ‘ş ³gÏj÷î½JˆOÔ¢E‹5×ÛG›2³Û«°¨D{ö6©µµÍ^íe~ù¹pá¢¼uô¶Ú·ÿ€v”–·_îæ|[qqqš3{®=vâÄI\"4ğĞãgN}u%3½ ×?Óôé_:vü„ãçUé7ŞPÄú\rÚºm‡Sfóµk×õŞ{ïé½÷ßo¯›¿_¿~İ­nØºôö;ºpñ¢jkëm™Ëäııüäí«°ĞµV¸ ˆĞÀCŸ»ÀtV]É‘§çÓôA€è?ˆ€jêÎì\'ò$Iœ£ÿHÿĞé–{ßğğpEEE«aç.íÜµÛâ2õî»7oÉ[İx÷]½óÎåëšCˆ:bËÏˆĞ‚ó5oŞÕÔÖ!@¡‡¾\0u5ÓÓ™ì @ôXj9sÖŞİ9 À_­mmv6ÈÔùl½ëxO6í? *‡´x®úÛªéÀA+?—¯\\Qsói[æxf6)88D+W¬ÒúˆH9sB€\r<´øy:½ÕÙé¯îÊ§Àè?Ò¿dfÌ)«ôôt%\'§Ø­.L]¾|ÅÂ2uåêUe®\r×†o­½¢¾÷M¥<õ˜-óçõÇœµ)+×3§Á.^¼dËyÜÖÖ6Í÷ñÑªUk´ß!JDhà!7ÔÓ+ÁÜçıG^€šOŸÖüÑ^ö~òäI}øáG¶>ùôSµ?oËˆLÎšÕŠºßK±_şkUÏœ¤¶ò:»¥PM«üm=ù3ûuSÙyúã?Ôùíi2SÎãšÇ³23ì: mÛw @¡‡·\0u63Äeğô8:å ·Ş:ªÖëÂ…úÓŸş¤Ï?ÿ\\_|ñ…=5fê\\k›òB×X¹Ù:á×ºT_cëTVz»\0™JşßgŸ“_¼ÅŠÎ™³çÚL›ã:k×®FÅÄÄ(/¿B€\r<t¨»Wu¶@ÚıyôıGúG€>¢ÒÒREDDèƒ>°õá‡Ú’?qÊÖÉæÓ*µ³?G¢Õº}‹F„Ü&?¦Òÿõ+@%ÛKõ‡k×ß{RW®\\µå<¶)s+ôô4»“üÉ“§ ˆĞÀÃw¨3Ùá>@ô8ÚÙ¸[[¶nQBB’®^ıƒ­7nØ»¿92uèÈ[*·‚³oÍr[îò³eÜí§Àv”Wªíü8tÄñßó¶œÇ6eÖ™›ª–”líğ¦ˆôÄ@êjQ´ûé?úô\0™m,òóó•ššŞ¾s»³víŞóçÚ«-\"ÚÇTÂ?~É\n‘)33dkô[dÿ[YS§SÍ§Õ¸§©}´çq/]ºäø«P•ãg?qB€\r<tøQ1Sä]şr>ÏµÌb®{Š™2›$º>Ÿş£ÿHÿPñ–mÊÊÊ²»¸Ÿjn¾­ªkëmU9„f{Ô†ÛÈSUM{Åş·ng£9¢šºkmµõöÛo·÷èÑ£Ú¾}»İ!şØñãDhà‘ÁÏ}cUgÂş#ı,@›ó‹”’’¦×_Ÿ¥Ã‡ÜVÛË*lm+-SYLT§òc@;A7îmÒî={UZ^igxL;v¼ı¸ÕÕ5*).Ò‡8†\0\ZxäñsİXÕY>ú¨] 	?úü…ß‘#Gì/÷B€ŠJ¶*2*V³f½®ôô+.Î*t|ÍT~Q‰*âcºœrÖ~‡ØT×Ö9Ä©\\‡ß:jËõ¸ë×¯×îÆFû<®C€\r<\"ùyš\r2Rd®Hı¿J¥¦¦¶¿7&Nœ¨ººº> Æİ{ä°\\K–,ÑìÙsU]SÛ^Ù›ómmÊÎUub|·ÈOyE¥\'³˜)ç1Í½æÍó¶¡ÍîñDhàÍÏòƒq6È¿®6•ìNµ¶¶Ú… ;wîR¶ãÃÅœ‚HIIWRRªbbâT_ß`7‹4ÿÿwû³è¿¡ışõtºø«_ıª=eÜ“÷GGıa.C_¶\"Hák×ê±Ç~¡Â¢bmßQj+eS¦­Gî¯¬Pƒÿâöª÷_ô—ò»½;Y^Y¥ÍE*,.±å<fdd”ÂÂBíı€¸4Dh`ø9bs#=î³AæTÙp #>{öìUbbŠã·íyzù¥ßkÂ„çõê«¿×k“&ë÷\'iìÓ¿Õè\'Òã¿|R“_›¦¼¼B»Q%42ß¿¦ÿ;»ÈÀ’ù¢·d*.!Y³çÎ×²e\n\\®|‡¸˜ŠML¾U	IÊpˆzã±Í®¼Â\"û÷¼ÂâÛ« X™Ù›•«¸¤eåäÚ2ÇKMK·3MÇ·’d¶á@€ BÃïÏ1§¿ÜgƒÌ ·k R€N:e+..A‹¾¡¹sç9ş§Úš\Z;vTMMMª®®vÈN¾ãÿÒÒRívŞs½õ«_Ñ¸±ã•››o«­­\r\Zaï_ooï.¯¶4ïó<³^¨§tğĞa½2q²‚CÂ´|ù\nÅ\'$*#3Kë£blEDF;ş«\rîí©âÚkíÆ(%§¦Û2ÇóõõSII±Îœ9kï?Änğ¡áça6hÔ¨Q>ÔÔèè7lˆ´µhá%%%ª¾®^•UÊÌÊÕò•AZ´di{ùÌ_¬‰¿Ÿ¢çû‚‚‚•›“£óhÌSãlùúúÛ=› ‘õşõt*¬£2ï3+äúKCW}’”¢qãŸs¼ÇŠ”¾Iñ‰IŠ‹·×ËŠW\\B’­ÉS¦©®®V»ï‡²Š*>}\ZB€\r¿bÖ9xšòï¯Ù » êêZÅÆÄkÕÊU¶Ö®]+ÿ€eß´ÃµzMh·Êwi€bb¿YoXoë…ç_Ò¬YŞvF	\Z9ï_Óóî¿üüÕ[Õ™9N#@â~C,fJß}à7‹@{{5L	PÓ¾ıvsXhˆ&LxÖÖR¿@E;„ÈTbRªŠŠ·¨aç.>ò–<¤}û¨Ê!MÛw”)gs~ûs£bâ”˜˜lkãÆš>}¦||êøñĞzÿš™Q×ÓÃó%/…öRÊ{^zu—x¸czè¡‡ì{©£>1B2wŞB=ÿâ«*//uôn£ïbmmˆŒîuıvü3¶rr²µcG©J¶n×Ş¦}ıÒ·ôÄ8ü†E<Í™5÷r6¨·dnî¶c{©B‚ƒÿÆyÚèø 0•å\"#6õ\r»töì9]¾|EW®^µe:=zLmµú<ş>[­%sµuÛû}Î2Wé;;uê…Ø«Å ‘óş5ãúøò·h³n•\"3+däÈııbîøÜY¯ñÕIzzÜª¬¬ÌVFV¶BÂÂ{T«‚LN·§|Meçä*%-Cuõ\rı6sIÿ!@|€ÃoØÄşfö§¿fƒz#@æÊ3›cnöæçç¯‚Âbí(-·UV^iEçÚµëº~ã†n©Õ{\'‹uã²¹B¬M--gôQö÷õŸ1^íu²4H;w5¶ÃTqÉ-\\°@3gÌVqq	4Œß¿FğM›‹Œğ›+%İ/x&à/ä,O§Æ\Z\Zº–s§æ	Ï¾¨Ÿÿ¿ÑŠµ­‡À˜EÒËV¬ê´Ö‡*44\\sæÌUJJ’“Rl­ØhO}õçÚ5úâ~Ã*æÁÓU1æ1\Zª1§·Œè˜™N§èÜwß}İZôüı±3ôĞÏï¼BÌˆLwåãĞáÃší=_ßø×ïØš4yš‚‚VÛ×èÑOêWO>¥ß<=NÓg¼.?sZ7N9¹›µn]„|||âx,VKŞôSCšLÕÖÕ÷ûÕ‹ôÄ8ü†eÌ‡…ûlY+ÔÑeÀı%@Í§O;şmõöƒ`ÏŸ÷Dr–9íõŞ{ïÛú¤zZ»äüGâıºÜvÊîšıIîo jo/{ÃD³¦È”99nTT´æÏ_ €€å:}º\ZB³˜NÑ1”İï}Õ›š™ò—Ó_æ”˜§÷DO¯^lnnVvn­§ÆNĞş‹^xñUùÊ×w©¦OŸ~[­X±\\ÁÁ!\nX¶B¯ş~ŠfÌòVBRªİÌÔ@Ü¿ŠşC€ø\0‡ß°MG³Aw³±ªëé´\n™İyçòeÅÆDÛŸÎûÿ8ëı÷?Ğ‡~¤/î¾MrLÅ,­k×®éı#i·=ô²—ŠŠŠÔÚÖfËy¬sçZ5eò-]\Z ­Û¶#@ƒPtÌmL/š+İò÷¤Ì÷šc˜c™cºö¼Yëcf}Œ¹¯û1rå\\#w77ğ42”_X¬×¦¾®Ÿ=6ZÿçëÁ¯SÿüĞ(}kÔ÷ôïıX?üñÏ5nüóš6sSÒtôØ±¿ƒ9Ÿàğöñ´±jofƒœ¿›ÿöF€ÌÍ,Ï¼y:ş‚]älêİwoÚ2òóÑGë³¶ò;è7ßöÒÿøG[{s–¨`š—Â\'xé+ÿã~;3tãÆ\r[Îcš*,,Òš 5v–3g š‰4÷Ü1rbúÆ}V²\'e¾ßÌ\n9EÇœ¶òW™2k}Ì\Z ®N	÷Å.¦ÌBıú†öj.sƒÃØøDûçÊê\Z8x¨Ï~ı‡\0ñĞÀğëÁlĞİn¬êú½æƒ­§dfv64híÚuÙùPŸ|ò‰£>ÕçŸnë‹/¾¸õç®é?“îo—Ÿƒozé§?ü¶şcÓWìß¿(zDg×}Åşºò-û÷OOåØºuÌ[uıúu­\\¹Rë×GÚ+Ï {×[î‘{+:¦\']EÇ·£=½<ñ3Ïu=û¥ïæø¶Æè+1é¯¢ÿ ^\0\Z~}0ÔUİ?XÌ÷ôD€Ìi©¶ó”››«¬¬l}öÙg¶Ì¦Nr• –ãûä÷ìWôÓ¯{é‘GÑşš<Xı•;f†Ú%)ğ~[æ~-ÎcüñÇÊÍÉ¶[l˜…ÒĞİÅ¹¹³+®z*:æ8æxæ¸=½eƒ\'~m‹a~fo¶Â@€Ä8ü†Ñl§U;›\r2Pî(aaaİş 9vü„İY{Ó¦t{eÖ{ƒ©>úH_T¾h«eÛ›úÜou(97â¾¢ë™xüš™\r2µuëÖöcß¼yS;w6(=-ÃŞu\Zê™èôÅBd3d¾ßËÌ¼ôå-<ñëHÈÌi±Î\"àğAñ´±ªëÂĞ®èá‡îö‰ÙPÒ\\¡UUUéø LÓ•+Wl}ĞZß.1~¿öêP~ºzS€Ì\rêœÇ6uâÄqeff©¶®rË½\\ˆ<w\"7‚ÕÑv]\"àğa1¿í{š\rrßXÕüİÓ‡‹û]Göşí=zvïnÔÆÈ¨ö«¶LIşw[w#@fQ´©’-[táâÅöc2W‰5v°»öHè?÷…Èw#:æû]E§/o«p·ü<ÍTuwD >ÀáÇlÇU]·Úp½œØyEXW$æ\n˜ìœÍ*,(°»¸›Óaîµ~ı}šü?{%@æqSÕÕ5ö¾?Îc6Ô×©ª²ZMûö\rkr]ˆÜ›+®ÌéB÷êèñÎ¾î|ÌõkıÅÏ]â{2…\0ˆpøàxÚXÕ9ä*@¿{»¹~Ğtvy°ÙQ;!!A>óèà¡CëƒŒÿÛ¡ü˜S\\	ĞË?¼UFzœÇÚ·¿6mJÓşıìÍ‡ƒ\0ugë‡.DîJfz#@®îï ÓÃ]-ê¿[2?§;u÷{ ˆĞÀğñ´±ªë‡¬¹¯Šë½U\\×Xt4`·´´üy×öD=ıô8íjÜí±ŞÎ{¦S2§¹<}múÓß°õÖ[GÛUSS«`ûaØÑ=Xkÿİë…È®ë¼Ü¦;3EÍ\Z¹?(¼ï•\0õäuB€ BÃoÎ¹n-`î®ëú˜ó7îÎíÄ¤T…†¯ÓoÆŒQFF–ªªkî¨³›_éT€LyúÚO~ò[®ÇòóPZZª._¹baFr_ˆ|7¢ãº¹\'W\\õôô—\'êìtÚp WYéĞ0ÄçDÃoÄÌ>¸ìµwî¨íœêlĞ.-¯Ğì¹óåëë«™3gÙ-*Ük_ŞÒNhÓÌ/·Ï9×ı|û¼ô¦ã˜¦œÇÙœ—o÷3wŸnnø½À\\\"÷õWw»¹;‚ÓÑ,OwÄi8	»ütGlúzöB€\r¿{3£ãI~ÌÚŸÈ¶[dşë>ÔÙ }òäI-\\ì«Ğ°µš6mº“’•—_p[Ud‡u*@3f¾®ñ&hÁ‚…\n03éwÃÔò+íú³³ü¾ûE€œësîÅÖİùnãéôVwJw%GÃñXgÒÒ™\0õäïDh`ø\r\"ñqî«ä”g¹Ïu5pçlÎ×/N´7D\\¹r•Ò7eŞQÁ‹_´3;f£Sçi/S³õÒoúzü×Z¶|…bbbtèĞ!íiÚgïİ—$÷b!²ë‘ï•èt6ÔÓ+ÁÜI§Àºs\Zâóâ~ÃT|œå>ÔĞĞĞéÀmdÄÛg‘¦L{]{÷ìVdT´â’<ÖR?{ÅØSO±5á™g;|®³V¬\\­ÌÌLÕÔÔØí/víŞÓëO‘{+:®‘¢ã~¯¥JO¨³™¡¼¾¿¨§ÂÂ)0>? >Àá7HÓÑtM}ù/}ì_®ü2eÖ\09Ëë,Ğøñã‡œ\0¹.D¾Û+®Ìúœînı0Xú¯£ÅÌİ½_\'9\ZÎk€ÜÅ¥§ {+=Dh`øõqÜw‰¿ÛêjÈÜ“gÌØ	š3×Gåå¥ÊÊÊ±µ~cd¯kÉ›¾¶æÏ_¨ÆÆFmÙº][¶íèğß`¤çñÇĞ…ÈƒQ€zz5XG3@q şšêé\ZWñqıúİˆãÄ@Ã¯b>¼ïfÖÃ½BBBºÀ+«kô‹\'ÒŒ™³UPo«¢¢R‰É)ZÖí\n\nÕ¢EK´pÁB[eeeJNM·kš;Yûcş½YˆÜ—[?UêjQ´û‡£\0yšÎÖu%LÜ\"40ü‘™|çZ#\0Î2R`Êl†ê~•Óı÷ß¯mÛ¶uk/¯¬Ò¿ıô1ıûøçlÅÄD+<<\\6FÚSYËWvXÁ¡áŠ‰·Wƒ…‡‡)}S†­ à0%§mêT~œ3@?úÑîXˆ|¯¯¸\ZŒı×İÓ_]İü°£Ç†“\0u$4=YÓÃ}€øü@€h`ø\rC~=ÈÍ›_|e’­¿şM½éë§¥K—jÊ”)úîw Q£¾«ñÓì9ŞZ¬Ğ0‡ mˆÔâE‹µH##ß ÅoøÙ*(*Vsss¿~ĞCŸß½>Æ^`ôÄ\0\n?è2Âb*-#SOüz¬xğ_ôÊÄÉ\n\\¶\\KŞxÃ!D~JKMUTT”,\\¤  5Zê¨×¦ÌĞËçElˆÒŞ¦}¶âƒ„şÙÄf¨ôÄ\0\n?øİõÀ~ôØ1•lİ&ïù‹ôø“OëÇ<fg†şù¡Qú×‡¿§G~şK»€zú,o%$§v¸É)Äû¢ÿ ^\0¿!#@îµ{ïŞ;Êì,?˜>Hè?¢ÿ Pø!@#îƒ„şC€ úb\0…„\0Ñğƒü \Z~DÿÁ~ğC€h`ø!@ı?ø!@\r?\"ğƒDh`ø!@ü BÃêéİx Ş¿~/\0ß ®öQêÎ^Uï_?ˆ€ÀoÈ	PÌú @¼	ü \Z~¢ÿxÿÂ @40üDÿñş…A€h`ø‘~ §ÌôDj úÀâ ğC€ Ş¿~/\0ßP w‘é®Ø @ôÄ@à7âˆËàé?¿!%@æ E\rê«…Ïä¦»Õ“ŸÏkFQT3@<üFğPwd¥·77dˆ÷/ß˜\"40üF\0±ı?ø!@„†„\0øÁ\"40ü ˆÀ~¡á‡\0!@~ğC€\r?¢ÿü ^\0?øÁ~~/\0üà??ˆ€À~ğƒDøÁ~ğƒA€h`øøÁ~ğ#\r??øÁ~¢áGà?øÁ @40üàGà?øÁ¢á??øÁ~\r?øøÁ~ğC€h`øÁÀ~ğC€ \Z~ğƒü BÃ~ğ#ğƒDh`øÁ~~ğC€\r?øÁ~ğƒÄ@à?øÁÀâ ğƒüàGà‡\0ñøÁ~ğ#ğC€xüà?øÁ @40üüà?øˆ†üà?‚\0ÑÀğ#ğƒüàG \Z~ğüà?ø!@40üàGà?øÁ¢á??øÁ~\r?øøÁ~DÃ~ğ#ğƒDh`øÁ~~ğC€\r?øÁÀ~¡á?øøÁâ ğƒüàGà‡\0ñøÁ~ğ#ğC€xüà?øø!@¼\0~ğƒüàG \Z~~ğƒüDÃÀ~ğƒA€h`øøÁ~ğ#\r??øÁ~ğC€h`øÁÀ~ğƒDÃ~~ğƒü \Z~ğ#ğƒü ˆ†üàGà?ˆĞÀğƒüüà‡\0\Z~ğƒü BÃ~ğ#ğƒÄ@Ã~ğƒÄ@à?øÁÀâ ğƒüàGà‡\0ñøÁ~ğƒü \Z~~ğƒüDÃÀ~ğƒA€h`øøÁ~ğ#\r??øÁ~ğC€h`øÁÀ~ğƒDÃ~~ğƒü \Z~ğ#ğƒüà‡\0ÑÀğƒü€\0?ø!@„†üàGà?ˆĞÀğƒüüà‡\0\Z~ğƒü BÃ~ğƒÄ@à?øÁÀâ ğƒüàGà‡\0ñøÁ~ğ#ğC€h`øøÁ~ğ#\r??øÁ~¢áGà?øÁ @40üüà?øÁ¢á??øÁ~\r?øøÁ~ğC€h`øÁÀ~ğƒDÃ~~ğƒDh`øÁ~~ğYdBQEQ5Š ~ğƒüFî¡á?øøÁ\"40üà??ø!@„†üàGà?ˆĞÀğƒüàø!@¼\0~ğƒüü ^\0?øÁ~~/\0üà??ˆ&ğƒüà?‚\0ÑÀğ#ğƒüàG \Z~~ğƒüDÃÀ~ğƒA€h`øÁÀ~ğƒDÃ~~ğƒü \Z~ğ#ğƒüà‡\0ÑÀğƒüà‡\0!@40üà??ø!@„†üàGà?ˆĞÀğƒüüà‡\0\Z~ğƒü ^\0?øÁ~~/\0üà??ˆ€À~ğƒÄ@à?øÁ~¢áGà?øÁ @40üüà?øˆ†üà?‚\0ÑÀğƒüà?øÁ¢á??øÁ~\r?øøÁ~ğC€h`øÁÀ~ğC€ä5gÎûŠ¢(Š¢¨‘P>>>úÿ›KöãöCî\0\0\0\0IEND®B`‚',1,'æµ‹è¯•æµç¨‹.png');

/*Table structure for table `jbpm4_participation` */

DROP TABLE IF EXISTS `jbpm4_participation`;

CREATE TABLE `jbpm4_participation` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `GROUPID_` varchar(255) DEFAULT NULL,
  `USERID_` varchar(255) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL,
  `TASK_` bigint(20) DEFAULT NULL,
  `SWIMLANE_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_PART_TASK` (`TASK_`),
  KEY `FK_PART_SWIMLANE` (`SWIMLANE_`),
  KEY `FK_PART_TASK` (`TASK_`),
  CONSTRAINT `FK_PART_SWIMLANE` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`),
  CONSTRAINT `FK_PART_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_participation` */

/*Table structure for table `jbpm4_property` */

DROP TABLE IF EXISTS `jbpm4_property`;

CREATE TABLE `jbpm4_property` (
  `KEY_` varchar(255) NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `VALUE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_property` */

insert  into `jbpm4_property`(`KEY_`,`VERSION_`,`VALUE_`) values ('next.dbid',1,'10001');

/*Table structure for table `jbpm4_swimlane` */

DROP TABLE IF EXISTS `jbpm4_swimlane`;

CREATE TABLE `jbpm4_swimlane` (
  `DBID_` bigint(20) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_SWIMLANE_EXEC` (`EXECUTION_`),
  KEY `FK_SWIMLANE_EXEC` (`EXECUTION_`),
  CONSTRAINT `FK_SWIMLANE_EXEC` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_swimlane` */

/*Table structure for table `jbpm4_task` */

DROP TABLE IF EXISTS `jbpm4_task`;

CREATE TABLE `jbpm4_task` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` char(1) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `DESCR_` longtext,
  `STATE_` varchar(255) DEFAULT NULL,
  `SUSPHISTSTATE_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `FORM_` varchar(255) DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_` datetime DEFAULT NULL,
  `DUEDATE_` datetime DEFAULT NULL,
  `PROGRESS_` int(11) DEFAULT NULL,
  `SIGNALLING_` bit(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(255) DEFAULT NULL,
  `ACTIVITY_NAME_` varchar(255) DEFAULT NULL,
  `HASVARS_` bit(1) DEFAULT NULL,
  `SUPERTASK_` bigint(20) DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  `PROCINST_` bigint(20) DEFAULT NULL,
  `SWIMLANE_` bigint(20) DEFAULT NULL,
  `TASKDEFNAME_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_TASK_SUPERTASK` (`SUPERTASK_`),
  KEY `FK_TASK_SWIML` (`SWIMLANE_`),
  KEY `FK_TASK_SUPERTASK` (`SUPERTASK_`),
  CONSTRAINT `FK_TASK_SUPERTASK` FOREIGN KEY (`SUPERTASK_`) REFERENCES `jbpm4_task` (`DBID_`),
  CONSTRAINT `FK_TASK_SWIML` FOREIGN KEY (`SWIMLANE_`) REFERENCES `jbpm4_swimlane` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_task` */

/*Table structure for table `jbpm4_variable` */

DROP TABLE IF EXISTS `jbpm4_variable`;

CREATE TABLE `jbpm4_variable` (
  `DBID_` bigint(20) NOT NULL,
  `CLASS_` varchar(255) NOT NULL,
  `DBVERSION_` int(11) NOT NULL,
  `KEY_` varchar(255) DEFAULT NULL,
  `CONVERTER_` varchar(255) DEFAULT NULL,
  `HIST_` bit(1) DEFAULT NULL,
  `EXECUTION_` bigint(20) DEFAULT NULL,
  `TASK_` bigint(20) DEFAULT NULL,
  `LOB_` bigint(20) DEFAULT NULL,
  `DATE_VALUE_` datetime DEFAULT NULL,
  `DOUBLE_VALUE_` double DEFAULT NULL,
  `CLASSNAME_` varchar(255) DEFAULT NULL,
  `LONG_VALUE_` bigint(20) DEFAULT NULL,
  `STRING_VALUE_` varchar(255) DEFAULT NULL,
  `TEXT_VALUE_` longtext,
  `EXESYS_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID_`),
  KEY `IDX_VAR_EXESYS` (`EXESYS_`),
  KEY `IDX_VAR_TASK` (`TASK_`),
  KEY `IDX_VAR_EXECUTION` (`EXECUTION_`),
  KEY `IDX_VAR_LOB` (`LOB_`),
  KEY `FK_VAR_EXESYS` (`EXESYS_`),
  KEY `FK_VAR_LOB` (`LOB_`),
  KEY `FK_VAR_TASK` (`TASK_`),
  KEY `FK_VAR_EXECUTION` (`EXECUTION_`),
  CONSTRAINT `FK_VAR_EXECUTION` FOREIGN KEY (`EXECUTION_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_EXESYS` FOREIGN KEY (`EXESYS_`) REFERENCES `jbpm4_execution` (`DBID_`),
  CONSTRAINT `FK_VAR_LOB` FOREIGN KEY (`LOB_`) REFERENCES `jbpm4_lob` (`DBID_`),
  CONSTRAINT `FK_VAR_TASK` FOREIGN KEY (`TASK_`) REFERENCES `jbpm4_task` (`DBID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `jbpm4_variable` */

/*Table structure for table `type_flow` */

DROP TABLE IF EXISTS `type_flow`;

CREATE TABLE `type_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeid` int(11) DEFAULT NULL,
  `flowid` int(11) DEFAULT NULL,
  `flowname` varchar(100) DEFAULT NULL,
  `flowdescribe` varchar(400) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `flag` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `type_flow` */

insert  into `type_flow`(`id`,`typeid`,`flowid`,`flowname`,`flowdescribe`,`version`,`flag`) values (8,4,1,'æµ‹è¯•æµç¨‹','æµ‹è¯•æµç¨‹',1,'å¯ç”¨');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
