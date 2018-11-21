-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: wage
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bonus`
--

DROP TABLE IF EXISTS `bonus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bonus` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NOT NULL,
  `rate` float(3,2) DEFAULT NULL,
  PRIMARY KEY (`b_id`),
  KEY `p_id` (`p_id`),
  CONSTRAINT `bonus_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `person` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonus`
--

LOCK TABLES `bonus` WRITE;
/*!40000 ALTER TABLE `bonus` DISABLE KEYS */;
INSERT INTO `bonus` VALUES (152,151,0.42),(153,195,0.88),(154,197,0.41),(155,104,0.21),(157,138,0.32),(158,159,0.48),(159,168,0.71),(160,179,0.30),(161,194,0.37),(163,135,0.08),(164,137,0.85),(165,184,0.85),(166,187,0.16),(169,147,0.45),(170,171,0.61),(171,176,0.12),(172,182,0.17),(173,198,0.16),(174,108,0.16),(175,180,0.80),(177,133,0.82),(178,136,0.23),(179,141,0.83),(180,160,0.39),(181,165,0.01),(184,145,0.08),(185,152,0.43),(186,156,0.11),(187,174,0.38),(188,192,0.96),(190,120,0.99),(191,150,0.00),(192,164,0.01),(194,106,0.20),(199,158,0.53),(200,167,0.90),(201,169,0.58),(202,172,0.73),(203,177,0.99),(204,196,0.78),(205,125,0.10),(206,143,0.48),(207,157,0.16),(208,163,0.38),(209,170,0.29),(210,191,0.85),(211,199,0.49),(213,123,0.70),(215,140,0.13),(216,153,0.76),(217,154,0.54),(218,173,0.14),(219,181,0.54),(220,183,0.62),(221,190,0.14),(223,134,0.61),(224,144,0.41),(225,161,0.91),(226,185,0.04),(229,142,0.70),(230,146,0.80),(231,188,0.17),(233,127,0.03),(235,148,0.40),(236,155,0.13),(237,162,0.03),(238,175,0.43),(240,200,0.57),(241,105,0.05),(242,128,0.84),(243,139,0.46),(244,178,0.32),(245,189,0.17),(247,149,0.04),(248,166,0.13),(249,186,0.02),(250,201,0.83);
/*!40000 ALTER TABLE `bonus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT,
  `d_name` varchar(20) DEFAULT NULL,
  `d_father_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`d_id`),
  KEY `d_father_id` (`d_father_id`),
  CONSTRAINT `dept_ibfk_1` FOREIGN KEY (`d_father_id`) REFERENCES `dept` (`d_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (1,'技术部门',NULL),(2,'产品部门',NULL),(3,'运营部门',NULL);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(20) DEFAULT NULL,
  `p_salary` decimal(9,2) DEFAULT NULL,
  `p_place_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `p_place_id` (`p_place_id`),
  CONSTRAINT `person_ibfk_1` FOREIGN KEY (`p_place_id`) REFERENCES `place` (`pl_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (104,'周先',12100.00,1),(105,'曹芝玉',12600.00,3),(106,'俞珊莎',12000.00,9),(108,'于琛',10440.00,5),(120,'傅光天',19900.00,2),(123,'席裕',14790.00,11),(125,'杨仪',10450.00,10),(127,'计家',9270.00,14),(128,'项彬',14720.00,15),(133,'纪瑞凡',16380.00,6),(134,'褚厚庆',12880.00,12),(135,'廉有',12960.00,3),(136,'昌可',11070.00,6),(137,'柳群豪',22200.00,3),(138,'贝兴',13200.00,2),(139,'滕翔旭',11680.00,15),(140,'郎良海',9831.00,11),(141,'毕安',16470.00,6),(142,'范昭',15300.00,13),(143,'赵亮',14060.00,10),(144,'邬有',11280.00,12),(145,'马伯',8640.00,7),(146,'计政谦',16200.00,13),(147,'谢和',14500.00,4),(148,'孟楠榕',12600.00,14),(149,'康辰',8320.00,16),(150,'葛强军',8000.00,8),(151,'董希',142000.00,1),(152,'萧天',11440.00,7),(153,'舒盛',15312.00,11),(154,'昌固之',13398.00,11),(155,'马可',10170.00,14),(156,'周江超',8880.00,7),(157,'鲍勤',11020.00,10),(158,'姚鹏泽',15300.00,9),(159,'傅河哲',14800.00,2),(160,'孔纨仪',12510.00,6),(161,'苏咏卿',15280.00,1),(162,'贝毅',9270.00,14),(163,'汪哲',13110.00,10),(164,'吕新',8080.00,8),(165,'谢海',9090.00,6),(166,'吴中',9040.00,16),(167,'阮鹏泽',19000.00,9),(168,'毛龙',17100.00,2),(169,'吕天',15800.00,9),(170,'云承乐',12255.00,10),(171,'吕真环',16100.00,4),(172,'时菁梦',17300.00,9),(173,'贺悦昭',9918.00,11),(174,'姚融',11040.00,7),(175,'谢伯',12870.00,14),(176,'傅莺',11200.00,4),(177,'戴霞香',19900.00,9),(178,'邵兴',10560.00,15),(179,'倪薇',13000.00,2),(180,'凤壮',16200.00,5),(181,'熊时',13398.00,11),(182,'何世',11700.00,4),(183,'冯泰盛',14094.00,11),(184,'安爱妹',22200.00,3),(185,'董士以',8320.00,12),(186,'萧韵融',8160.00,16),(187,'闵友裕',13920.00,3),(188,'马泽',10530.00,13),(189,'章国胜',9360.00,15),(190,'柏雁蓓',9918.00,11),(191,'王世',17575.00,10),(192,'姜康星',15680.00,7),(194,'毛善',13700.00,2),(195,'鲍山仁',188000.00,1),(196,'费安',17800.00,9),(197,'伏中',141000.00,1),(198,'费娴',11600.00,4),(199,'尹子',14155.00,10),(200,'项新',14130.00,14),(201,'戴冠',18300.00,2);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `pl_id` int(11) NOT NULL AUTO_INCREMENT,
  `pl_name` varchar(20) DEFAULT NULL,
  `pl_salary` decimal(9,2) DEFAULT NULL,
  `pl_dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pl_id`),
  KEY `pl_dept_id` (`pl_dept_id`),
  CONSTRAINT `place_ibfk_1` FOREIGN KEY (`pl_dept_id`) REFERENCES `dept` (`d_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'架构师',100000.00,1),(2,'前端工程师',10000.00,1),(3,'Java工程师',12000.00,1),(4,'Python工程师',10000.00,1),(5,'测试工程师',9000.00,1),(6,'运维工程师',9000.00,1),(7,'产品经理',8000.00,2),(8,'产品助理',8000.00,2),(9,'设计师',10000.00,2),(10,'产品运营',9500.00,3),(11,'活动策划',8700.00,3),(12,'会员运营',8000.00,3),(13,'数据运营',9000.00,3),(14,'媒体运营',9000.00,3),(15,'内容策划',8000.00,3),(16,'编辑',8000.00,3);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-21 20:26:16
