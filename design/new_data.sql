/*Table structure for table `mf_history` */

DROP TABLE IF EXISTS `mf_history`;

CREATE TABLE `mf_history` (
  `order_no` VARCHAR(20) NOT NULL,
  `user_no` INT(11) DEFAULT NULL,
  `comment` VARCHAR(200) DEFAULT NULL,
  `status` VARCHAR(10) DEFAULT NULL,
  `active` VARCHAR(4) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  PRIMARY KEY  (`order_no`),
  KEY `FK_mf_order` (`user_no`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_history` */

INSERT  INTO `mf_history`(`order_no`,`user_no`,`comment`,`status`,`active`,`created_date`) VALUES ('123456',1,'测试描述','created','Y','2014-07-31 21:59:06'),('234567',3,'描述2','paid','Y','2014-07-30 22:03:48');


/*Table structure for table `mf_history_detail` */

DROP TABLE IF EXISTS `mf_history_detail`;

CREATE TABLE `mf_history_detail` (
  `order_no` VARCHAR(20) NOT NULL,
  `case_no` INT(11) NOT NULL,
  `line_no` INT(11) NOT NULL,
  `qty` INT(11) DEFAULT NULL,
  `price` DECIMAL(9,2) DEFAULT NULL,
  `comment` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY  (`order_no`,`case_no`),
  KEY `FK2_mf_history_detail` (`case_no`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_history_detail` */

INSERT  INTO `mf_history_detail`(`order_no`,`case_no`,`line_no`,`qty`,`price`,`comment`) VALUES ('123456',1,1,10,'200.00','一点说明'),('123456',2,2,5,'300.00','不想所'),('234567',1,1,1,'120.00',NULL);



/*Table structure for table `mf_monthly_report` */

DROP TABLE IF EXISTS `mf_monthly_report`;

CREATE TABLE `mf_monthly_report` (
  `year` VARCHAR(20) NOT NULL,
  `month` INT(11) NOT NULL,

  `case_total` INT(11) NOT NULL,
  `order_total` INT(11) NOT NULL,
  `ticket_total` INT(11) DEFAULT NULL,
  `price_total` DECIMAL(9,4) DEFAULT NULL,
  `coupon_total` DECIMAL(9,4) DEFAULT NULL,

  `real_case_total` INT(11) NOT NULL,
  `real_order_total` INT(11) NOT NULL,
  `real_ticket_total` INT(11) DEFAULT NULL,
  `real_price_total` DECIMAL(9,4) DEFAULT NULL,
  `real_coupon_total` DECIMAL(9,4) DEFAULT NULL,

  `status` VARCHAR(10) DEFAULT NULL,
  `comment` VARCHAR(500) DEFAULT NULL,
  `created_id` VARCHAR(50) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  PRIMARY KEY  (`year`,`month`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8;

/*Data for the table `mf_history_detail` */