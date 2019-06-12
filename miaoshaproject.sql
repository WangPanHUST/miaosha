/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : miaoshaproject

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-06-12 22:07:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` double(10,0) NOT NULL DEFAULT '0',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT '0',
  `img_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '小米手机', '3999', '为发烧而生', '8', 'http://img3.imgtn.bdimg.com/it/u=1840119314,3815959133&fm=26&gp=0.jpg');
INSERT INTO `item` VALUES ('2', '苹果手机', '5000', '苹果', '6', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=880754541,2489463290&fm=26&gp=0.jpg');
INSERT INTO `item` VALUES ('3', '华为手机', '4000', '华为牛逼', '8', 'http://img5.imgtn.bdimg.com/it/u=1269994246,3262847429&fm=15&gp=0.jpg');
INSERT INTO `item` VALUES ('5', '荣耀手机', '3500', '定格奇幻之美', '6', 'http://img5.imgtn.bdimg.com/it/u=1269994246,3262847429&fm=15&gp=0.jpg');
INSERT INTO `item` VALUES ('6', '一加手机', '5000', '不将就', '3', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3643120908,3756870182&fm=11&gp=0.jpg');
INSERT INTO `item` VALUES ('7', 'oppo手机', '4000', '想象，更近一点', '2', 'https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=b7978a1caeec08fa390015a769ef3d4d/b17eca8065380cd7d1b78c4eaf44ad34588281e3.jpg');
INSERT INTO `item` VALUES ('8', 'vivo手机', '3000', '生而强悍', '6', 'https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=2053255e34f33a87816d061af65d1018/8d5494eef01f3a29d89f73a09725bc315c607c0d.jpg');
INSERT INTO `item` VALUES ('9', '三星手机', '6000', 'boom', '0', 'http://img3.imgtn.bdimg.com/it/u=825799650,2555648205&fm=26&gp=0.jpg');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `item_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES ('1', '354533', '1');
INSERT INTO `item_stock` VALUES ('2', '199999', '2');
INSERT INTO `item_stock` VALUES ('3', '25550', '3');
INSERT INTO `item_stock` VALUES ('4', '22219', '5');
INSERT INTO `item_stock` VALUES ('5', '99', '6');
INSERT INTO `item_stock` VALUES ('6', '1000', '7');
INSERT INTO `item_stock` VALUES ('7', '199999', '8');
INSERT INTO `item_stock` VALUES ('8', '500', '9');

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `item_price` double(10,2) NOT NULL DEFAULT '0.00',
  `amount` int(11) NOT NULL DEFAULT '0',
  `order_price` double(10,2) NOT NULL DEFAULT '0.00',
  `promo_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('201906100000000', '20', '3', '0.00', '0', '0.00', '0');
INSERT INTO `order_info` VALUES ('2019061000000100', '20', '5', '3500.00', '0', '3500.00', '0');
INSERT INTO `order_info` VALUES ('2019061000000200', '20', '2', '5000.00', '1', '5000.00', '0');
INSERT INTO `order_info` VALUES ('2019061000000300', '20', '6', '5000.00', '0', '5000.00', '0');
INSERT INTO `order_info` VALUES ('2019061000000400', '20', '3', '4000.00', '1', '4000.00', '0');
INSERT INTO `order_info` VALUES ('2019061000000500', '20', '8', '3000.00', '1', '3000.00', '0');
INSERT INTO `order_info` VALUES ('2019061100000600', '20', '5', '1000.00', '1', '3500.00', '0');
INSERT INTO `order_info` VALUES ('2019061100000700', '20', '5', '1000.00', '1', '1000.00', '1');
INSERT INTO `order_info` VALUES ('2019061100000800', '20', '3', '1500.00', '1', '1500.00', '2');
INSERT INTO `order_info` VALUES ('2019061100000900', '20', '3', '1500.00', '1', '1500.00', '2');
INSERT INTO `order_info` VALUES ('2019061100001000', '21', '1', '3999.00', '1', '3999.00', '0');
INSERT INTO `order_info` VALUES ('2019061200001100', '21', '3', '1500.00', '1', '1500.00', '2');

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_date` datetime NOT NULL DEFAULT '2018-12-31 23:59:59' COMMENT '默认为当前时间',
  `item_id` int(11) NOT NULL DEFAULT '0',
  `promo_item_price` double(10,2) NOT NULL DEFAULT '0.00',
  `end_date` datetime NOT NULL DEFAULT '2099-12-31 23:59:59',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES ('1', 'honor秒杀', '2019-06-10 17:22:11', '5', '1000.00', '2099-12-31 23:59:59');
INSERT INTO `promo` VALUES ('2', '华为秒杀', '2019-06-11 19:37:59', '3', '1500.00', '2099-12-31 23:59:59');

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT '0',
  `step` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', '12', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '0',
  `age` int(11) NOT NULL DEFAULT '0',
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `third_party_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'wp', '1', '23', '13297919066', 'phone', '0');
INSERT INTO `user_info` VALUES ('14', 'wo', '1', '23', '11397919060', 'byPhone', '');
INSERT INTO `user_info` VALUES ('21', '蔡徐坤', '2', '30', '12345678910', 'byPhone', '');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrypt_password` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', 'asgfgaga', '1');
INSERT INTO `user_password` VALUES ('2', 'gY3eG8oRiYvdI10b0CBKig==', '14');
INSERT INTO `user_password` VALUES ('6', '+uCyfEUccohnpWfowbtOUw==', '21');
