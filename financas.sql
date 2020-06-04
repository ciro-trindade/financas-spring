-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: financas
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
-- Table structure for table `tb_categoria`
--

DROP TABLE IF EXISTS `tb_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nm_nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_categoria`
--

LOCK TABLES `tb_categoria` WRITE;
/*!40000 ALTER TABLE `tb_categoria` DISABLE KEYS */;
INSERT INTO `tb_categoria` VALUES (1,'Financas'),(2,'Lazer'),(3,'Despesas domésticas');
/*!40000 ALTER TABLE `tb_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_cliente`
--

DROP TABLE IF EXISTS `tb_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ds_endereco` varchar(120) DEFAULT NULL,
  `nm_nome` varchar(60) DEFAULT NULL,
  `fk_conta_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mm3h5ajdx3v4hr2voj5fqyr22` (`fk_conta_id`),
  CONSTRAINT `FKiim12wo5q0v3wx4593tts0qhh` FOREIGN KEY (`fk_conta_id`) REFERENCES `tb_conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cliente`
--

LOCK TABLES `tb_cliente` WRITE;
/*!40000 ALTER TABLE `tb_cliente` DISABLE KEYS */;
INSERT INTO `tb_cliente` VALUES (1,'Santos(SP)','Ciro Trindade',1);
/*!40000 ALTER TABLE `tb_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_conta`
--

DROP TABLE IF EXISTS `tb_conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_conta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nm_agencia` varchar(60) DEFAULT NULL,
  `nm_banco` varchar(60) DEFAULT NULL,
  `nr_numero` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_conta`
--

LOCK TABLES `tb_conta` WRITE;
/*!40000 ALTER TABLE `tb_conta` DISABLE KEYS */;
INSERT INTO `tb_conta` VALUES (1,'Centro','Itaú',3434);
/*!40000 ALTER TABLE `tb_conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_movimentacao`
--

DROP TABLE IF EXISTS `tb_movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_movimentacao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dt_data` date DEFAULT NULL,
  `ds_descricao` varchar(100) DEFAULT NULL,
  `nm_tipo_movimentacao` varchar(255) DEFAULT NULL,
  `vl_valor` decimal(19,2) DEFAULT NULL,
  `conta_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa8bf6ns85n1pl6neassf6m6nq` (`conta_id`),
  CONSTRAINT `FKa8bf6ns85n1pl6neassf6m6nq` FOREIGN KEY (`conta_id`) REFERENCES `tb_conta` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_movimentacao`
--

LOCK TABLES `tb_movimentacao` WRITE;
/*!40000 ALTER TABLE `tb_movimentacao` DISABLE KEYS */;
INSERT INTO `tb_movimentacao` VALUES (1,'2020-05-20','Supermercado','SAIDA',100.00,1);
/*!40000 ALTER TABLE `tb_movimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_movimentacao_categoria`
--

DROP TABLE IF EXISTS `tb_movimentacao_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_movimentacao_categoria` (
  `fk_movimentacao_id` bigint(20) NOT NULL,
  `fk_categoria_id` bigint(20) NOT NULL,
  KEY `FK8y9s33xvok5etlr1e86iy1ckq` (`fk_categoria_id`),
  KEY `FKde70wop9xuvhx6120wimk3sms` (`fk_movimentacao_id`),
  CONSTRAINT `FK8y9s33xvok5etlr1e86iy1ckq` FOREIGN KEY (`fk_categoria_id`) REFERENCES `tb_categoria` (`id`),
  CONSTRAINT `FKde70wop9xuvhx6120wimk3sms` FOREIGN KEY (`fk_movimentacao_id`) REFERENCES `tb_movimentacao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_movimentacao_categoria`
--

LOCK TABLES `tb_movimentacao_categoria` WRITE;
/*!40000 ALTER TABLE `tb_movimentacao_categoria` DISABLE KEYS */;
INSERT INTO `tb_movimentacao_categoria` VALUES (1,2),(1,3);
/*!40000 ALTER TABLE `tb_movimentacao_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pessoa_fisica`
--

DROP TABLE IF EXISTS `tb_pessoa_fisica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pessoa_fisica` (
  `cd_cpf` varchar(11) DEFAULT NULL,
  `nm_profissao` varchar(30) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKth1jtw0l894v2x08rg6ookphu` FOREIGN KEY (`id`) REFERENCES `tb_cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pessoa_fisica`
--

LOCK TABLES `tb_pessoa_fisica` WRITE;
/*!40000 ALTER TABLE `tb_pessoa_fisica` DISABLE KEYS */;
INSERT INTO `tb_pessoa_fisica` VALUES ('9990688511','professor',1);
/*!40000 ALTER TABLE `tb_pessoa_fisica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pessoa_juridica`
--

DROP TABLE IF EXISTS `tb_pessoa_juridica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pessoa_juridica` (
  `cd_cnpj` varchar(14) DEFAULT NULL,
  `nm_ramo_atividade` varchar(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK3a6nhfk2tb8vl83ula84inx5p` FOREIGN KEY (`id`) REFERENCES `tb_cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pessoa_juridica`
--

LOCK TABLES `tb_pessoa_juridica` WRITE;
/*!40000 ALTER TABLE `tb_pessoa_juridica` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pessoa_juridica` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-25 15:28:37
