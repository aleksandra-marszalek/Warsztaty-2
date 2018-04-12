-- MySQL dump 10.13  Distrib 5.7.21, for osx10.12 (x86_64)
--
-- Host: localhost    Database: workshop_2
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(245) DEFAULT NULL,
  `user_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_group_id` (`user_group_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`user_group_id`) REFERENCES `User_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'januszkowalski@gmail.com','Janusz Kowalski','$2a$10$RgCN99zP.NBA8F9n7ZQABu/Y4yu/EJxuAc1UpuCvVwr7XH7IRhNQi',1),(5,'Nowak','Janina','$2a$10$zAW93Mhf1FHOm42Pystv7.g0v0lnPQwfzCS8LWJft8KoO/E46n7O2',1),(7,'am@outlook.com','Aleksandra Marszałek','$2a$10$B/C0uJmXE52g7Y51JbEXdOT/eej7TC3bz.d94BSBfopGA6ERhEZpS',1),(9,'kryskaSokol@buziaczek.pl','Krystyna Sokołowska','$2a$10$RR8LNLNzUBsiRVRc6OSZwOhLyRRiEpWUUSh.Geb6HfmPqqv9EGcZq',1),(11,'kati5@gmail.com','Katrin Oehme','$2a$10$zBdgfOpMJdhKtIiaVL155e.Kl2Wp4aQ/yAfyB9nW2oN7tIgJo2QAq',1),(12,'anianowak@yahoo.com','Anna Nowak','$2a$10$MVRlrItZAlawMDI7M8M4FuSM.plefk6YaSZ017LJnGYgnoLycBjQ6',1),(15,'jasmadej@jasmail.com','jas Madej','$2a$10$353xZnttPrszAMM5BeaZeeH./SYoDyX3st.G93wyrD.t34EJRGSxy',1);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `excercise`
--

DROP TABLE IF EXISTS `excercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `excercise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `excercise`
--

LOCK TABLES `excercise` WRITE;
/*!40000 ALTER TABLE `excercise` DISABLE KEYS */;
INSERT INTO `excercise` VALUES (2,'ćwiczenie','Ciężkie ćwiczenie'),(3,'Ex3','Description of Ex3'),(4,'Ex4','Description of Ex4'),(5,'next Excercise','Very easy excercise'),(6,'Excercise from java','very hard excercise from Java');
/*!40000 ALTER TABLE `excercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solution`
--

DROP TABLE IF EXISTS `solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `description` text,
  `excercise_id` int(11) DEFAULT NULL,
  `users_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `excercise_id` (`excercise_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `solution_ibfk_1` FOREIGN KEY (`excercise_id`) REFERENCES `excercise` (`id`),
  CONSTRAINT `solution_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solution`
--

LOCK TABLES `solution` WRITE;
/*!40000 ALTER TABLE `solution` DISABLE KEYS */;
INSERT INTO `solution` VALUES (4,'2018-04-10 14:21:23','2018-04-10 14:21:23','Description of solution2 by user5.',2,5),(6,'2018-04-10 23:41:22','2018-04-12 10:35:36','1',2,11),(7,'2018-04-10 23:45:53','2018-04-12 11:18:59','new solution for ex 3',3,11),(8,'2018-04-10 23:53:57','2018-04-12 11:36:25','new SOlution for excercise_2',2,15),(9,'2018-04-11 12:44:05','2018-04-12 11:20:01','new solution for ex5',5,5),(10,'2018-04-12 11:39:49','2018-04-12 11:41:31','it is very easy solution for the hard excercise',6,12);
/*!40000 ALTER TABLE `solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (1,'Java_Amelco_02'),(3,'Python_07'),(4,'JavaScript_11');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-12 12:51:23
