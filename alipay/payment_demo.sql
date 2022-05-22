/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.28-log : Database - payment_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`payment_demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `payment_demo`;

/*Table structure for table `t_order_info` */

CREATE TABLE `t_order_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `title` varchar(256) DEFAULT NULL COMMENT '订单标题',
  `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
  `payment_type` varchar(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '支付产品id',
  `total_fee` int(11) DEFAULT NULL COMMENT '订单金额(分)',
  `code_url` varchar(50) DEFAULT NULL COMMENT '订单二维码连接',
  `order_status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_order_info` */

insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (57,'UI课程','ORDER_20211214184634520','微信',NULL,4,1,'weixin://wxpay/bizpayurl?pr=V5WM2wWzz','已退款','2021-12-14 18:46:34','2022-04-08 02:49:06');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (58,'Java课程','ORDER_20211214214049802','微信',NULL,1,1,'weixin://wxpay/bizpayurl?pr=C0l6GOezz','超时已关闭','2021-12-14 21:40:49','2022-04-08 02:49:06');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (64,'前端课程','ORDER_20211214222026123','微信',NULL,1,1,'weixin://wxpay/bizpayurl?pr=YZR0EHCzz','支付成功','2021-12-14 22:20:26','2022-04-08 02:49:06');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (65,'大数据课程','ORDER_20211214232430509','微信',NULL,1,1,'weixin://wxpay/bizpayurl?pr=DgOtpmTzz','超时已关闭','2021-12-14 23:24:30','2022-04-08 02:49:06');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (66,'UI课程','ORDER_20211214233855296','微信',NULL,1,1,'weixin://wxpay/bizpayurl?pr=uSSMv5vzz','支付成功','2021-12-14 23:38:55','2022-04-08 02:49:06');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (86,'Java课程','ORDER_20220324191827447','微信',NULL,1,1,'weixin://wxpay/bizpayurl?pr=XkynW2Izz','超时已关闭','2022-03-24 19:18:27','2022-04-08 02:49:06');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (94,'Java课程','ORDER_20220407233913685','支付宝',NULL,1,1,NULL,'支付成功','2022-04-07 23:39:13','2022-04-08 02:49:06');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (98,'Java课程','ORDER_20220408022309952','微信',NULL,1,1,'weixin://wxpay/bizpayurl?pr=ETX0RFczz','用户已取消','2022-04-08 02:23:09','2022-04-08 03:15:28');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (104,'Java课程','ORDER_20220408043309962','支付宝',NULL,1,1,NULL,'用户已取消','2022-04-08 04:33:09','2022-04-08 04:44:41');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (105,'Java课程','ORDER_20220408044456853','支付宝',NULL,1,1,NULL,'用户已取消','2022-04-08 04:44:56','2022-04-08 04:45:54');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (106,'Java课程','ORDER_20220408044747571','支付宝',NULL,1,1,NULL,'用户已取消','2022-04-08 04:47:47','2022-04-08 04:47:58');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (107,'Java课程','ORDER_20220408051709571','微信',NULL,1,1,'weixin://wxpay/bizpayurl?pr=osvGS8Hzz','未支付','2022-04-08 05:17:09','2022-04-08 05:17:10');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (108,'Java课程','ORDER_20220408051728922','支付宝',NULL,1,1,NULL,'超时已关闭','2022-04-08 05:17:28','2022-04-10 17:32:30');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (114,'大数据课程','ORDER_20220410184105053','支付宝',NULL,2,1,NULL,'超时已关闭','2022-04-10 18:41:05','2022-04-10 18:43:30');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (115,'前端课程','ORDER_20220410195142968','支付宝',NULL,3,1,NULL,'退款异常','2022-04-10 19:51:42','2022-04-11 08:09:29');
insert  into `t_order_info`(`id`,`title`,`order_no`,`payment_type`,`user_id`,`product_id`,`total_fee`,`code_url`,`order_status`,`create_time`,`update_time`) values (125,'UI课程','ORDER_20220411081128077','支付宝',NULL,4,1,NULL,'已退款','2022-04-11 08:11:28','2022-04-11 08:12:40');

/*Table structure for table `t_payment_info` */

CREATE TABLE `t_payment_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '支付记录id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
  `transaction_id` varchar(50) DEFAULT NULL COMMENT '支付系统交易编号',
  `payment_type` varchar(20) DEFAULT NULL COMMENT '支付类型',
  `trade_type` varchar(20) DEFAULT NULL COMMENT '交易类型',
  `trade_state` varchar(50) DEFAULT NULL COMMENT '交易状态',
  `payer_total` int(11) DEFAULT NULL COMMENT '支付金额(分)',
  `content` text COMMENT '通知参数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_payment_info` */

insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (29,'ORDER_20211214130546776','4200001335202112149645887495','微信','NATIVE','SUCCESS',1,'{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":1,\"total\":1},\"appid\":\"wx74862e0dfcf69954\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1558950191\",\"out_trade_no\":\"ORDER_20211214130546776\",\"payer\":{\"openid\":\"oHwsHuEpuA7tEM4GPGtBiKpjAcqM\"},\"promotion_detail\":[],\"success_time\":\"2021-12-14T13:06:16+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200001335202112149645887495\"}','2021-12-14 13:07:11','2021-12-14 13:07:11');
insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (30,'ORDER_20211214144311039','4200001345202112143715320866','微信','NATIVE','SUCCESS',1,'{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":1,\"total\":1},\"appid\":\"wx74862e0dfcf69954\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1558950191\",\"out_trade_no\":\"ORDER_20211214144311039\",\"payer\":{\"openid\":\"oHwsHuEpuA7tEM4GPGtBiKpjAcqM\"},\"promotion_detail\":[],\"success_time\":\"2021-12-14T14:43:26+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200001345202112143715320866\"}','2021-12-14 14:44:30','2021-12-14 14:44:30');
insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (31,'ORDER_20211214152230866','4200001302202112148505782534','微信','NATIVE','SUCCESS',1,'{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":1,\"total\":1},\"appid\":\"wx74862e0dfcf69954\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1558950191\",\"out_trade_no\":\"ORDER_20211214152230866\",\"payer\":{\"openid\":\"oHwsHuEpuA7tEM4GPGtBiKpjAcqM\"},\"promotion_detail\":[],\"success_time\":\"2021-12-14T15:22:48+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200001302202112148505782534\"}','2021-12-14 15:24:00','2021-12-14 15:24:00');
insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (33,'ORDER_20211214184634520','4200001184202112147549577350','微信','NATIVE','SUCCESS',1,'{\"mchid\":\"1558950191\",\"appid\":\"wx74862e0dfcf69954\",\"out_trade_no\":\"ORDER_20211214184634520\",\"transaction_id\":\"4200001184202112147549577350\",\"trade_type\":\"NATIVE\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"bank_type\":\"OTHERS\",\"attach\":\"\",\"success_time\":\"2021-12-14T18:46:55+08:00\",\"payer\":{\"openid\":\"oHwsHuEpuA7tEM4GPGtBiKpjAcqM\"},\"amount\":{\"total\":1,\"payer_total\":1,\"currency\":\"CNY\",\"payer_currency\":\"CNY\"}}','2021-12-14 18:46:59','2021-12-14 18:46:59');
insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (34,'ORDER_20211214233855296','4200001302202112148087910775','微信','NATIVE','SUCCESS',1,'{\"amount\":{\"currency\":\"CNY\",\"payer_currency\":\"CNY\",\"payer_total\":1,\"total\":1},\"appid\":\"wx74862e0dfcf69954\",\"attach\":\"\",\"bank_type\":\"OTHERS\",\"mchid\":\"1558950191\",\"out_trade_no\":\"ORDER_20211214233855296\",\"payer\":{\"openid\":\"oHwsHuEpuA7tEM4GPGtBiKpjAcqM\"},\"promotion_detail\":[],\"success_time\":\"2021-12-14T23:39:37+08:00\",\"trade_state\":\"SUCCESS\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"transaction_id\":\"4200001302202112148087910775\"}','2021-12-14 23:40:00','2021-12-14 23:40:00');
insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (36,'ORDER_20220407233913685','2022040722001476680502101336','支付宝','电脑网站支付','TRADE_SUCCESS',1,'{\"gmt_create\":\"2022-04-07 23:39:24\",\"charset\":\"UTF-8\",\"gmt_payment\":\"2022-04-07 23:39:40\",\"notify_time\":\"2022-04-07 23:39:41\",\"subject\":\"Java课程\",\"buyer_id\":\"2088622958076685\",\"invoice_amount\":\"0.01\",\"version\":\"1.0\",\"notify_id\":\"2022040700222233940076680520843474\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"ORDER_20220407233913685\",\"total_amount\":\"0.01\",\"trade_status\":\"TRADE_SUCCESS\",\"trade_no\":\"2022040722001476680502101336\",\"auth_app_id\":\"2021000119635499\",\"receipt_amount\":\"0.01\",\"point_amount\":\"0.00\",\"app_id\":\"2021000119635499\",\"buyer_pay_amount\":\"0.01\",\"seller_id\":\"2088621957993562\"}','2022-04-07 23:39:45','2022-04-07 23:39:45');
insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (38,'ORDER_20220410195142968','2022041022001476680502103065','支付宝','电脑网站支付','TRADE_SUCCESS',1,'{\"code\":\"10000\",\"msg\":\"Success\",\"buyer_logon_id\":\"fmv***@sandbox.com\",\"buyer_pay_amount\":\"0.00\",\"buyer_user_id\":\"2088622958076685\",\"buyer_user_type\":\"PRIVATE\",\"invoice_amount\":\"0.00\",\"out_trade_no\":\"ORDER_20220410195142968\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.00\",\"send_pay_date\":\"2022-04-10 19:52:03\",\"total_amount\":\"0.01\",\"trade_no\":\"2022041022001476680502103065\",\"trade_status\":\"TRADE_SUCCESS\"}','2022-04-10 19:59:00','2022-04-10 19:59:00');
insert  into `t_payment_info`(`id`,`order_no`,`transaction_id`,`payment_type`,`trade_type`,`trade_state`,`payer_total`,`content`,`create_time`,`update_time`) values (46,'ORDER_20220411081128077','2022041122001476680502103259','支付宝','电脑网站支付','TRADE_SUCCESS',1,'{\"gmt_create\":\"2022-04-11 08:11:55\",\"charset\":\"UTF-8\",\"gmt_payment\":\"2022-04-11 08:12:05\",\"notify_time\":\"2022-04-11 08:12:08\",\"subject\":\"UI课程\",\"buyer_id\":\"2088622958076685\",\"invoice_amount\":\"0.01\",\"version\":\"1.0\",\"notify_id\":\"2022041100222081206076680520869991\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"ORDER_20220411081128077\",\"total_amount\":\"0.01\",\"trade_status\":\"TRADE_SUCCESS\",\"trade_no\":\"2022041122001476680502103259\",\"auth_app_id\":\"2021000119635499\",\"receipt_amount\":\"0.01\",\"point_amount\":\"0.00\",\"app_id\":\"2021000119635499\",\"buyer_pay_amount\":\"0.01\",\"seller_id\":\"2088621957993562\"}','2022-04-11 08:12:10','2022-04-11 08:12:10');

