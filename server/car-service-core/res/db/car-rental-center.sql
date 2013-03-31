/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.22-community-nt : Database - car-rental-center
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`car-rental-center` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `car-rental-center`;

/*Table structure for table `cust_merchant` */

DROP TABLE IF EXISTS `cust_merchant`;

CREATE TABLE `cust_merchant` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '主键',
  `name` varchar(100) default NULL COMMENT '门店名称',
  `address` varchar(300) default NULL COMMENT '地址',
  `managerId` bigint(20) default NULL COMMENT '店长（与sys_user关联）',
  `status` int(11) default NULL COMMENT '状态，字典表字段，组名：cust_merchant_status，0：删除；',
  `createTime` datetime default NULL COMMENT '创建时间',
  `updateTime` datetime default NULL COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家（本系统的直接客户）';

/*Data for the table `cust_merchant` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
