/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.13 : Database - colormefun
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`colormefun` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `colormefun`;

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
  KEY `purview_upid` (`purview_upid`),
  CONSTRAINT `auth_purview_ibfk_1` FOREIGN KEY (`purview_upid`) REFERENCES `auth_purview` (`purview_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_purview` */

insert  into `auth_purview`(`purview_id`,`purview_name`,`purview_upid`,`purview_url`,`purview_image`,`purview_type`,`purview_mask`,`purview_comment`,`active`) values ('100','快乐秘方',NULL,'#',NULL,'0','100',NULL,'Y'),('101','订单管理',NULL,'#','','0','101','','Y'),('102','客户管理',NULL,'#','','0','102','','Y'),('103','内容管理',NULL,'#','','0','103','','Y'),('104','财务管理',NULL,'#','','0','104','','Y'),('107','管 理 员',NULL,'#','','0','107','','Y'),('108','系统设置',NULL,'#','','0','108','','Y'),('109','安全设置',NULL,'#','','0','109','','Y'),('238','管理员列表','107','/admin/system/user/list.do',NULL,'1','107000',NULL,'Y'),('239','添加管理员','107','/admin/system/user/add.do',NULL,'1','107001',NULL,'Y'),('240','修改管理员密码','107','/admin/system/user/editPassword.do',NULL,'1','107002',NULL,'Y'),('241','管理员操作日志','107','/admin/system/user/oplog.do','','1','107003','','N'),('242','角色管理','107','/admin/system/role/list.do',NULL,'1','107004',NULL,'Y'),('243','添加角色','107','/admin/system/role/add.do',NULL,'1','107005',NULL,'Y'),('246','菜单名称修改','108','/admin/system/menu/list.do','','1','108002','','Y'),('247','敏感词汇修改','108','todo:/admin/sysparameter/serieceword.do','','1','108003','','N'),('250','数据库备份','108','/admin/db/list.jsp','','1','108006','','Y'),('251','设置搜索时间','108','todo:/admin/sysparameter/searchtime.do','','1','108007','','N'),('252','限制IP登录','109','/admin/surparameter/setlimitip.do','','1','109000','','N'),('254','限制密码长度','109','todo:/admin/surparameter/setpwdlength.do','','1','109002','','N'),('255','安全退出','109','/admin/user/logout.do',NULL,'1','109003',NULL,'Y'),('301','活动列表','100','/admin/mfCase/list.do',NULL,'1','100001',NULL,'Y'),('302','发布活动','100','/admin/mfCase/add.do',NULL,'1','100002',NULL,'Y'),('303','私人订制','100','/admin/todo.jsp',NULL,'1','100003',NULL,'Y'),('304','企业订制','100','/admin/todo.jsp',NULL,'1','100004',NULL,'Y'),('305','FUN相册','100','/admin/todo.jsp',NULL,'1','100005',NULL,'Y'),('401','订单列表','101','/admin/todo.jsp',NULL,'1','101001',NULL,'Y'),('402','购物车','101','/admin/todo.jsp',NULL,'1','101002',NULL,'Y'),('405','客户列表','102','/admin/user/list.do',NULL,'1','102001',NULL,'Y'),('406','添加客户','102','/admin/user/add.do',NULL,'1','102002',NULL,'Y'),('407','收藏夹','102','/admin/todo.jsp',NULL,'1','102003',NULL,'Y'),('408','邮件管理','102','/admin/todo.jsp',NULL,'1','102004',NULL,'N'),('409','短信管理','102','/admin/todo.jsp',NULL,'1','102005',NULL,'N'),('501','常见问题','103','/admin/content/list.do',NULL,'1','103001',NULL,'Y'),('502','添加问题','103','/admin/content/add.do',NULL,'1','103002',NULL,'Y'),('503','关于快乐秘方','103','/admin/content/about.do',NULL,'1','103005',NULL,'Y'),('504','滚动图片列表','103','/admin/content/listMarquee.do',NULL,'1','103003',NULL,'Y'),('505','发布滚动图片','103','/admin/content/addMarquee.do',NULL,'1','103004',NULL,'Y'),('601','优 惠 劵','104','/admin/todo.jsp',NULL,'1','104001',NULL,'Y'),('602','添加优惠劵','104','/admin/todo.jsp',NULL,'1','104002',NULL,'Y'),('603','生成优惠劵','104','/admin/todo.jsp',NULL,'1','104003',NULL,'Y'),('604','支付接口','104','/admin/todo.jsp',NULL,'1','104004',NULL,'Y'),('605','短信接口','104','/admin/todo.jsp',NULL,'1','104005',NULL,'Y'),('606','邮件接口','104','/admin/todo.jsp',NULL,'1','104006',NULL,'Y');

/*Table structure for table `auth_role` */

DROP TABLE IF EXISTS `auth_role`;

CREATE TABLE `auth_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `auth_role` */

insert  into `auth_role`(`role_id`,`role_name`) values (1,'系统管理员'),(2,'销售主管'),(3,'销售人员'),(4,'财务人员');

/*Table structure for table `auth_role_purview` */

DROP TABLE IF EXISTS `auth_role_purview`;

CREATE TABLE `auth_role_purview` (
  `role_id` int(11) NOT NULL,
  `purview_id` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`purview_id`),
  KEY `purview_id` (`purview_id`),
  CONSTRAINT `auth_role_purview_ibfk_1` FOREIGN KEY (`purview_id`) REFERENCES `auth_purview` (`purview_id`),
  CONSTRAINT `auth_role_purview_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_user` */

insert  into `auth_user`(`user_name`,`user_realname`,`user_password`,`user_password2`,`USER_COMMENT`) values ('admin','超级用户','oyotong','1111','管理系统'),('ceo','ceo','qqqq','qqqq',''),('text1','新燕','123456',NULL,''),('text2','小钟','123456',NULL,'');

/*Table structure for table `auth_user_role` */

DROP TABLE IF EXISTS `auth_user_role`;

CREATE TABLE `auth_user_role` (
  `user_id` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `auth_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`user_name`),
  CONSTRAINT `auth_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  KEY `user_id` (`user_id`),
  CONSTRAINT `auth_user_shortcutmenu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `auth_purview` (`purview_id`),
  CONSTRAINT `auth_user_shortcutmenu_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `auth_user_shortcutmenu` */

/*Table structure for table `mf_cart` */

DROP TABLE IF EXISTS `mf_cart`;

CREATE TABLE `mf_cart` (
  `cart_no` int(11) NOT NULL AUTO_INCREMENT,
  `user_no` int(11) DEFAULT NULL,
  `active` varchar(4) DEFAULT NULL,
  `ordered_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`cart_no`),
  KEY `FK_mf_cart` (`user_no`),
  CONSTRAINT `FK_mf_cart` FOREIGN KEY (`user_no`) REFERENCES `mf_user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_cart` */