/*Table structure for table `t_product` */

CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `title` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `price` int(11) DEFAULT NULL COMMENT '价格（分）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_product` */

insert  into `t_product`(`id`,`title`,`price`,`create_time`,`update_time`) values (1,'Java课程',1,'2021-12-08 00:51:26','2021-12-10 00:21:37');
insert  into `t_product`(`id`,`title`,`price`,`create_time`,`update_time`) values (2,'大数据课程',1,'2021-12-08 00:51:26','2021-12-10 00:21:38');
insert  into `t_product`(`id`,`title`,`price`,`create_time`,`update_time`) values (3,'前端课程',1,'2021-12-08 00:51:26','2022-04-11 07:55:00');
insert  into `t_product`(`id`,`title`,`price`,`create_time`,`update_time`) values (4,'UI课程',1,'2021-12-08 00:51:26','2021-12-10 00:21:39');

/*Table structure for table `t_refund_info` */

CREATE TABLE `t_refund_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '退款单id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
  `refund_no` varchar(50) DEFAULT NULL COMMENT '商户退款单编号',
  `refund_id` varchar(50) DEFAULT NULL COMMENT '支付系统退款单号',
  `total_fee` int(11) DEFAULT NULL COMMENT '原订单金额(分)',
  `refund` int(11) DEFAULT NULL COMMENT '退款金额(分)',
  `reason` varchar(50) DEFAULT NULL COMMENT '退款原因',
  `refund_status` varchar(50) DEFAULT NULL COMMENT '退款状态',
  `content_return` text COMMENT '申请退款返回参数',
  `content_notify` text COMMENT '退款结果通知参数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_refund_info` */

