-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: apzaprodajuelektrorobe
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `administrator_id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password_hash` varchar(128) NOT NULL,
  PRIMARY KEY (`administrator_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `category_id` int unsigned NOT NULL,
  `excerpt` varchar(255) NOT NULL,
  `description` text,
  `status` enum('available','visible','hidden') DEFAULT 'available',
  `is_promoted` tinyint unsigned DEFAULT '0',
  PRIMARY KEY (`article_id`),
  KEY `FK_article_category_category_id_idx` (`category_id`),
  CONSTRAINT `FK_article_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (2,'ACHME HD11 1TB',1,'newki kratak opis proizvoda','Neki malo duzi opis proizvoda ','visible',1),(3,'VOXKG250F',1,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(4,'VOXKG250F',2,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(5,'VOXKG250F',2,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(6,'VOXKG250F',2,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(7,'VOXKG250F',2,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(8,'VOXKG250F',2,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(9,'VOXKG250F',1,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(10,'VOXKG250F',4,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(11,'VOXKG250F',5,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(12,'VOXKG250F',5,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(13,'VOXKG250F',5,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(14,'XOXKG250F',5,'Moderan i efikasan frižider sa prostranim unutrašnjim prostorom, naprednim hlađenjem i brojnim pametnim funkcijama.','Naš moderan frižider predstavlja vrhunsku kombinaciju stila, funkcionalnosti i tehnologije za vaš dom. S prostranim kapacitetom od [navesti zapreminu], ovaj frižider pruža dovoljno prostora za sve vaše potrebe čuvanja hrane i pića.','available',0),(15,'ACHME HD11 1TB',5,'newki kratak opis proizvoda','Neki malo duzi opis proizvoda ','visible',1);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_feature`
--

DROP TABLE IF EXISTS `article_feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_feature` (
  `article_feature_id` int unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int unsigned NOT NULL,
  `feature_id` int unsigned NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`article_feature_id`),
  UNIQUE KEY `feature_id_UNIQUE` (`feature_id`,`article_id`),
  KEY `FK_article_feature_article_article_id_idx` (`article_id`),
  KEY `FK_article_feature_feature_feature_id_idx` (`feature_id`),
  CONSTRAINT `FK_article_feature_article_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_article_feature_feature_feature_id` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`feature_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_feature`
--

LOCK TABLES `article_feature` WRITE;
/*!40000 ALTER TABLE `article_feature` DISABLE KEYS */;
INSERT INTO `article_feature` VALUES (7,3,3,'Sata1'),(13,15,1,'1024GB'),(14,15,2,'SATA 3.0');
/*!40000 ALTER TABLE `article_feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_price`
--

DROP TABLE IF EXISTS `article_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_price` (
  `article_price_id` int unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int unsigned NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_price_id`),
  KEY `FK_article_price_article_article_id_idx` (`article_id`),
  CONSTRAINT `FK_article_price_article_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_price`
--

LOCK TABLES `article_price` WRITE;
/*!40000 ALTER TABLE `article_price` DISABLE KEYS */;
INSERT INTO `article_price` VALUES (2,2,120.00,'2023-08-19 15:39:51'),(3,2,150.00,'2023-08-19 15:40:01'),(4,3,200.10,'2023-08-19 16:16:04'),(5,3,250.10,'2023-08-19 16:16:13'),(6,15,56.89,'2023-08-19 19:45:59'),(7,15,125.22,'2023-08-23 19:16:41'),(8,2,125.22,'2023-08-23 19:22:59'),(9,2,126.22,'2023-08-23 19:25:50'),(10,15,126.22,'2023-08-23 19:26:31'),(11,15,126.22,'2023-08-23 19:31:34'),(12,15,126.22,'2023-08-23 19:34:49'),(13,15,126.22,'2023-08-23 19:42:34'),(14,15,126.22,'2023-08-23 19:44:44'),(15,15,126.22,'2023-08-23 19:45:23'),(16,15,126.22,'2023-08-23 19:47:19'),(17,15,126.22,'2023-08-23 19:47:53'),(18,15,126.22,'2023-08-23 19:49:33'),(19,15,126.22,'2023-08-23 19:49:50'),(20,15,126.22,'2023-08-23 19:52:27'),(21,15,126.22,'2023-08-23 19:54:23'),(22,15,125.22,'2023-08-24 13:55:52');
/*!40000 ALTER TABLE `article_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_id`),
  KEY `FK_cart_user_user_id_idx` (`user_id`),
  CONSTRAINT `FK_cart_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,11,'2023-08-18 15:30:23'),(2,11,'2023-08-18 16:04:41'),(3,11,'2023-08-18 16:06:44'),(4,9,'2023-08-18 16:12:47'),(5,9,'2023-08-18 16:18:25');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_article`
--

DROP TABLE IF EXISTS `cart_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_article` (
  `cart_article_id` int unsigned NOT NULL AUTO_INCREMENT,
  `cart_id` int unsigned NOT NULL,
  `article_id` int unsigned NOT NULL,
  `quantity` int unsigned NOT NULL,
  PRIMARY KEY (`cart_article_id`),
  UNIQUE KEY `cart_id_UNIQUE` (`cart_id`,`article_id`),
  KEY `FK_cart_article_cart_cart_id_idx` (`cart_id`),
  KEY `FK_cart_article_article_article_id_idx` (`article_id`),
  CONSTRAINT `FK_cart_article_article_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_cart_article_cart_cart_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_article`
--

LOCK TABLES `cart_article` WRITE;
/*!40000 ALTER TABLE `cart_article` DISABLE KEYS */;
INSERT INTO `cart_article` VALUES (1,1,2,2);
/*!40000 ALTER TABLE `cart_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `image_path` varchar(128) NOT NULL,
  `parent_category_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `FK_category_parent_category_id_idx` (`parent_category_id`),
  CONSTRAINT `FK_category_parent_category_id` FOREIGN KEY (`parent_category_id`) REFERENCES `category` (`category_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Frizideri','/image/frizider1',NULL),(2,'Racunarske komponente','assets/pc.jpg',NULL),(4,'Laptop Racunari','assets/pc/laptop.jpg',2),(5,'Memorijski Mediji','assets/pc/memory.jpg',2);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature`
--

DROP TABLE IF EXISTS `feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feature` (
  `feature_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `category_id` int unsigned NOT NULL,
  PRIMARY KEY (`feature_id`),
  UNIQUE KEY `category_id_UNIQUE` (`category_id`,`name`) /*!80000 INVISIBLE */,
  KEY `FK_feature_category_category_id_idx` (`category_id`),
  CONSTRAINT `FK_feature_category_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature`
--

LOCK TABLES `feature` WRITE;
/*!40000 ALTER TABLE `feature` DISABLE KEYS */;
INSERT INTO `feature` VALUES (1,'Tehnologija',5),(2,'Kapacitet',5),(3,'TIP',5);
/*!40000 ALTER TABLE `feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int unsigned NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `cart_id` int unsigned NOT NULL,
  `status` enum('rejected','accepted','shipped','pending') NOT NULL DEFAULT 'pending',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `cart_id_UNIQUE` (`cart_id`),
  CONSTRAINT `FK_order_cart_cart_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo` (
  `photo_id` int unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int unsigned NOT NULL,
  `image_path` varchar(128) NOT NULL,
  PRIMARY KEY (`photo_id`),
  UNIQUE KEY `image_path_UNIQUE` (`image_path`),
  KEY `FK_photo_article_article_id_idx` (`article_id`),
  CONSTRAINT `FK_photo_article_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (26,2,'1692898274629184735hladnjakvoxks2510f.jpg');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int unsigned NOT NULL AUTO_INCREMENT,
  `authority` varchar(64) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `password_hash` varchar(128) NOT NULL,
  `forename` varchar(64) NOT NULL,
  `surname` varchar(64) NOT NULL,
  `phone_number` varchar(64) NOT NULL,
  `postal_address` text NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'Marko123@gmail.com','$2a$10$8GVXBk3U6qrtbLLV2Xwdc.IPK5bNupXk4CFwGEivQzkiBmuIMAvq6','Marko123','Marcic','061-121-322-2',' neka tamo adresa'),(9,'Zarko123@gmail.com','$2a$10$PejM2.wC73VdavX64PEQCeKGSM0eoIEbJm0.IQV5uDgzp6.WR.L1G','Zarko','Zaric','062255234','Beograd Zemun bb'),(11,'sinisa97@gmail.com','$2a$10$4FBxeNQysgSXqV//1B8Ar.Qs9X.ngblOBUyK.SAWj2VOr08dPDFjO','sinisa','gromovic','061121153','josicki put 65'),(15,'sinisa97@gmaill.com','$2a$10$kMQjlrExrUWBA5CIWkr2pOFRW4rgwQmEXQMxrVCsJaKA5q7/G4Hvq','sinisa','gromovic','0611221153','josicki put 65');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int unsigned NOT NULL,
  `role_id` int unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_user_role_role_role_id_idx` (`role_id`),
  CONSTRAINT `FK_user_role_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_user_role_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (11,1),(6,2),(9,2),(15,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'apzaprodajuelektrorobe'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
