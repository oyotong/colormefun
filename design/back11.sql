/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.13 : Database - a0812133843
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`a0812133843` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `a0812133843`;

/*Table structure for table `auth_purview` */

DROP TABLE IF EXISTS `auth_purview`;

CREATE TABLE `auth_purview` (
  `purview_id` varchar(50) NOT NULL,
  `purview_name` varchar(50) DEFAULT NULL,
  `purview_upid` varchar(50) DEFAULT NULL,
  `purview_url` varchar(50) DEFAULT NULL,
  `purview_image` varchar(5) DEFAULT NULL,
  `purview_type` varchar(50) DEFAULT NULL,
  `purview_mask` varchar(50) DEFAULT NULL,
  `purview_comment` varchar(250) DEFAULT NULL,
  `active` varchar(4) DEFAULT 'Y',
  PRIMARY KEY (`purview_id`),
  KEY `purview_upid` (`purview_upid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `auth_purview` */

insert  into `auth_purview`(`purview_id`,`purview_name`,`purview_upid`,`purview_url`,`purview_image`,`purview_type`,`purview_mask`,`purview_comment`,`active`) values ('100','快乐秘方',NULL,'#',NULL,'0','100',NULL,'Y'),('101','订单管理',NULL,'#','','0','101','','N'),('102','客户管理',NULL,'#','','0','102','','Y'),('103','内容管理',NULL,'#','','0','103','','Y'),('104','财务管理',NULL,'#','','0','104','','Y'),('107','管 理 员',NULL,'#','','0','107','','Y'),('108','系统设置',NULL,'#','','0','108','','Y'),('109','安全设置',NULL,'#','','0','109','','Y'),('238','管理员列表','107','/admin/system/user/list.do',NULL,'1','107000',NULL,'Y'),('239','添加管理员','107','/admin/system/user/add.do',NULL,'1','107001',NULL,'Y'),('240','修改管理员密码','107','/admin/system/user/editPassword.do',NULL,'1','107002',NULL,'Y'),('241','管理员操作日志','107','/admin/system/user/oplog.do','','1','107003','','N'),('242','角色管理','107','/admin/system/role/list.do',NULL,'1','107004',NULL,'Y'),('243','添加角色','107','/admin/system/role/add.do',NULL,'1','107005',NULL,'Y'),('246','菜单名称修改','108','/admin/system/menu/list.do','','1','108002','','Y'),('247','系统参数设置','108','/admin/sysParam/list.do','','1','108003','','Y'),('250','数据库备份','108','/admin/db/list.jsp','','1','108006','','Y'),('255','安全退出','109','/admin/user/logout.do',NULL,'1','109003',NULL,'Y'),('301','活动列表','100','/admin/mfCase/list.do',NULL,'1','100001',NULL,'Y'),('302','发布活动','100','/admin/mfCase/add.do',NULL,'1','100002',NULL,'Y'),('303','私人派对','100','/admin/vipCase/list.do',NULL,'1','100003',NULL,'Y'),('304','企业活动','100','/admin/vipCase/list.do?caseType=ent_vip',NULL,'1','100004',NULL,'Y'),('305','FUN相册','100','/admin/todo.jsp',NULL,'1','100005',NULL,'N'),('402','购物车','101','/admin/todo.jsp',NULL,'1','101002',NULL,'N'),('405','客户列表','102','/admin/user/list.do',NULL,'1','102001',NULL,'Y'),('406','添加客户','102','/admin/user/add.do',NULL,'1','102002',NULL,'Y'),('407','收藏夹','102','/admin/todo.jsp',NULL,'1','102003',NULL,'N'),('408','邮件管理','102','/admin/todo.jsp',NULL,'1','102005',NULL,'N'),('409','短信管理','102','/admin/todo.jsp',NULL,'1','102006',NULL,'N'),('501','常见问题','103','/admin/content/list.do',NULL,'1','103001',NULL,'Y'),('502','添加问题','103','/admin/content/add.do',NULL,'1','103002',NULL,'Y'),('503','关于快乐秘方','103','/admin/content/about.do',NULL,'1','103005',NULL,'Y'),('504','滚动图片列表','103','/admin/content/listMarquee.do',NULL,'1','103003',NULL,'Y'),('505','发布滚动图片','103','/admin/content/addMarquee.do',NULL,'1','103004',NULL,'Y'),('601','优 惠 劵','104','/admin/coupon/list.do',NULL,'1','104001',NULL,'Y'),('602','添加优惠劵','104','/admin/todo.jsp',NULL,'1','104002',NULL,'N'),('603','生成优惠劵','104','/admin/coupon/add.do',NULL,'1','104003',NULL,'Y'),('604','订单列表','104','/admin/order/list.do',NULL,'1','104005',NULL,'Y'),('605','统计报表','104','/admin/report/list.do',NULL,'1','104005',NULL,'Y'),('606','短信接口','104','/admin/todo.jsp',NULL,'1','104006',NULL,'N'),('607','邮件接口','104','/admin/todo.jsp',NULL,'1','104007',NULL,'N');

/*Table structure for table `auth_role` */

DROP TABLE IF EXISTS `auth_role`;

CREATE TABLE `auth_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `auth_role` */

insert  into `auth_role`(`role_id`,`role_name`) values (1,'系统管理员'),(2,'销售主管'),(3,'销售人员'),(4,'财务人员');

/*Table structure for table `auth_role_purview` */

DROP TABLE IF EXISTS `auth_role_purview`;

CREATE TABLE `auth_role_purview` (
  `role_id` int(11) NOT NULL,
  `purview_id` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`purview_id`),
  KEY `purview_id` (`purview_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `auth_role_purview` */

/*Table structure for table `auth_user` */

DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `user_name` varchar(50) NOT NULL,
  `user_realname` varchar(50) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `user_password2` varchar(50) DEFAULT NULL COMMENT '财务密码',
  `USER_COMMENT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `auth_user` */

insert  into `auth_user`(`user_name`,`user_realname`,`user_password`,`user_password2`,`USER_COMMENT`) values ('admin','超级用户','4mefun','4mefun','管理系统'),('ceo','ceo','4mefun','4mefun','');

/*Table structure for table `auth_user_role` */

DROP TABLE IF EXISTS `auth_user_role`;

CREATE TABLE `auth_user_role` (
  `user_id` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `auth_user_role` */

insert  into `auth_user_role`(`user_id`,`role_id`) values ('admin',1),('ceo',1),('text1',1),('text2',1);

/*Table structure for table `auth_user_shortcutmenu` */

DROP TABLE IF EXISTS `auth_user_shortcutmenu`;

CREATE TABLE `auth_user_shortcutmenu` (
  `short_menu_id` int(11) NOT NULL,
  `menu_id` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`short_menu_id`),
  KEY `menu_id` (`menu_id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `auth_user_shortcutmenu` */

/*Table structure for table `mf_cart` */

DROP TABLE IF EXISTS `mf_cart`;

CREATE TABLE `mf_cart` (
  `cart_no` int(11) NOT NULL AUTO_INCREMENT,
  `user_no` int(11) DEFAULT NULL,
  `case_no` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `active` varchar(4) DEFAULT NULL,
  `ordered_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`cart_no`),
  KEY `FK_mf_cart` (`user_no`),
  KEY `FK_mf_cart_case` (`case_no`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `mf_cart` */

insert  into `mf_cart`(`cart_no`,`user_no`,`case_no`,`qty`,`active`,`ordered_date`,`created_date`) values (5,4,2,1,'Y',NULL,'2014-08-23 23:10:29');

/*Table structure for table `mf_cart_coupon` */

DROP TABLE IF EXISTS `mf_cart_coupon`;

CREATE TABLE `mf_cart_coupon` (
  `cart_no` int(11) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  PRIMARY KEY (`cart_no`,`coupon_id`),
  KEY `FK2_mf_cart_coupon` (`coupon_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_cart_coupon` */

/*Table structure for table `mf_cart_detail` */

DROP TABLE IF EXISTS `mf_cart_detail`;

CREATE TABLE `mf_cart_detail` (
  `cart_no` int(11) NOT NULL,
  `case_no` int(11) NOT NULL,
  `line_no` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_no`,`case_no`),
  KEY `FK2_mf_cart_detail` (`case_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_cart_detail` */

/*Table structure for table `mf_case` */

DROP TABLE IF EXISTS `mf_case`;

CREATE TABLE `mf_case` (
  `case_no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `level` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `time_range` varchar(50) NOT NULL,
  `location` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `ticket_price` decimal(9,2) DEFAULT NULL,
  `ticket_number` int(11) DEFAULT NULL,
  `remaining_ticket` int(11) DEFAULT NULL,
  `sales_volume` int(11) DEFAULT '0',
  `picture` varchar(200) DEFAULT NULL,
  `description` text,
  `active` varchar(4) DEFAULT NULL,
  `created_id` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`case_no`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `mf_case` */

insert  into `mf_case`(`case_no`,`title`,`level`,`start_date`,`time_range`,`location`,`address`,`ticket_price`,`ticket_number`,`remaining_ticket`,`sales_volume`,`picture`,`description`,`active`,`created_id`,`created_date`) values (1,'中文测试',1,'2015-08-13 00:00:00','7:00 - 9:00pm','活动场所','活动地点','198.00',30,30,0,'/resources/images/pic/pic-01.jpg','<p>\r\n                            <div>快乐秘方派对是什么样的活动？</div>\r\n                            －\r\n                        </p>\r\n                        <p>\r\n                            <div>活动票价包含了哪些方面？</div>\r\n                            －\r\n                        </p>','Y','admin','2014-07-27 07:20:56'),(2,'活动主题2',1,'2014-08-03 00:00:00','9:00 - 10:00pm','活动场所2','活动地点2','250.00',100,80,20,'/resources/images/pic/pic-02.jpg','<p>\r\n                            <div>快乐秘方派对是什么样的活动？</div>\r\n                            －\r\n                        </p>\r\n                        <p>\r\n                            <div>活动票价包含了哪些方面？</div>\r\n                            －\r\n                        </p>','Y','admin','2014-07-27 08:06:58'),(3,'8月活动',4,'2014-09-30 00:00:00','8pm - 10pm','活动场所','活动地点','120.00',120,0,0,'/resources/images/pic/pic-03.jpg','','Y','admin','2014-08-03 00:44:07');

/*Table structure for table `mf_content` */

DROP TABLE IF EXISTS `mf_content`;

CREATE TABLE `mf_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `sub_title` varchar(200) DEFAULT NULL,
  `content_type` varchar(20) DEFAULT NULL,
  `content` text,
  `comment` varchar(400) DEFAULT NULL,
  `pic1` varchar(200) DEFAULT NULL,
  `seq_no` int(11) DEFAULT '1',
  `active` varchar(4) DEFAULT NULL,
  `created_id` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `mf_content` */

insert  into `mf_content`(`id`,`title`,`sub_title`,`content_type`,`content`,`comment`,`pic1`,`seq_no`,`active`,`created_id`,`created_date`) values (1,'快乐秘方',NULL,'question','<p><span style=\"font-family: 宋体; line-height: 18px;\">他是这个地方的首富,但生活得并不快乐。先是亲戚和朋友向他借钱,但都是有去无回,这让他很伤心。后来他花钱请戏班子唱场戏,让大家去看。结果,那天晚上他的家让人给盗了。他实在想不明白,自己对他们这么好,他们为什么这样?从此,他变得越来越不快乐。直到有一天,他家门前来了一位远游的高僧。</span><span style=\"font-family: 宋体; line-height: 18px;\">&nbsp;</span></p>\r\n<p style=\"font-family: 宋体; line-height: 18px;\">　　他便把自己的苦闷向高僧说了。高僧听罢笑了,说,我有一个快乐的秘方放在山上的庙中了,施主愿意跟我去拿吗?不过路很远的,你得带上足够的盘缠。&nbsp;<br />\r\n就这样,他跟高僧上路了。路真的很远,他们走过了一个又一个村庄,翻过了一座又一座高山。路上他遇到很多穷人,高僧毫不犹豫地让他掏出钱施舍给穷人,直到他口袋里的钱越来越少了。他有点儿担心,他拿到秘方后怎么回来?&nbsp;<br />\r\n高僧看出了他的心思。高僧说,你不必担心,我保证你到时候会开开心心地回到家。&nbsp;<br />\r\n他听了高僧的话,就把剩余的盘缠也毫无保留地施舍给了穷人。&nbsp;<br />\r\n他们终于来到了庙中。他便问高僧讨要快乐的秘方。&nbsp;<br />\r\n高僧说,我已经把秘方给你了啊!&nbsp;<br />\r\n他听了很吃惊,说,你什么时候给我的,我怎么不知道啊?&nbsp;<br />\r\n高僧说,你既然来了,就过一些日子再回去吧。&nbsp;<br />\r\n于是,他便在山上度过了一段日子。在庙中,他听和尚们念那些听不懂的经文,时间久了,他就很烦躁。他向高僧要盘缠回去。</p>\r\n<p><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　高僧说,我已经把盘缠给你了。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　他一听明白了,这是个骗人的僧人,他前前后后在逗自己玩儿呢!他一气之下离开了庙,下山去了,一赌气跑出了很远。当他来到一个小山村的时候已经很饿了,但他的口袋空空的,他不知道如何是好。这个时候,一个老农从他眼前走过,一眼就认出他来了,说,哎呀,这不是我的恩人吗?你怎么会来到这里?他想不起对这个老农施舍过什么,但老农已经把他当亲人一样看待了。老农把他领到家中过了一晚。次日,他继续赶路。在途中,每当他遇到困难的时候,就会有人来帮他,那些人对他的印象很深,一眼就能认出他,这让他感到惊喜。一路上,他没有分文,受着人家的施舍快乐地回到了家。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　回到家,他才恍然大悟,高僧真的把快乐给了他。原来,带着快乐去施舍,这快乐早晚也要回到自己身上的。以前他的施舍里充满了回报的欲念,那欲念带来的痛苦也自然回到了他的身上。&nbsp;</span></p>\r\n<p>&nbsp;</p>',NULL,NULL,1,'Y','admin','2014-07-26 06:50:57'),(3,'关于快乐秘方（Color Me Fun）',NULL,'about','<p style=\"font-family: 宋体; line-height: 18px;\">&nbsp; &nbsp; 他是这个地方的首富,但生活得并不快乐。先是亲戚和朋友向他借钱,但都是有去无回,这让他很伤心。后来他花钱请戏班子唱场戏,让大家去看。结果,那天晚上他的家让人给盗了。他实在想不明白,自己对他们这么好,他们为什么这样?从此,他变得越来越不快乐。直到有一天,他家门前来了一位远游的高僧。&nbsp;<br />\r\n他便把自己的苦闷向高僧说了。高僧听罢笑了,说,我有一个快乐的秘方放在山上的庙中了,施主愿意跟我去拿吗?不过路很远的,你得带上足够的盘缠。&nbsp;<br />\r\n就这样,他跟高僧上路了。路真的很远,他们走过了一个又一个村庄,翻过了一座又一座高山。路上他遇到很多穷人,高僧毫不犹豫地让他掏出钱施舍给穷人,直到他口袋里的钱越来越少了。他有点儿担心,他拿到秘方后怎么回来?&nbsp;<br />\r\n高僧看出了他的心思。高僧说,你不必担心,我保证你到时候会开开心心地回到家。&nbsp;<br />\r\n他听了高僧的话,就把剩余的盘缠也毫无保留地施舍给了穷人。&nbsp;<br />\r\n他们终于来到了庙中。他便问高僧讨要快乐的秘方。&nbsp;<br />\r\n高僧说,我已经把秘方给你了啊!&nbsp;<br />\r\n他听了很吃惊,说,你什么时候给我的,我怎么不知道啊?&nbsp;<br />\r\n高僧说,你既然来了,就过一些日子再回去吧。&nbsp;<br />\r\n于是,他便在山上度过了一段日子。在庙中,他听和尚们念那些听不懂的经文,时间久了,他就很烦躁。他向高僧要盘缠回去。</p>\r\n<p><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　高僧说,我已经把盘缠给你了。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　他一听明白了,这是个骗人的僧人,他前前后后在逗自己玩儿呢!他一气之下离开了庙,下山去了,一赌气跑出了很远。当他来到一个小山村的时候已经很饿了,但他的口袋空空的,他不知道如何是好。这个时候,一个老农从他眼前走过,一眼就认出他来了,说,哎呀,这不是我的恩人吗?你怎么会来到这里?他想不起对这个老农施舍过什么,但老农已经把他当亲人一样看待了。老农把他领到家中过了一晚。次日,他继续赶路。在途中,每当他遇到困难的时候,就会有人来帮他,那些人对他的印象很深,一眼就能认出他,这让他感到惊喜。一路上,他没有分文,受着人家的施舍快乐地回到了家。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　回到家,他才恍然大悟,高僧真的把快乐给了他。原来,带着快乐去施舍,这快乐早晚也要回到自己身上的。以前他的施舍里充满了回报的欲念,那欲念带来的痛苦也自然回到了他的身上。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　选自《山东青年》倪早菊/荐</span></p>',NULL,NULL,1,'Y','admin','2014-07-26 07:36:01'),(4,'滚动图片','#','marquee','<p>你快乐吗？</p>',NULL,'/resources/images/pic/banner-01.jpg',0,'Y','admin','2014-07-26 08:41:49'),(5,'滚动图片','#','marquee','抽象派',NULL,'/resources/images/pic/banner-02.jpg',1,'Y','admin','2014-07-26 09:04:40'),(6,'滚动图片','#','marquee','现代主义',NULL,'/resources/images/pic/banner-03.jpg',1,'Y','admin','2014-07-26 09:04:40'),(7,'滚动图片','#','marquee','水墨画',NULL,'/resources/images/pic/banner-04.jpg',1,'Y','admin','2014-07-26 09:04:40');

/*Table structure for table `mf_coupon` */

DROP TABLE IF EXISTS `mf_coupon`;

CREATE TABLE `mf_coupon` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_no` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `deadline` datetime NOT NULL,
  `version` varchar(20) DEFAULT NULL,
  `deduction` decimal(9,2) DEFAULT NULL,
  `discount` decimal(9,2) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `coupon_type` varchar(10) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `active` varchar(4) DEFAULT NULL,
  `user_no` int(11) DEFAULT NULL,
  `created_id` varchar(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=MyISAM AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

/*Data for the table `mf_coupon` */

insert  into `mf_coupon`(`coupon_id`,`coupon_no`,`password`,`deadline`,`version`,`deduction`,`discount`,`description`,`coupon_type`,`total`,`status`,`active`,`user_no`,`created_id`,`created_date`) values (111,'2240884521925334','75DD8814E0170AB459DBA3DD570BA90C','2014-09-30 00:51:26','C2EFF587D183D202','20.00',NULL,'','1',10,'released','N',NULL,'admin','2014-07-31 00:51:37'),(112,'0434160145415140','0217DABAE95C89BE8A0AD7890DCB5D9E','2014-09-30 00:51:26','C2EFF587D183D202','30.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(113,'5654452470224250','C4809CF651460E98E0A4467F4898B0BA','2014-09-30 00:51:26','C2EFF587D183D202','40.00',NULL,'','1',10,'released','Y',4,'admin','2014-07-31 00:51:37'),(114,'6123823533462142','428CFFFECD5736AF804D273116B0D35E','2014-09-30 00:51:26','C2EFF587D183D202','120.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(115,'5861235214546535','A8CB7B10A9AE8A9B17312D9EAA599623','2014-09-30 00:51:26','C2EFF587D183D202','2000.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(116,'7250751515243332','476B84D402A02C33DB2651C765A1398D','2014-09-30 00:51:26','C2EFF587D183D202','250.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(117,'7535610848301269','02C6F5679C8566AD60AD7D29ACF76BB4','2014-09-30 00:51:26','C2EFF587D183D202','1120.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(118,'5623040702464124','A16450B50DA23E88939A5FB9F433EE70','2014-09-30 00:51:26','C2EFF587D183D202','20.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(119,'4187602552108063','3E38C2A6C3A6881BB6DCBCC4C5A6C1D0','2014-09-30 00:51:26','C2EFF587D183D202','20.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(120,'4070930521003070','8B0FC1FA949151361F7B4F303E5CE947','2014-09-30 00:51:26','C2EFF587D183D202','20.00',NULL,'','1',10,'released','Y',NULL,'admin','2014-07-31 00:51:37'),(121,'2421045584048403','AEA8D7B8CFC1B8E67988F26A5B11557C','2014-09-30 00:51:26','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(122,'7395631007042692','06D83D166FB11ED402C14738D2302188','2014-09-30 00:51:26','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(123,'2400004222481080','13D7630C669E1C3023059974A451088A','2014-09-30 00:51:26','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(124,'0333612244932051','787B2D0E26F3199E3EE5B2F763DE7C7B','2014-09-06 01:36:05','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(125,'1002537338610099','183255DBADBB369BB6078DC0A68DF4CE','2014-09-06 01:36:05','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(126,'9157531670605314','CCC8B89334C02CC118D00D78E7467367','2014-09-06 01:36:05','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(127,'8712954643145503','19D50F5F4EB59E5C561E4C4F2E640F6B','2014-09-06 01:36:05','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(128,'3872419251172461','A11E4F73471471F3E706927626FEF8C1','2014-09-06 01:36:05','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(129,'4744188092868648','1E160CF56B352A5251950391502C95FA','2014-09-06 01:36:05','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15'),(130,'2195315437793084','FE4CA757255622E8950B2149A826414F','2014-09-06 01:36:05','FFB2B3B09727487C','50.00',NULL,'','1',10,'created','Y',NULL,'admin','2014-07-31 01:36:15');

/*Table structure for table `mf_favorite` */

DROP TABLE IF EXISTS `mf_favorite`;

CREATE TABLE `mf_favorite` (
  `favorite_id` int(11) NOT NULL AUTO_INCREMENT,
  `case_no` int(11) NOT NULL,
  `user_no` int(11) NOT NULL,
  `comment` varchar(400) DEFAULT NULL,
  `active` varchar(4) DEFAULT NULL,
  `deleted_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`favorite_id`),
  KEY `FK_mf_favorite` (`case_no`),
  KEY `FK2_mf_favorite` (`user_no`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `mf_favorite` */

insert  into `mf_favorite`(`favorite_id`,`case_no`,`user_no`,`comment`,`active`,`deleted_date`,`created_date`) values (7,3,4,NULL,'Y',NULL,'2014-09-24 22:27:04');

/*Table structure for table `mf_history` */

DROP TABLE IF EXISTS `mf_history`;

CREATE TABLE `mf_history` (
  `order_no` varchar(20) NOT NULL,
  `user_no` int(11) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `active` varchar(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_no`),
  KEY `FK_mf_order` (`user_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_history` */

insert  into `mf_history`(`order_no`,`user_no`,`comment`,`status`,`active`,`created_date`) values ('123456',1,'测试描述','created','Y','2014-07-31 21:59:06'),('234567',3,'描述2','paid','Y','2014-07-30 22:03:48');

/*Table structure for table `mf_history_detail` */

DROP TABLE IF EXISTS `mf_history_detail`;

CREATE TABLE `mf_history_detail` (
  `order_no` varchar(20) NOT NULL,
  `case_no` int(11) NOT NULL,
  `line_no` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`order_no`,`case_no`),
  KEY `FK2_mf_order_detail` (`case_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_history_detail` */

insert  into `mf_history_detail`(`order_no`,`case_no`,`line_no`,`qty`,`price`,`comment`) values ('123456',1,1,10,'200.00','一点说明'),('123456',2,2,5,'300.00','不想所'),('234567',1,1,1,'120.00',NULL);

/*Table structure for table `mf_monthly_report` */

DROP TABLE IF EXISTS `mf_monthly_report`;

CREATE TABLE `mf_monthly_report` (
  `year` varchar(20) NOT NULL,
  `month` int(11) NOT NULL,
  `case_total` int(11) NOT NULL,
  `order_total` int(11) NOT NULL,
  `ticket_total` int(11) DEFAULT NULL,
  `price_total` decimal(9,4) DEFAULT NULL,
  `coupon_total` decimal(9,4) DEFAULT NULL,
  `real_case_total` int(11) NOT NULL,
  `real_order_total` int(11) NOT NULL,
  `real_ticket_total` int(11) DEFAULT NULL,
  `real_price_total` decimal(9,4) DEFAULT NULL,
  `real_coupon_total` decimal(9,4) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `created_id` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`year`,`month`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_monthly_report` */

/*Table structure for table `mf_order` */

DROP TABLE IF EXISTS `mf_order`;

CREATE TABLE `mf_order` (
  `order_no` varchar(20) NOT NULL,
  `user_no` int(11) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `active` varchar(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_no`),
  KEY `FK_mf_order` (`user_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_order` */

insert  into `mf_order`(`order_no`,`user_no`,`comment`,`status`,`active`,`created_date`) values ('123456',4,'测试描述','created','Y','2014-07-31 21:59:06'),('234567',4,'描述2','paid','Y','2014-07-30 22:03:48');

/*Table structure for table `mf_order_coupon` */

DROP TABLE IF EXISTS `mf_order_coupon`;

CREATE TABLE `mf_order_coupon` (
  `coupon_id` int(11) NOT NULL,
  `order_no` varchar(20) NOT NULL,
  PRIMARY KEY (`coupon_id`,`order_no`),
  KEY `FK_mf_order_coupon` (`order_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_order_coupon` */

insert  into `mf_order_coupon`(`coupon_id`,`order_no`) values (111,'123456'),(128,'123456');

/*Table structure for table `mf_order_detail` */

DROP TABLE IF EXISTS `mf_order_detail`;

CREATE TABLE `mf_order_detail` (
  `order_no` varchar(20) NOT NULL,
  `case_no` int(11) NOT NULL,
  `line_no` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`order_no`,`case_no`),
  KEY `FK2_mf_order_detail` (`case_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_order_detail` */

insert  into `mf_order_detail`(`order_no`,`case_no`,`line_no`,`qty`,`price`,`comment`) values ('123456',1,1,10,'200.00','一点说明'),('123456',2,2,5,'300.00','不想所'),('234567',1,1,1,'120.00',NULL);

/*Table structure for table `mf_ticket_buffer` */

DROP TABLE IF EXISTS `mf_ticket_buffer`;

CREATE TABLE `mf_ticket_buffer` (
  `case_no` int(11) NOT NULL,
  `index_no` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile_phone` varchar(20) DEFAULT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`case_no`,`index_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_ticket_buffer` */

insert  into `mf_ticket_buffer`(`case_no`,`index_no`,`email`,`mobile_phone`,`comments`,`qty`,`created_date`,`created_id`) values (3,1,'oyotong@163.com','13218181930',NULL,1,'2014-09-27 17:24:04',NULL);

/*Table structure for table `mf_user` */

DROP TABLE IF EXISTS `mf_user`;

CREATE TABLE `mf_user` (
  `user_no` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `mobile_phone` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `favorite` varchar(200) DEFAULT NULL,
  `my_color` varchar(100) DEFAULT NULL,
  `pic1` varchar(200) DEFAULT NULL,
  `let_me_know` varchar(4) DEFAULT NULL,
  `active` varchar(1) NOT NULL DEFAULT 'Y',
  `registered_date` date DEFAULT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `mf_user` */

insert  into `mf_user`(`user_no`,`user_name`,`mobile_phone`,`password`,`email`,`birthday`,`sex`,`favorite`,`my_color`,`pic1`,`let_me_know`,`active`,`registered_date`) values (1,'张三','1328889998','cdd8db94b0e663d09b21d6f2f7454542','oyotong@153.com','2014-07-18','M','兴趣爱好','颜色','\\userfiles\\user1.png',NULL,'Y','2014-07-18'),(3,'飞得更高','56424342','f0baff9b791981fcfd3b04208aceb283','sdfsdf@asdfasdf.com','2014-07-22','M','asdfasdf','adsf4545fgdfg','/userfiles/image/20140725233304263.png',NULL,'Y','2014-07-25'),(4,'[匿名]','13228181890','rt1234','oyotong@163.com',NULL,NULL,NULL,NULL,NULL,NULL,'Y',NULL),(5,'[匿名]','13228181891','123456','oyotong@163.com',NULL,NULL,NULL,NULL,NULL,NULL,'Y',NULL),(6,'[匿名]','13228181892','123456','oyotong@163.com',NULL,NULL,NULL,NULL,NULL,NULL,'Y',NULL),(7,'[匿名]','13228181894','123456','oyotong@163.com',NULL,NULL,NULL,NULL,NULL,NULL,'Y',NULL);

/*Table structure for table `mf_vip_case` */

DROP TABLE IF EXISTS `mf_vip_case`;

CREATE TABLE `mf_vip_case` (
  `case_no` int(11) NOT NULL AUTO_INCREMENT,
  `case_type` varchar(20) DEFAULT NULL,
  `company_name` varchar(50) DEFAULT NULL,
  `region` varchar(20) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `contact_email` varchar(50) DEFAULT NULL,
  `member_num` int(11) DEFAULT NULL,
  `kids_num` int(11) DEFAULT NULL,
  `need_location` varchar(10) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `case_key` varchar(32) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `active` varchar(4) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`case_no`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `mf_vip_case` */

insert  into `mf_vip_case`(`case_no`,`case_type`,`company_name`,`region`,`area`,`contact_name`,`contact_phone`,`contact_email`,`member_num`,`kids_num`,`need_location`,`location`,`description`,`case_key`,`status`,`active`,`created_date`) values (1,'vip',NULL,'金牛区','玉林','张晓峰','13455565665','test@163.com',5,1,'N','四川美术馆','请安排在下午5:00到8:00',NULL,NULL,'Y','2014-08-02 08:55:20'),(2,'ent_vip','成都58同城',NULL,NULL,'薄瓜瓜','15734343434','haha@test.com',55,NULL,'Y',NULL,'是不是所有的时间都可以安排？',NULL,NULL,'Y','2014-07-31 08:59:54'),(3,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言',NULL,NULL,NULL,NULL),(4,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','6542BF400F37395B259AE4D278D6A15D',NULL,'Y','2014-09-13 00:47:40'),(5,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','D3F4B90143EF873A9AFA0ECB4748846A',NULL,'Y','2014-09-13 00:50:44'),(6,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','68DCA263830D0722CF005FDAC11D22E5',NULL,'Y','2014-09-13 00:52:25'),(7,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','B7E1304C8ED132B130CD5990CE6C84C4',NULL,'Y','2014-09-13 00:53:25'),(8,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','F21B7807718F7E0134AFFCD242444A2D',NULL,'Y','2014-09-13 00:57:36'),(9,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','74C013E8AD0005BF62A4EA710C703240',NULL,'Y','2014-09-13 01:01:26'),(10,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','97361D621FC4940072824DC4F607061F',NULL,'Y','2014-09-13 01:06:16'),(11,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','A2773A4B7864841E0C4977BEC5BBC273',NULL,'Y','2014-09-13 01:19:18'),(12,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','2984DE740F6DE48D896B188150561D17',NULL,'Y','2014-09-13 01:22:48'),(13,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','0AAF717D351AEDA40DD8D41EF310EAB6',NULL,'Y','2014-09-13 01:25:24'),(14,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','00AE42436B1F692DF66ACEEE42ED5409',NULL,'Y','2014-09-13 01:45:37'),(15,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','C6A1545A8471F8078582E94221A01240',NULL,'Y','2014-09-13 01:51:05'),(16,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','C4FE8DD773AC4640EA4B52398C6C8F00',NULL,'Y','2014-09-13 01:54:24'),(17,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','913F35DD30FD813D9C58E694BF40E90C',NULL,'Y','2014-09-13 02:07:43'),(18,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','F03C9EA558C2B152CCDC3A507A3366A7',NULL,'Y','2014-09-13 02:09:43'),(19,'ent_vip','企业/机构',NULL,NULL,'联系人','13228181890','oyotong@163.com',45,NULL,'N','我们自己安排活动场所','给我们留言','422C5727BA012E155455A22161EA01D8',NULL,'Y','2014-09-13 02:14:52'),(20,'vip',NULL,'邛崃市','宽窄巷子','姓名','13228181890','oyotong@163.com',24,2,'N','请填写参加活动总人数','给我们留言','61991CE728B7EFAECF6A0F703D5F8B4E',NULL,'Y','2014-09-14 20:26:46'),(21,'vip',NULL,'邛崃市','宽窄巷子','姓名','13228181890','oyotong@163.com',24,2,'N','请填写参加活动总人数','给我们留言','A47307B4FDE5C964EB5ED4B57EB743BB',NULL,'Y','2014-09-14 20:26:50'),(22,'vip',NULL,'邛崃市','宽窄巷子','姓名','13228181890','oyotong@163.com',24,2,'N','请填写参加活动总人数','给我们留言','A55445C24FE8819BC691CEBE9ABD58F7',NULL,'Y','2014-09-14 20:26:44'),(23,'vip',NULL,'邛崃市','宽窄巷子','姓名','13228181890','oyotong@163.com',24,2,'N','请填写参加活动总人数','给我们留言','61991CE728B7EFAECF6A0F703D5F8B4E',NULL,'Y','2014-09-14 20:26:46');

/*Table structure for table `site_parameter` */

DROP TABLE IF EXISTS `site_parameter`;

CREATE TABLE `site_parameter` (
  `param_key` varchar(50) NOT NULL DEFAULT '',
  `param_name` varchar(50) DEFAULT NULL,
  `param_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`param_key`),
  KEY `param_key` (`param_key`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `site_parameter` */

insert  into `site_parameter`(`param_key`,`param_name`,`param_comment`) values ('EMAIL_API','邮件接口',NULL),('FUN_URL','FUN相册',NULL),('SMS_API','短信接口',NULL),('SMS_TEMP','短信模板',NULL),('WEIBO','微博',NULL),('WEIXIN','微信',NULL),('LINKED_IN','Linked in',NULL),('QQ_SPACE','QQ空间',NULL);

/*Table structure for table `site_parameter_item` */

DROP TABLE IF EXISTS `site_parameter_item`;

CREATE TABLE `site_parameter_item` (
  `param_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) NOT NULL,
  `param_item_value` varchar(1000) NOT NULL,
  `param_item_value1` varchar(255) DEFAULT NULL,
  `param_item_value2` varchar(255) DEFAULT NULL,
  `param_item_value3` varchar(255) DEFAULT NULL,
  `param_item_name` varchar(100) NOT NULL,
  `param_item_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`param_item_id`),
  KEY `param_key` (`param_key`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `site_parameter_item` */

insert  into `site_parameter_item`(`param_item_id`,`param_key`,`param_item_value`,`param_item_value1`,`param_item_value2`,`param_item_value3`,`param_item_name`,`param_item_comment`) values (1,'FUN_URL','http://user.qzone.qq.com/2780025534/4',NULL,NULL,NULL,'URL','FUN相册链接'),(2,'EMAIL_API','smtp.163.com',NULL,NULL,NULL,'HOST','服务器'),(3,'EMAIL_API','25',NULL,NULL,NULL,'PORT','端口'),(4,'EMAIL_API','colormefun@163.com',NULL,NULL,NULL,'USERNAME','用户名'),(5,'EMAIL_API','4testuser',NULL,NULL,NULL,'PASSWORD','密码'),(7,'EMAIL_API','SMTP',NULL,NULL,NULL,'PROTOCOL','发送协议'),(8,'EMAIL_API','',NULL,NULL,NULL,'CC','抄送'),(9,'EMAIL_API','',NULL,NULL,NULL,'BCC','密送'),(11,'EMAIL_API','colormefun@163.com',NULL,NULL,NULL,'FROM','发件人'),(12,'SMS_API','http://utf8.sms.webchinese.cn',NULL,NULL,NULL,'API','接口地址'),(13,'SMS_API','colormefun',NULL,NULL,NULL,'UID','用户名'),(14,'SMS_API','68a2eb316e847a8c669e',NULL,NULL,NULL,'KEY','密码短信'),(15,'SMS_TEMP','您本次操作的验证码是：${validateCode}，请勿将验证码告诉他人。',NULL,NULL,NULL,'VALIDATE_CODE','验证码'),(21,'WEIXIN','/resources/images/weixincode.jpg',NULL,NULL,NULL,'CODE_URL','二维码'),(22,'LINKED_IN','/resources/images/linkedincode.png',NULL,NULL,NULL,'CODE_URL','二维码'),(23,'QZONE','/resources/images/qzonecode.png',NULL,NULL,NULL,'CODE_URL','二维码'),(16,'WEIBO','http://weibo.com/colormefun',NULL,NULL,NULL,'URL','地址'),(17,'WEIXIN','https://mp.weixin.qq.com/',NULL,NULL,NULL,'URL','地址'),(18,'LINKED_IN','https://cn.linkedin.com/pub/秘方-快乐/a6/68/a14',NULL,NULL,NULL,'URL','地址'),(19,'QZONE','http://user.qzone.qq.com/2780025534/4',NULL,NULL,NULL,'URL','地址'),(20,'WEIBO','/resources/images/weibocode.png',NULL,NULL,NULL,'CODE_URL','二维码');

/*Table structure for table `system_db_backup` */

DROP TABLE IF EXISTS `system_db_backup`;

CREATE TABLE `system_db_backup` (
  `bk_id` int(11) NOT NULL AUTO_INCREMENT,
  `bk_time` datetime DEFAULT NULL,
  `bk_title` varchar(50) DEFAULT NULL,
  `bk_path` varchar(255) DEFAULT NULL,
  `bk_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bk_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `system_db_backup` */

/*Table structure for table `system_dictionary` */

DROP TABLE IF EXISTS `system_dictionary`;

CREATE TABLE `system_dictionary` (
  `dict_id` varchar(50) NOT NULL,
  `dict_name` varchar(50) DEFAULT NULL,
  `dict_comment` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `system_dictionary` */

insert  into `system_dictionary`(`dict_id`,`dict_name`,`dict_comment`) values ('CASE_LEVEL','难度级别',NULL),('CASE_NEED_LOC','是否需要我们推荐活动场所',NULL),('CASE_TYPE','活动方式',NULL),('CD_REGION','成都城区',NULL),('COUPON_DISCOUNT','优惠劵折扣',NULL),('COUPON_NUM_RANG','生成优惠劵数量',NULL),('COUPON_PRICE','优惠劵价格',NULL),('COUPON_STATUS','优惠劵状态',NULL),('COUPON_TYPE','优惠劵类型',NULL),('ORDER_STATUS','订单状态',NULL),('PASSWORD_QUESTION','密码提示问题',NULL),('SEX','性别',NULL),('TIME_RANGE','时间段',NULL),('VANUES','场地',NULL),('WEEK','星期',NULL);

/*Table structure for table `system_dictionary_item` */

DROP TABLE IF EXISTS `system_dictionary_item`;

CREATE TABLE `system_dictionary_item` (
  `dict_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_id` varchar(50) DEFAULT NULL,
  `dict_item_key` varchar(50) DEFAULT NULL,
  `dict_item_value` varchar(50) DEFAULT NULL,
  `dict_item_comment` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`dict_item_id`),
  KEY `dict_id` (`dict_id`)
) ENGINE=MyISAM AUTO_INCREMENT=525 DEFAULT CHARSET=utf8;

/*Data for the table `system_dictionary_item` */

insert  into `system_dictionary_item`(`dict_item_id`,`dict_id`,`dict_item_key`,`dict_item_value`,`dict_item_comment`) values (44,'PASSWORD_QUESTION','100','最喜欢的宠物',NULL),(45,'PASSWORD_QUESTION','200','最喜爱的电影',NULL),(46,'PASSWORD_QUESTION','300','自己的姓名',NULL),(47,'PASSWORD_QUESTION','400','中学的校名',NULL),(48,'PASSWORD_QUESTION','500','大学的校名',NULL),(49,'PASSWORD_QUESTION','600','父亲的姓名',NULL),(50,'PASSWORD_QUESTION','700','母亲的姓名',NULL),(51,'PASSWORD_QUESTION','800','配偶的姓名',NULL),(52,'PASSWORD_QUESTION','900','父亲的生日',NULL),(53,'PASSWORD_QUESTION','1000','出生的城市',NULL),(54,'PASSWORD_QUESTION','1100','身份证的后四位',NULL),(55,'PASSWORD_QUESTION','1200','邮箱地址',NULL),(56,'PASSWORD_QUESTION','1300','手机号码',NULL),(57,'PASSWORD_QUESTION','1400','最难忘的人',NULL),(241,'SEX','1','男',NULL),(242,'SEX','0','女',NULL),(243,'SEX','2','保密',NULL),(301,'VANUES','1','Group Exercise Room 阳光操厅',NULL),(302,'VANUES','2','Yoga room瑜伽房',NULL),(303,'VANUES','3','Spinning Room单车房',NULL),(401,'WEEK','1','Monday',NULL),(402,'WEEK','2','Tuesday ',NULL),(403,'WEEK','3','Wednesday',NULL),(404,'WEEK','4','Thursday',NULL),(405,'WEEK','5','Friday',NULL),(406,'WEEK','6','Saturday',NULL),(407,'WEEK','7','Sunday',NULL),(450,'TIME_RANGE','1','10:00-11:30\r\n',NULL),(451,'TIME_RANGE','2','12:00-13:00\r\n',NULL),(452,'TIME_RANGE','3','15:00-16:00',NULL),(453,'TIME_RANGE','4','18:15-19:15',NULL),(454,'TIME_RANGE','5','19:30-20:30',NULL),(455,'TIME_RANGE','6','18:15-19:00',NULL),(456,'TIME_RANGE','7','19:15-20:00',NULL),(457,'TIME_RANGE','8','18:30-19:30',NULL),(458,'TIME_RANGE','9','19:30-20:15',NULL),(459,'TIME_RANGE','91','19:45-20:45',NULL),(460,'TIME_RANGE','92','19:30-20:30',NULL),(461,'CASE_LEVEL','1','入门',NULL),(462,'CASE_LEVEL','2','简单',NULL),(463,'CASE_LEVEL','3','较难',NULL),(464,'CASE_LEVEL','4','专业',NULL),(465,'COUPON_STATUS','created','已创建',NULL),(466,'COUPON_STATUS','released','已发行',NULL),(467,'COUPON_STATUS','locked','已锁定',NULL),(468,'COUPON_STATUS','used','已使用',NULL),(469,'COUPON_STATUS','expired','已过期',NULL),(470,'COUPON_STATUS','closed','已关闭',NULL),(472,'COUPON_TYPE','1','现金卷',NULL),(475,'COUPON_NUM_RANG','10','10',NULL),(476,'COUPON_NUM_RANG','50','50',NULL),(477,'COUPON_NUM_RANG','100','100',NULL),(478,'COUPON_NUM_RANG','1000','1000',NULL),(479,'COUPON_PRICE','5','5元',NULL),(480,'COUPON_PRICE','10','10元',NULL),(481,'COUPON_PRICE','20','20元',NULL),(482,'COUPON_PRICE','50','50元',NULL),(483,'COUPON_PRICE','100','100元',NULL),(484,'ORDER_STATUS','created','已创建',NULL),(485,'ORDER_STATUS','paid','已支付',NULL),(486,'ORDER_STATUS','closed','已关闭',NULL),(500,'CASE_TYPE','vip','私人派对',NULL),(501,'CASE_TYPE','ent_vip','企业活动',NULL),(502,'CASE_NEED_LOC','Y','是',NULL),(503,'CASE_NEED_LOC','N','否',NULL),(504,'CD_REGION','武侯区','武侯区',NULL),(505,'CD_REGION','金牛区','金牛区',NULL),(506,'CD_REGION','青羊区','青羊区',NULL),(507,'CD_REGION','成华区','成华区',NULL),(508,'CD_REGION','高新区','高新区',NULL),(509,'CD_REGION','锦江区','锦江区',NULL),(510,'CD_REGION','郫县','郫县',NULL),(511,'CD_REGION','双流县','双流县',NULL),(512,'CD_REGION','高新西区*','高新西区*',NULL),(513,'CD_REGION','龙泉驿区','龙泉驿区',NULL),(514,'CD_REGION','新都区','新都区',NULL),(515,'CD_REGION','温江区','温江区',NULL),(516,'CD_REGION','都江堰市','都江堰市',NULL),(517,'CD_REGION','彭州市','彭州市',NULL),(518,'CD_REGION','青白江区','青白江区',NULL),(519,'CD_REGION','崇州市','崇州市',NULL),(520,'CD_REGION','金堂县','金堂县',NULL),(521,'CD_REGION','新津县','新津县',NULL),(522,'CD_REGION','邛崃市','邛崃市',NULL),(523,'CD_REGION','大邑县','大邑县',NULL),(524,'CD_REGION','蒲江县','蒲江县',NULL);

/*Table structure for table `system_login_log` */

DROP TABLE IF EXISTS `system_login_log`;

CREATE TABLE `system_login_log` (
  `login_id` int(50) NOT NULL AUTO_INCREMENT,
  `mem_id` varchar(50) DEFAULT NULL,
  `login_ip` varchar(50) DEFAULT NULL,
  `login_addr` varchar(50) DEFAULT NULL,
  `login_type` varchar(50) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  `login_comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `system_login_log` */

/*Table structure for table `system_operation_log` */

DROP TABLE IF EXISTS `system_operation_log`;

CREATE TABLE `system_operation_log` (
  `op_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `start_time` varchar(20) DEFAULT NULL,
  `end_time` varchar(20) DEFAULT NULL,
  `op_ip` varchar(50) DEFAULT NULL,
  `op_addr` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `operation` varchar(50) DEFAULT NULL,
  `op_comment` varchar(100) DEFAULT NULL,
  `op_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`op_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `system_operation_log` */

/* Procedure structure for procedure `monthly_report` */

/*!50003 DROP PROCEDURE IF EXISTS  `monthly_report` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `monthly_report`()
monthly_report:
    BEGIN
	DECLARE _last_month INT;
	DECLARE _month INT;
	DECLARE _last_year INT;
	DECLARE _year INT;
	DECLARE _last_report_year INT;
	DECLARE _last_report_month INT;
	DECLARE _now_year INT;
	DECLARE _now_month INT;
	DECLARE _now DATETIME;
	DECLARE _temp_start_time DATETIME;
	DECLARE _temp_end_time DATETIME;
	
	-- field in the report
	DECLARE _case_total INT;
	DECLARE _order_total INT;
	DECLARE _ticket_total INT;
	DECLARE _price_total INT;
	DECLARE _coupon_total INT;
	
	DECLARE _real_case_total INT;
	DECLARE _real_order_total INT;
	DECLARE _real_ticket_total INT;
	DECLARE _real_price_total INT;
	DECLARE _real_coupon_total INT;
	
	DECLARE _status VARCHAR(10);
	
	SET _now = NOW();
	SET _now_year = YEAR(_now);
	SET _now_month = MONTH(_now);
	/* 1. get the last report date */
	SELECT t.year, t.month INTO _last_report_year, _last_report_month FROM mf_monthly_report t WHERE t.status='closed' ORDER BY t.year DESC, t.month DESC LIMIT 1;
	IF _last_report_month IS NULL THEN
		SELECT YEAR(t.start_date), MONTH(t.start_date) INTO _last_report_year, _last_report_month FROM mf_case t ORDER BY t.start_date LIMIT 1;
  ELSEIF _last_report_month = 12 THEN
    SET _last_report_year = _last_report_year + 1;
    SET _last_report_month = 1;
  ELSE
    SET _last_report_month = _last_report_month + 1;
	END IF;
	IF _last_report_month IS NULL THEN
		LEAVE monthly_report;
	END IF;
	
	/* 2. find out the pre-total
	set _case_total = 5;
	set _ticket_total = _case_total * 20;
	SET _order_total = _ticket_total;
	set _price_total = _ticket_total * 200.00;
	set _coupon_total = _price_total * 0.10;
	*/
	/* 3. find out the real total monthly*/
	SET _last_year = _now_year;
	SET _year = _last_report_year;
	WHILE _year <= _last_year DO
	BEGIN
		IF _last_report_year < _now_year THEN
			SET _last_month = 12;
		ELSE
			SET _last_month = _now_month;
		END IF;
		SET _month = _last_report_month;
		WHILE _month <= _last_month DO
		BEGIN
			SET _temp_start_time = CONVERT(CONCAT(_year,'-',_month,'-01 00:00:00'), DATETIME);
			SET _temp_end_time = DATE_ADD(CONCAT(_year,'-',_month+1,'-01 00:00:00'),INTERVAL -1 SECOND);
		
			-- _case_total, _ticket_total, _order_total
			SELECT  COUNT(*), SUM(ticket_number), SUM(ticket_number), SUM(ticket_number * ticket_price)
			 INTO _case_total, _ticket_total, _order_total, _price_total
			 FROM mf_case WHERE start_date BETWEEN _temp_start_time AND _temp_end_time;
			
			-- _coupon_total
			SELECT SUM(deduction) INTO _coupon_total FROM mf_coupon WHERE `deadline` >=_temp_start_time 
        /*AND (status<>'used' or (status='used' and used_date BETWEEN _temp_start_time AND _temp_end_time ) )*/
        AND STATUS<>'used' AND active='Y';
			IF _coupon_total IS NULL THEN
        SET _coupon_total = 0;
      END IF;
			-- _real_case_total
			SELECT COUNT(*) INTO _real_case_total FROM mf_case WHERE active='Y' AND start_date BETWEEN _temp_start_time AND _temp_end_time;
			-- _real_order_total
			SELECT COUNT(DISTINCT o.order_no) INTO _real_order_total FROM mf_order o 
			 INNER JOIN mf_order_detail od ON o.order_no = od.order_no
			 WHERE o.status = 'closed' AND od.case_no IN 
			 (SELECT case_no FROM mf_case WHERE start_date BETWEEN _temp_start_time AND _temp_end_time);
			 
			-- _real_order_total, _real_price_total, _real_ticket_total
			SELECT COUNT(DISTINCT o.order_no), SUM(od.qty), SUM(od.price * od.qty)
			 INTO _real_order_total, _real_ticket_total, _real_price_total
			 FROM mf_order o INNER JOIN mf_order_detail od ON o.order_no = od.order_no
			 WHERE o.status = 'closed' AND od.case_no IN 
			 (SELECT case_no FROM mf_case WHERE start_date BETWEEN _temp_start_time AND _temp_end_time);
			 
			-- _real_coupon_total
			SELECT SUM(c.deduction) INTO _real_coupon_total FROM mf_order o 
			 INNER JOIN mf_order_coupon oc ON o.order_no = oc.order_no 
			 INNER JOIN mf_coupon c ON oc.coupon_id = c.coupon_id
			 WHERE c.`status`<>'used' AND o.order_no IN (SELECT so.order_no FROM mf_order so INNER JOIN mf_order_detail sod
				ON so.order_no = sod.order_no WHERE so.status = 'closed' AND sod.case_no IN 
				(SELECT case_no FROM mf_case WHERE start_date BETWEEN _temp_start_time AND _temp_end_time));
      IF _real_coupon_total IS NULL THEN
        SET _real_coupon_total = 0;
      END IF;
			-- delete all 'open' report
			DELETE FROM mf_monthly_report WHERE `year` = _year AND `month` = _month AND `status`='open';
			
			-- insert into monthly report
			INSERT INTO `mf_monthly_report`
				    (`year`,
				     `month`,
             `case_total`,
				     `order_total`,
				     `ticket_total`,
				     `price_total`,
				     `coupon_total`,
				     `real_case_total`,
				     `real_order_total`,
				     `real_ticket_total`,
				     `real_price_total`,
				     `real_coupon_total`,
				     `status`,
				     `comment`,
				     `created_id`,
				     `created_date`)
			VALUES (_year,
				_month,
				CASE WHEN _case_total IS NULL THEN 0 ELSE _case_total END,
				CASE WHEN _order_total IS NULL THEN 0 ELSE _order_total END,
				CASE WHEN _ticket_total IS NULL THEN 0 ELSE _ticket_total END,
				CASE WHEN _price_total IS NULL THEN 0 ELSE _price_total END,
				CASE WHEN _coupon_total IS NULL THEN 0 ELSE _coupon_total END,
				CASE WHEN _real_case_total IS NULL THEN 0 ELSE _real_case_total END,
				CASE WHEN _real_order_total IS NULL THEN 0 ELSE _real_order_total END,
				CASE WHEN _real_ticket_total IS NULL THEN 0 ELSE _real_ticket_total END,
				CASE WHEN _real_price_total IS NULL THEN 0 ELSE _real_price_total END,
				CASE WHEN _real_coupon_total IS NULL THEN 0 ELSE _real_coupon_total END,
				CASE WHEN _year=_now_year AND _month=_now_month THEN 'open' ELSE 'closed' END,
				'',
				-1,
				_now);
			
			SET _month = _month + 1;
		END;
		END WHILE;
		SET _year = _year + 1;
	END;
	END WHILE;
	
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