insert  into `t_refund_info`(`id`,`order_no`,`refund_no`,`refund_id`,`total_fee`,`refund`,`reason`,`refund_status`,`content_return`,`content_notify`,`create_time`,`update_time`) values (2,'ORDER_20211214152230866','REFUND_20211214152433011','50301400132021121415361072383',1,1,'不喜欢','SUCCESS','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":1,\"payer_total\":1,\"refund\":1,\"settlement_refund\":1,\"settlement_total\":1,\"total\":1},\"channel\":\"ORIGINAL\",\"create_time\":\"2021-12-14T15:24:36+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"REFUND_20211214152433011\",\"out_trade_no\":\"ORDER_20211214152230866\",\"promotion_detail\":[],\"refund_id\":\"50301400132021121415361072383\",\"status\":\"SUCCESS\",\"success_time\":\"2021-12-14T15:24:43+08:00\",\"transaction_id\":\"4200001302202112148505782534\",\"user_received_account\":\"支付用户零钱\"}',NULL,'2021-12-14 15:24:33','2021-12-14 17:07:30');
insert  into `t_refund_info`(`id`,`order_no`,`refund_no`,`refund_id`,`total_fee`,`refund`,`reason`,`refund_status`,`content_return`,`content_notify`,`create_time`,`update_time`) values (3,'ORDER_20211214130546776','REFUND_20211214171113826','50301400132021121415370407561',1,1,'不喜欢','SUCCESS','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":1,\"payer_total\":1,\"refund\":1,\"settlement_refund\":1,\"settlement_total\":1,\"total\":1},\"channel\":\"ORIGINAL\",\"create_time\":\"2021-12-14T17:11:15+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"REFUND_20211214171113826\",\"out_trade_no\":\"ORDER_20211214130546776\",\"promotion_detail\":[],\"refund_id\":\"50301400132021121415370407561\",\"status\":\"SUCCESS\",\"success_time\":\"2021-12-14T17:11:22+08:00\",\"transaction_id\":\"4200001335202112149645887495\",\"user_received_account\":\"支付用户零钱\"}',NULL,'2021-12-14 17:11:13','2021-12-14 17:19:30');
insert  into `t_refund_info`(`id`,`order_no`,`refund_no`,`refund_id`,`total_fee`,`refund`,`reason`,`refund_status`,`content_return`,`content_notify`,`create_time`,`update_time`) values (5,'ORDER_20211214184634520','REFUND_20211214184815124','50301000072021121415364123490',1,1,'不喜欢','SUCCESS','{\"amount\":{\"currency\":\"CNY\",\"discount_refund\":0,\"from\":[],\"payer_refund\":1,\"payer_total\":1,\"refund\":1,\"settlement_refund\":1,\"settlement_total\":1,\"total\":1},\"channel\":\"ORIGINAL\",\"create_time\":\"2021-12-14T18:48:18+08:00\",\"funds_account\":\"AVAILABLE\",\"out_refund_no\":\"REFUND_20211214184815124\",\"out_trade_no\":\"ORDER_20211214184634520\",\"promotion_detail\":[],\"refund_id\":\"50301000072021121415364123490\",\"status\":\"PROCESSING\",\"transaction_id\":\"4200001184202112147549577350\",\"user_received_account\":\"支付用户零钱\"}','{\"mchid\":\"1558950191\",\"out_trade_no\":\"ORDER_20211214184634520\",\"transaction_id\":\"4200001184202112147549577350\",\"out_refund_no\":\"REFUND_20211214184815124\",\"refund_id\":\"50301000072021121415364123490\",\"refund_status\":\"SUCCESS\",\"success_time\":\"2021-12-14T18:48:25+08:00\",\"amount\":{\"total\":1,\"refund\":1,\"payer_total\":1,\"payer_refund\":1},\"user_received_account\":\"支付用户零钱\"}','2021-12-14 18:48:15','2021-12-14 18:48:31');
insert  into `t_refund_info`(`id`,`order_no`,`refund_no`,`refund_id`,`total_fee`,`refund`,`reason`,`refund_status`,`content_return`,`content_notify`,`create_time`,`update_time`) values (21,'ORDER_20220410195142968','REFUND_20220411080928052',NULL,1,1,'不喜欢','REFUND_ERROR','{\"alipay_trade_refund_response\":{\"code\":\"40004\",\"msg\":\"Business Failed\",\"sub_code\":\"ACQ.REFUND_AMT_NOT_EQUAL_TOTAL\",\"sub_msg\":\"撤销或退款金额与订单金额不一致\",\"out_trade_no\":\"ORDER_20220410195142968\",\"refund_fee\":\"0.00\",\"send_back_fee\":\"0.00\"},\"sign\":\"XUyvAa2UrT1lubjPmTeP6eVGxKJVUoJlF8i8qQseso0/cS31VlAfDh5rEoZ/4AmmhYiieCTGpl4OelUDJ3Nglc9vVvMZfHyMSa0useFPKzIeryHzzxemgANh9F/szeLoXhVB+6P7slaKBqjwBVxCN0tXrk0bnakp5zRbHyY5CAH8aWCWdcPgHfJ6b4JosCQcBDyO2aqgoyh7L5bvF161K/g0qb9+y7yXv+Z4PoQTZIF4m4VVe8agyy8NVZBd3L8hX9fg8+3RDqXP7n0TEFp8AbGIUOXCR9OvvXR5/5MFV1RMZ9TsG9Zk7yugnQl1Yht4SOuTSt5UndUZS+qmiW43Jg==\"}',NULL,'2022-04-11 08:09:28','2022-04-11 08:09:29');
insert  into `t_refund_info`(`id`,`order_no`,`refund_no`,`refund_id`,`total_fee`,`refund`,`reason`,`refund_status`,`content_return`,`content_notify`,`create_time`,`update_time`) values (22,'ORDER_20220411081128077','REFUND_20220411081239320',NULL,1,1,'买错了','REFUND_SUCCESS','{\"alipay_trade_refund_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"buyer_logon_id\":\"fmv***@sandbox.com\",\"buyer_user_id\":\"2088622958076685\",\"fund_change\":\"Y\",\"gmt_refund_pay\":\"2022-04-11 08:12:39\",\"out_trade_no\":\"ORDER_20220411081128077\",\"refund_fee\":\"0.01\",\"send_back_fee\":\"0.00\",\"trade_no\":\"2022041122001476680502103259\"},\"sign\":\"GQ26z+yhYu29IIGHAB9MVKIYBdnedZock79xs1Id5i+yzabK5bA0HM7zU9VwAC/IntQ/4ksNSNYPapi77Eor8B6RxD5JD/xOOipDmWB9aFExEqBoxgLL2ar9sD4IZjHFBtI/02qL8SIiXZxUT9/wDht29FTMHPCykC9K8eM8pvyXTqXA3P+Twj3O5JcH1q302hUAAM85YI4EU/58kdHMziloj+ujqpjMj3cq4q+m4C1/b7SbAlqHZcQY0RMmOa3ZjqzPxLDxeXn78xYPvN1A9RAOSqKHBNCqCKdGaIC8cpxh7u+06IqLl6Sq7yijK8k3xML12B+6sUw1AO1UL1iWLA==\"}',NULL,'2022-04-11 08:12:39','2022-04-11 08:12:40');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
