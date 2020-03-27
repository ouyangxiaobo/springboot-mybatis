/*
Navicat MySQL Data Transfer

Source Server         : app
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : member

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-03-27 13:50:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `gender` int(4) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-27', '13023658596', '1', null);
INSERT INTO `member` VALUES ('2', 'root', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-27', '13645786325', '1', null);
INSERT INTO `member` VALUES ('3', 'xiaoming', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-27', '13245892365', '1', null);
INSERT INTO `member` VALUES ('4', 'xaiozhi', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-27', '13245892968', '1', null);
INSERT INTO `member` VALUES ('5', 'pdd', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-27', '13245891234', '1', null);
INSERT INTO `member` VALUES ('6', 'milk', 'e10adc3949ba59abbe56e057f20f883e', '2020-03-27', '13023657412', '1', null);
