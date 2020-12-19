/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.15 : Database - trading
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`trading` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `trading`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`id`,`admin_name`,`password`,`level`) values (1,'root','root',1);

/*Table structure for table `business` */

DROP TABLE IF EXISTS `business`;

CREATE TABLE `business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `business_name` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `stu_num` varchar(20) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `business` */

insert  into `business`(`id`,`phone`,`password`,`business_name`,`name`,`stu_num`,`major`,`class_name`,`email`,`status`) values (1,'10086','10086','测试用商家','测试用姓名','20181001001','信息学院','软工1801','10086@qq.com',1),(2,'10010','10010','审核中商家','审核中姓名','20181001002','金融学院','金融1801','10010@qq.com',0),(3,'10038','10038','审核失败商家','审核失败姓名','20181001003','英教学院','英教1801','10038@qq.com',-1),(10,'13070230452','password','软工1801小分队','张三','20181009999','信息学院','软工1801','2113114379@qq.com',1),(11,'1008611','123456','baba','刘刘','20181002999','软件工程','1801','123@eamil',1),(12,'1008612','123456','666','张四','2018100','软件工程','15','7email',1);

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `details` varchar(2000) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `photo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`type`,`title`,`details`,`price`,`business_id`,`status`,`photo`) values (1,'电子设备','蓝牙耳机','九成新！无损坏！',2000,1,2,'demo.png'),(2,'电子设备','蓝牙耳机','九成新！无损坏！',2000,1,1,'demo.png'),(3,'电子设备','电脑','联想电脑',8888,1,2,'demo.png'),(6,'电子设备','苹果手机','iphone12你值得拥有！',8699,10,1,'iphone.jpg'),(7,'电子设备','ipad','ipad2020全新款',5666,10,1,'1605709313036.jpg'),(9,'电子设备','魅族17','魅族17喔，只用了几年',4555,10,1,'1605709406765.jpg'),(10,'电子设备','oppo','女神专用',3999,10,2,'1605709445304.jpg'),(11,'电子设备','三星','随时会爆喔',19999,10,1,'1605709510408.jpg'),(12,'电子设备','小米','are you ok',1999,10,1,'1605709549142.jpg'),(13,'书籍','c语言','c语言书',66,10,2,'1605709607574.jpg'),(14,'书籍','linux书','linux书，linux值得你学',34,10,1,'1605709641871.jpg'),(15,'书籍','数学书','数学书，数学学的会吗',67,10,1,'1605709677341.jpg'),(16,'书籍','python书','python还不学?',76,10,1,'1605709722125.jpg'),(17,'书籍','计算机网络','计算机网络书',99,10,1,'1605709764564.jpg'),(18,'书籍','计算机组成','计算机组成书',99,10,1,'1605709781603.jpg'),(19,'宿舍用品','床铺','我睡过的',455,10,2,'1605709822215.jpg'),(20,'宿舍用品','缝纫机','没有缝纫机的宿舍算宿舍吗',677,10,1,'1605709861023.jpg'),(21,'宿舍用品','柜子','柜子很大',177,10,1,'1605709893166.jpg'),(22,'宿舍用品','毛巾','用过的',34,10,1,'1605709959686.jpg'),(23,'宿舍用品','洗衣机','坏的',677,10,1,'1605709978954.jpg'),(24,'其他','箱子','箱子很旧的',477,10,1,'1605710004659.jpg'),(25,'其他','aj1','aj1鞋，穿上贼厉害',5666,10,1,'1605710045564.jpg'),(26,'其他','aj1低帮','aj1低帮，刚穿过',999,10,1,'1605710081883.jpg'),(27,'其他','阿迪达斯','阿迪达斯也还行',899,10,1,'1605710115363.jpg'),(28,'其他','小白鞋','啦啦啦',899,10,1,'1605710131177.jpg'),(29,'其他','两个小帅哥','出卖肉体的两个小帅哥',1,10,1,'1605710178209.jpg'),(30,'其他','矿泉水包邮','很贵但好喝',2500,11,2,'1606287959716.jpg'),(31,'其他','矿泉水','好喝',2500,12,2,'1606290774313.jpg'),(33,'其他','aj2','这是一双鞋',1000,10,0,'1608207041210.jpg')；

/*Table structure for table `recommend` */

DROP TABLE IF EXISTS `recommend`;

CREATE TABLE `recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `recommend` */

insert  into `recommend`(`id`,`goods_id`) values (6,29),(10,11),(12,28);

/*Table structure for table `trade` */

DROP TABLE IF EXISTS `trade`;

CREATE TABLE `trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `business_id` int(11) DEFAULT NULL,
  `user_address` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `trade` */

insert  into `trade`(`id`,`goods_id`,`user_id`,`business_id`,`user_address`,`status`) values (1,3,1,1,'13栋421',0),(2,1,1,1,'a203',1),(3,10,2,10,'13栋421',0),(4,19,2,10,'13栋421',1),(5,30,3,11,'饭堂厕所',1),(6,31,3,12,'caochang',1),(7,13,2,10,'aaa',0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `stu_num` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`stu_num`,`password`,`phone`,`name`,`major`,`class_name`) values (1,'测试用昵称','20181001004','20181001004','10086','测试用姓名','信息学院','软工1801'),(2,'小学生','20181006666','20181006666','13070230452','张三','信息学院','软工1801'),(3,'无敌','2015111','123456','123456','鸭王','信息学院','1801'),(4,'123','20181006667','','12345','321','软件工程','18'),(5,'123','20181006665','123456','12345','321','软件工程','18');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