/*Table structure for table `mf_cart_coupon` */

DROP TABLE IF EXISTS `mf_cart_coupon`;

CREATE TABLE `mf_cart_coupon` (
  `cart_no` int(11) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  PRIMARY KEY (`cart_no`,`coupon_id`),
  KEY `FK2_mf_cart_coupon` (`coupon_id`),
  CONSTRAINT `FK2_mf_cart_coupon` FOREIGN KEY (`coupon_id`) REFERENCES `mf_coupon` (`coupon_id`),
  CONSTRAINT `FK_mf_cart_coupon` FOREIGN KEY (`cart_no`) REFERENCES `mf_cart` (`cart_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_cart_coupon` */

/*Table structure for table `mf_cart_detail` */

DROP TABLE IF EXISTS `mf_cart_detail`;

CREATE TABLE `mf_cart_detail` (
  `cart_no` int(11) NOT NULL,
  `case_no` int(11) NOT NULL,
  `line_no` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_no`,`case_no`),
  KEY `FK2_mf_cart_detail` (`case_no`),
  CONSTRAINT `FK2_mf_cart_detail` FOREIGN KEY (`case_no`) REFERENCES `mf_case` (`case_no`),
  CONSTRAINT `FK_mf_cart_detail` FOREIGN KEY (`cart_no`) REFERENCES `mf_cart` (`cart_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `mf_case` */

insert  into `mf_case`(`case_no`,`title`,`level`,`start_date`,`time_range`,`location`,`address`,`ticket_price`,`ticket_number`,`remaining_ticket`,`sales_volume`,`picture`,`description`,`active`,`created_id`,`created_date`) values (1,'活动主题',2,'2014-07-27 00:00:00','9:00 - 10:00pm','活动场所','活动地点','250.00',100,NULL,0,'/userfiles/image/201407272121369987.jpg','<p>&nbsp;<span style=\"color: rgb(39, 69, 90); font-family: \'Courier New\', Courier, monospace; font-size: 13px;\">活动详情</span></p>','N','admin','2014-07-27 07:20:56'),(2,'活动主题2',1,'2014-07-28 00:00:00','9:00 - 10:00pm','活动场所2','活动地点2','250.00',100,NULL,20,'/userfiles/image/201407272121263976.jpg','<p><span style=\"color: rgb(39, 69, 90); font-family: \'Courier New\', Courier, monospace; font-size: 13px;\">活动详情2</span></p>','Y','admin','2014-07-27 08:06:58');

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
  `active` varchar(4) DEFAULT NULL,
  `created_id` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `mf_content` */

insert  into `mf_content`(`id`,`title`,`sub_title`,`content_type`,`content`,`comment`,`pic1`,`active`,`created_id`,`created_date`) values (1,'快乐秘方',NULL,'question','<p><span style=\"font-family: 宋体; line-height: 18px;\">他是这个地方的首富,但生活得并不快乐。先是亲戚和朋友向他借钱,但都是有去无回,这让他很伤心。后来他花钱请戏班子唱场戏,让大家去看。结果,那天晚上他的家让人给盗了。他实在想不明白,自己对他们这么好,他们为什么这样?从此,他变得越来越不快乐。直到有一天,他家门前来了一位远游的高僧。</span><span style=\"font-family: 宋体; line-height: 18px;\">&nbsp;</span></p>\r\n<p style=\"font-family: 宋体; line-height: 18px;\">　　他便把自己的苦闷向高僧说了。高僧听罢笑了,说,我有一个快乐的秘方放在山上的庙中了,施主愿意跟我去拿吗?不过路很远的,你得带上足够的盘缠。&nbsp;<br />\r\n就这样,他跟高僧上路了。路真的很远,他们走过了一个又一个村庄,翻过了一座又一座高山。路上他遇到很多穷人,高僧毫不犹豫地让他掏出钱施舍给穷人,直到他口袋里的钱越来越少了。他有点儿担心,他拿到秘方后怎么回来?&nbsp;<br />\r\n高僧看出了他的心思。高僧说,你不必担心,我保证你到时候会开开心心地回到家。&nbsp;<br />\r\n他听了高僧的话,就把剩余的盘缠也毫无保留地施舍给了穷人。&nbsp;<br />\r\n他们终于来到了庙中。他便问高僧讨要快乐的秘方。&nbsp;<br />\r\n高僧说,我已经把秘方给你了啊!&nbsp;<br />\r\n他听了很吃惊,说,你什么时候给我的,我怎么不知道啊?&nbsp;<br />\r\n高僧说,你既然来了,就过一些日子再回去吧。&nbsp;<br />\r\n于是,他便在山上度过了一段日子。在庙中,他听和尚们念那些听不懂的经文,时间久了,他就很烦躁。他向高僧要盘缠回去。</p>\r\n<p><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　高僧说,我已经把盘缠给你了。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　他一听明白了,这是个骗人的僧人,他前前后后在逗自己玩儿呢!他一气之下离开了庙,下山去了,一赌气跑出了很远。当他来到一个小山村的时候已经很饿了,但他的口袋空空的,他不知道如何是好。这个时候,一个老农从他眼前走过,一眼就认出他来了,说,哎呀,这不是我的恩人吗?你怎么会来到这里?他想不起对这个老农施舍过什么,但老农已经把他当亲人一样看待了。老农把他领到家中过了一晚。次日,他继续赶路。在途中,每当他遇到困难的时候,就会有人来帮他,那些人对他的印象很深,一眼就能认出他,这让他感到惊喜。一路上,他没有分文,受着人家的施舍快乐地回到了家。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　回到家,他才恍然大悟,高僧真的把快乐给了他。原来,带着快乐去施舍,这快乐早晚也要回到自己身上的。以前他的施舍里充满了回报的欲念,那欲念带来的痛苦也自然回到了他的身上。&nbsp;</span></p>\r\n<p>&nbsp;</p>',NULL,NULL,'Y','admin','2014-07-26 06:50:57'),(3,'关于快乐秘方（Color Me Fun）',NULL,'about','<p style=\"font-family: 宋体; line-height: 18px;\">&nbsp; &nbsp; 他是这个地方的首富,但生活得并不快乐。先是亲戚和朋友向他借钱,但都是有去无回,这让他很伤心。后来他花钱请戏班子唱场戏,让大家去看。结果,那天晚上他的家让人给盗了。他实在想不明白,自己对他们这么好,他们为什么这样?从此,他变得越来越不快乐。直到有一天,他家门前来了一位远游的高僧。&nbsp;<br />\r\n他便把自己的苦闷向高僧说了。高僧听罢笑了,说,我有一个快乐的秘方放在山上的庙中了,施主愿意跟我去拿吗?不过路很远的,你得带上足够的盘缠。&nbsp;<br />\r\n就这样,他跟高僧上路了。路真的很远,他们走过了一个又一个村庄,翻过了一座又一座高山。路上他遇到很多穷人,高僧毫不犹豫地让他掏出钱施舍给穷人,直到他口袋里的钱越来越少了。他有点儿担心,他拿到秘方后怎么回来?&nbsp;<br />\r\n高僧看出了他的心思。高僧说,你不必担心,我保证你到时候会开开心心地回到家。&nbsp;<br />\r\n他听了高僧的话,就把剩余的盘缠也毫无保留地施舍给了穷人。&nbsp;<br />\r\n他们终于来到了庙中。他便问高僧讨要快乐的秘方。&nbsp;<br />\r\n高僧说,我已经把秘方给你了啊!&nbsp;<br />\r\n他听了很吃惊,说,你什么时候给我的,我怎么不知道啊?&nbsp;<br />\r\n高僧说,你既然来了,就过一些日子再回去吧。&nbsp;<br />\r\n于是,他便在山上度过了一段日子。在庙中,他听和尚们念那些听不懂的经文,时间久了,他就很烦躁。他向高僧要盘缠回去。</p>\r\n<p><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　高僧说,我已经把盘缠给你了。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　他一听明白了,这是个骗人的僧人,他前前后后在逗自己玩儿呢!他一气之下离开了庙,下山去了,一赌气跑出了很远。当他来到一个小山村的时候已经很饿了,但他的口袋空空的,他不知道如何是好。这个时候,一个老农从他眼前走过,一眼就认出他来了,说,哎呀,这不是我的恩人吗?你怎么会来到这里?他想不起对这个老农施舍过什么,但老农已经把他当亲人一样看待了。老农把他领到家中过了一晚。次日,他继续赶路。在途中,每当他遇到困难的时候,就会有人来帮他,那些人对他的印象很深,一眼就能认出他,这让他感到惊喜。一路上,他没有分文,受着人家的施舍快乐地回到了家。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　回到家,他才恍然大悟,高僧真的把快乐给了他。原来,带着快乐去施舍,这快乐早晚也要回到自己身上的。以前他的施舍里充满了回报的欲念,那欲念带来的痛苦也自然回到了他的身上。&nbsp;</span><br style=\"font-family: 宋体; line-height: 18px;\" />\r\n<span style=\"font-family: 宋体; line-height: 18px;\">　　选自《山东青年》倪早菊/荐</span></p>',NULL,NULL,'Y','admin','2014-07-26 07:36:01'),(4,'滚动图片','/test.jsp?hello=1','marquee','<p>&nbsp;<span style=\"color: rgb(39, 69, 90); font-family: \'Courier New\', Courier, monospace; font-size: 13px;\">图片描述</span></p>',NULL,'/userfiles/image/20140726210720851.jpg','Y','admin','2014-07-26 08:41:49'),(5,'滚动图片','/test.jsp?hello=1','marquee','<p>&nbsp;菊花</p>',NULL,'/userfiles/image/201407262105111648.jpg','Y','admin','2014-07-26 09:04:40');

/*Table structure for table `mf_coupon` */

DROP TABLE IF EXISTS `mf_coupon`;

CREATE TABLE `mf_coupon` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_no` varchar(32) NOT NULL,
  `password` varchar(10) NOT NULL,
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
  `created_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_coupon` */

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
  KEY `FK2_mf_favorite` (`user_no`),
  CONSTRAINT `FK2_mf_favorite` FOREIGN KEY (`user_no`) REFERENCES `mf_user` (`user_no`),
  CONSTRAINT `FK_mf_favorite` FOREIGN KEY (`case_no`) REFERENCES `mf_case` (`case_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_favorite` */

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
  KEY `FK_mf_order` (`user_no`),
  CONSTRAINT `FK_mf_order` FOREIGN KEY (`user_no`) REFERENCES `mf_user` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_order` */

/*Table structure for table `mf_order_coupon` */

DROP TABLE IF EXISTS `mf_order_coupon`;

CREATE TABLE `mf_order_coupon` (
  `coupon_id` int(11) NOT NULL,
  `order_no` varchar(20) NOT NULL,
  PRIMARY KEY (`coupon_id`,`order_no`),
  KEY `FK_mf_order_coupon` (`order_no`),
  CONSTRAINT `FK2_mf_order_coupon` FOREIGN KEY (`coupon_id`) REFERENCES `mf_coupon` (`coupon_id`),
  CONSTRAINT `FK_mf_order_coupon` FOREIGN KEY (`order_no`) REFERENCES `mf_order` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_order_coupon` */

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
  KEY `FK2_mf_order_detail` (`case_no`),
  CONSTRAINT `FK2_mf_order_detail` FOREIGN KEY (`case_no`) REFERENCES `mf_case` (`case_no`),
  CONSTRAINT `FK_mf_order_detail` FOREIGN KEY (`order_no`) REFERENCES `mf_order` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mf_order_detail` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `mf_user` */

insert  into `mf_user`(`user_no`,`user_name`,`mobile_phone`,`password`,`email`,`birthday`,`sex`,`favorite`,`my_color`,`pic1`,`let_me_know`,`active`,`registered_date`) values (1,'张三','1328889998','cdd8db94b0e663d09b21d6f2f7454542','oyotong@153.com','2014-07-18','M','兴趣爱好','颜色','\\userfiles\\user1.png',NULL,'Y','2014-07-18'),(3,'飞得更高','56424342','f0baff9b791981fcfd3b04208aceb283','sdfsdf@asdfasdf.com','2014-07-22','M','asdfasdf','adsf4545fgdfg','/userfiles/image/20140725233304263.png',NULL,'Y','2014-07-25');

/*Table structure for table `site_parameter` */

DROP TABLE IF EXISTS `site_parameter`;

CREATE TABLE `site_parameter` (
  `param_key` varchar(50) NOT NULL DEFAULT '',
  `param_name` varchar(50) DEFAULT NULL,
  `param_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`param_key`),
  KEY `param_key` (`param_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `site_parameter` */

/*Table structure for table `site_parameter_item` */

DROP TABLE IF EXISTS `site_parameter_item`;

CREATE TABLE `site_parameter_item` (
  `param_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) NOT NULL,
  `param_item_value` varchar(255) NOT NULL,
  `param_item_value1` varchar(255) DEFAULT NULL,
  `param_item_value2` varchar(255) DEFAULT NULL,
  `param_item_value3` varchar(255) DEFAULT NULL,
  `param_item_name` varchar(100) NOT NULL,
  `param_item_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`param_item_id`),
  KEY `param_key` (`param_key`),
  CONSTRAINT `site_parameter_item_ibfk_1` FOREIGN KEY (`param_key`) REFERENCES `site_parameter` (`param_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `site_parameter_item` */

/*Table structure for table `system_db_backup` */

DROP TABLE IF EXISTS `system_db_backup`;

CREATE TABLE `system_db_backup` (
  `bk_id` int(11) NOT NULL AUTO_INCREMENT,
  `bk_time` datetime DEFAULT NULL,
  `bk_title` varchar(50) DEFAULT NULL,
  `bk_path` varchar(255) DEFAULT NULL,
  `bk_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `system_db_backup` */

/*Table structure for table `system_dictionary` */

DROP TABLE IF EXISTS `system_dictionary`;

CREATE TABLE `system_dictionary` (
  `dict_id` varchar(50) NOT NULL,
  `dict_name` varchar(50) DEFAULT NULL,
  `dict_comment` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `system_dictionary` */

insert  into `system_dictionary`(`dict_id`,`dict_name`,`dict_comment`) values ('CASE_LEVEL','难度级别',NULL),('COUPON_DISCOUNT','优惠劵折扣',NULL),('COUPON_NUM_RANG','生成优惠劵数量',NULL),('COUPON_PRICE','优惠劵价格',NULL),('COUPON_STATUS','优惠劵状态',NULL),('COUPON_TYPE','优惠劵类型',NULL),('PASSWORD_QUESTION','密码提示问题',NULL),('SEX','性别',NULL),('TIME_RANGE','时间段',NULL),('VANUES','场地',NULL),('WEEK','星期',NULL);

/*Table structure for table `system_dictionary_item` */

DROP TABLE IF EXISTS `system_dictionary_item`;

CREATE TABLE `system_dictionary_item` (
  `dict_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_id` varchar(50) DEFAULT NULL,
  `dict_item_key` varchar(50) DEFAULT NULL,
  `dict_item_value` varchar(50) DEFAULT NULL,
  `dict_item_comment` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`dict_item_id`),
  KEY `dict_id` (`dict_id`),
  CONSTRAINT `system_dictionary_item_ibfk_1` FOREIGN KEY (`dict_id`) REFERENCES `system_dictionary` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=484 DEFAULT CHARSET=utf8;

/*Data for the table `system_dictionary_item` */

insert  into `system_dictionary_item`(`dict_item_id`,`dict_id`,`dict_item_key`,`dict_item_value`,`dict_item_comment`) values (44,'PASSWORD_QUESTION','100','最喜欢的宠物',NULL),(45,'PASSWORD_QUESTION','200','最喜爱的电影',NULL),(46,'PASSWORD_QUESTION','300','自己的姓名',NULL),(47,'PASSWORD_QUESTION','400','中学的校名',NULL),(48,'PASSWORD_QUESTION','500','大学的校名',NULL),(49,'PASSWORD_QUESTION','600','父亲的姓名',NULL),(50,'PASSWORD_QUESTION','700','母亲的姓名',NULL),(51,'PASSWORD_QUESTION','800','配偶的姓名',NULL),(52,'PASSWORD_QUESTION','900','父亲的生日',NULL),(53,'PASSWORD_QUESTION','1000','出生的城市',NULL),(54,'PASSWORD_QUESTION','1100','身份证的后四位',NULL),(55,'PASSWORD_QUESTION','1200','邮箱地址',NULL),(56,'PASSWORD_QUESTION','1300','手机号码',NULL),(57,'PASSWORD_QUESTION','1400','最难忘的人',NULL),(241,'SEX','1','男',NULL),(242,'SEX','0','女',NULL),(243,'SEX','2','保密',NULL),(301,'VANUES','1','Group Exercise Room 阳光操厅',NULL),(302,'VANUES','2','Yoga room瑜伽房',NULL),(303,'VANUES','3','Spinning Room单车房',NULL),(401,'WEEK','1','Monday',NULL),(402,'WEEK','2','Tuesday ',NULL),(403,'WEEK','3','Wednesday',NULL),(404,'WEEK','4','Thursday',NULL),(405,'WEEK','5','Friday',NULL),(406,'WEEK','6','Saturday',NULL),(407,'WEEK','7','Sunday',NULL),(450,'TIME_RANGE','1','10:00-11:30\r\n',NULL),(451,'TIME_RANGE','2','12:00-13:00\r\n',NULL),(452,'TIME_RANGE','3','15:00-16:00',NULL),(453,'TIME_RANGE','4','18:15-19:15',NULL),(454,'TIME_RANGE','5','19:30-20:30',NULL),(455,'TIME_RANGE','6','18:15-19:00',NULL),(456,'TIME_RANGE','7','19:15-20:00',NULL),(457,'TIME_RANGE','8','18:30-19:30',NULL),(458,'TIME_RANGE','9','19:30-20:15',NULL),(459,'TIME_RANGE','91','19:45-20:45',NULL),(460,'TIME_RANGE','92','19:30-20:30',NULL),(461,'CASE_LEVEL','1','入门',NULL),(462,'CASE_LEVEL','2','简单',NULL),(463,'CASE_LEVEL','3','较难',NULL),(464,'CASE_LEVEL','4','专业',NULL),(465,'COUPON_STATUS','created','已创建',NULL),(466,'COUPON_STATUS','released','已发行',NULL),(467,'COUPON_STATUS','locked','已锁定',NULL),(468,'COUPON_STATUS','used','已使用',NULL),(469,'COUPON_STATUS','expired','已过期',NULL),(470,'COUPON_STATUS','closed','已关闭',NULL),(472,'COUPON_TYPE','1','现金卷',NULL),(475,'COUPON_NUM_RANG','10','10',NULL),(476,'COUPON_NUM_RANG','50','50',NULL),(477,'COUPON_NUM_RANG','100','100',NULL),(478,'COUPON_NUM_RANG','1000','1000',NULL),(479,'COUPON_PRICE','5','5元',NULL),(480,'COUPON_PRICE','10','10元',NULL),(481,'COUPON_PRICE','20','20元',NULL),(482,'COUPON_PRICE','50','50元',NULL),(483,'COUPON_PRICE','100','100元',NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `system_operation_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
